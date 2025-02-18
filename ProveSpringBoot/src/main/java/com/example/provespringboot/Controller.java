package com.example.provespringboot;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class Controller {

    @GetMapping("/hello")
    public String printMessage(@RequestParam(defaultValue = "Your Message") String message) {
        return message;
    }

    @GetMapping("/helloWorld")
    public String printMessage() {
        return "Hello World";
    }

    @GetMapping("/secure-data")
    public String getSecureData() {
        return "Dati Protetti";
    }
}
