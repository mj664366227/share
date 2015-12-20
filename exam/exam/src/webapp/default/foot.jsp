<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript">
var kemuObj = document.getElementById('kemu');
if(kemuObj){
	var kemu = parseInt(kemuObj.innerHTML);
}
var judgeHTML = '<a class="answer-right" onClick="question(\'%baid%\',\'正确\')">正确</a> <a class="answer-wrong" onClick="question(\'%baid%\',\'错误\')">错误</a>';
var selectHTML = '<a class="answer-right" onClick="question(\'%baid%\',\'A\')">A</a> <a class="answer-wrong" onClick="question(\'%baid%\',\'B\')">B</a><a class="answer-right" onClick="question(\'%baid%\',\'C\')">C</a> <a class="answer-wrong" onClick="question(\'%baid%\',\'D\')">D</a>';
var multiSelectHTML = '<a class="answer-right multiSelect" onClick="ding(this)">A</a> <a class="answer-wrong multiSelect" onClick="ding(this)">B</a><a class="answer-right multiSelect" onClick="ding(this)">C</a> <a class="answer-wrong multiSelect" onClick="ding(this)">D</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;此题目为多项选择：<button style="background:#32b16c;border:0;width:100px;height:35px;margin-top:3px;outline:none;border-radius:5px;color:#fff;font-size:16px;" type="button" onClick="question(\'%baid%\',getMultiSelectAnswer())">确定</button>';
var time = 0;
var tmp = 0;
var last = 1; 
var _do = 0;
var status = false;

$(function(){
	var clientHeight = parseInt(document.body.clientHeight);
	$('#box').css('padding-top',(clientHeight/3.5)+'px');
	
	$('#datika-container ul li').mouseover(function(e) {
        $(this).addClass('hover');
    });
	
	$('#datika-container ul li').mouseout(function(e) {
        $(this).removeAttr('class');
    });
	
	time = parseInt($('#examInfo span').html());
	
	$('#examInfo span').html(second2minute(time));
	
	showNextQuestion();
	
	second2time();
	setInterval('second2time()',1000);
	
	$('#handIn').click(function(){
		if(!(kemu == 1 && tmp >= 99) && !(kemu == 4 && tmp >= 49)){
			alert('您有题目没做完，不能交卷！');
			return false;
		}
		$('#my-answer').submit();
	});
})

function showQuestion(id,number){
	$.post('exam'+kemu,{id:id},function(data){
				if(!data) {
					return false;
				}
				$('.timu-p').html(number+'.&nbsp;'+data.question+'<br><br><a href="javascript:void(0)" onClick="soundPlay();"><div class="sound" title="点击可以听到语音提示" content="'+data.question+'"></div></a><div class="sound-play"></div>');
				if(data.a){
					var hhhh = data.a+'<br>'+data.b+'<br>'+data.c+'<br>'+data.d+'<br>';
					$('#daan').html(hhhh);
				}
				$.post('getanswer',{id:id}, function(data){
					$('.tip-content').html(data.explain.toString().trim()+'<br>答案：<font color="red">'+data.answer+'</font><br>&nbsp;<a href="javascript:void(0)" onClick="soundPlayTips();"><div class="sound-tips" title="点击可以听到语音提示" content="'+data.explain.toString().trim()+'"></div></a><div class="sound-play-tips"></div>');

				});
				if(kemu == 1){
					if(number <= 40){
						$('#options-container span').html(judgeHTML.replace(/%baid%/ig,id));
					} else {
						$('#options-container span').html(selectHTML.replace(/%baid%/ig,id));
					}
				} else if(kemu == 4) {
					if(number <= 22){
						$('#options-container span').html(judgeHTML.replace(/%baid%/ig,id));
					} else if(number <= 45){
						$('#options-container span').html(selectHTML.replace(/%baid%/ig,id));
					} else {
						$('#options-container span').html(multiSelectHTML.replace(/%baid%/ig,id));
					}
				}
				if(data.image){
					var html = '<img src="'+data.image+'" width="300" height="400">';
					$('#media-container').html(html);
				}
				if(data.flashurl){
					var html = '<object id="player" height="200" width="300"><param name="movie" value="'+data.flashurl+'"><param name="quality" value="best"></object>';
					$('#media-container').html(html);
				}
			});
}

