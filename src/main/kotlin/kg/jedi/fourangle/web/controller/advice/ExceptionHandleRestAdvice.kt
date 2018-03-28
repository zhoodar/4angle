package kg.jedi.fourangle.web.controller.advice

import kg.jedi.fourangle.common.ObjectNotFoundException
import kg.jedi.fourangle.common.logger
import kg.jedi.fourangle.web.controller.dto.ErrorResponse
import kg.jedi.fourangle.web.security.exceptions.AuthMethodNotSupportedException
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandleRestAdvice {

    companion object {
        val TAG = ExceptionHandleRestAdvice::class.simpleName
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun commonException(ex: Exception): ErrorResponse {
        logger.error(TAG, ex)
        return ErrorResponse(HttpStatus.BAD_REQUEST, "UNHANDLED EXCEPTION LOGGED DURING REQUEST")
    }

    @ExceptionHandler(ObjectNotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun objectNotFoundException(ex: ObjectNotFoundException): ErrorResponse {
        logger.error(TAG, ex)
        return ErrorResponse(HttpStatus.NOT_FOUND, ex.localizedMessage)
    }

    @ExceptionHandler(BadCredentialsException::class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    fun badCredentialsException(ex: BadCredentialsException): ErrorResponse {
        logger.error(TAG, ex)
        return ErrorResponse(HttpStatus.UNAUTHORIZED,  ex.localizedMessage)
    }

    @ExceptionHandler(UsernameNotFoundException::class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    fun usernameNotFoundException(ex: UsernameNotFoundException): ErrorResponse {
        logger.error(TAG, ex)
        return ErrorResponse(HttpStatus.UNAUTHORIZED,  ex.localizedMessage)
    }
    @ExceptionHandler(AuthMethodNotSupportedException::class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    fun authMethodNotSupportedException(ex: AuthMethodNotSupportedException): ErrorResponse {
        logger.error(TAG, ex)
        return ErrorResponse(HttpStatus.UNAUTHORIZED,  ex.localizedMessage)
    }

}