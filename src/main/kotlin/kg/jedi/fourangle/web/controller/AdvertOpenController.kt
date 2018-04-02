package kg.jedi.fourangle.web.controller

import kg.jedi.fourangle.buisness.service.AdvertCrudService
import kg.jedi.fourangle.common.Endpoints
import kg.jedi.fourangle.domain.entity.Advert
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = [Endpoints.ADVERT_V1], produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
class AdvertOpenController(advertService: AdvertCrudService) : ReadController<Advert>(advertService) {

    @GetMapping("/getAll")
    fun all() {

    }
}