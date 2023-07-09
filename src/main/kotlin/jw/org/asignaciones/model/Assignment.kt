package jw.org.asignaciones.model

import jakarta.persistence.*
import org.hibernate.annotations.DynamicUpdate
import java.time.LocalDateTime

@Entity
@Table(name = "assignment")
@DynamicUpdate
data class Assignment (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
        name = TableDef.Assignment.ColDef.ID.NAME,
        columnDefinition = TableDef.Assignment.ColDef.ID.DEFINITION,
        updatable = false,
        nullable = false
    )
    var id: Int? = null,

    @Column(
        name = TableDef.Assignment.ColDef.NAME.NAME,
        length = TableDef.Assignment.ColDef.NAME.LEN
    )
    @Convert(converter = StringEncryptConverter::class)
    var name: String? = null,

    @Column(
        name = TableDef.Assignment.ColDef.DESCRIPTION.NAME,
        length = TableDef.Assignment.ColDef.DESCRIPTION.LEN
    )
    @Convert(converter = StringEncryptConverter::class)
    var description: String? = null,

    @Column(
        name = TableDef.Assignment.ColDef.START.NAME
    )
    var start: LocalDateTime? = LocalDateTime.now(),

    @Column(
        name = TableDef.Assignment.ColDef.END.NAME
    )
    var end: LocalDateTime? = LocalDateTime.now(),

    @Column(
        name = TableDef.Assignment.ColDef.NOTES.NAME,
        length = TableDef.Assignment.ColDef.NOTES.LEN
    )
    @Convert(converter = StringEncryptConverter::class)
    var notes: String? = null
)

fun Assignment.isCompatibleWith(other: Assignment): Boolean? {
    if (this.start == null ||
        this.end == null ||
        other.start == null ||
        other.end == null){

        return null
    }

    if (this.start!!.isAfter(this.end!!)){
        throw RuntimeException("Wrong dates: $this")
    }

    if (other.start!!.isAfter(other.end!!)){
        throw RuntimeException("Wrong dates: $other")
    }

    return this.end!!.isBefore(other.start!!) ||
            this.start!!.isAfter(other.end!!)
}