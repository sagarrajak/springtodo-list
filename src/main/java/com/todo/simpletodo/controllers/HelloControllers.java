package com.todo.simpletodo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/hello")
public class HelloControllers {
    @GetMapping("user")
    public String helloUser() {
        return "simple hello user";
    }

    @GetMapping("admin")
    public String helloAdmin() {
        return "Simple hello admin!";
    }
}
