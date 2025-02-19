package com.abdullayev.apps.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/first")
public class FirstController {

    @GetMapping("/hello-world")
    public String sayHello(@RequestParam(value = "name", required = false) String name,
                           @RequestParam(value = "surname", required = false) String surname) {

        System.out.println("Hello, " + name + " " + surname);

        return "first/hello_world";
    }

    @GetMapping("/hehe")
    public String sayHehe() {
        return "first/hehe";
    }
}
