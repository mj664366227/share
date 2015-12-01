<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="gu" uri="https://gatherup.cc" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html data-role='none'>
<head lang="en">
<meta charset="UTF-8">
<title>微信</title>
<meta name='MobileOptimized' content='640'>
<meta name="viewport" content="target-densitydpi=device-dpi,user-scalable=no, width=640px" />
<link type="text/css" rel="stylesheet" href="/mobile/default/groupTalk/style/index.css">
</head>
<body>
<div class="top"></div>
<div class="container w" id="container">
  <div class="loading" id="loading">
    <div class="loadBox">
      <div class="logo"><img src="/mobile/default/groupTalk/images/logo.png"></div>
      <div class="loadBarBox">
        <div class="loadBar" id="loadBar" style="width:0%"></div>
      </div>
      <div class="text" id="loadTips">Please wait,loading...<span id="loadTotal">0%</span></div>
    </div>
  </div>
  <div class="content" id="content">
    <div id="page1" class="div page page1 w none">
      <div class="s1">${gu:date('HH:mm',now)}</div>
      <div class="s2">${gu:date('MM月dd日',now)}&nbsp;${week}</div>
      <div class="s3">
        <ul class="clearfix">
          <li><img src="/mobile/default/groupTalk/images/email_icon.png"></li>
          <li>邮箱 <span>现在</span><br>
            史上最强的Brief，你敢接么？<br>
            <span>滑动来查看</span></li>
        </ul>
      </div>
      <div class="s s4"><img class="w100" src="/mobile/default/groupTalk/images/2.png"></div>
      <div class="s s5" id="unlock"></div>
    </div>
    <div id="page2" class="div page page2 none">
      <div class="s1">
        <ul>
          <li>史上最强的Brief，你敢接么？</li>
          <li>发件人：杜X斯市场部</li>
        </ul>
      </div>
      <div class="s2">杜X斯套套昵称包装全新上市，你有什么好段子吗？</div>
      <div class="s3"><img class="w100" src="/mobile/default/groupTalk/images/5.jpg"></div>
      <div class="s4">还记得写着“白富美“、”萝莉控“的可乐瓶吗？继可口可乐的“昵称瓶”和“歌词瓶”风靡市场后，个性化包装风潮依旧继续，现在杜X斯套套也想在包装上加点互动元素，你有什么好建议呢？</div>
      <div class="s5 clearfix">
        <div class="help1 fl"><img src="/mobile/default/groupTalk/images/7_1.png"></div>
        <div class="help2 fr"><img src="/mobile/default/groupTalk/images/7_3.png"></div>
        <div class="direct_bg"></div>
        <div id="direct01" class="direct"><a href="javascript:void(0);"></a></div>
      </div>
    </div>
    <div id="page3" class="div page page3 none">
      <div class="s s1">
        <div class="clearfix pr" style="padding-bottom:20px;">
          <div class="myAvatar fl"> <img class="w100" src="${headimgurl}"> <span>${nickname}</span> </div>
          <div class="addMum fl"><img class="w100" src="/mobile/default/groupTalk/images/9.png"></div>
          <div class="direct_bg"></div>
          <div id="direct02" class="direct"><a href="javascript:void(0);"></a></div>
        </div>
        <div class="m m1">
          <div class="jian">全部群成员（1）</div>
        </div>
      </div>
      <div class="s s2">
        <div class="m m1">
          <div class="jian">创意共做室｜头脑疯会</div>
        </div>
        <div class="m m1">
          <div class="jian">群二维码</div>
        </div>
        <div class="m m1 m2">
          <div class="jian">群公告<br>
            <span>创意聚集</span></div>
        </div>
      </div>
      <div class="s2">
        <div class="m1 m3">
          <div class="lock">消息免打扰</div>
        </div>
        <div class="m1 m3">
          <div class="unlock">置顶聊天</div>
        </div>
        <div class="m1 m2 m3">
          <div class="lock">保存到通讯录</div>
        </div>
      </div>
    </div>
    <div id="page4" class="page page4 none">
      <div class="s1"></div>
      <div class="s2"></div>
      <div class="s3">
        <dl>
          <dd class="list list1">
            <ul class="clearfix">
              <li><img src="/mobile/default/groupTalk/images/17_1.jpg"></li>
              <li>庄淑芬｜广告女王</li>
            </ul>
          </dd>
          <dd class="list list2">
            <ul class="clearfix">
              <li><img src="/mobile/default/groupTalk/images/17_2.jpg"></li>
              <li>老金｜环时互动CKO</li>
            </ul>
          </dd>
          <dd class="list list3">
            <ul class="clearfix">
              <li><img src="/mobile/default/groupTalk/images/17_3.jpg"></li>
              <li>雷军｜小米CEO</li>
            </ul>
          </dd>
          <dd class="list list4">
            <ul class="clearfix">
              <li><img src="/mobile/default/groupTalk/images/17_4.jpg"></li>
              <li>王尼玛｜暴走漫画主编</li>
            </ul>
          </dd>
          <dd class="list list5">
            <ul class="clearfix">
              <li><img src="/mobile/default/groupTalk/images/17_5.jpg"></li>
              <li>顾爷｜创意鬼才</li>
            </ul>
          </dd>
          <dd class="list list6">
            <ul class="clearfix">
              <li><img src="/mobile/default/groupTalk/images/17_6.jpg"></li>
              <li>罗振宇｜罗辑思维创始人</li>
            </ul>
          </dd>
        </dl>
      </div>
      <div class="s4">
        <div class="btn" id="SelectAll"><a href="javascript:void(0)">确认</a></div>
      </div>
    </div>
    <div id="page5" class="page page5 none">
      <div class="s1">
        <div class="m1">你邀请庄淑芬｜广告女王、老金｜环时互动CKO、雷军｜小米CEO、王尼玛｜暴走漫画主编、顾爷｜创意鬼才、罗振宇｜罗辑思维创始人加入群聊</div>
      </div>
      <div class="s2">
        <div class="list m m2 clearfix">
          <div class="avatar fl"><img src="/mobile/default/groupTalk/images/17_3.jpg"></div>
          <div class="name">雷军｜小米CEO</div>
          <div class="sayCon fl">
            <div>Hello，Are you ok？</div>
            <div class="angle"></div>
          </div>
        </div>
        <div class="list m m3 clearfix">
          <div class="avatar fr"><img src="${headimgurl}"></div>
          <div class="sayCon fr">
            <p>哈哈，not ok阿，各位大大，杜X斯套套昵称包装全新上市，你有什么好段子吗？</p>
            <div class="angle"></div>
          </div>
        </div>
        <div class="list m m2 clearfix">
          <div class="avatar fl"><img src="/mobile/default/groupTalk/images/17_4.jpg"></div>
          <div class="name">王尼玛｜暴走漫画主编</div>
          <div class="sayConImg fl"><img src="/mobile/default/groupTalk/images/21.jpg"></div>
        </div>
        <div class="list m m2 clearfix">
          <div class="avatar fl"><img src="/mobile/default/groupTalk/images/17_2.jpg"></div>
          <div class="name">老金｜环时互动CKO</div>
          <div class="sayCon fl">
            <div>结合网络红词来玩，“憋不住了”、“爆炸糖”、“来虐我”</div>
            <div class="angle"></div>
          </div>
        </div>
        <div class="list m m2 clearfix">
          <div class="avatar fl"><img src="/mobile/default/groupTalk/images/17_5.jpg"></div>
          <div class="name">顾爷｜创意鬼才</div>
          <div class="sayCon fl">
            <div>可以推出“角色包”系列：“一夜7次郎”、“金牌快枪手”、“持久帝”</div>
            <div class="angle"></div>
          </div>
        </div>
        <div class="list m m2 clearfix">
          <div class="avatar fl"><img src="/mobile/default/groupTalk/images/17_6.jpg"></div>
          <div class="name">罗振宇｜罗辑思维创始人</div>
          <div class="sayCon fl">
            <div>可以做一个罗胖XXL特别系列。。死磕自个，愉悦大家。</div>
            <div class="angle"></div>
          </div>
        </div>
        <div class="list m m2 clearfix">
          <div class="avatar fl"><img src="/mobile/default/groupTalk/images/17_1.jpg"></div>
          <div class="name">庄淑芬｜广告女王</div>
          <div class="sayCon fl">
            <div>我认识很多业内广告人都在共做室，你可以上去找更多的创意灵感</div>
            <div class="angle"></div>
          </div>
        </div>
        <div class="list m m2 clearfix">
          <div class="avatar fl"><img src="/mobile/default/groupTalk/images/17_1.jpg"></div>
          <div class="name">庄淑芬｜广告女王</div>
          <div class="sayCon fl">
            <div> <a href="javascript:void(0)" id="page6Case">
              <p>据说脑洞不够的人都不敢点开的</p>
              <div class="mm1 clearfix"> <img class="fl" src="/mobile/default/groupTalk/images/20.jpg">
                <p>这里真的有庄淑芬吗？</p>
              </div>
              </a> </div>
            <div class="angle"></div>
          </div>
        </div>
      </div>
    </div>
    <div id="page6" class="page page6 none">
      <div class="header">
        <ul class="clearfix">
          <li class="logo"><a class="alertBox" href="https://itunes.apple.com/us/app/gong-zuo-shi/id1021533113">Gatherup共做室</a></li>
          <li class="comment"><a class="alertBox" href="https://itunes.apple.com/us/app/gong-zuo-shi/id1021533113">发表评论</a></li>
          <li class="openApp"><a class="alertBox" href="https://itunes.apple.com/us/app/gong-zuo-shi/id1021533113">打开APP</a></li>
        </ul>
      </div>
      <div class="s1">
        <div class="caseTitle">
          <p>杜X斯套套昵称包装全新上市，你有什么好段子吗？</p>
        </div>
        <div class="caseImgs"><img src="/mobile/default/groupTalk/images/5.jpg"></div>
        <div class="caseCon">还记得写着“白富美“、”萝莉控“的可乐瓶吗？继可口可乐的“昵称瓶”和“歌词瓶”风靡市场后，个性化包装风潮依旧继续，现在杜X斯套套也想在包装上加点互动元素，你有什么好建议呢？</div>
        <div class="ideas">
          <div class="ideasTitle">
            <p>创意点子</p>
          </div>
          <div class="ideasList">
            <div class="ideasItem pr">
              <div class="rank rank_01 pa"></div>
              <div class="avatar clearfix pr">
                <div class="img pa"><img src="${headimgurl}"></div>
                <div class="name">
                  <p class="text">${nickname}</p>
                  <p class="info">创意达人</p>
                </div>
              </div>
              <div class="ideaCont">射射射！简单粗暴的包装，形成强烈视觉冲击.吸引大众的眼球</div>
              <div class="ideaFeed clearfix">
                <div class="editTime fl">编辑于 12小时前</div>
                <div class="praiseNum fl">4赞</div>
                <div class="commentNum fl">12评论</div>
              </div>
            </div>
            <div class="ideasItem pr">
              <div class="rank rank_02 pa"></div>
              <div class="avatar clearfix pr">
                <div class="img pa"><img src="/mobile/default/groupTalk/images/17_2.jpg"></div>
                <div class="name">
                  <p class="text">老金</p>
                  <p class="info">环时互动CKO</p>
                </div>
              </div>
              <div class="ideaCont">结合网络红词来玩，“憋不住了”、“爆炸糖”、“来虐我”</div>
              <div class="ideaFeed clearfix">
                <div class="editTime fl">编辑于 12小时前</div>
                <div class="praiseNum fl">4赞</div>
                <div class="commentNum fl">12评论</div>
              </div>
            </div>
            <div class="ideasItem pr">
              <div class="rank rank_03 pa"></div>
              <div class="avatar clearfix pr">
                <div class="img pa"><img src="/mobile/default/groupTalk/images/17_5.jpg"></div>
                <div class="name">
                  <p class="text">老金</p>
                  <p class="info">环时互动CKO</p>
                </div>
              </div>
              <div class="ideaCont">可以推出“角色包”系列，、“一夜7次郎”“金牌快枪手”、“持久帝”</div>
              <div class="ideaFeed clearfix">
                <div class="editTime fl">编辑于 12小时前</div>
                <div class="praiseNum fl">4赞</div>
                <div class="commentNum fl">12评论</div>
              </div>
            </div>
            <div class="ideasItem pr">
              <div class="avatar clearfix pr">
                <div class="img pa"><img src="/mobile/default/groupTalk/images/17_6.jpg"></div>
                <div class="name">
                  <p class="text">罗振宇</p>
                  <p class="info">罗辑思维创始人</p>
                </div>
              </div>
              <div class="ideaCont">可以做一个罗胖XXL特别系列。。死磕自个，愉悦大家。</div>
              <div class="ideaFeed clearfix">
                <div class="editTime fl">编辑于 12小时前</div>
                <div class="praiseNum fl">4赞</div>
                <div class="commentNum fl">12评论</div>
              </div>
            </div>
            <div class="moreIdeas"><a class="alertBox" href="https://itunes.apple.com/us/app/gong-zuo-shi/id1021533113">更多</a></div>
            <div class="careCase"> <a class="alertBox" href="https://itunes.apple.com/us/app/gong-zuo-shi/id1021533113" class="clearfix"> <span class="carebtn">关注专案</span> <span class="careNum">23839 人关注该问题</span> </a> </div>
            <div class="author pr">
              <div class="aboutAuthor">关于企业</div>
              <div class="img pa"><img src="/mobile/default/groupTalk/images/5_1.jpg"></div>
              <div class="name">
                <p class="text">杜X斯</p>
                <p class="info">爱爱爱爱</p>
              </div>
              <div class="care pa"><a class="alertBox" href="https://itunes.apple.com/us/app/gong-zuo-shi/id1021533113">关注</a></div>
            </div>
            <div class="downApp pr"> <a class="alertBox" href="https://itunes.apple.com/us/app/gong-zuo-shi/id1021533113">
              <div class="appLogo pa"><img src="/mobile/default/groupTalk/images/logo_lg.jpg"></div>
              <div class="name">
                <p class="text">共做室客户端</p>
                <p class="info">下载并加入共做室，随时随地为品牌出谋划策，获得金钱奖励。</p>
                <p class="more">查看详情&nbsp;&gt;&gt;</p>
              </div>
              </a> </div>
          </div>
        </div>
      </div>
      <div class="footBar">
        <div class="m m1 pa"><a class="alertBox" href="https://itunes.apple.com/us/app/gong-zuo-shi/id1021533113">玩创意送G点</a> </div>
        <div id="shareBtn"  class="m m2 pa"><a class="alertBox" href="https://itunes.apple.com/us/app/gong-zuo-shi/id1021533113">分享我的创意圈</a> </div>
      </div>
    </div>
  </div>
</div>
<div class="guide clearfix none"><img class="fr" src="/mobile/default/images/guide.png"></div>
<div class="footer"></div>
</body>
<script type="text/javascript" src="/mobile/default/groupTalk/js/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="/mobile/default/groupTalk/js/jquery.mobile.custom.min.js"></script>
<script type="text/javascript" src="/mobile/default/groupTalk/js/index.js"></script>
<script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
<script>
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
                    ',user-scalable=no, width=640px');
        }
    });
	
var wxShare= {
	 title:'史上最强的Brief，你敢接么？',
	 desc:'有一个土豪群在聊天',
	 link:'${link}',
	 imgUrl:'http://gatherup.cc/mobile/default/groupTalk/images/17_1.jpg'
 }

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
</html>