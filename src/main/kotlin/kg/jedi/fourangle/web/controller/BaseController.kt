package kg.jedi.fourangle.web.controller

import kg.jedi.fourangle.buisness.service.crud.ReferenceReadService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

open class ReadController<E>(private val readReferenceService: ReferenceReadService<E>) {

    @GetMapping("/{id:[\\d]+}")
    fun get(@PathVariable id: Long): E = this.readReferenceService.get(id)
}