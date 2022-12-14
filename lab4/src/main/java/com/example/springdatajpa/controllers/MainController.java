package com.example.springdatajpa.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@CrossOrigin
public class MainController {

    @GetMapping("/")
    public String index() {
        return "index.html";
    }

    @GetMapping("/main")
    public String main() {
        return "index.html";
    }

    @GetMapping("/login")
    public String login() {
        return "index.html";
    }
}
