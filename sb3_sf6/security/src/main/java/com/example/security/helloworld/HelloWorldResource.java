package com.example.security.helloworld;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldResource {
    @GetMapping(path = "hello-world")
    public String helloWorld() {
        return "Hello World";
    }
}
