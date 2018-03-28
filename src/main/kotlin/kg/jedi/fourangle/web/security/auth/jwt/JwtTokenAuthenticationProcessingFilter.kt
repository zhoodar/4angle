package kg.jedi.fourangle.web.security.auth.jwt

import kg.jedi.fourangle.web.security.auth.JwtAuthenticationToken
import kg.jedi.fourangle.web.security.auth.jwt.extractor.TokenExtractor
import kg.jedi.fourangle.web.security.model.RawAccessJwtToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.security.web.util.matcher.RequestMatcher
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JwtTokenAuthenticationProcessingFilter(internal val failureHandler: AuthenticationFailureHandler,
                                             internal val tokenExtractor: TokenExtractor,
                                             matcher: RequestMatcher) : AbstractAuthenticationProcessingFilter(matcher) {

    @Throws(AuthenticationException::class, IOException::class, ServletException::class)
    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {
        val tokenPayload = request.getHeader(JWT_TOKEN_HEADER_PARAM)
        val token = RawAccessJwtToken(tokenExtractor.extract(tokenPayload))

        return authenticationManager.authenticate(JwtAuthenticationToken(token))
    }

    @Throws(IOException::class, ServletException::class)
    override fun successfulAuthentication(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain?,
                                          authResult: Authentication) {
        val context = SecurityContextHolder.createEmptyContext()
        context.authentication = authResult
        SecurityContextHolder.setContext(context)
        chain!!.doFilter(request, response)
    }

    @Throws(IOException::class, ServletException::class)
    override fun unsuccessfulAuthentication(request: HttpServletRequest, response: HttpServletResponse,
                                            failed: AuthenticationException) {
        SecurityContextHolder.clearContext()
        failureHandler.onAuthenticationFailure(request, response, failed)
    }

    companion object {
        private val JWT_TOKEN_HEADER_PARAM = "X-Authorization"
    }
}
