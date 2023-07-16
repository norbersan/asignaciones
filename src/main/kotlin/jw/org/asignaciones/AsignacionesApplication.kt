package jw.org.asignaciones

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Contact
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.annotations.info.License
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan(basePackages = [
    "jw.org.asignaciones",
    "jw.org.asignaciones.notification",
    "org.telegram.telegrambots"
])
@OpenAPIDefinition(
    // TODO complete info
    info = Info(
        title = "",
        description = "",
        termsOfService = "",
        version = "",
        contact = Contact(),
        license = License()
    )
)
class AsignacionesApplication

fun main(args: Array<String>) {
    runApplication<AsignacionesApplication>(*args)
}
