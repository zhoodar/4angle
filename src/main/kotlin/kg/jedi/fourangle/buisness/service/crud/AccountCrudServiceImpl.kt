package kg.jedi.fourangle.buisness.service.crud

import kg.jedi.fourangle.buisness.repository.AccountRepository
import kg.jedi.fourangle.buisness.service.AccountCrudService
import kg.jedi.fourangle.domain.entity.Account
import org.springframework.stereotype.Service

@Service
class AccountCrudServiceImpl(accountRepository: AccountRepository)
    : DefaultCrudService<Account>(accountRepository), AccountCrudService