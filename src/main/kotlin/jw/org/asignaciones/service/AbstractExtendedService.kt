package jw.org.asignaciones.service

import jw.org.asignaciones.model.IndexedEntity
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

abstract class AbstractExtendedService<E: IndexedEntity<K>, K: Any>(
    repo: JpaRepository<E, K>
): AbstractCRUDService<E, K>(repo){
    fun findAll() = repo.findAll()

    fun findAll(pageable: Pageable) = repo.findAll(pageable)

}