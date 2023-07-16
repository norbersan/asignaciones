package jw.org.asignaciones.controller

import jakarta.persistence.EntityNotFoundException
import jw.org.asignaciones.model.IndexedEntity
import jw.org.asignaciones.service.AbstractCRUDService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

abstract class AbstractCRUDController<E: IndexedEntity<K>, K: Any>(
    protected val service: AbstractCRUDService<E,K>
){
    open fun create(e: E): ResponseEntity<out Any> {
        var result: E? = null
        if (e.index() != null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Field id must be null. To update a ${e.javaClass.simpleName} use the update endpoint")
        runCatching {
            result = service.create(e)
        }.onSuccess {
            return ResponseEntity.ok(result)
        }.onFailure {
            return ResponseEntity.internalServerError().body(it)
        }
        return ResponseEntity.internalServerError().build()
    }

    open fun read(id: K): ResponseEntity<out Any> {
        var result: E? = null
        runCatching {
            result = service.read(id)
        }.onSuccess {
            return ResponseEntity.ok(result)
        }.onFailure {
            return if (it is EntityNotFoundException){
                ResponseEntity.notFound().build()
            } else {
                ResponseEntity.internalServerError().body(it)
            }
        }
        return ResponseEntity.internalServerError().build()
    }

    open fun update(e: E): ResponseEntity<out Any> {
        var result: E? = null
        runCatching {
            //service.update(e)
        }.onSuccess {
            return ResponseEntity.ok(e)
        }.onFailure {
            return if (it is EntityNotFoundException){
                ResponseEntity.notFound().build()
            } else if (it is IllegalArgumentException){
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Field id must be null")
            } else {
                ResponseEntity.internalServerError().body(it)
            }
        }
        return ResponseEntity.internalServerError().build()
    }

    open fun delete(id: K): ResponseEntity<out Any> {
        runCatching {
            service.delete(id)
        }.onSuccess {
            return ResponseEntity.ok().build()
        }.onFailure {
            return if (it is EntityNotFoundException){
                ResponseEntity.notFound().build()
            } else {
                ResponseEntity.internalServerError().body(it)
            }
        }
        return ResponseEntity.internalServerError().build()
    }
}
