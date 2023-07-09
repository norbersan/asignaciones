package jw.org.asignaciones

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(basePackages = [
    "jw.org.asignaciones.notification",
    "org.telegram.telegrambots"
])
class AsignacionesApplication

fun main(args: Array<String>) {
    runApplication<AsignacionesApplication>(*args)
}
