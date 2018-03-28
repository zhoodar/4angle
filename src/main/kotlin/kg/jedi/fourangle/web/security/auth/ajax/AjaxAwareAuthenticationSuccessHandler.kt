package kg.jedi.fourangle.web.security.auth.ajax

import com.fasterxml.jackson.databind.ObjectMapper
import kg.jedi.fourangle.web.controller.dto.RestResponse
import kg.jedi.fourangle.web.controller.dto.TokenDto
import kg.jedi.fourangle.web.security.model.UserContext
import kg.jedi.fourangle.web.security.token.IJwtTokenFactory
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.core.Authentication
import org.springframework.security.web.WebAttributes
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.stereotype.Component
import java.io.IOException
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AjaxAwareAuthenticationSuccessHandler(private val mapper: ObjectMapper,
                                            val tokenFactory: IJwtTokenFactory) : AuthenticationSuccessHandler {

    @Throws(IOException::class, ServletException::class)
    override fun onAuthenticationSuccess(request: HttpServletRequest, response: HttpServletResponse,
                                         authentication: Authentication) {
        val userContext = authentication.principal as UserContext

        val accessToken = tokenFactory.createAccessJwtToken(userContext)

        val tokenDto = TokenDto(accessToken.token)
        val restResponse = RestResponse(tokenDto)

        response.status = HttpStatus.OK.value()
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        mapper.writeValue(response.writer, restResponse)

        clearAuthenticationAttributes(request)
    }

    protected fun clearAuthenticationAttributes(request: HttpServletRequest) {
        val session = request.getSession(false) ?: return
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION)
    }
}
