package jw.org.asignaciones.service

import jakarta.persistence.EntityNotFoundException
import jw.org.asignaciones.model.IndexedEntity
import org.springframework.data.jpa.repository.JpaRepository
import kotlin.jvm.optionals.getOrElse

abstract class AbstractCRUDService<E: IndexedEntity<K>, K: Any> (
    val repo: JpaRepository<E, K>
){
    fun create(entity: E) = repo.save(entity)

    fun read(id: K): E {
        val value = repo.findById(id)
        return value.getOrElse {
            throw EntityNotFoundException("Not Found")
        }
    }

    fun update(entity: E) {
        if (entity.index() == null)
            throw IllegalArgumentException("id must not be null")
        if (!repo.existsById(entity.index()!!))
            throw EntityNotFoundException("entity not found")
        repo.save(entity)
    }

    fun delete(id: K) {
        if (!repo.existsById(id))
            throw EntityNotFoundException("entity not found")
        repo.deleteById(id)
    }
}