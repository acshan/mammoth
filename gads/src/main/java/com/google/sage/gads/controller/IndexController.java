package com.google.sage.gads.controller;


import com.google.sage.gads.model.enums.CreativeItemEnum;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

    @RequestMapping("/clicktobuy")
    public String clickToBuy(Model model) {
//        model.addAttribute("name", name);
        return "clicktobuy/index";
    }


    @RequestMapping("/video/vast")
    public String vast(Model model) {
        return "video/vast";
    }



    @RequestMapping(value = "/clicktobuy/callback")
    public String ctbCallback(@RequestParam(value = "creative",required = false) String creative,Model model) {

        CreativeItemEnum creativeItemEnum = CreativeItemEnum.valueOf(creative);
        if(creativeItemEnum == null){
            return null;
        }

        model.addAttribute("creative_code",creativeItemEnum.getCode());
        model.addAttribute("creative_img",creativeItemEnum.getImg());
        model.addAttribute("creative_url",creativeItemEnum.getUrl());
        return "clicktobuy/callback";
    }


}
