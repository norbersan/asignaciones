package jw.org.asignaciones.controller

import jw.org.asignaciones.model.Dummy
import jw.org.asignaciones.service.DummyService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/", consumes = ["application/json"] )
class DummyController (
    private val dummyService: DummyService
){

    @PutMapping("/dummy", produces = ["application/json"])
    fun create(@RequestBody dummy: Dummy): ResponseEntity<out Any> {
        var result: Dummy? = null
        runCatching {
            result = dummyService.create(dummy)
        }.onSuccess {
            return ResponseEntity.ok(result)
        }.onFailure {
            return ResponseEntity.internalServerError().body(it)
        }
        return ResponseEntity.internalServerError().build()
    }

    @GetMapping(value = ["/dummy/{id}"], produces = ["application/json"])
    fun read(@PathVariable id: Int): ResponseEntity<out Any> {
        var result: Dummy? = null
        runCatching {
            result = dummyService.read(id)
        }.onSuccess {
            return ResponseEntity.ok(result)
        }.onFailure {
            return ResponseEntity.internalServerError().body(it)
        }
        return ResponseEntity.internalServerError().build()
    }


    @PostMapping(value = ["/dummy"], produces = ["application/json"])
    fun update(@RequestBody dummy: Dummy): ResponseEntity<out Any> {
        var result: Dummy? = null
        runCatching {
            dummyService.update(dummy)
        }.onSuccess {
            return ResponseEntity.ok(dummy)
        }.onFailure {
            return ResponseEntity.internalServerError().body(it)
        }
        return ResponseEntity.internalServerError().build()
    }

    @DeleteMapping(value = ["/dummy/{id}"], produces = ["application/json"])
    fun delete(@PathVariable id: Int): ResponseEntity<out Any> {
        var result: Dummy? = null
        runCatching {
            dummyService.delete(id)
        }.onSuccess {
            return ResponseEntity.ok(result)
        }.onFailure {
            return ResponseEntity.internalServerError().body(it)
        }
        return ResponseEntity.internalServerError().build()
    }


}