package jw.org.asignaciones.repository.secondary

import jw.org.asignaciones.model.Dummy
import org.springframework.data.jpa.repository.JpaRepository

interface DummyPostgresRepository: JpaRepository<Dummy, Int> {
}