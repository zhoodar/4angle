package kg.jedi.fourangle.buisness.repository

import kg.jedi.fourangle.domain.entity.Account
import kg.jedi.fourangle.domain.entity.Advert
import kg.jedi.fourangle.domain.entity.Image
import kg.jedi.fourangle.domain.entity.Role
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface AccountRepository : JpaRepository<Account, Long> {
    fun findOneByEmailOrPhoneNumber(emailOrPhone: String): Optional<Account>
}

interface AdvertRepository : JpaRepository<Advert, Long>

interface ImageRepository : JpaRepository<Image, Long>

interface RoleRepository : JpaRepository<Role, Long>

