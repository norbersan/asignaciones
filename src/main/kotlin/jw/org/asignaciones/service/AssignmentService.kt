package jw.org.asignaciones.service

import jw.org.asignaciones.model.Assignment
import jw.org.asignaciones.repository.AssignmentRepository
import org.springframework.stereotype.Service

@Service
class AssignmentService (
    assignmentRepository: AssignmentRepository
): AbstractCRUDService<Assignment, Int>(assignmentRepository){
    fun update(assignment: Assignment) {
        if (assignment.id == null)
            throw IllegalArgumentException("id must not be null")
        update(assignment, assignment.id!!)
    }

}
