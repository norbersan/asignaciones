package jw.org.asignaciones.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.ArraySchema
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import jw.org.asignaciones.model.Assignment
import jw.org.asignaciones.service.AssignmentService
import org.springframework.data.domain.Pageable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/" )
class AssignmentController(
    service: AssignmentService
):AbstractExtendedController<Assignment, Int>(service){
    @Operation(summary = "Creates an Assignment in database (Crud)")
    @ApiResponses(value = [
        ApiResponse(responseCode = "200",
            description = "Assignment created successfully",
            content = [ Content(mediaType = "application/json",
                schema = Schema(implementation = Assignment::class)
            ) ]),
        ApiResponse(responseCode = "400",
            description = "Assignment id not null",
            content = [ Content(mediaType = "application/json",
                schema = Schema(implementation = String::class)
            ) ]),
        ApiResponse(responseCode = "500",
            description = "Internal server error",
            content = [ Content() ])
    ])
    @PutMapping("/assignment", produces = ["application/json"])
    override fun create(@RequestBody assignment: Assignment): ResponseEntity<out Any> = super.create(assignment)

    @Operation(summary = "Reads a Assignment from database  (cRud)")
    @GetMapping(value = ["/assignment/{id}"], produces = ["application/json"])
    @ApiResponses(value = [
        ApiResponse(responseCode = "200",
            description = "Assignment read successfully",
            content = [ Content(mediaType = "application/json",
                schema = Schema(implementation = Assignment::class)
            ) ]),
        ApiResponse(responseCode = "404",
            description = "Assignment not found",
            content = [ Content() ]),
        ApiResponse(responseCode = "500",
            description = "Internal server error",
            content = [ Content() ])
    ])
    override fun read(@PathVariable id: Int): ResponseEntity<out Any> = super.read(id)

    @Operation(summary = "Updates an Assignment in database  (crUd)")
    @PostMapping(value = ["/assignment"], produces = ["application/json"])
    @ApiResponses(value = [
        ApiResponse(responseCode = "200",
            description = "Assignment updated successfully",
            content = [ Content(mediaType = "application/json",
                schema = Schema(implementation = Assignment::class)
            )]),
        ApiResponse(responseCode = "400",
            description = "Assignment id not null",
            content = [ Content() ]),
        ApiResponse(responseCode = "404",
            description = "Assignment not found. To create a new Assignment use the right endpoint",
            content = [ Content() ]),
        ApiResponse(responseCode = "500",
            description = "Internal server error",
            content = [ Content() ])
    ])
    override fun update(@RequestBody assignment: Assignment): ResponseEntity<out Any> = super.update(assignment)

    @Operation(summary = "Deletes a Assignment from database  (cruD)")
    @DeleteMapping(value = ["/assignment/{id}"], produces = ["application/json"])
    @ApiResponses(value = [
        ApiResponse(responseCode = "200",
            description = "Assignment deleted successfully",
            content = [ Content(mediaType = "application/json",
                schema = Schema(implementation = Assignment::class)
            )]),
        ApiResponse(responseCode = "404",
            description = "Assignment not found. To create a new Assignment use the right endpoint",
            content = [ Content() ]),
        ApiResponse(responseCode = "500",
            description = "Internal server error",
            content = [ Content() ])
    ])
    override fun delete(@PathVariable id: Int): ResponseEntity<out Any> = super.delete(id)

    @Operation(summary = "Get a list of all assignments")
    @GetMapping(value = ["/assignments"], produces = ["application/json"])
    @ApiResponses(value = [
        ApiResponse(responseCode = "200",
            description = "List of all assignments",
            content = [ Content(mediaType = "application/json",
                array = ArraySchema(schema = Schema(implementation = Assignment::class)
                )
            )]),
        ApiResponse(responseCode = "500",
            description = "Internal server error",
            content = [ Content() ])
    ])
    override fun findAll() = super.findAll()

    @Operation(summary = "Get a page list of all assignments")
    @GetMapping(value = ["/assignments/"], produces = ["application/json"])
    @ApiResponses(value = [
        ApiResponse(responseCode = "200",
            description = "Page of assignments",
            content = [ Content(mediaType = "application/json",
                array = ArraySchema(schema = Schema(implementation = Assignment::class)
                )
            )]),
        ApiResponse(responseCode = "500",
            description = "Internal server error",
            content = [ Content() ])
    ])
    override fun findAll(pageable: Pageable) = super.findAll(pageable)
}