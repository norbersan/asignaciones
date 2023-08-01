package jw.org.asignaciones.repository.postgres

import jw.org.asignaciones.model.Assignee
import org.springframework.data.jpa.repository.JpaRepository

interface AssigneePostgresRepository: JpaRepository<Assignee, Int> {
}