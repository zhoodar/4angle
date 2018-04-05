package kg.jedi.fourangle.buisness.service

import kg.jedi.fourangle.buisness.service.crud.CrudService
import kg.jedi.fourangle.buisness.service.crud.ReferenceReadService
import kg.jedi.fourangle.domain.entity.Account
import kg.jedi.fourangle.domain.entity.Advert
import kg.jedi.fourangle.domain.entity.Image
import kg.jedi.fourangle.domain.entity.Role
import java.util.*


interface AccountCrudService : CrudService<Account> {
    fun getByEmailOrPhone(emailOrPhone: String): Optional<Account>
}

interface AdvertCrudService : CrudService<Advert>

interface ImageCrudService : CrudService<Image>

interface RoleReferenceReadService : ReferenceReadService<Role>