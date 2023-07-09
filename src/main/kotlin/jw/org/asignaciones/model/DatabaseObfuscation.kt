package jw.org.asignaciones.model

import jakarta.persistence.AttributeConverter
import jakarta.persistence.Converter

class TableDef {
    object Assignee {
        object ColDef {
            object ID {
                const val NAME = "id"
                const val DEFINITION = "serial"
            }
            object NAME {
                const val NAME = "name"
                const val LEN = 50
            }
            object SURNAME {
                const val NAME = "surname"
                const val LEN = 50
            }
            object PHONE {
                const val NAME = "phone"
                const val LEN = 50
            }
            object TELEGRAMNICK {
                const val NAME = "nick"
                const val LEN = 50
            }
            object NOTES {
                const val NAME = "notes"
                const val LEN = 1024
            }
        }
    }

    object Assignment {
        object ColDef {
            object ID {
                const val NAME = "id"
                const val DEFINITION = "serial"
            }

            object NAME {
                const val NAME = "name"
                const val LEN = 50
            }

            object DESCRIPTION {
                const val NAME = "description"
                const val LEN = 255
            }

            object START {
                const val NAME = "start"
            }

            object END {
                const val NAME = "end"
            }

            object NOTES {
                const val NAME = "notes"
                const val LEN = 1024
            }
        }
    }

    object Notification {
        object ColumnDef {
            const val ID = "id"
        }
    }
}

@Converter
class StringEncryptConverter(): AttributeConverter<String, String>{
    override fun convertToDatabaseColumn(attribute: String?): String? {
        //TODO("Not yet implemented")
        return attribute?.reversed()
    }

    override fun convertToEntityAttribute(dbData: String?): String? {
        //TODO("Not yet implemented")
        return dbData?.reversed()
    }

}



