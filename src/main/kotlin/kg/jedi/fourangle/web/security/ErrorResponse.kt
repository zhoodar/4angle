package kg.jedi.fourangle.web.security

import com.fasterxml.jackson.annotation.JsonValue
import org.springframework.http.HttpStatus
import java.util.*

class ErrorResponse (val message: String, val errorCode: ErrorCode, val status: HttpStatus) {
    val timestamp: Date = Date()

    companion object {
        fun of(message: String, errorCode: ErrorCode, status: HttpStatus): ErrorResponse {
            return ErrorResponse(message, errorCode, status)
        }
    }
}

enum class ErrorCode (@get:JsonValue val errorCode: Int) {
    AUTHENTICATION(401)
}
