package jw.org.asignaciones.repository

import jw.org.asignaciones.model.Assignment
import org.springframework.data.jpa.repository.JpaRepository

interface AssignmentRepository: JpaRepository<Assignment, Int> {
}