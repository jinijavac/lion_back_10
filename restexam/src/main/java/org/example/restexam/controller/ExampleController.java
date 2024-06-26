package org.example.restexam.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {
    @GetMapping("/api/error")
    public String apierror(){
        throw new RuntimeException("API ERROR");
    }
}
