package kg.jedi.fourangle.web.security.auth.ajax

import com.fasterxml.jackson.databind.ObjectMapper
import kg.jedi.fourangle.web.security.ErrorCode
import kg.jedi.fourangle.web.security.ErrorResponse
import kg.jedi.fourangle.web.security.exceptions.AuthMethodNotSupportedException
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.stereotype.Component
import java.io.IOException
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
@Qualifier("ajax")
class AjaxAwareAuthenticationFailureHandler(val mapper: ObjectMapper) : AuthenticationFailureHandler {

    @Throws(IOException::class, ServletException::class)
    override fun onAuthenticationFailure(request: HttpServletRequest, response: HttpServletResponse,
                                         e: AuthenticationException) {

        response.status = HttpStatus.UNAUTHORIZED.value()
        response.contentType = MediaType.APPLICATION_JSON_VALUE

        if (e is BadCredentialsException) {
            mapper.writeValue(response.writer, ErrorResponse.of(e.message!!, ErrorCode.AUTHENTICATION, HttpStatus.UNAUTHORIZED))
        } else if (e is AuthMethodNotSupportedException) {
            mapper.writeValue(response.writer, ErrorResponse.of(e.message!!, ErrorCode.AUTHENTICATION, HttpStatus.UNAUTHORIZED))
        }

        mapper.writeValue(response.writer, ErrorResponse.of("Authentication failed", ErrorCode.AUTHENTICATION, HttpStatus.UNAUTHORIZED))
    }
}
