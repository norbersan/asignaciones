package jw.org.asignaciones.model

import jakarta.persistence.Column
import jakarta.persistence.Convert
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.validation.constraints.NotNull
import jw.org.asignaciones.model.converter.StringEncryptConverter
import org.hibernate.annotations.DynamicUpdate
import jw.org.asignaciones.model.TableDef.Assignee.ColDef
import jw.org.asignaciones.model.TableDef.Assignee.TABLENAME

@Entity
@Table(name = TABLENAME)
@DynamicUpdate
data class Assignee (
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
        name = ColDef.SURNAME.NAME,
        length = ColDef.SURNAME.LEN
    )
    @Convert(converter = StringEncryptConverter::class)
    var surname: String? = null,

    @Column(
        name = ColDef.PHONE.NAME,
        length = ColDef.PHONE.LEN
    )
    @Convert(converter = StringEncryptConverter::class)
    var phone: String? = null,

    @Column(
        name = ColDef.TELEGRAMNICK.NAME,
        length = ColDef.TELEGRAMNICK.LEN
    )
    @Convert(converter = StringEncryptConverter::class)
    var telegramNick: String? = null,

    @Column(
        name = ColDef.NOTES.NAME,
        length = ColDef.NOTES.LEN
    )
    @Convert(converter = StringEncryptConverter::class)
    var notes: String? = null

): IndexedEntity<Int> {
    override fun index(): Int? = id
}