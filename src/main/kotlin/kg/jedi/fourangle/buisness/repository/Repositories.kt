package kg.jedi.fourangle.buisness.repository

import kg.jedi.fourangle.domain.entity.Account
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface AccountRepository : JpaRepository<Account, Long> {
    fun findOneByEmailOrPhoneNumber(emailOrPhone: String): Optional<Account>
}