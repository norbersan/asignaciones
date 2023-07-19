package jw.org.asignaciones.model

import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jw.org.asignaciones.model.converter.StringEncryptConverter
import org.hibernate.annotations.DynamicUpdate

@Entity
@Table(name = "dummy")
@DynamicUpdate
data class Dummy(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    var id: Int? = null,

    @Convert(converter = StringEncryptConverter::class)
    var text: String? = null
): IndexedEntity<Int> {
    override fun index(): Int? = id
}