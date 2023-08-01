package jw.org.asignaciones.repository.mysql

import jw.org.asignaciones.model.Assignment
import org.springframework.data.jpa.repository.JpaRepository

interface AssignmentMySqlRepository: JpaRepository<Assignment, Int> {
}