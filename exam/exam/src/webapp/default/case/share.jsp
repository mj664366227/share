<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="gu" uri="https://gatherup.cc" %>
<%@include file="../head.jsp" %>
<style>
body, div, a, span, ul, ol, li, h1, h2, h3, h4, h5, h6, fieldset, p, blockquote {
	margin: 0;
	padding: 0;
	font: 14px/1.5 "Helvetica Neue", "Helvetica", "Arial", "Microsoft Yahei", "Hiragino Sans GB", "Heiti SC", "WenQuanYi Micro Hei", sans-serif;
	-webkit-font-smoothing: antialiased;
}
table {
	border-collapse: collapse;
	border-spacing: 0;
}
button, input, textarea, fieldset {
	padding: 0;
*overflow:visible;
}
input, textarea {
	outline: none;
	resize: none;
}
fieldset, img {
	border: 0;
}
address, caption, cite, code, dfn, em, strong, th, var {
	font-style: normal;
	font-weight: normal;
}
ol, ul, li {
	list-style: none;
}
caption, th {
	text-align: left;
}
h1, h2, h3, h4, h5, h6 {
	font-size: 100%;
}
q:before, q:after {
	content: '';
}
abbr, acronym {
	border: 0;
}
label {
	cursor: pointer;
}
legend {
	margin-left: 0;
}
*+html legend {
	margin-left: -7px;
	margin-top: -7px;
	padding-bottom: 10px;
}
*html legend {
	margin-left: -7px;
	margin-top: -7px;
	padding-bottom: 10px;
}
a {
	text-decoration: none;
	outline: none;
	-webkit-tap-highlight-color: transparent;
}
.w {
	width: 640px;
	margin: 0 auto;
}
.clearfix:after {
	visibility: hidden;
	display: block;
	font-size: 0;
	content: " ";
	clear: both;
	height: 0;
}
.clearfix {
*zoom:1;
}
.fl {
	float: left;
	display: inline;
}
.fr {
	float: right;
	display: inline;
}
.pr {
	position: relative;
}
.pa {
	position: absolute;
}
body {
	position: relative;
}
header {
	position: fixed;
	top: 0;
	left: 0;
	width: 100%;
	background: #fe8445;
	z-index: 1000;
}
header ul {
	width: 640px;
	margin: 0 auto;
}
header li {
	float: left;
	width: 213px;
	height: 88px;
	box-sizing: border-box;
}
header li:nth-child(2) {
	width: 214px;
}
header li a {
	height: 88px;
	line-height: 88px;
	display: block;
	color: #fff;
	font-size: 24px;
}
header li:first-child a {
	height: 88px;
	background: url("../images/logo.png") no-repeat center center;
	text-indent: -9999px;
	border-right: 1px solid #d8703b;
}
header li:nth-child(2) a {
	background: url("../images/basic.png") no-repeat 35px 30px;
	border-left: 1px solid #fe9661;
	border-right: 1px solid #d8703b;
	padding-left: 70px;
}
header li:last-child a {
	background: url("../images/basic.png") no-repeat 35px -31px;
	border-left: 1px solid #fe9661;
	padding-left: 70px;
}
.container {
	box-sizing: border-box;
	padding: 100px 15px 0;
	height: 100px;
}
.caseTitle {
	width: 610px;
	line-height: 1.8;
	font-size: 24px;
	color: #fe8445;
}
.caseImgs {
	padding-bottom: 15px;
	width: 610px;
	overflow: hidden;
}
.caseImgs img {
	display: block;
	height: 400px;
}
.caseCon {
	width: 610px;
	padding-bottom: 15px;
	border-bottom: 1px solid #ddd;
	line-height: 1.5;
	font-size: 20px;
}
.ideaTitle p {
	font-size: 24px;
	line-height: 1.8;
}
.ideaImgs {
	padding-bottom: 15px;
}
.ideaImgs img {
	display: block;
	height: 240px;
}
.ideaCont {
	width: 610px;
	color: #333;
	line-height: 1.8;
	margin-bottom: 10px;
	font-size: 16px;
}
.remarksItem {
	border-bottom: 1px solid #ddd;
}
.remarksItem.nob {
	border: 0;
	margin-bottom: 20px;
}
.remarksTitle {
	height: 84px;
	line-height: 84px;
	font-size: 24px;
	color: #333;
	border-bottom: 1px solid #ddd;
}
.remarksTitle a {
	font-size: 24px;
	color: #fe8445;
}
.avatar {
	padding: 15px 0;
}
.avatar .img {
	width: 60px;
	height: 60px;
	-webkit-border-radius: 50%;
	-moz-border-radius: 50%;
	border-radius: 50%;
}
.avatar .img img {
	display: block;
	width: 100%;
	height: 100%;
}
.avatar .name {
	padding-left: 70px;
}
.avatar .name p {
	height: 30px;
	line-height: 30px;
}
.avatar .name p.text {
	font-size: 20px;
}
.avatar .name p.info {
	font-size: 16px;
	color: #fba94d;
}
.remarkCont {
	font-size: 20px;
	color: #333;
	line-height: 1.5;
	height: 120px;
	overflow: hidden;
	margin-bottom: 10px;
}
.remarkTime {
	height: 40px;
	line-height: 40px;
	font-size: 14px;
	color: #b2b2b2;
}
.moreRemarks {
	padding: 40px 0;
	border-bottom: 1px solid #ddd;
}
.moreRemarks a {
	display: block;
	width: 100%;
	height: 50px;
	line-height: 50px;
	text-align: center;
	box-sizing: border-box;
	border: 1px solid #b3b3b3;
	background: #e6e6e6;
	color: #a0a0a0;
	font-size: 20px;
	border-radius: 5px;
}
.author {
	padding: 20px 0 40px;
	border-bottom: 1px solid #ddd;
}
.author .img {
	width: 90px;
	height: 90px;
}
.author .img img {
	display: block;
	width: 100%;
}
.author .name {
	padding-left: 100px;
	padding-right: 140px;
}
.author .name p {
	height: 45px;
	line-height: 45px;
}
.author .name p.text {
	font-size: 24px;
	color: #fe8445;
}
.author .name p.info {
	font-size: 20px;
	color: #999;
}
.author .care {
	right: 0;
	top: 62px;
}
.author .care a {
	width: 138px;
	height: 44px;
	line-height: 44px;
	text-align: center;
	font-size: 20px;
	background: #8955ff;
	color: #fff;
	display: block;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	border-radius: 5px;
}
.downApp a {
	display: block;
	width: 100%;
	padding: 40px 0;
}
.downApp .appLogo {
	height: 150px;
	width: 150px;
	overflow: hidden;
}
.downApp .appLogo img {
	display: block;
	width: 100%;
	height: 100%;
}
.downApp .name {
	padding-left: 170px;
}
.downApp .name .text {
	font-size: 30px;
	font-weight: bold;
	color: #333;
}
.downApp .name .info {
	font-size: 20px;
	color: #a0a0a0;
}
.downApp .name .more {
	font-size: 24px;
	color: #fe8445;
}
.ideaFeed {
	padding-bottom: 10px;
}
.ideaFeed > div {
	margin-right: 20px;
	color: #b2b2b2;
	height: 24px;
	line-height: 24px;
	font-size: 16px;
}
.praiseNum{background:url("${skin}/images/basic.png") no-repeat 0 -154px;padding-left:26px;}
 .commentNum{background:url("${skin}/images/basic.png") no-repeat 0 -114px;padding-left:26px;}
