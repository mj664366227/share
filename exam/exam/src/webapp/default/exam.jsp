<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="head.jsp" %>
<div class="hide" id="kemu">${kemu}</div>
<div id='simple-container' class='simple-container'>
  <div class="jkbd-main-main erjiyemian" data-item="jkbd-main-main-container" data-simple-node-dom="jkbd-app-template-kaoshi-kaoshi">
    <div class="kaoshi-container" data-item="main-kaoshi-container">
      <div class="ks-loading jkbd-loading" data-item="kaoshi-loading"></div>
      <div class="kaoshi-container-inner jkbd-width wid-auto">
        <div class="kaoshi-popup-container" data-item="kaoshi-popup">
          <div class="kaoshi-wrong-container" data-item="kaoshi-wrong-container">
            <div class="wrong-header">
              <div class="kaoshi-wrong-close" data-item="close"></div>
            </div>
            <div class="wrong-content">
              <p class="title">您刚做的题目答题错误，正确答案如下：</p>
              <p class="explain" data-item="explain"></p>
              <p class="right-answer">正确答案：<span data-item="right-answer"></span></p>
            </div>
            <div class="operate-container">
              <button class="kaoshi-btn-disable" type="button" data-item="goon">继续考试</button>
              <p>以上正确答案确认无误后点击“继续考试”按钮开始下一题答题</p>
            </div>
            <div class="wrong-fotter">
              <p>页面将在<span data-item="leftTime">3</span>秒后自动关闭，回到考试状态！</p>
            </div>
          </div>
          <div class="kaoshi-jiaojuan-container" data-item="jiaojuanContainer">
            <div class="jiaojuan-header"></div>
            <h2>考试确认窗口</h2>
            <div class="jiaojuan-content">
              <p>操作提示：</p>
              <p>1：点击【确认交卷】，将提交考试成绩，结束考试！</p>
              <p>2：点击【继续考试】，将关闭本窗口，继续考试！</p>
            </div>
            <div class="jiaojuan-fotter">
              <button type="button" class="first-c" data-item="submit">确认交卷</button>
              <button type="button" data-item="cancel">继续考试</button>
            </div>
          </div>
        </div>
        <div class="shiti-mask-container" data-item="shiti-mask-container">
          <div class="shiti-mask-zz" data-item="mask-bg"></div>
          <div class="mask-img-content" data-item="mask-img-content">
            <div class="mask-close" data-item="mask-close"></div>
            <div class="mask-content" data-item="mask-content"></div>
          </div>
        </div>
        <div class="kaoshi-wapper">
          <div class="info-up">
            <div class="infoup-left">
              <fieldset class="kaochang-info">
                <legend>理论考试</legend>
                <span>${title}</span>
              </fieldset>
              <fieldset class="kaosheng-info">
                <legend>考生信息</legend>
                <div id="examInfo"> <img src="${skin}/image/1.jpg">
                  <p class="name">考生姓名：我是车神</p>
                  <p>考试题数：100题</p>
                  <p>考试时间：<span>${time}</span></p>
                  <p>合格标准：满分100分</p>
                  <p class="ll">90合格</p>
                </div>
              </fieldset>
            </div>
            <div class="infoup-center">
              <fieldset class="kaochang-main">
                <legend>考试题目</legend>
                <div class="timu-container" data-item="shiti-container">
                <div class="timu-item clearfix">
                  <div class="timu-content float-l">
                    <div class="timu-x">
                      <p class="timu-p"></p>
                    </div>
                  </div>
                  <div class="result-container " data-item="result-container">
                    <div class="result-info float-l" data-item="result-tip"></div>
                    <div class="options-container float-l " data-item="options-container" id="options-container">
                      <label>请选择：</label>
                      <span></span> </div>
                  </div>
                </div>
                <div class="media-container float-l" data-item="media-container" id="media-container"></div>
              </fieldset>
            </div>
          </div>
          <div class="info-middle clearfix">
            <fieldset class="time-info float-l">
              <legend>剩余时间</legend>
              <span id="left-time">${time}</span>
            </fieldset>
            <fieldset class="tip-container float-l">
              <legend>提示信息</legend>
              <div class="tip-content" data-item="tip-content"></div>
            </fieldset>
            <div class="fun-btns clearfix float-l">
              <button type="button" class="jiaojuan option-btn float-r" data-item="jiaojuan">交卷</button>
            </div>
          </div>
          <div class="info-down">
            <fieldset class="">
              <legend>答题信息</legend>
              <div class="datika-container" data-item="datika-container" id="datika-container">
                <ul>
                  <c:set var="i" value="1"/>
                  <c:forEach items="${examMap}" var="exam">
                    <li id="${exam.value.id}" number="${i}">${i}</li>
                    <c:set var="i" value="${i+1}"/>
                  </c:forEach>
                </ul>
              </div>
            </fieldset>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<%@include file="foot.jsp" %>