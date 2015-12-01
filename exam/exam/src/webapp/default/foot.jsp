<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <div class="downApp pr">
        <a href="https://itunes.apple.com/us/app/gong-zuo-shi/id1021533113">
            <div class="appLogo pa"><img src="${skin}/images/logo_lg.jpg"></div>
            <div class="name">
                <p class="text">共做室客户端</p>
                <p class="info">下载并加入共做室，随时随地为品牌出谋划策，获得金钱奖励。</p>
                <p class="more">查看详情&nbsp;&gt;&gt;</p>
            </div>
        </a>
    </div>
</div>
</body>
<script type="text/javascript" src="${skin}/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script type="text/javascript">
$(function () {
    var DEFAULT_WIDTH = 640, // 页面的默认宽度
            ua = navigator.userAgent.toLowerCase(), // 根据 user agent 的信息获取浏览器信息
            deviceWidth = window.screen.width, // 设备的宽度
            devicePixelRatio = window.devicePixelRatio || 1, // 物理像素和设备独立像素的比例，默认为1
            targetDensitydpi;

    // Android4.0以下手机不支持viewport的width，需要设置target-densitydpi
    if (ua.indexOf("android") !== -1 && parseFloat(ua.slice(ua.indexOf("android")+8)) < 4) {
        targetDensitydpi = DEFAULT_WIDTH / deviceWidth * devicePixelRatio * 160;
        $('meta[name="viewport"]').attr('content', 'target-densitydpi=' + targetDensitydpi +
                ', width=device-width, user-scalable=no');
    }
    $(".alertBox").click(function(){
    	if(is_weixin()){
            $(".guide").fadeIn();
        }
    });
    $(".guide").click(function(){
    	$(this).fadeOut();
    });
    function is_weixin() {
        var ua = navigator.userAgent.toLowerCase();
        if (ua.match(/MicroMessenger/i) == "micromessenger") {
            return true;
        } else {
            return false;
        }
    }
});

// wechat
wx.config({
    debug: false,
    appId: '${appId}',
    timestamp: ${timestamp},
    nonceStr: '${nonceStr}',
    signature: '${signature}',
    jsApiList: ['onMenuShareTimeline', 'onMenuShareAppMessage', 'onMenuShareQQ', 'onMenuShareWeibo', 'onMenuShareQZone']
});

wx.ready(function(){
    wx.onMenuShareAppMessage({
        title: wxShare.title,
        desc: wxShare.desc,
        link: wxShare.link,
        imgUrl: wxShare.imgUrl
    });
    wx.onMenuShareTimeline({
        title: wxShare.title,
        link: wxShare.link,
        imgUrl: wxShare.imgUrl
    });
    wx.onMenuShareQQ({
        title: wxShare.title,
        desc: wxShare.desc,
        link: wxShare.link,
        imgUrl: wxShare.imgUrl
    });
	wx.onMenuShareWeibo({
        title: wxShare.title,
        desc: wxShare.desc,
        link: wxShare.link,
        imgUrl: wxShare.imgUrl
    });
	wx.onMenuShareQZone({
        title: wxShare.title,
        desc: wxShare.desc,
        link: wxShare.link,
        imgUrl: wxShare.imgUrl
    });
});
</script>

<div style="display:none"><script type="text/javascript">var cnzz_protocol = (("https:" == document.location.protocol) ? " https://" : " http://");document.write(unescape("%3Cspan id='cnzz_stat_icon_1256382178'%3E%3C/span%3E%3Cscript src='" + cnzz_protocol + "s4.cnzz.com/z_stat.php%3Fid%3D1256382178' type='text/javascript'%3E%3C/script%3E"));</script></div>
</html>