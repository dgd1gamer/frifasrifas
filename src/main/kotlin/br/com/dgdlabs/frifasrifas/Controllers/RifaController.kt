package br.com.dgdlabs.frifasrifas.Controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class RifaController {


    @GetMapping("/")
    fun helloworld(): String {
        return "Hello World"
    }

}