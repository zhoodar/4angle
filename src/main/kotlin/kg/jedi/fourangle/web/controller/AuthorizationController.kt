package kg.jedi.fourangle.web.controller

import kg.jedi.fourangle.common.Endpoints
import kg.jedi.fourangle.web.controller.dto.AccountDto
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping(value = [Endpoints.AUTH_V1], produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
class AuthorizationController {

    @PostMapping(value = ["/signUp"])
    fun signUp(@RequestBody @Valid accountDto: AccountDto) {

    }
}