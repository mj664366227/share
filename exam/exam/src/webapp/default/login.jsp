<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="head.jsp" %>

<center><b>请输入登录密码</b></center>
<br><br><br><br>
<center><h3>${tips}</h3></center>
<br><br><br><br>
<center>
  <form method="post">
  <input type="password" name="password"/>
  <input type="submit" value="登录" class="exam kemu1-exam"/>
  </form>
</center>
<%@include file="foot.jsp" %>