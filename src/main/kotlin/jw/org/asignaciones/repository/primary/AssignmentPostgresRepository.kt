package jw.org.asignaciones.repository.primary

import jw.org.asignaciones.model.Assignment
import org.springframework.data.jpa.repository.JpaRepository

interface AssignmentPostgresRepository: JpaRepository<Assignment, Int> {
}