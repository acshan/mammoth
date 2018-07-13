package com.google.sage.gads.controller;


import com.google.sage.gads.model.Buyer;
import com.google.sage.gads.model.enums.CreativeItemEnum;
import com.google.sage.gads.service.BuyerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.HtmlUtils;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;


@Controller
@Slf4j
public class IndexController {


    @Autowired
    BuyerService buyerService;

    @RequestMapping("/clicktobuy")
    public String clickToBuy(Model model) {
//        model.addAttribute("name", name);
        return "clicktobuy/index";
    }


    @RequestMapping("/video/viv")
    public String vast(Model model) {
        return "video/videoInVideo";
    }

    @RequestMapping("/video/sdkless")
    public String sdkless(Model model) {
        return "sdkless/SDKless";
    }


    @RequestMapping(value = "/clicktobuy/callback")
    public String ctbCallback(@RequestParam(value = "creative", required = false) String creative, Model model) {
        if (StringUtils.isEmpty(creative)) {
            log.error("creative param is Empty!");
            fillCallBack(model);
        } else {
            Buyer buyer = buyerService.getBuyer(creative);
            if (buyer == null) {
                log.error("Buyer is Empty! creative = " + creative);
                fillCallBack(model);
            } else {
                model.addAttribute("creative_code", buyer.getCode());
                model.addAttribute("creative_img", buyer.getImageUrl());
                model.addAttribute("creative_url", buyer.getLandingUrl());
            }
        }
        return "clicktobuy/callback";
    }


    @RequestMapping(value = "/clicktobuy/mcallback")
    public String ctbMCallback(Model model) {
        return "clicktobuy/mobile/mcallback_dfp";
    }


    private void fillCallBack(Model model) {
        model.addAttribute("creative_code", CreativeItemEnum.EF.getCode());
        model.addAttribute("creative_img", CreativeItemEnum.EF.getImg());
        model.addAttribute("creative_url", CreativeItemEnum.EF.getUrl());
    }


    @RequestMapping(value = "/banner")
    public String banner(Model model) {
        return "banner/index";
    }


    @RequestMapping(value = "/tracker")
    public String clickTracker(HttpServletRequest request, Model model) {
        log.info(request.getParameterMap().toString());
        return null;
    }


    @RequestMapping(value = "/message")
    public String websocketMessage(HttpServletRequest request, Model model) {
        log.info(request.getParameterMap().toString());
        return "message/message";
    }


    @RequestMapping(value = "/banner/nonstandard")
    public String bannerAds(HttpServletRequest request, Model model) {
        log.info(request.getParameterMap().toString());
        return "banner/non_standard_banner";
    }


    @RequestMapping(value = "/fb/receive")
    public String receiveMsg(HttpServletRequest request, Model model) {
        log.info(request.getParameterMap().toString());
        return "firebase/receive";
    }


    @RequestMapping(value = "/fb/send")
    public String sendMsg(HttpServletRequest request, Model model) {
        log.info(request.getParameterMap().toString());
        return "firebase/send";
    }




    @MessageMapping("/playAds")
    @SendTo("/topic/playAds")
    public String playAds() throws Exception {
        Thread.sleep(10); // simulated delay
        log.info("Play Ads Request Received");
        return "";
//        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }





}
