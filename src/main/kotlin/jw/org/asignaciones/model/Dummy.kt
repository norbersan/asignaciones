package jw.org.asignaciones.model

import jakarta.persistence.*
import jw.org.asignaciones.model.converter.StringEncryptConverter
import org.hibernate.annotations.DynamicUpdate

@Entity
@Table(name = "dummy")
@DynamicUpdate
data class Dummy (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "serial")
    var id: Int? = null,

    @Convert(converter = StringEncryptConverter::class)
    var text: String? = null
){
}