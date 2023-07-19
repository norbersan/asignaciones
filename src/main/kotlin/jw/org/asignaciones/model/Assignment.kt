package jw.org.asignaciones.model

import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.validation.constraints.NotNull
import jw.org.asignaciones.model.converter.LocalDateTimeEncryptConverter
import jw.org.asignaciones.model.converter.StringEncryptConverter
import org.hibernate.annotations.DynamicUpdate
import java.time.LocalDateTime
import jw.org.asignaciones.model.TableDef.Assignment.ColDef
import jw.org.asignaciones.model.TableDef.Assignment.TABLENAME

@Entity
@Table(name = TABLENAME)
@DynamicUpdate
data class Assignment (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(
        name = ColDef.ID.NAME,
        columnDefinition = ColDef.ID.DEFINITION,
        updatable = false,
        nullable = false
    )
    var id: Int? = null,

    @Column(
        name = ColDef.NAME.NAME,
        length = ColDef.NAME.LEN
    )
    @Convert(converter = StringEncryptConverter::class)
    var name: String? = null,

    @Column(
        name = ColDef.DESCRIPTION.NAME,
        length = ColDef.DESCRIPTION.LEN
    )
    @Convert(converter = StringEncryptConverter::class)
    var description: String? = null,

    @Column(
        name = ColDef.START.NAME,
        length = ColDef.NOTES.LEN
    )
    @Convert(converter = LocalDateTimeEncryptConverter::class)
    var start: LocalDateTime? = LocalDateTime.now(),

    @Column(
        name = ColDef.END.NAME,
        length = ColDef.NOTES.LEN
    )
    @Convert(converter = LocalDateTimeEncryptConverter::class)
    var end: LocalDateTime? = LocalDateTime.now(),

    @Column(
        name = ColDef.NOTES.NAME,
        length = ColDef.NOTES.LEN
    )
    @Convert(converter = StringEncryptConverter::class)
    var notes: String? = null
): IndexedEntity<Int> {
    override fun index(): Int? = id
}

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