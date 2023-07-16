package jw.org.asignaciones.model.converter

import jakarta.persistence.AttributeConverter
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class LocalDateTimeEncryptConverter: AttributeConverter<LocalDateTime, String> {

    private val formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME
    override fun convertToDatabaseColumn(attribute: LocalDateTime?): String? {
        return attribute?.format(formatter)?.reversed()
    }

    override fun convertToEntityAttribute(dbData: String?): LocalDateTime? {
        return if (dbData == null)
            null
        else
            LocalDateTime.parse(dbData.reversed(), formatter)
    }
}