function showNextQuestion(){
	$('#datika-container ul li').each(function(index, e) {
        var has = $(e).attr('style');
		var id = $(e).attr('id');
		var number = $(e).attr('number');
		if(last <= number) {
			if (typeof(has) == 'undefined') {
				showQuestion(id,number);
				return false;
			}
		}
		tmp = index;
    });
	if((kemu == 1 && _do >= 100) || (kemu == 4 && _do >= 50)){
		if(status){
			$('.timu-p').html('<center><b><br><br><br>题目做完了，请交卷！</b></center>');
			$('.tip-content').html('');
			$('#media-container').html('');
			$('#options-container span').html('');
			$('#please-select').css('display','none');
		}
		status = true;
		return false;
	}
}

function ding(obj){
	obj = $(obj);
	if(obj.hasClass('hover')){
		return false;
	}	
	obj.addClass('hover');
}

function getMultiSelectAnswer(){
	var answer = '';
	$('.multiSelect').each(function(index, element) {
		obj = $(element);
        if(obj.hasClass('hover')){
			answer += obj.html();
		}	
    });
	return answer;
}

function question(baid, answer){
	$('#'+baid).css('background','#dfdfdf').css('cursor','pointer').addClass('hover');
	$('#my-answer').prepend('<input type="hidden" name="'+baid+'" value="'+answer+'"/>');
	last = parseInt($('#'+baid).attr('number'));
	_do = _do + 1;
	if(last >= 100){
		last = 0;
	}
	showNextQuestion();
}

function second2minute(second){
	return parseInt(second/60)+'分钟';
}

function soundPlay(){
	var content = $('.sound').attr('content').trim();
	var html = '<video controls autoplay name="media"><source src="http://tts.baidu.com/text2audio?lan=zh&pid=101&ie=UTF-8&text='+content+'&spd=3" type="audio/mp3"></video>';
	$('.sound-play').html(html);
}
function jump(obj){
	jump = true;
	obj = $(obj);
	var number = obj.attr('number');
	var id = obj.attr('id');
	showQuestion(id,number);
}
function soundPlayTips(){
	var content = $('.sound-tips').attr('content').trim();
	var html = '<video controls autoplay name="media-tips"><source src="http://tts.baidu.com/text2audio?lan=zh&pid=101&ie=UTF-8&text='+content+'&spd=3" type="audio/mp3"></video>';
	$('.sound-play-tips').html(html);
}

function second2time(){
	time -= 1;
	if(time <= 0) {
		alert('考试时间到！系统强制交卷！');
		$('#my-answer').submit();
		return false;
	}
	var minute = parseInt(time/60);
	var second = parseInt(time%60);
	second = second < 10 ? '0'+second : second;
	var html;
	if(minute < 5){
		html = '<font color="red">'+minute+':'+second+'</font>';
	} else {
		html = minute+':'+second;
	}
	$('#left-time').html(html);
}

document.oncontextmenu = function(){
	return true; 
} 
</script>
<style>
.sound, .sound-active,.sound-tips {
	width: 25px;
	height: 25px;
	margin-top: -10px
}
.sound,.sound-tips {
	background: url(${skin}/image/icons.png) no-repeat -73px -88px;
}
.sound:hover,.sound-tips:hover {
	cursor: pointer;
	background: url(${skin}/image/icons.png) no-repeat -123px -88px;
}
.sound-active {
	background: url(${skin}/image/sound.gif) no-repeat -12px -11px;
}
.sound-play,.sound-play-tips {
	display: none
}
.explain {
	margin: 20px;
	padding: 30px;
	word-break: break-all;
	text-align: left;
	width: 590px;
	background: #f7f7f7;
}
</style>
</body>
</html>