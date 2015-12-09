<%@ page contentType="text/html; charset=utf-8" language="java" import="java.sql.*" errorPage="" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="${skin}/js/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="${skin}/js/js.js"></script>
<script type="text/javascript">
var kemuObj = document.getElementById('kemu');
if(kemuObj){
	var kemu = parseInt(kemuObj.innerHTML);
}
var judgeHTML = '<a class="answer-right" onClick="question(\''+kemu+'\',\'%baid%\',\'正确\')">正确</a> <a class="answer-wrong" onClick="question(\''+kemu+'\',\'%baid%\',\'错误\')">错误</a>';
	var selectHTML = '<a class="answer-right" onClick="question(\''+kemu+'\',\'%baid%\',\'A\')">A</a> <a class="answer-wrong" onClick="question(\''+kemu+'\',\'%baid%\',\'B\')">B</a><a class="answer-right" onClick="question(\''+kemu+'\',\'%baid%\',\'C\')">C</a> <a class="answer-wrong" onClick="question(\''+kemu+'\',\'%baid%\',\'D\')">D</a>';
	var time = 0;
	
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
})

function showNextQuestion(){
	var tmp = 0;
	$('#datika-container ul li').each(function(index, e) {
        var has = $(e).attr('style');
		var id = $(e).attr('id');
		var number = $(e).attr('number');
		if (typeof(has) == 'undefined') { 
			$.post('exam1',{id:id},function(data){
				if(!data) {
					return false;
				}
				$('.timu-p').html(number+'.&nbsp;'+data.question+'<a href="javascript:void(0)" onClick="soundPlay();"><div class="sound" title="点击可以听到语音提示" content="'+data.question+'"></div></a><div class="sound-play"></div>');
				$.post('getanswer',{id:id}, function(data){
					$('.tip-content').html(data.explain.toString().trim());
				});
				if(kemu == 1){
					if(number <= 40){
						$('#options-container span').html(judgeHTML.replace(/%baid%/ig,id));
					} else {
						$('#options-container span').html(selectHTML.replace(/%baid%/ig,id));
					}
				}
				if(data.image){
					var html = '<img src="'+data.image+'" width="300" height="300">';
					$('#media-container').html(html);
				}
			});
			return false;
		}
		tmp = index;
    });
	if(tmp >= 99){
		//alert('题目做完了');
	}
}

function question(kemu, baid, answer){
	$('#'+baid).css('background','#dfdfdf').css('cursor','pointer').addClass('hover');
	$('#my-answer').append('<span id="'+baid+'" answer="'+answer+'"></span>');
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

function second2time(){
	time -= 1;
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
</script>
<style>
.sound,.sound-active{width:25px; height:25px; margin-top:5px}
.sound{background:url(${skin}/image/icons.png) no-repeat -73px -88px;}
.sound:hover{cursor:pointer;background:url(${skin}/image/icons.png) no-repeat -123px -88px;}
.sound-active{background:url(${skin}/image/sound.gif) no-repeat -12px -11px;}
.sound-play{display:none}
</style>
</body>
</html>