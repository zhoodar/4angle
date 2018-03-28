package kg.jedi.fourangle.web.security.auth.ajax

import com.fasterxml.jackson.databind.ObjectMapper
import kg.jedi.fourangle.web.controller.dto.LoginDto
import kg.jedi.fourangle.web.security.exceptions.AuthMethodNotSupportedException
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


class AjaxLoginProcessingFilter(defaultProcessUrl: String,
                                private val authSuccessHandler: AuthenticationSuccessHandler,
                                private val authFailureHandler: AuthenticationFailureHandler,
                                val objectMapper: ObjectMapper) : AbstractAuthenticationProcessingFilter(defaultProcessUrl) {

    @Throws(AuthenticationException::class, IOException::class, ServletException::class)
    override fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {
        if (HttpMethod.POST.name != request.method || isAjaxLoginRequest(request)) {
            throw AuthMethodNotSupportedException("Authentication method not supported")
        }
        val loginRequest = objectMapper.readValue(request.reader, LoginDto::class.java)

        val token = UsernamePasswordAuthenticationToken(loginRequest.username, loginRequest.password)
        return this.authenticationManager.authenticate(token)
    }

    @Throws(IOException::class, ServletException::class)
    override fun successfulAuthentication(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain?, authResult: Authentication) {
        authSuccessHandler.onAuthenticationSuccess(request, response, authResult)
    }

    @Throws(IOException::class, ServletException::class)
    override fun unsuccessfulAuthentication(request: HttpServletRequest, response: HttpServletResponse, failed: AuthenticationException) {
        SecurityContextHolder.clearContext()
        authFailureHandler.onAuthenticationFailure(request, response, failed)
    }

    private fun isAjaxLoginRequest(request: HttpServletRequest): Boolean {
        return XML_HTTP_REQUEST == request.getHeader(X_REQUESTED_WITH)
    }

    companion object {
        private val XML_HTTP_REQUEST = "XMLHttpRequest"
        private val X_REQUESTED_WITH = "X-Requested-With"
    }
}