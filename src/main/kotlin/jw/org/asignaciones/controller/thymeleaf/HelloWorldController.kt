package jw.org.asignaciones.controller.thymeleaf

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping


@Controller
class HelloWorldController {
    // handler method to handle /helloworld request
    // http://localhost:8080/hello-world
    @GetMapping("/hello-world")
    fun helloWorld(model: Model): String {
        model.addAttribute("message", "Hello World!")
        return "hello-world"
    }
}