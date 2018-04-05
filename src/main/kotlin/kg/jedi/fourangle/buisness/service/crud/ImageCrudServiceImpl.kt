package kg.jedi.fourangle.buisness.service.crud

import kg.jedi.fourangle.buisness.repository.ImageRepository
import kg.jedi.fourangle.buisness.service.ImageCrudService
import kg.jedi.fourangle.domain.entity.Image
import org.springframework.stereotype.Service

@Service
class ImageCrudServiceImpl(imageRepository: ImageRepository)
    : DefaultCrudService<Image>(imageRepository), ImageCrudService