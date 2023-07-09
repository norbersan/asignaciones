package jw.org.asignaciones.service

import jw.org.asignaciones.model.Dummy
import jw.org.asignaciones.repository.DummyRepository
import org.springframework.stereotype.Service

@Service
class DummyService(
    dummyRepository: DummyRepository
): AbstractCRUDService<Dummy, Int>(dummyRepository) {

    fun update(dummy: Dummy) {
        if (dummy.id == null)
            throw IllegalArgumentException("id must not be null")
        update(dummy, dummy.id!!)
    }
}