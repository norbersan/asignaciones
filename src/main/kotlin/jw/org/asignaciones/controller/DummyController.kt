package jw.org.asignaciones.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.Parameters
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.media.Schema
import jw.org.asignaciones.model.Dummy
import jw.org.asignaciones.service.DummyService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/" )
class DummyController (
    private val dummyService: DummyService
){
    @Operation(summary = "Creates a Dummy in database (<em>C</em>)RUD")
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

    @Operation(summary = "Reads a Dummy from database  (C<em>R</em>)UD")
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


    @Operation(summary = "Updates a Dummy in database  (CR<em>U</em>)D")
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

    @Operation(summary = "Deletes a Dummy from database  (CRU<em>D</em>)")
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