package kg.jedi.fourangle.buisness.service

import kg.jedi.fourangle.buisness.service.crud.CrudService
import kg.jedi.fourangle.domain.entity.Account
import java.util.*


interface AccountCrudService : CrudService<Account> {
    fun getByEmailOrPhone(emailOrPhone: String): Optional<Account>
}