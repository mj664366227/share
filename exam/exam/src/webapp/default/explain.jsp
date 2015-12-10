<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@include file="head.jsp" %>
<div style="width:100%; text-align:center; margin-top:20px">
  <h1>题目解析</h1>
</div>
<div class="app-quick-container wid-auto" style="width:50%">
  <c:set var="i" value="1"/>
  <c:forEach items="${examMap}" var="exam">
    <div class="explain">${i}&nbsp;.&nbsp;${exam.value.question}<br>
      <br>
      <br>
      <c:if test="${not empty exam.value.image}"><img src="${exam.value.image}" width="150" height="150"/><br>
        <br>
      </c:if>
      您的答案：${learnerAnswer.get(fn:trim(exam.key))}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;正确答案：${answerMap.get(exam.key).split(',')[0]}
      <c:set var="i" value="${i+1}"/>
      <br>
      <br>
      <br>
      答案解析：<br>
      <br>
      ${answerMap.get(exam.key).split(',')[1]} <a href="javascript:void(0)" onClick="soundPlay_${exam.key}();">
      <div class="sound sound-${exam.key}" title="点击可以听到语音提示" content="${answerMap.get(exam.key).split(',')[1]}"></div>
      </a>
      <div class="sound-play sound-play-${exam.key}"></div>
      <script>
      function soundPlay_${exam.key}(){
		 	var content = $('.sound-${exam.key}').attr('content').trim();
			var html = '<video controls autoplay name="media"><source src="http://tts.baidu.com/text2audio?lan=zh&pid=101&ie=UTF-8&text='+content+'&spd=3" type="audio/mp3"></video>';
		$('.sound-play-${exam.key}').html(html);
	  }
      </script> 
    </div>
  </c:forEach>
</div>
<br>
<br>
<br>
<center>
  <a href="index" style="font-size:20px">返回首页</a>
</center>
<br>
<br>
<br>
<%@include file="foot.jsp" %>