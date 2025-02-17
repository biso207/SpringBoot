package com.example.provespringboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PrintController {

    @GetMapping("/hello")
    public String printMessage(@RequestParam(defaultValue = "Your Message") String message) {
        return message;
    }
}
