package jw.org.asignaciones.model

import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.DynamicUpdate

@Entity
@Table(name = "dummy")
@DynamicUpdate
data class Dummy (
    @Id
    var id: Int? = null,
    var text: String? = null
){
}