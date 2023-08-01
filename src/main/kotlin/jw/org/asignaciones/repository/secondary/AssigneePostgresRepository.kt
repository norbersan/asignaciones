package jw.org.asignaciones.repository.secondary

import jw.org.asignaciones.model.Assignee
import org.springframework.data.jpa.repository.JpaRepository

interface AssigneePostgresRepository: JpaRepository<Assignee, Int> {
}