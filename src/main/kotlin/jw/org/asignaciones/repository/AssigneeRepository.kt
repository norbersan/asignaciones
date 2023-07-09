package jw.org.asignaciones.repository

import jw.org.asignaciones.model.Assignee
import org.springframework.data.jpa.repository.JpaRepository

interface AssigneeRepository: JpaRepository<Assignee, Int> {
}