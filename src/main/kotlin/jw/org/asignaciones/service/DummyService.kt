package jw.org.asignaciones.service

import jakarta.persistence.EntityNotFoundException
import jw.org.asignaciones.model.Dummy
import jw.org.asignaciones.repository.DummyRepository
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrElse

@Service
class DummyService(
    private val dummyRepository: DummyRepository
) {
    fun create(dummy: Dummy) = dummyRepository.save(dummy)

    fun read(id: Int): Dummy {
        val value = dummyRepository.findById(id)
        return value.getOrElse {
            throw EntityNotFoundException("Not Found")
        }
    }

    fun update(dummy: Dummy) {
        if (dummy.id == null)
            throw IllegalArgumentException("id must not be null")
        if (!dummyRepository.existsById(dummy.id!!))
            throw IllegalArgumentException("entity not found")
        dummyRepository.save(dummy)
    }

    fun delete(id: Int) = dummyRepository.deleteById(id)

}