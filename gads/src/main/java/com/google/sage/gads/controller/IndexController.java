package com.google.sage.gads.controller;


import com.google.sage.gads.model.enums.CreativeItemEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;


@Controller
@Slf4j
public class IndexController {

    @RequestMapping("/clicktobuy")
    public String clickToBuy(Model model) {
//        model.addAttribute("name", name);
        return "clicktobuy/index";
    }


    @RequestMapping("/video/viv")
    public String vast(Model model) {
        return "video/videoInVideo";
    }


    @RequestMapping(value = "/clicktobuy/callback")
    public String ctbCallback(@RequestParam(value = "creative", required = false) String creative, Model model) {
        CreativeItemEnum creativeItemEnum;
        if (creative == null) {
            creativeItemEnum = CreativeItemEnum.EF;
        } else {
            creativeItemEnum = CreativeItemEnum.valueOf(creative);
        }
        if (creativeItemEnum == null) {
            return null;
        }
        model.addAttribute("creative_code", creativeItemEnum.getCode());
        model.addAttribute("creative_img", creativeItemEnum.getImg());
        model.addAttribute("creative_url", creativeItemEnum.getUrl());
        return "clicktobuy/callback";
    }


    @RequestMapping(value = "/banner")
    public String banner(Model model) {
        return "banner/index";
    }


    @RequestMapping(value="/tracker")
    public String clickTracker(HttpServletRequest request, Model model){
        log.info(request.getParameterMap().toString());
        return null;
    }


}
