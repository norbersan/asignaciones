package jw.org.asignaciones.controller

import jw.org.asignaciones.model.IndexedEntity
import jw.org.asignaciones.service.AbstractExtendedService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity

abstract class AbstractExtendedController<E: IndexedEntity<K>, K: Any>(
    service: AbstractExtendedService<E, K>
): AbstractCRUDController<E,K>(service) {
    open fun findAll(): ResponseEntity<out Any> {
        var result: MutableList<E>? = null
        runCatching {
            result = (service as AbstractExtendedService<E, K>).findAll()
        }.onSuccess {
            return ResponseEntity.ok(result ?: mutableListOf())
        }.onFailure {
            return ResponseEntity.internalServerError().body(it)
        }
        return ResponseEntity.internalServerError().build()
    }

    open fun findAll(pageable: Pageable): ResponseEntity<out Any>  {
        var result: Page<E>? = null
        runCatching {
            result = (service as AbstractExtendedService<E, K>).findAll(pageable)
        }.onSuccess {
            return ResponseEntity.ok(result?.content ?: mutableListOf())
        }.onFailure {
            return ResponseEntity.internalServerError().body(it)
        }
        return ResponseEntity.internalServerError().build()
    }
}