package jw.org.asignaciones.repository

import jw.org.asignaciones.model.Dummy
import org.springframework.data.jpa.repository.JpaRepository

interface DummyRepository: JpaRepository<Dummy, Int> {
}