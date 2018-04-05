package kg.jedi.fourangle.web.controller

import kg.jedi.fourangle.buisness.service.AdvertCrudService
import kg.jedi.fourangle.common.Endpoints
import kg.jedi.fourangle.web.controller.dto.AdvertDto
import kg.jedi.fourangle.web.controller.dto.RestResponse
import kg.jedi.fourangle.web.controller.dto.toAdvertDto
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(value = [Endpoints.ADVERT_V1], produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
class AdvertOpenController(private val advertService: AdvertCrudService) {

    @GetMapping("/{id:[\\d]+}")
    fun get(@PathVariable id: Long): RestResponse<AdvertDto> {
        val model = advertService.get(id)
        val advertDto = model.toAdvertDto()
        return RestResponse(advertDto)
    }
}