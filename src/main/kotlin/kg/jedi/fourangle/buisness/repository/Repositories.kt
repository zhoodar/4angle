package kg.jedi.fourangle.buisness.repository

import kg.jedi.fourangle.domain.entity.Account
import org.springframework.data.jpa.repository.JpaRepository

interface AccountRepository : JpaRepository<Account, Long>