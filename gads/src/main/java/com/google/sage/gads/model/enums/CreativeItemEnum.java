package com.google.sage.gads.model.enums;


public enum CreativeItemEnum {
    KFC("kfc", "kfc.png", "https://m.4008823823.com.cn"),
    PIZZAHUT("pizzahut", "pizza.jpg", "https://m.4008123123.com"),
    JD("jd", "pizza.jpg", ""),
    EF("ef","ef.png","http://www.ef.com.cn/online/lp/cn/2016yr/mobile/newdesign_phase2_hg1.aspx?ctr=cn&lng=cs&apr=EmailEnglish&offer=None");


    private String code;
    private String img;
    private String url;

    CreativeItemEnum(String code, String img, String url) {
        this.code = code;
        this.img = img;
        this.url = url;
    }

    public String getCode() {
        return code;
    }

    public String getUrl() {
        return url;
    }

    public String getImg() {
        return img;
    }
}
