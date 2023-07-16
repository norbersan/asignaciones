package jw.org.asignaciones.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.Parameters
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import jakarta.annotation.PostConstruct
import jakarta.persistence.EntityNotFoundException
import jw.org.asignaciones.model.Dummy
import jw.org.asignaciones.repository.AssigneeRepository
import jw.org.asignaciones.repository.AssignmentRepository
import jw.org.asignaciones.service.AssigneeService
import jw.org.asignaciones.service.AssignmentService
import jw.org.asignaciones.service.DummyService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/" )
class DummyController (
    service: DummyService
): AbstractCRUDController<Dummy, Int>(service){
    @Operation(summary = "Creates a Dummy in database (Crud)")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200",
            description = "Dummy created successfully",
            content = [ Content(mediaType = "application/json",
                schema = Schema(implementation = Dummy::class)) ]),
        ApiResponse(responseCode = "400",
            description = "Dummy id not null",
            content = [ Content(mediaType = "application/json",
                schema = Schema(implementation = String::class)) ]),
        ApiResponse(responseCode = "500",
            description = "Internal server error",
            content = [ Content() ])
    ])
    @PutMapping("/dummy", produces = ["application/json"])
    override fun create(@RequestBody dummy: Dummy): ResponseEntity<out Any> = super.create(dummy)

    @Operation(summary = "Reads a Dummy from database  (cRud)")
    @GetMapping(value = ["/dummy/{id}"], produces = ["application/json"])
    @ApiResponses(value = [
        ApiResponse(responseCode = "200",
            description = "Dummy read successfully",
            content = [ Content(mediaType = "application/json",
                schema = Schema(implementation = Dummy::class)) ]),
        ApiResponse(responseCode = "404",
            description = "Dummy not found",
            content = [ Content() ]),
        ApiResponse(responseCode = "500",
            description = "Internal server error",
            content = [ Content() ])
    ])
    override fun read(@PathVariable id: Int): ResponseEntity<out Any> = super.read(id)

    @Operation(summary = "Updates a Dummy in database  (crUd)")
    @PostMapping(value = ["/dummy"], produces = ["application/json"])
    @ApiResponses(value = [
        ApiResponse(responseCode = "200",
            description = "Dummy updated successfully",
            content = [ Content(mediaType = "application/json",
                schema = Schema(implementation = Dummy::class))]),
        ApiResponse(responseCode = "400",
            description = "Dummy id not null",
            content = [ Content() ]),
        ApiResponse(responseCode = "404",
            description = "Dummy not found. To create a new Dummy use the right endpoint",
            content = [ Content() ]),
        ApiResponse(responseCode = "500",
            description = "Internal server error",
            content = [ Content() ])
    ])
    override fun update(@RequestBody dummy: Dummy): ResponseEntity<out Any> = super.update(dummy)

    @Operation(summary = "Deletes a Dummy from database  (cruD)")
    @DeleteMapping(value = ["/dummy/{id}"], produces = ["application/json"])
    @ApiResponses(value = [
        ApiResponse(responseCode = "200",
            description = "Dummy deleted successfully",
            content = [ Content(mediaType = "application/json",
                schema = Schema(implementation = Dummy::class))]),
        ApiResponse(responseCode = "404",
            description = "Dummy not found. To create a new Dummy use the right endpoint",
            content = [ Content() ]),
        ApiResponse(responseCode = "500",
            description = "Internal server error",
            content = [ Content() ])
    ])
    override fun delete(@PathVariable id: Int): ResponseEntity<out Any> = super.delete(id)
}