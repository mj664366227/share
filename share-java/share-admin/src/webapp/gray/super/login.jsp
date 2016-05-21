<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../head.jsp" %>
<div class="container">
  <div class="row">
    <div id="login-panel">
      <div class="col-md-4 col-md-offset-4">
        <div class="login-panel panel panel-default">
          <div class="panel-heading">
            <h3 class="panel-title text-center">share-admin管理后台登录</h3>
          </div>
          <div class="panel-body">
            <form role="form" method="post">
              <fieldset>
                <div class="col-lg-12">
                  <input class="form-control" placeholder="用户名" name="username" type="username" autofocus>
                </div>
                <br>
                <br>
                <br>
                <div class="col-lg-12">
                  <input class="form-control" placeholder="密码" name="password" type="password">
                </div>
                <br>
                <br>
                <br>
                <div class="col-lg-7">
                  <input class="form-control" placeholder="验证码" name="captcha" type="captcha">
                </div>
                <div class="col-lg-4"><img src="captcha" onClick="this.src='captcha?'+Math.random();" style="cursor:pointer;" title="点击图片刷新验证码"> </div>
                <br>
                <br>
                <br>
                <div class="col-lg-12">
                  <input class="btn btn-lg btn-success btn-block" value="登录" type="submit">
                </div>
              </fieldset>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<%@include file="../foot.jsp" %>