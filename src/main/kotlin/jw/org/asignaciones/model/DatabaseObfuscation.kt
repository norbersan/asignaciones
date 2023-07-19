package jw.org.asignaciones.model

object TableDef {
    object Assignee {
        const val TABLENAME = "assignee"
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
        const val TABLENAME = "assignment"
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
                const val NAME = "starts"
                const val LEN = 50
            }

            object END {
                const val NAME = "ends"
                const val LEN = 50
            }

            object NOTES {
                const val NAME = "notes"
                const val LEN = 1024
            }
        }
    }

    object Notification {
        const val TABLENAME = "notification"
        object ColumnDef {
            object ID {
                const val NAME = "id"
                const val DEFINITION = "serial"
            }
        }
    }
}



