package com.google.sage.gads.model.enums;


public enum CreativeItemEnum {
    KFC("kfc", "kfc.png", "https://m.4008823823.com.cn"),
    PIZZAHUT("pizzahut", "pizza.jpg", "https://m.4008123123.com"),
    JD("jd", "jd.png", "https://m.jd.com");


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
