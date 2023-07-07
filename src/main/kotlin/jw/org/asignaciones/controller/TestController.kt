package jw.org.asignaciones.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class TestController {

    @GetMapping(value = ["/test"], produces = ["application/json"])
    fun test() = "endpoint called"
}