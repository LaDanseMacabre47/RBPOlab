package com.example.demo.controller;

import com.example.demo.dto.GreetRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class GreetController {
    @PostMapping("/greet")
    public Map<String, String> greet(@RequestBody GreetRequest request) {
        String name = request.getName() != null ? request.getName() : "stranger";
        return Map.of("message", "Hello, " + name + "!");
    }
}