package jw.org.asignaciones.service

import jw.org.asignaciones.model.Assignee
import jw.org.asignaciones.repository.AssigneeRepository
import org.springframework.stereotype.Service

@Service
class AssigneeService (
    assigneeRepository: AssigneeRepository
): AbstractCRUDService<Assignee, Int>(assigneeRepository)