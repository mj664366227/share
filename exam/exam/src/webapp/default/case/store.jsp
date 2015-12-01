<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@include file="../head.jsp" %>
<div class="container w">
  <div class="downCont">
    <div class="logo"><img src="${skin}/images/logo.png"></div>
    <p class="descApp02">您的<span>G</span>点已经发放到账号啦</p>
    <form method="post">
      <div class="getPhone">
        <input type="text" value="" placeholder="快输入手机号！">
      </div>
      <input id="getPhoneInputHid" type="hidden" value="" name="mobile">
      <div class="downAppBtns">
      <div class="iosBtn downBtn">
        <button type="submit" id="submitGet"> 确定领取 </button>
      </div>
    </form>
  </div>
</div>
</div>
<%@include file="../foot.jsp" %>
<c:if test="${result==0}">
  <script type="text/javascript">
 	$(function(){
		window.location.href='downapp';
	})
 </script>
</c:if>

<c:if test="${result==1}">
  <script type="text/javascript">
 	$(function(){
		alert('请输入正确的手机号！');
	})
 </script>
</c:if>

<c:if test="${result==2}">
  <script type="text/javascript">
 	$(function(){
		alert('这个手机号已经注册了！');
	})
 </script>
</c:if>

<c:if test="${result==3}">
  <script type="text/javascript">
 	$(function(){
		alert('这个手机号已经预留帐号了！赶紧下载登录吧！');
	})
 </script>
</c:if>