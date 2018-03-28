package kg.jedi.fourangle.web.controller.dto

import org.springframework.http.HttpStatus

data class RestResponse<E>(
        var response: E
)

data class ErrorResponse(
        var httpStatus: HttpStatus,
        var message: String
)