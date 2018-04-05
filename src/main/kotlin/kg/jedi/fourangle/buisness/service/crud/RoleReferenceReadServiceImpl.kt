package kg.jedi.fourangle.buisness.service.crud

import kg.jedi.fourangle.buisness.repository.RoleRepository
import kg.jedi.fourangle.buisness.service.RoleReferenceReadService
import kg.jedi.fourangle.domain.entity.Role
import org.springframework.stereotype.Service

@Service
class RoleReferenceReadServiceImpl(roleRepository: RoleRepository)
    : DefaultReferenceReadService<Role>(roleRepository), RoleReferenceReadService