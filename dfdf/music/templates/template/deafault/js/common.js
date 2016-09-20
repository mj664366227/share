/***********************************公共部分js***************************************/
$(document).ready(function(){
		$("#jquery_jplayer").jPlayer({
		ready:function(){
	$(".song ul li .img:eq(0)").addClass('p').css('display','none').parent().parent().siblings().children().children(".img").css('display','block').removeClass('p');
	$(".song ul li .img:eq(0)").parent().parent().siblings().children().children(".pause").css('display','none');
	$(".song ul li .img:eq(0)").parent().parent().siblings().children().children(".play").css('display','none');
	$('.song ul li:eq(0) .pause').css('display','block');
	var src=$(".song ul li .img").parent().parent().attr("jplayer");
	var id=$(".song ul li .img").parent().parent().attr("id");
	/*更新点播次数*/
	$.ajax({
		type:'post',
		url:'do.php',
		data:'action=update_have_played&id='+id,
		success:function(){}
		});
	$("#jquery_jplayer").changeAndPlay(src);
			},
	swfPath:"templates/template/deafault/js"
	});
	
	
//搜索栏	
$('#search_input').attr('value','有木有你喜欢的歌手...').css('color','#cccccc');	

//首页图片滑动效果

//自动跳转

setInterval("auto()",5000);
auto=function()
{
	var current=$('.slide .slide_content_list .current').index()+1;
	if(current==$('.slide .dddd').length)
	{
		current=0;
	}
	$('.slide_content_list ul li:eq('+current+')').fadeIn(400).addClass('current').siblings('.current').removeClass("current").removeAttr('style');
	$('.slide_content_list span:eq('+current+')').slideDown(400).addClass('current1').siblings().removeClass("current1").removeAttr('style');
	$('.slide_nav .dddd:eq('+current+')').addClass('click mouseover').siblings().removeClass('click mouseover');
}

//初始化，显示第一幅图片
$('.slide_content_list ul li:eq('+0+')').fadeIn(400).addClass('current');
$('.slide_content_list span:eq('+0+')').slideDown(400).addClass('current1');
$('.slide_nav .dddd:eq('+0+')').addClass('mouseover');

//点点鼠滑动标效果
$('.slide .slide_nav .dddd').mouseover(function(){
	$(this).addClass('pointer').siblings().removeClass('pointer');
	var current=$('.slide .slide_nav .pointer').index()+1;
	current--;
	$('.slide .slide_nav .dddd:eq('+current+')').addClass('onmouseover');
});
$('.slide .slide_nav .dddd').mouseout(function(){
	var current=$('.slide .slide_nav .pointer').index();
	$('.slide .slide_nav .dddd').removeClass('onmouseover');
});

//点点选择
$('.slide .slide_nav .dddd').click(function(){
	$(this).addClass('click').siblings().removeClass('click mouseover');
	var current=$('.slide .slide_nav .click').index()+1;
	current--;
	$('.slide_content_list ul li:eq('+current+')').fadeIn(400).addClass('current').siblings('.current').removeClass("current").removeAttr('style');
	$('.slide_content_list span:eq('+current+')').slideDown(400).addClass('current1').siblings().removeClass("current1").removeAttr('style');
	$('.slide_nav .dddd:eq('+current+')').addClass('click mouseover').siblings().removeClass('click mouseover');	
});


//首页音乐推荐的效果

function show(father,child)
{
	$(father).mouseover(function(){
		var v=$.browser.version;
		switch(v)
		{
			case '6.0':$(child).animate({top:139},500);break;
			case '7.0':$(child).animate({top:144},500);break;
			case '8.0':$(child).animate({top:140},500);break;
			case '9.0':$(child).animate({top:139},500);break;
			default:$(child).animate({top:139},500);break;
			
		}
	})
}

//高度预处理
$('.music_introduction_list li a span').css('top','192px');

//鼠标滑过的效果
show('#on1','#show1');
show('#on2','#show2');
show('#on3','#show3');
show('#on4','#show4');
show('#on5','#show5');
show('#on6','#show6');
show('#on7','#show7');
show('#on8','#show8');
show('#on9','#show9');

//鼠标离开的效果
$('.music_introduction_list img').mouseout(function(){
$('.music_introduction_list li a span').animate({top:192},500);
});


/*下载选中歌曲*/
$('#down').click(function(){
	var total=$(".song ul li").length+1;
	var str='';
	for(var i=1;i<total;i++)
	{
		str+=$(".song ul li:eq("+(i-1)+")").attr('jplayer')+"\n";
	}
	clipboardData.setData('text',str);
});


//资源页广告位
$('.ad .slide .slide_nav .ddd').click(function(){
	$(this).addClass('click').siblings().removeClass('click mouseover');
	var current=$('.slide .slide_nav .click').index();
	$('.slide_content_list ul li:eq('+current+')').fadeIn(400).addClass('adcurrent').siblings('.adcurrent').removeClass("adcurrent").removeAttr('style');
	$('.slide_nav .ddd:eq('+current+')').addClass('adcurrent').siblings().removeClass('adcurrent');	
});

//鼠滑动标效果
$('.ad .slide .slide_nav .ddd').mouseover(function(){
	$(this).addClass('pointer').siblings().removeClass('pointer');
	var current=$('.slide .slide_nav .pointer').index();
	$('.slide .slide_nav .ddd:eq('+current+')').addClass('onmouseover');
});
$('.ad .slide .slide_nav .ddd').mouseout(function(){
	var current=$('.slide .slide_nav .pointer').index();
	$('.slide .slide_nav .ddd').removeClass('onmouseover');
});


//文章页面
 $('#button').click(function(){
    //提交前检测
	 if($('#usernickname').val().trim()=='')
	 {
		 alert("请输入您的昵称！");
		 return false;
	}
	
	 if($('#yanzhengma').val().trim()=='')
	 {
		 alert("请输入验证码！");
		 return false;
	}
	
	 if($('#text').val().trim()=='')
	 {
		 alert("请说两句吧！");
		 return false;
	}
	
  });
 //评论页鼠标效果
  $('.comment_div').mouseover(function(){
	  $(this).css({'background':'#f7f7f7','cursor':'text'});
  });
  
    $('.comment_div').mouseout(function(){
	  $(this).css('background','#fff');
  });
  
  
  
  //搜索
  $("#search_submit").click(function(){
	  if($('#search_input').val().trim()==''||$('#search_input').val().trim()=='有木有你喜欢的歌手...')
	  {
		  alert('关键字为空！');
		  return false;
	  }
	  })
	  
	//ajax搜索
	
	$('#search_input').keyup(function(){
		$(this).css('color','#000');
		$.ajax({
		type:'post',
		url:'do.php',
		data:'action=ajax_search&text='+$(this).val(),
		success:function(data){
			$('.result').html(data);
			if(data.length!=0)
			{
				$('.result').css('display','block');
			}
			else
			{
				$('.result').css('display','none');
			}
			$('.ajax_search_result').mouseover(function(){
			     $(this).css('background','#339CFB');	
				 $(this).addClass('a');
			});
			$('.ajax_search_result').mouseout(function(){
			     $(this).css('background','#fff');	
				 $(this).addClass('b');
			});
			$('.ajax_search_result').click(function(){
				var index=$(this).index();
				var str=$('.ajax_search_result:eq('+index+') ').text();
				$('.search_input').attr('value',str);
				$('.result').css('display','none');
			});
			}
		});
	});
	
	 
/*统计下载次数*/
$(".song ul li .songdown").click(function(){
	var id=$(this).parent().parent().parent().attr("id");
	$.ajax({
		type:'post',
		url:'do.php',
		data:'action=update_download&id='+id,
		success:function(){}
		});
});	 

/***********************************************/


	
	/*首页播放*/
$(".hot ul li .img").click(function(){
	$(this).addClass('p').css('display','none').parent().siblings().children(".img").css('display','block').removeClass('p');
	$(this).parent().siblings().children(".pause").css('display','none');
	$(this).parent().siblings().children(".play").css('display','none');
	var index=$(this).parent().index();
	$('.hot ul li:eq('+index+') .pause').css({'display':'block','margin-top':'10px'});
	var src=$(this).parent().attr("jplayer");
	var id=$(this).parent().attr("id");
	/*更新点播次数*/
	$.ajax({
		type:'post',
		url:'do.php',
		data:'action=update_have_played&id='+id,
		success:function(){}
		});
	$("#jquery_jplayer").changeAndPlay(src);
});


/*暂停*/
$(".hot ul li .pause").click(function(){
	$("#jquery_jplayer").pause();
	$(this).css('display','none');
	var index=$(this).parent().index();
	$('.hot ul li:eq('+index+') .play').css({'display':'block','margin-top':'10px'});
});

/*暂停后继续播放*/
$(".hot ul li .play").click(function(){
	$("#jquery_jplayer").play();
	$(this).css('display','none');
	var index=$(this).parent().index();
	$('.hot ul li:eq('+index+') .pause').css({'display':'block','margin-top':'10px'});
});


$(".hot ul li").mouseover(function(){
	var current=$(this).index();
	$(".hot ul li:eq("+current+") .img").css('background','url(templates/template/deafault/images/play.png)');
});

$(".hot ul li").mouseout(function(){
	var current=$(this).index();
	$(".hot ul li:eq("+current+") .img").css('background','url(templates/template/deafault/images/unplay.png)');
});






/*专辑页面播放*/
$(".song ul li .img").click(function(){
	$(this).addClass('p').css('display','none').parent().parent().siblings().children().children(".img").css('display','block').removeClass('p');
	$(this).parent().parent().siblings().children().children(".pause").css('display','none');
	$(this).parent().parent().siblings().children().children(".play").css('display','none');
	var index=$(this).parent().parent().index();
	$('.song ul li:eq('+index+') .pause').css('display','block');
	var src=$(this).parent().parent().attr("jplayer");
	var id=$(this).parent().parent().attr("id");
	/*更新点播次数*/
	$.ajax({
		type:'post',
		url:'do.php',
		data:'action=update_have_played&id='+id,
		success:function(){}
		});
	$("#jquery_jplayer").changeAndPlay(src);
});

$(".song ul li .img").mouseover(function(){
		$(this).css('background','url(templates/template/deafault/images/play.png)');
})

$(".song ul li .img").mouseout(function(){
		$(this).css('background','url(templates/template/deafault/images/unplay.png)');
})

/*暂停*/
$(".song ul li .pause").click(function(){
	$("#jquery_jplayer").pause();
	$(this).css('display','none');
	var index=$(this).parent().parent().index();
	$('.song ul li:eq('+index+') .play').css('display','block');
});

/*暂停后继续播放*/
$(".song ul li .play").click(function(){
	$("#jquery_jplayer").play();
	$(this).css('display','none');
	var index=$(this).parent().parent().index();
	$('.song ul li:eq('+index+') .pause').css('display','block');
});

$(".song ul li").mouseover(function(){
	$(this).css({'color':'#ff0066'});
});

$(".song ul li").mouseout(function(){
	$(this).css({'color':'#000000'});
});

/*自动播放下一首*/
$("#jquery_jplayer").onSoundComplete(function(){
	var total=$(".song ul li").length;
	var currentIndex = $(".song ul li").children().children(".img").index($(".p"));
	++currentIndex;
	if(currentIndex>total-1)
	{
		//$("#jquery_jplayer").stop();
		var str=$('.other .about:eq(0) a').attr('href');
		var id=str.slice(13);
		window.location='album.php?id='+id;
		return false;
	}

	$(".song ul li:eq("+(currentIndex)+") .img").addClass('p').css('display','none').parent().parent().siblings().children().children(".img").css('display','block').removeClass('p');
	$(".song ul li:eq("+(currentIndex)+") .img").parent().parent().siblings().children().children(".pause").css('display','none');
	$(".song ul li:eq("+(currentIndex)+") .img").parent().parent().siblings().children().children(".play").css('display','none');
	var index=$(".song ul li:eq("+(currentIndex)+") .img").parent().parent().index();
	$('.song ul li:eq('+index+') .pause').css('display','block');
	var src = $(".song ul li:eq("+currentIndex+")").attr("jplayer");
	var id=$(".song ul li:eq("+currentIndex+")").attr("id");
	/*更新点播次数*/
	$.ajax({
		type:'post',
		url:'do.php',
		data:'action=update_have_played&id='+id,
		success:function(){}
		});
	$("#jquery_jplayer").changeAndPlay(src);
});

/*下载*/
$(".songdown").mouseover(function(){
	$(this).css('background','url(templates/template/deafault/images/down_mouseover.png)');
})

$(".songdown").mouseout(function(){
	$(this).css('background','url(templates/template/deafault/images/down_mouseout.png)');
})


//底部
});