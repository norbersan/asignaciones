package jw.org.asignaciones.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import org.hibernate.annotations.DynamicUpdate

@Entity
@Table(name = TableDef.Notification.TABLENAME)
@DynamicUpdate
data class Notification (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
        name = TableDef.Assignment.ColDef.ID.NAME,
        columnDefinition = TableDef.Assignment.ColDef.ID.DEFINITION,
        updatable = false,
        nullable = false
    )
    var id: Int? = null

): IndexedEntity<Int> {
    override fun index(): Int? = id
}
