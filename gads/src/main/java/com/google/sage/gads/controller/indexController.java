package com.google.sage.gads.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class indexController {

    @RequestMapping("/clicktobuy")
    public String clickToBuy(Model model) {
//        model.addAttribute("name", name);
        return "clicktobuy/index";
    }


    @RequestMapping("/clicktobuy/callback")
    public String ctbCallback(Model model) {
        return "clicktobuy/callback";
    }


}
