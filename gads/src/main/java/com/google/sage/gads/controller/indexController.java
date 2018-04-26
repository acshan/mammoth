package com.google.sage.gads.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class indexController {

    @RequestMapping("/clicktobuy")
    public String greeting(Model model) {
//        model.addAttribute("name", name);
        return "clicktobuy/index";
    }

//    @GetMapping("/")
//    public String hello() {
//        return "Hello Spring Boot!";
//    }
}
