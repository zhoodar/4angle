package kg.jedi.fourangle.web.controller

import kg.jedi.fourangle.buisness.service.AccountCrudService
import kg.jedi.fourangle.common.Endpoints
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = [Endpoints.ACCOUNT_SECURE_V1], produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
class AccountSecureController(private val accountService: AccountCrudService) {

    @PutMapping("/{id:[\\d]+}")
    fun update(@PathVariable id: Long) {
    }

}