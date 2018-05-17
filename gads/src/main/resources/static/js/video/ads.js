var AD_INTERVAL = 3;
var adsManager;
var videoContent = document.getElementById('contentElement');
// var videoContent = document.getElementById('adsVideoElement');
videoContent.play();
var adDisplayContainer = new google.ima.AdDisplayContainer(document.getElementById('adContainer'), videoContent);
adDisplayContainer.initialize();

var adsLoader = new google.ima.AdsLoader(adDisplayContainer);

// 添加manager loader监听
adsLoader.addEventListener(google.ima.AdsManagerLoadedEvent.Type.ADS_MANAGER_LOADED, onAdsManagerLoaded, false);

//添加error监听
adsLoader.addEventListener(google.ima.AdErrorEvent.Type.AD_ERROR, onAdError, false);

//添加预加载监听
adsLoader.addEventListener(google.ima.AdEvent.Type.LOADED, onMediaLoaded, false);

//绑定videoContent的onended动作

var contentEndedListener = function () {
    adsLoader.contentComplete();
};
videoContent.onended = contentEndedListener;

var adsRequest = new google.ima.AdsRequest();

// adsRequest.adTagUrl = 'https://pubads.g.doubleclick.net/gampad/ads?' +
//     'sz=640x480&iu=/124319096/external/single_ad_samples&ciu_szs=300x250&' +
//     'impl=s&gdfp_req=1&vpos=midroll&env=vp&output=vast&unviewed_position_start=1&' +
//     'cust_params=deployment%3Ddevsite%26sample_ct%3Dlinear&correlator=';


// preroll
adsRequest.adTagUrl = 'https://pubads.g.doubleclick.net/gampad/ads?slotname=/124319096/external/ad_rule_samples&sz=640x480&ciu_szs=300x250&unviewed_position_start=1&output=xml_vast3&impl=s&env=vp&gdfp_req=1&ad_rule=0&vad_type=linear&vpos=preroll&pod=1&ppos=1&lip=true&min_ad_duration=0&max_ad_duration=30000&vrid=5776&cust_params=deployment%3Ddevsite%26sample_ar%3Dpreonly&url=https://developers.google.com/interactive-media-ads/docs/sdks/html5/tags&video_doc_id=short_onecue&cmsid=496&kfa=0&tfcd=0'
// midroll
// adsRequest.adTagUrl = 'https://pubads.g.doubleclick.net/gampad/ads?slotname=/124319096/external/ad_rule_samples&sz=640x480&ciu_szs=300x250&unviewed_position_start=1&output=xml_vast3&impl=s&env=vp&gdfp_req=1&ad_rule=0&cue=15000&vad_type=linear&vpos=midroll&pod=2&mridx=1&ppos=1&lip=true&min_ad_duration=0&max_ad_duration=30000&vrid=6256&cust_params=deployment%3Ddevsite%26sample_ar%3Dpremidpost&url=https://developers.google.com/interactive-media-ads/docs/sdks/html5/tags&video_doc_id=short_onecue&cmsid=496&kfa=0&tfcd=0'


// vmap
// adsRequest.adTagUrl = 'https://pubads.g.doubleclick.net/gampad/ads?sz=640x480&iu=/124319096/external/ad_rule_samples&ciu_szs=300x250&ad_rule=1&impl=s&gdfp_req=1&env=vp&output=vmap&unviewed_position_start=1&cust_params=deployment%3Ddevsite%26sample_ar%3Dpremidpost&cmsid=496&vid=short_onecue&correlator='

// adsRequest.adTagUrl = 'https://pubads.g.doubleclick.net/gampad/ads?sz=640x480&iu=/14028434/dfp_video_640&impl=s&gdfp_req=1&env=vp&vpos=midroll&output=vast&unviewed_position_start=1&url=[referrer_url]&description_url=[description_url]&correlator=[timestamp]';

