package kg.jedi.fourangle.buisness.service.crud

import kg.jedi.fourangle.buisness.repository.AdvertRepository
import kg.jedi.fourangle.buisness.service.AdvertCrudService
import kg.jedi.fourangle.domain.entity.Advert
import org.springframework.stereotype.Service

@Service
class AdvertCrudServiceImpl(advertRepository: AdvertRepository)
    : DefaultCrudService<Advert>(advertRepository), AdvertCrudService