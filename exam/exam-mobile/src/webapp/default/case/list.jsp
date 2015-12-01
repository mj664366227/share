<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="gu" uri="https://gatherup.cc" %>
<%@include file="../head.jsp" %>
   <style>
        /*reset*/
        body,div,a,span,ul,ol,li,h1,h2,h3,h4,h5,h6,fieldset,p,blockquote{ margin: 0; padding: 0; font:14px/1.5 "Helvetica Neue","Helvetica","Arial","Microsoft Yahei","Hiragino Sans GB","Heiti SC","WenQuanYi Micro Hei",sans-serif;-webkit-font-smoothing:antialiased; }
        table{ border-collapse:collapse; border-spacing:0; }
        button,input,textarea,fieldset{ padding:0;*overflow:visible;}
        input,textarea{outline: none;resize:none;}
        fieldset,img{ border:0; }
        address,caption,cite,code,dfn,em,strong,th,var{ font-style:normal; font-weight:normal; }
        ol,ul,li{ list-style:none; }
        caption,th{ text-align:left; }
        h1,h2,h3,h4,h5,h6{ font-size:100%; }
        q:before,q:after{ content:''; }
        abbr,acronym{ border:0; }
        label{ cursor:pointer; }
        legend{ margin-left:0; }*+html legend{ margin-left:-7px;  margin-top:-7px; padding-bottom:10px; }*html legend{ margin-left:-7px;  margin-top:-7px;  padding-bottom:10px; }
        a{ text-decoration:none; outline: none; -webkit-tap-highlight-color:transparent;}
        /*reset end*/

        .w{width:640px;margin:0 auto;}
        .clearfix:after{ visibility: hidden; display: block; font-size: 0; content: " "; clear: both; height: 0; }
        .clearfix{*zoom:1;}
        .fl{float:left;display:inline;}
        .fr{float:right;display:inline;}
        .pr{position:relative;}
        .pa{position:absolute;}

        body{position:relative;}
        header{position:fixed;top:0;left:0;width:100%;background:#fe8445;z-index: 1000;}
        header ul{width:640px;margin:0 auto;}
        header li{float:left;width:213px;height:88px;box-sizing:border-box;}
        header li:nth-child(2){width:214px;}
        header li a{height:88px;line-height:88px;display:block;color:#fff;font-size:24px;}
        header li:first-child a{height:88px;background:url("${skin}/images/logo.png") no-repeat center center;text-indent:-9999px;border-right:1px solid #d8703b;}
        header li:nth-child(2) a{background:url("${skin}/images/basic.png") no-repeat 35px 30px;border-left:1px solid #fe9661;border-right:1px solid #d8703b;padding-left:70px;}
        header li:last-child a{background:url("${skin}/images/basic.png") no-repeat 35px -31px;border-left:1px solid #fe9661;padding-left:70px;}
        .container{box-sizing:border-box;padding:100px 15px 0;}
        .caseTitle{width:610px;line-height:1.8;font-size:24px;color:#fe8445;}
        .caseImgs{padding-bottom:15px;width:610px;overflow:hidden;}
        .caseImgs img{display:block;height:400px;}
        .caseCon{width:610px;padding-bottom:15px;border-bottom:1px solid #ddd;}
        .caseCon .cont{font-size:20px;line-height:30px;height:120px;overflow:hidden;}
        .ideasItem{border-bottom:1px solid #ddd;}
        .ideasTitle p{height:84px;line-height:84px;font-size:24px;color:#333;border-bottom:1px solid #ddd;}
        .avatar{padding:15px 0;}
        .avatar .img{width:60px;height:60px;-webkit-border-radius: 50%;-moz-border-radius: 50%;border-radius: 50%;overflow:hidden;}
        .avatar .img img{display:block;width:100%;height:100%;}
        .avatar .name{padding-left:70px;}
        .avatar .name .nameChild{height:30px;line-height:30px;}
        .avatar .name .text{font-size:20px;}
        .avatar .name .info{font-size:16px;color:#fba94d;}
        .ideaCont{width:610px;color:#333;margin-bottom:10px;}
        .ideaCont .cont{font-size:20px;line-height:30px;height:120px;overflow:hidden;}
        .ideaImgs{margin-bottom:10px;}
        .ideaImgs img{display:block;height:280px;}
        .ideaFeed{padding-bottom:10px;}
        .ideaFeed > div{margin-right:20px;color:#b2b2b2;height:24px;line-height:24px;font-size:16px;}
        .praiseNum{background:url("${skin}/images/basic.png") no-repeat 0 -154px;padding-left:26px;}
        .commentNum{background:url("${skin}/images/basic.png") no-repeat 0 -114px;padding-left:26px;}
        .rank{top:0;right:0;background:url("${skin}/images/basic.png") no-repeat 0 0;width:40px;height:64px;}
        .rank_01{background-position:0 -206px;}
        .rank_02{background-position:-55px -206px;}
        .rank_03{background-position:-110px -206px;}
        .moreIdeas{padding:40px 0;}
        .moreIdeas a{display: block;width:100%;height:50px;line-height:50px;text-align: center;box-sizing: border-box;border:1px solid #b3b3b3;background:#e6e6e6;color:#a0a0a0;font-size:20px;border-radius: 5px;}
        .careCase{padding-bottom:40px;border-bottom:1px solid #ddd;height:50px;}
        .careCase a{height:50px;}
        .careCase span{float:left;height:50px;line-height:50px;font-size:20px;}
        .carebtn{width:170px;color:#fff;background:#8955ff;text-align:center;margin-right:30px;
            -webkit-border-radius: 5px;
            -moz-border-radius: 5px;
            border-radius: 5px;}
        .careNum{color:#a0a0a0;}
        .author{padding:40px 0;border-bottom:1px solid #ddd;}
        .author .img{width:90px;height:90px;}
        .author .img img{display:block;width:100%;}
        .author .name{padding-left:100px;padding-right:140px;}
        .author .name p{height:45px;line-height:45px;}
        .author .name p.text{font-size:24px;color:#fe8445;}
        .author .name p.info{font-size:20px;color:#999;}
        .author .care{right:0;top:62px;}
        .author .care a{width:138px;height:44px;line-height:44px;text-align:center;font-size:20px;background:#8955ff;color: #fff;display:block;-webkit-border-radius: 5px;-moz-border-radius: 5px;border-radius: 5px;}
        .downApp a{display: block;width:100%;padding:40px 0;}
        .downApp .appLogo{height:150px;width:150px;overflow: hidden;}
        .downApp .appLogo img{display:block;width:100%;height:100%;}
        .downApp .name{padding-left:170px;padding-top:20px;}
        .downApp .name .text{font-size:30px;font-weight: bold;color:#333;}
        .downApp .name .info{font-size:20px;color:#a0a0a0;}
        .downApp .name .more{font-size:24px;color:#fe8445;}
        .aboutAuthor{font-size:20px;height:30px;line-height:30px;font-weight:bold;margin-bottom:10px;color:#666;}
        .guide{position:fixed;top:0;bottom:0;left:0;right:0;background:rgba(0,0,0,0.85);z-index:2005;display:none;}
    </style>
    <title>专案分享 - ${case.name}</title>
</head>
<body>
<header>
    <ul class="clearfix">
        <li class="logo"><a class="alertBox" href="https://itunes.apple.com/us/app/gong-zuo-shi/id1021533113">Gatherup共做室</a></li>
        <li class="comment"><a class="alertBox" href="https://itunes.apple.com/us/app/gong-zuo-shi/id1021533113">发表评论</a></li>
        <li class="openApp"><a class="alertBox" href="https://itunes.apple.com/us/app/gong-zuo-shi/id1021533113">打开APP</a></li>
    </ul>
</header>
<div class="container w">
    <div class="caseTitle">${case.name}</div>
    <div class="caseImgs"><img src="${case.image1}"></div>
    <div class="caseCon">
    	<div class="cont">${case.description}</div>
    </div>
    <div class="ideas">
        <div class="ideasTitle">
            <p>${gu:numberFormat(case.ideaNum)}&nbsp;个点子</p>
        </div>
        <div class="ideasList">
        <c:set var="i" value="0"/>
        <c:forEach items="${ideaList}" var="idea">
        <c:set var="i" value="${i+1}"/>
            <div class="ideasItem pr">
                <div class="rank rank_0${i} pa"></div>
                <div class="avatar clearfix pr">
                    <div class="img pa"><img src="${idea.avatarImage}"></div>
                    <div class="name">
                        <div class="nameChild text">${idea.nickname}</div>
                        <div class="nameChild info">${idea.identity}</div>
                    </div>
                </div>
                <div class="ideaCont">
                	<div class="cont">${idea.content}</div>
                </div>
                <c:if test="${idea.imageList.size()>0}">
                
                <c:if test="${not empty idea.imageList.get(0)}"><div class="ideaImgs"><img src="${idea.imageList.get(0)}"></div></c:if>
                
                </c:if>
                <div class="ideaFeed clearfix">
                    <div class="editTime fl">编辑于&nbsp;&nbsp;${gu:createTimeFormat(idea.createTime)}</div>
                    <div class="praiseNum fl">${gu:numberFormat(idea.praise)}&nbsp;赞</div>
                    <div class="commentNum fl">${gu:numberFormat(idea.ideaCommentCount)}&nbsp;评论</div>
                </div>
            </div>
            </c:forEach>
        
            
           
           
            <div class="moreIdeas"><a class="alertBox" href="https://itunes.apple.com/us/app/gong-zuo-shi/id1021533113">更多</a></div>
            <div class="careCase">
                <a class="alertBox" href="https://itunes.apple.com/us/app/gong-zuo-shi/id1021533113" class="clearfix">
                    <span class="carebtn">关注专案</span>
                    <span class="careNum">${gu:numberFormat(case.fansNum)}&nbsp;人关注该问题</span>
                </a>
            </div>
        </div>
    </div>
    <div class="author pr">
    	<div class="aboutAuthor">关于企业</div>
        <div class="img pa"><img src="${company.logoImage}"></div>
        <div class="name">
            <p class="text">${company.name}</p>
            <p class="info">${company.introduce}</p>
        </div>
        <div class="care pa"><a href="https://itunes.apple.com/us/app/gong-zuo-shi/id1021533113">关注</a></div>
    </div>
    <div class="guide clearfix none"><img class="fr" src="/mobile/default/images/guide.png"></div>
    <script type="text/javascript">
     var wxShare= {
		 title:'专案分享 - ${case.name}',
		 desc:'${gu:filtString(case.description)}',
		 link:'${link}',
		 imgUrl:'${case.image1}'
     }
    </script>
<%@include file="../foot.jsp" %>