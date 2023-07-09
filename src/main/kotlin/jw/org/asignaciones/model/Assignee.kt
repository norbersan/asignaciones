package jw.org.asignaciones.model

import jakarta.persistence.*
import jakarta.persistence.Table
import org.hibernate.annotations.DynamicUpdate

@Entity
@Table(name = "assignee")
@DynamicUpdate
data class Assignee (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
        name = TableDef.Assignee.ColDef.ID.NAME,
        columnDefinition = TableDef.Assignee.ColDef.ID.DEFINITION,
        updatable = false,
        nullable = false
    )
    var id: Int? = null,

    @Column(
        name = TableDef.Assignee.ColDef.NAME.NAME,
        length = TableDef.Assignee.ColDef.NAME.LEN
    )
    @Convert(converter = StringEncryptConverter::class)
    var name: String? = null,

    @Column(
        name = TableDef.Assignee.ColDef.SURNAME.NAME,
        length = TableDef.Assignee.ColDef.SURNAME.LEN
    )
    @Convert(converter = StringEncryptConverter::class)
    var surname: String? = null,

    @Column(
        name = TableDef.Assignee.ColDef.PHONE.NAME,
        length = TableDef.Assignee.ColDef.PHONE.LEN
    )
    @Convert(converter = StringEncryptConverter::class)
    var phone: String? = null,

    @Column(
        name = TableDef.Assignee.ColDef.TELEGRAMNICK.NAME,
        length = TableDef.Assignee.ColDef.TELEGRAMNICK.LEN
    )
    @Convert(converter = StringEncryptConverter::class)
    var telegramNick: String? = null,

    @Column(
        name = TableDef.Assignee.ColDef.NOTES.NAME,
        length = TableDef.Assignee.ColDef.NOTES.LEN
    )
    @Convert(converter = StringEncryptConverter::class)
    var notes: String? = null

)