package kg.jedi.fourangle.web.controller

import kg.jedi.fourangle.buisness.service.AccountCrudService
import kg.jedi.fourangle.common.Endpoints
import kg.jedi.fourangle.domain.entity.Account
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = [Endpoints.ACCOUNT_SECURE_V1], produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
class AccountSecureController(private val accountService: AccountCrudService
) : ReadController<Account>(accountService) {

    @PutMapping("/{id:[\\d]+}")
    fun update() {

        accountService
    }

}