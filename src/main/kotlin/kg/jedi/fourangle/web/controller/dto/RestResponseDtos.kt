package kg.jedi.fourangle.web.controller.dto

import org.springframework.http.HttpStatus

data class RestResponse<E>(
        var response: E
)

data class ErrorResponse(
        val httpStatus: HttpStatus,
        val message: String
)

data class PageableRestResponse<E>(
        val meta: MetaData,
        val response: E
)

data class MetaData(val page: Int, val limit: Int, val total: Long)