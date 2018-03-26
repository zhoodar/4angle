package kg.jedi.fourangle.web.controller

import kg.jedi.fourangle.common.ApiDoc
import kg.jedi.fourangle.common.Endpoints
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping(value = [(Endpoints.ACCOUNT_V1)], produces = [(ApiDoc.JSON_UTF8)])
class AccountController {
}