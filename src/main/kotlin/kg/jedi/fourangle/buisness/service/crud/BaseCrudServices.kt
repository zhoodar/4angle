package kg.jedi.fourangle.buisness.service.crud

import kg.jedi.fourangle.common.ObjectNotFoundException
import kg.jedi.fourangle.domain.entity.BaseEntity
import org.springframework.data.repository.CrudRepository
import java.util.*


interface ReferenceReadService<E> {
    fun get(id: Long): E
    fun findAll(ids: List<Long>): MutableIterable<E>
}

interface CrudService<E : BaseEntity> : ReferenceReadService<E> {
    fun saveOrUpdate(entity: E): E
    fun saveOrUpdate(entities: List<E>): List<E>
    fun remove(id: Long)
}

open class DefaultReferenceReadService<E : BaseEntity>(
        private val crudRepository: CrudRepository<E, Long>
) : ReferenceReadService<E> {

    override fun findAll(ids: List<Long>): MutableIterable<E> {
        return crudRepository.findAllById(ids)
    }

    @Throws(ObjectNotFoundException::class)
    override fun get(id: Long): E {
        return crudRepository.findById(id).orElseThrow { throw ObjectNotFoundException("Record not found for id: $id") }
    }
}

open class DefaultCrudService<E : BaseEntity>(
        private val crudRepository: CrudRepository<E, Long>
) : DefaultReferenceReadService<E>(crudRepository), CrudService<E> {

    override fun saveOrUpdate(entity: E): E {
        return internalSaveOrUpdate(entity)
    }

    override fun saveOrUpdate(entities: List<E>): List<E> {
        val result = LinkedList<E>()
        for (entity in entities) {
            val saveOrUpdate = internalSaveOrUpdate(entity)
            result.add(saveOrUpdate)
        }
        return result
    }

    override fun remove(id: Long) {
        crudRepository.deleteById(id)
    }

    private fun internalSaveOrUpdate(entity: E): E {
        if (null == entity.id) {
            val savedEntity = crudRepository.save(entity)
            return savedEntity
        }
        //patch
        return crudRepository.save(entity)
    }
}