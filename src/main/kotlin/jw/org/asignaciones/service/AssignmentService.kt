package jw.org.asignaciones.service

import jw.org.asignaciones.model.Assignment
import jw.org.asignaciones.repository.AssignmentRepository
import org.springframework.stereotype.Service

@Service
class AssignmentService (
    assignmentRepository: AssignmentRepository
): AbstractCRUDService<Assignment, Int>(assignmentRepository)
