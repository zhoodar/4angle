package kg.jedi.fourangle.web.controller

import kg.jedi.fourangle.common.Endpoints
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = [Endpoints.ACCOUNT_V1], produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
class AccountController {
}