package jw.org.asignaciones.repository.mysql

import jw.org.asignaciones.model.Dummy
import org.springframework.data.jpa.repository.JpaRepository

interface DummyMySqlRepository: JpaRepository<Dummy, Int> {
}