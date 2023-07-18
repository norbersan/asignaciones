package jw.org.asignaciones.service

import jw.org.asignaciones.model.Dummy
import jw.org.asignaciones.repository.DummyRepository
import org.springframework.stereotype.Service

@Service
class DummyService(
    dummyRepository: DummyRepository
): AbstractExtendedService<Dummy, Int>(dummyRepository)