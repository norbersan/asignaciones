package jw.org.asignaciones.model.converter

import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

@Converter
class StringEncryptConverter(): AttributeConverter<String, String> {
    override fun convertToDatabaseColumn(attribute: String?): String? {
        //TODO("Not yet implemented")
        return attribute?.reversed()
    }

    override fun convertToEntityAttribute(dbData: String?): String? {
        //TODO("Not yet implemented")
        return dbData?.reversed()
    }

}