.moreRemarksTitle {
	border-bottom: 1px solid #ddd;
	height: 30px;
}
.moreRemarksTitle span {
	font-size: 20px;
	color: #999;
	bottom: -16px;
	background: #fff;
	padding: 0 20px;
	left: 245px;
}
.aboutAuthor {
	font-size: 20px;
	height: 30px;
	line-height: 30px;
	font-weight: bold;
	margin-bottom: 10px;
	color: #666;
}
.guide{position:fixed;top:0;bottom:0;left:0;right:0;background:rgba(0,0,0,0.85);z-index:2005;display:none;}
</style>
<title>创意分享 - ${case.name}</title>
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
  <c:if test="${not empty case.image1}">
    <div class="caseImgs"><img src="${case.image1}"></div>
  </c:if>
  <div class="caseCon">${case.description}</div>
  <div class="remarks">
    <div class="remarksTitle"><a class="alertBox" href="https://itunes.apple.com/us/app/gong-zuo-shi/id1021533113">查看全部${gu:numberFormat(case.ideaNum)}个创意</a></div>
    <div class="remarksList">
      <div class="remarksItem nob">
        <div class="avatar clearfix pr">
          <c:if test="${not empty user.avatarImage}">
            <div class="img pa"><img src="${user.avatarImage}"></div>
          </c:if>
          <div class="name">
            <p class="text">${user.nickname}</p>
            <p class="info">${user.identity}</p>
          </div>
        </div>
        <div class="ideaCont">${fn:replace(idea.content,'','')}</div>
        <c:if test="${not empty idea.image1}">
          <div class="ideaImgs"><img src="${idea.image1}"></div>
        </c:if>
        <div class="ideaFeed clearfix">
          <div class="editTime fl">编辑于&nbsp;&nbsp;${gu:createTimeFormat(idea.createTime)}</div>
          <div class="praiseNum fl">${gu:numberFormat(idea.praise)}&nbsp;赞</div>
          <div class="commentNum fl">${gu:numberFormat(idea.commentNum)}&nbsp;评论</div>
        </div>
      </div>
      <div class="moreRemarksTitle pr"><span class="pa">更多回答</span></div>
      <c:forEach items="${ideaList}" var="idea">
        <div class="remarksItem">
          <div class="avatar clearfix pr">
            <c:if test="${not empty idea.userBase.avatarImage}">
              <div class="img pa"><img src="${idea.userBase.avatarImage}"></div>
            </c:if>
            <div class="name">
              <p class="text">${idea.userBase.nickname}</p>
              <p class="info">${idea.userBase.identity}</p>
            </div>
          </div>
          <div class="ideaCont">${idea.content}</div>
          <div class="ideaFeed clearfix">
            <div class="editTime fl">编辑于&nbsp;&nbsp;${gu:createTimeFormat(idea.createTime)}</div>
            <div class="praiseNum fl">${gu:numberFormat(idea.praise)}&nbsp;赞</div>
            <div class="commentNum fl">${gu:numberFormat(idea.ideaCommentCount)}&nbsp;评论</div>
          </div>
        </div>
      </c:forEach>
      <div class="remarksTitle"><a class="alertBox" href="https://itunes.apple.com/us/app/gong-zuo-shi/id1021533113">查看全部${gu:numberFormat(case.ideaNum)}个创意</a></div>
    </div>
  </div>
  <div class="author pr">
    <div class="aboutAuthor">关于作者</div>
    <c:if test="${not empty company.logoImage}">
      <div class="img pa"><img src="${company.logoImage}"></div>
    </c:if>
    <div class="name">
      <p class="text">${company.name}</p>
      <p class="info">${company.introduce}</p>
    </div>
    <div class="care pa"><a class="alertBox" href="https://itunes.apple.com/us/app/gong-zuo-shi/id1021533113">关注</a></div>
  </div>
  <div class="guide clearfix none"><img class="fr" src="/mobile/default/images/guide.png"></div>
  <script type="text/javascript">
     var wxShare= {
		 title:'创意分享 - ${case.name}',
		 desc:'${gu:filtString(case.description)}',
		 link:'${link}',
		 imgUrl:'${case.image1}'
     }
    </script>
  <%@include file="../foot.jsp" %>