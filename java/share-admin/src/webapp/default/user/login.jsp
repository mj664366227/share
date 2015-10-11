<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../head.jsp" %>
<div class="container">
  <div class="row">
    <div class="col-md-4 col-md-offset-4">
      <div class="login-panel panel panel-default">
        <div class="panel-heading">
          <h3 class="panel-title">share-admin管理后台登录</h3>
        </div>
        <div class="panel-body">
          <form role="form" method="post">
            <fieldset>
              <div class="form-group">
                <input class="form-control" placeholder="用户名" name="username" type="username" autofocus>
              </div>
              <div class="form-group">
                <input class="form-control" placeholder="密码" name="password" type="password" value="">
              </div>
              <div class="checkbox">
                <label>
                  <input name="remember" type="checkbox" value="remember">
                  记住我</label>
              </div>
              <input class="btn btn-lg btn-success btn-block" value="登录" type="submit">
            </fieldset>
          </form>
        </div>
      </div>
    </div>
  </div>
</div>
<%@include file="../foot.jsp" %>