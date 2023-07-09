package jw.org.asignaciones.service

import jakarta.persistence.EntityNotFoundException
import org.springframework.data.jpa.repository.JpaRepository
import kotlin.jvm.optionals.getOrElse

abstract class AbstractCRUDService<E : Any, K : Any> (
    val repo: JpaRepository<E, K>
){
    fun create(entity: E) = repo.save(entity)

    fun read(id: K): E {
        val value = repo.findById(id)
        return value.getOrElse {
            throw EntityNotFoundException("Not Found")
        }
    }

    fun update(entity: E, key: K) {
        if (!repo.existsById(key))
            throw IllegalArgumentException("entity not found")
        repo.save(entity)
    }

    fun delete(id: K) = repo.deleteById(id)
}