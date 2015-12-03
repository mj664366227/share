<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="head.jsp" %>
<style>
.sound,.sound-active{width:25px; height:25px;}
.sound{background:url(${skin}/image/icons.png) no-repeat -73px -88px;}
.sound:hover{cursor:pointer;background:url(${skin}/image/icons.png) no-repeat -123px -88px;}
.sound-active{background:url(${skin}/image/sound.gif) no-repeat -12px -11px;}
</style>
<div class="sound"></div>
<%@include file="foot.jsp" %>