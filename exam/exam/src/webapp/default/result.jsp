<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="head.jsp" %>
<br>
<br>
<br>
<br>
<br>
<br>
<div style="width:100%; text-align:center">
  <h1>你的得分是：${score}<br>
    <br>
    <br>
    <c:if test="${score>=90}">恭喜你，考试合格！</c:if>
    <c:if test="${score<90}">很遗憾，考试不合格！</c:if>
  </h1>
  <br>
  <br>
  <c:if test="${score!=100}"><a href="explain?userId=${userId}" style="text-decoration:underline;color:#F90409; font-size:30px">看看我错的题目</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</c:if>
  <a href="${from}" style="text-decoration:underline;color:#F90409; font-size:30px">再做一次</a> </div>
<%@include file="foot.jsp" %>