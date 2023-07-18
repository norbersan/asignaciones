package jw.org.asignaciones.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import jw.org.asignaciones.model.Assignee
import jw.org.asignaciones.service.AssigneeService
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/" )
class AssigneeController(
    service: AssigneeService
):AbstractExtendedController<Assignee, Int>(service){
    @Operation(summary = "Creates an Assignee in database (Crud)")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200",
            description = "Assignee created successfully",
            content = [ Content(mediaType = "application/json",
                schema = Schema(implementation = Assignee::class)
            ) ]),
        ApiResponse(responseCode = "400",
            description = "Assignee id not null",
            content = [ Content(mediaType = "application/json",
                schema = Schema(implementation = String::class)
            ) ]),
        ApiResponse(responseCode = "500",
            description = "Internal server error",
            content = [ Content() ])
    ])
    @PutMapping("/assignee", produces = ["application/json"])
    override fun create(@RequestBody assignee: Assignee): ResponseEntity<out Any> = super.create(assignee)

    @Operation(summary = "Reads a Assignee from database  (cRud)")
    @GetMapping(value = ["/assignee/{id}"], produces = ["application/json"])
    @ApiResponses(value = [
        ApiResponse(responseCode = "200",
            description = "Assignee read successfully",
            content = [ Content(mediaType = "application/json",
                schema = Schema(implementation = Assignee::class)
            ) ]),
        ApiResponse(responseCode = "404",
            description = "Assignee not found",
            content = [ Content() ]),
        ApiResponse(responseCode = "500",
            description = "Internal server error",
            content = [ Content() ])
    ])
    override fun read(@PathVariable id: Int): ResponseEntity<out Any> = super.read(id)

    @Operation(summary = "Updates an Assignee in database  (crUd)")
    @PostMapping(value = ["/assignee"], produces = ["application/json"])
    @ApiResponses(value = [
        ApiResponse(responseCode = "200",
            description = "Assignee updated successfully",
            content = [ Content(mediaType = "application/json",
                schema = Schema(implementation = Assignee::class)
            )]),
        ApiResponse(responseCode = "400",
            description = "Assignee id not null",
            content = [ Content() ]),
        ApiResponse(responseCode = "404",
            description = "Assignee not found. To create a new Assignee use the right endpoint",
            content = [ Content() ]),
        ApiResponse(responseCode = "500",
            description = "Internal server error",
            content = [ Content() ])
    ])
    override fun update(@RequestBody assignee: Assignee): ResponseEntity<out Any> = super.update(assignee)

    @Operation(summary = "Deletes a Assignee from database  (cruD)")
    @DeleteMapping(value = ["/assignee/{id}"], produces = ["application/json"])
    @ApiResponses(value = [
        ApiResponse(responseCode = "200",
            description = "Assignee deleted successfully",
            content = [ Content(mediaType = "application/json",
                schema = Schema(implementation = Assignee::class)
            )]),
        ApiResponse(responseCode = "404",
            description = "Assignee not found. To create a new Assignee use the right endpoint",
            content = [ Content() ]),
        ApiResponse(responseCode = "500",
            description = "Internal server error",
            content = [ Content() ])
    ])
    override fun delete(@PathVariable id: Int): ResponseEntity<out Any> = super.delete(id)

    override fun findAll() = super.findAll()

    override fun findAll(pageable: Pageable) = super.findAll(pageable)
}