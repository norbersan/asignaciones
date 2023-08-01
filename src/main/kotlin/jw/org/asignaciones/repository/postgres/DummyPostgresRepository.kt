package jw.org.asignaciones.repository.postgres

import jw.org.asignaciones.model.Dummy
import org.springframework.data.jpa.repository.JpaRepository

interface DummyPostgresRepository: JpaRepository<Dummy, Int> {
}