// adsRequest.adTagUrl = 'https://pubads.g.doubleclick.net/gampad/ads?sz=640x480&iu=/14028434/dfp_video_640&impl=s&gdfp_req=1&env=vp&output=vast&unviewed_position_start=1&url=[referrer_url]&description_url=[description_url]&correlator=[timestamp]'
// adsRequest.adTagUrl = 'https://pubads.g.doubleclick.net/gampad/ads?sz=640x480&' +
// 'iu=/124319096/external/ad_rule_samples&ciu_szs=300x250&ad_rule=1&' +
// 'impl=s&gdfp_req=1&env=vp&output=vmap&unviewed_position_start=1&' +
// 'cust_params=deployment%3Ddevsite%26sample_ar%3Dpremidpost&' +
// 'cmsid=496&vid=short_onecue&correlator=';


//adx
// adsRequest.adTagUrl='https://googleads.g.doubleclick.net/pagead/ads?client=ca-video-pub-3285741363974337&slotname=5404051226&ad_type=video&description_url=https%3A%2F%2Fwww.google.com&max_ad_duration=30000&videoad_start_delay=0&vpmute=0&vpa=0';

adsRequest.linearAdSlotWidth = 340;
adsRequest.linearAdSlotHeight = 100;
adsRequest.nonLinearAdSlotWidth = 640;
adsRequest.nonlinearAdSlotHeight = 150;


// 按钮控制
var playButton = document.getElementById('playButton');
playButton.addEventListener('click', requestAds);

function requestAds() {
    adsLoader.requestAds(adsRequest);
}


function onMediaLoaded(adEvent) {
    console.log("PreLoaded Event trigger:" + adEvent.toString());
}


function onAdsManagerLoaded(adsManagerLoadedEvent) {

    var adsRenderingSettings = new google.ima.AdsRenderingSettings();
    adsRenderingSettings.enablePreloading = false;

    // Get the ads manager.
    adsManager = adsManagerLoadedEvent.getAdsManager(
        videoContent, adsRenderingSettings);  // See API reference for contentPlayback

    // Add listeners to the required events.
    // adsManager.addEventListener(
    //     google.ima.AdErrorEvent.Type.AD_ERROR,
    //     onAdError);
    console.log('1. adsManager Constructed!');

    // CONTENT_PAUSE_REQUESTED事件会被sdk框架在ads开始播放时自动调用
    adsManager.addEventListener(
        google.ima.AdEvent.Type.CONTENT_PAUSE_REQUESTED,
        onContentPauseRequested);

    //CONTENT_RESUME_REQUESTED事件是在一个ads播放完毕后（playback complete之后）被自动调用
    adsManager.addEventListener(
        google.ima.AdEvent.Type.CONTENT_RESUME_REQUESTED,
        onContentResumeRequested);



    adsManager.addEventListener(google.ima.AdEvent.Type.LOADED, onMediaLoaded, false);


    adsManager.addEventListener(
        google.ima.AdEvent.Type.ALL_ADS_COMPLETED,onAllAdsComplete,false
    );

    console.log('2. adsManager LOADED Event Added!');


    // Initialize the ads manager. Ad rules playlist will start at this time.
    adsManager.init(200, 120, google.ima.ViewMode.NORMAL);
    console.log('3. adsManager Init');

    try {
        setTimeout(playAds, AD_INTERVAL * 1000);
        // videoContent.play();

    } catch (adError) {
        // An error may be thrown if there was a problem with the VAST response.
        // Play content here, because we won't be getting an ad.
        console.log(adError);
        // videoContent.play();
    }
}

function onContentPauseRequested() {
    // This function is where you should setup UI for showing ads (e.g.
    // display ad timer countdown, disable seeking, etc.)
    videoContent.removeEventListener('ended', contentEndedListener);
    // videoContent.pause();
    console.log("Stream Paused!")
}

function onContentResumeRequested() {
    // This function is where you should ensure that your UI is ready
    // to play content.
    videoContent.addEventListener('ended', contentEndedListener);
    // videoContent.play();
    console.log('Stream Resumed!')
}

function playAds() {

    // Call start to show ads. Single video and overlay ads will
    // start at this time; this call will be ignored for ad rules, as ad rules
    // ads start when the adsManager is initialized.
    adsManager.start();
    console.log('4. adsManager Started');

}


function onAllAdsComplete(){
    console.log('All Ads Completed');

}

function onAdError(adErrorEvent) {
    console.log('Error:' + adErrorEvent.h.l)
}
