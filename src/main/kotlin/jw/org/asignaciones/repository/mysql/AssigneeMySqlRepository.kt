package jw.org.asignaciones.repository.mysql

import jw.org.asignaciones.model.Assignee
import org.springframework.data.jpa.repository.JpaRepository

interface AssigneeMySqlRepository: JpaRepository<Assignee, Int> {
}