var reffer = document.referrer;
var ua=navigator.userAgent.toLowerCase();
var noqrcode = window.JD?(JD.url.getUrlParam("noqrcode") || "0"):"0";
if(ua.indexOf('android') > -1 || ua.indexOf('linux') > -1 || ua.indexOf('iphone') > -1 || ua.indexOf('ipad') > -1 || ua.indexOf('micromessenger') > -1 || /qq\/([\d\.]+)*/.test(ua) || location.href.indexOf("error")>-1 || noqrcode == "1" || reffer.indexOf("wqadmin.jd.com")>-1 || reffer.indexOf("mm.wanggou.com")>-1){
    //是微信或手Q内置浏览器
}else{
    var cw=document.documentElement.clientWidth||document.body.clientWidth;
    //大于800宽度展示
    if(1000<cw){
        window.JD && JD.report.umpBiz({
            bizid     : "100",
            operation : "1",
            result    : "0",
            source    : "0",
            message   : ""
        });
        console.log('show QR Code');
    }
}