package kg.jedi.fourangle.buisness.service.crud

import kg.jedi.fourangle.buisness.repository.AccountRepository
import kg.jedi.fourangle.buisness.service.AccountCrudService
import kg.jedi.fourangle.domain.entity.Account
import org.springframework.stereotype.Service
import java.util.*

@Service
class AccountCrudServiceImpl(private val accountRepository: AccountRepository)
    : DefaultCrudService<Account>(accountRepository), AccountCrudService {


    override fun getByEmailOrPhone(emailOrPhone: String): Optional<Account> {
        return accountRepository.findOneByEmailOrPhoneNumber(emailOrPhone, emailOrPhone)
    }
}