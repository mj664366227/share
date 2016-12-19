/***********************************公共部分js***************************************/
$(document).ready(function(){
		$("#jquery_jplayer").jPlayer({
		ready:function(){
		$(".song ul li .songimg:eq(0)").addClass('p').css('display','none').parent().parent().siblings().children().children(".img").css('display','block').removeClass('p');
		$(".song ul li .songimg:eq(0)").parent().parent().siblings().children().children(".pause").css('display','none');
		$(".song ul li .songimg:eq(0)").parent().parent().siblings().children().children(".play").css('display','none');
		$('.song ul li:eq(0) .songpause').css('display','block');
		var src=$(".song ul li .songimg").parent().parent().attr("jplayer");
		var id=$(".song ul li .songimg").parent().parent().attr("id");
		/*更新点播次数*/
		$.ajax({
			type:'post',
			url:'do.php',
			data:'action=update_have_played&id='+id,
			success:function(){}
			});
		$("#jquery_jplayer").changeAndPlay(src);
				},
		swfPath:"templates/template/gray/js"
		});


var uid = $('.login').attr('uid');

//搜索栏	
$('#search_input').attr('value','搜搜有木有你喜欢的歌手...').css('color','#e1e1e1');	

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

/*所有播放按钮*/
$(".pause").css('display','none');
$(".play").css('display','none');

//专辑页喜欢
$(".album_like_on").css('display','none');
$(".album_like_out").mouseover(function(){
	$(".album_like_on").css({'display':'block','cursor':'pointer'});
	$(".album_like_out").css('display','none');
});
$(".album_like_on").mouseout(function(){
	$(".album_like_on").css('display','none');
	$(".album_like_out").css('display','block');
});
$(".album_like_on").click(function(){
	if(uid <= 0){
		alert('你还没有登录，不能标记喜欢，快去登录吧！');
		var url = location.href.substring(0, location.href.indexOf('album.php'));
		return false;
	}
	var id = $('.album_detail').attr('album_id');
	$.ajax({
		type:'post',
		url:'do.php',
		data:'action=favourite&album_id='+id+'&uid='+uid,
		success:function(){}
		});
	$(".album_like_out").removeClass('album_like_out').addClass('album_like');
	$(".album_like_on").removeClass('album_like_on').addClass('album_like');
	if($.browser.version==6){
		$(".album_like").css('top','-42px');
	}
});


$(".artist_like_on").css('display','none');
$(".artist_like_out").mouseover(function(){
	$(".artist_like_on").css('display','block');
	$(".artist_like_out").css('display','none');
});
$(".artist_like_on").mouseout(function(){
	$(".artist_like_on").css('display','none');
	$(".artist_like_out").css('display','block');
});
$(".artist_like_on").click(function(){
	if(uid <= 0){
		alert('你还没有登录，不能标记喜欢，快去登录吧！');
		var url = location.href.substring(0, location.href.indexOf('album.php'));
		return false;
	}
	var id = $('.artist_details').attr('id');
	$.ajax({
		type:'post',
		url:'do.php',
		data:'action=favourite_artist&artist_id='+id+'&uid='+uid,
		success:function(){}
		});
		$(".artist_like_out").removeClass('artist_like_out').addClass('artist_like');
		$(".artist_like_on").removeClass('artist_like_on').addClass('artist_like');
})
$("#up").css('display','none');
$("#expand").click(function(){
	$('.artist_details').css('height','auto');
	$(this).css('display','none');
	$("#up").css('display','block');
	if($.browser.version==6){
		$('.details').css({'margin-left':'95px','line-height':'14px','margin-top':'20px'});
	}else{
		$('.details').css({'margin-left':'190px','line-height':'14px','margin-top':'20px'});
	}
});

$("#up").click(function(){
	$('.artist_details').css('height','150px');
	$(this).css('display','none');
	$("#expand").css('display','block');
	$('.details').css({'line-height':'14px','margin-top':'-20px'});
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


//获取其他喜欢这专辑的用户 
get_otherlike=function(){
	var id = $('.album_detail').attr('album_id');
	var limit = 10;
	$.ajax({
		type:'post',
		url:'do.php',
		data:'action=otherlike&album_id='+id+'&limit='+limit,
		success:function(data){
			var myobj=eval(data);
			var html='';
			if(myobj){
				if(myobj.length>=1){
					for(var i=0;i<myobj.length;i++){
						html+='<li><a href="'+myobj[i].url+'api.php?skin=gray&action=user&mod=index&look=true&uid='+myobj[i].uid+'" title="'+myobj[i].nick+'"><img src="'+myobj[i].avatar+'" width="60" height="60" border="0"/></a></li>';
					}
				} else {
					html+='<li id="onlyone">这张专辑还没有标记喜欢呢，赶快标记吧！</li>';
				}
			}
			$('.otherlike ul').html(html);
		}
	});
}

$('.otherlike #otherlike').click(function(){
	get_otherlike();
});

//你可能还喜欢其他专辑
get_maybelike=function(){
	var limit = 8;
	$.ajax({
		type:'post',
		url:'do.php',
		data:'action=maybelike&limit='+limit,
		success:function(data){
			var myobj=eval(data);
			var html='';
			if(myobj){
				if(myobj.length>=1){
					for(var i=0;i<myobj.length;i++){
						html+='<li onmouseover="this.className=\'maybelike_on\'" onmouseout="this.className=\'maybelike_out\'" class="maybelike_out"><a href="album.php?skin=gray&id='+myobj[i].id+'" title="'+myobj[i].artist+'  '+myobj[i].title+'"><img src="thumbs/thumbs/cache/w190h190/admin/'+myobj[i].picture+'" border="0" width="80" height="80" align="absmiddle"/><div class="name">'+myobj[i].title.substring(0,14)+'</div><div class="artist">'+myobj[i].artist.substring(0,14)+'</div></a></li>';
					}
				}
			}
			$('.maybelike ul').html(html);
		}
	});
}

$('.maybelike #maybelike').click(function(){
	get_maybelike();
});

get_maybelike_=function(){
	$.ajax({
		type:'post',
		url:'do.php',
		data:'action=maybelike&limit=4',
		success:function(data){
			var myobj=eval(data);
			var html='';
			if(myobj){
				if(myobj.length>=1){
					for(var i=0;i<myobj.length;i++){
						html+='<li onmouseover="this.className=\'maybelike_on\'" onmouseout="this.className=\'maybelike_out\'" class="maybelike_out"><a href="album.php?skin=gray&id='+myobj[i].id+'" title="'+myobj[i].artist+'  '+myobj[i].title+'"><img src="thumbs/thumbs/cache/w190h190/admin/'+myobj[i].picture+'" border="0" width="80" height="80" align="absmiddle"/><div class="name">'+myobj[i].title.substring(0,14)+'</div><div class="artist">'+myobj[i].artist.substring(0,14)+'</div></a></li>';
					}
				}
			}
			$('#_maybelike ul').html(html);
		}
	});
}
$('#_maybelike #maybelike_').click(function(){
	get_maybelike_();
});


$(".maybelike ul li").addClass('maybelike_out');
$(".maybelike ul li").mouseover(function(){
	var index=$(this).index();
	$(".maybelike ul li:eq("+index+")").addClass('maybelike_on');
});

$(".maybelike ul li").mouseout(function(){
	var index=$(this).index();
	$(".maybelike ul li:eq("+index+")").removeClass('maybelike_on').addClass('maybelike_out');
});


//获取其他喜欢这歌手的用户 
get_otherlike_artist=function(){
	var id = $('.artist_details').attr('id');
	var limit = 10;
	$.ajax({
		type:'post',
		url:'do.php',
		data:'action=otherlike_artist&artist_id='+id+'&limit='+limit,
		success:function(data){
			var myobj=eval(data);
			var html='';
			if(myobj){
				if(myobj.length>=1){
					for(var i=0;i<myobj.length;i++){
						html+='<li><a href="'+myobj[i].url+'api.php?skin=gray&action=user&mod=index&look=true&uid='+myobj[i].uid+'" title="'+myobj[i].nick+'"><img src="'+myobj[i].avatar+'" width="60" height="60" border="0"/></a></li>';
					}
				} else {
					html+='<li id="onlyone">这张专辑还没有标记喜欢呢，赶快标记吧！</li>';
				}
			}
			$('.otherlike .ul').html(html);
		}
	});
}

$('.otherlike #otherlike_artist').click(function(){
	get_otherlike_artist();
});


//你可能还喜欢其他歌手
get_maybelike_artist=function(){
	$.ajax({
		type:'post',
		url:'do.php',
		data:'action=maybelike_artist',
		success:function(data){
			var myobj=eval(data);
			var html='';
			if(myobj){
				if(myobj.length>=1){
					for(var i=0;i<myobj.length;i++){
						html+='<li><a href="musician.php?skin=gray&action=look&id='+myobj[i].id+'" title="'+myobj[i].name+'"><img src="thumbs/thumbs/cache/w184h184/admin/'+myobj[i].pic+'" border="0" width="110" height="110" align="absmiddle"/></a></li>';
					}
				}
			}
			$('.maybelike .ul').html(html);
		}
	});
}

$('.maybelike #maybelike_artist').click(function(){
	get_maybelike_artist();
});


	
/*首页播放*/
$(".hot ul li .img").click(function(){
	var index=$(this).parent().parent().index();
	$(this).css('display','none');
	$(".hot ul li .pause").css('display','none');
	$(".hot ul li .play").css('display','none');
	$(".hot ul li .img").css('display','block');
	$(".hot ul li:eq("+index+") .img").css('display','none');
	$(".hot ul li:eq("+index+") .pause").css('display','block');
	var src=$(".hot ul li:eq("+index+")").attr("jplayer");
	var id=$(".hot ul li:eq("+index+")").attr("id");
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
	var index=$(this).parent().parent().index();
	$('.hot ul li:eq('+index+') .pause').css('display','none');
	$('.hot ul li:eq('+index+') .play').css({'display':'block'});
});

/*暂停后继续播放*/
$(".hot ul li .play").click(function(){
	$("#jquery_jplayer").play();
	var index=$(this).parent().parent().index();
	$('.hot ul li:eq('+index+') .play').css('display','none');
	$('.hot ul li:eq('+index+') .pause').css({'display':'block'});
});


$(".hot ul li").mouseover(function(){
	var index=$(this).index();
	$(".hot ul li:eq("+index+")").css('background','#fff');
});

$(".hot ul li").mouseout(function(){
	var index=$(this).index();
	$(".hot ul li:eq("+index+")").css('background','#eee');
});



$(".song ul .songpause").css('display','none');
$(".song ul .songplay").css('display','none');
$(".song ul .songpausetext").css('display','none');


/*专辑页面播放*/
$(".song ul li .songimg").click(function(){
	$(this).addClass('p').css('display','none').parent().parent().siblings().children().children(".img").css('display','block').removeClass('p');
	var currentIndex=$(this).parent().parent().index();
	$('.song ul li .songimg').removeClass('p').css('display','block');
	$(".song ul li:eq("+currentIndex+") .songimg:eq(0)").addClass('p').css('display','none').parent().parent().siblings().children().children(".img").css('display','block');
	$(".song ul li:eq("+currentIndex+") .songimg:eq(0)").parent().parent().siblings().children().children(".pause").css('display','none');
	$(".song ul li:eq("+currentIndex+") .songimg:eq(0)").parent().parent().siblings().children().children(".play").css('display','none');
	$('.song ul li:eq('+currentIndex+') .songpause').css('display','block');
	var src=$(".song ul li:eq("+currentIndex+") .songimg").parent().parent().attr("jplayer");
	var id=$(".song ul li:eq("+currentIndex+") .songimg").parent().parent().attr("id");
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
$(".song ul li .songpause").click(function(){
	$("#jquery_jplayer").pause();
	$(this).css('display','none');
	var index=$(this).parent().parent().index();
	$('.song ul li:eq('+index+') .songplay').css('display','block');
	$('.song ul li:eq('+index+') .songplaytext').css('display','block');
	$('.song ul li:eq('+index+') .songpausetext').css('display','none');
});

/*暂停后继续播放*/
$(".song ul li .songplay").click(function(){
	$("#jquery_jplayer").play();
	$(this).css('display','none');
	var index=$(this).parent().parent().index();
	$('.song ul li:eq('+index+') .songpause').css('display','block');
	$('.song ul li:eq('+index+') .songplaytext').css('display','none');
	$('.song ul li:eq('+index+') .songpausetext').css('display','block');
});

$(".song ul li").mouseover(function(){
	var index=$(this).index();
	$(".song ul li:eq("+index+")").css('background','#fff');
});

$(".song ul li").mouseout(function(){
	var index=$(this).index();
	$(".song ul li:eq("+index+")").css('background','#eee');
});

/*自动播放下一首*/
$("#jquery_jplayer").onSoundComplete(function(){
	var total=$(".song ul li").length;
	var currentIndex = $(".song ul li .p").parent().parent().index();
	++currentIndex;
	if(currentIndex >= total)
	{
		$("#jquery_jplayer").stop();
		return false;
	}
	$('.song ul li .songimg').removeClass('p').css('display','');
	$(".song ul li:eq("+currentIndex+") .songimg:eq(0)").addClass('p').css('display','none').parent().parent().siblings().children().children(".img").css('display','block');
	$(".song ul li:eq("+currentIndex+") .songimg:eq(0)").parent().parent().siblings().children().children(".pause").css('display','none');
	$(".song ul li:eq("+currentIndex+") .songimg:eq(0)").parent().parent().siblings().children().children(".play").css('display','none');
	$('.song ul li:eq('+currentIndex+') .songpause').css('display','block');
	var src=$(".song ul li:eq("+currentIndex+") .songimg").parent().parent().attr("jplayer");
	var id=$(".song ul li:eq("+currentIndex+") .songimg").parent().parent().attr("id");
	/*更新点播次数*/
	$.ajax({
		type:'post',
		url:'do.php',
		data:'action=update_have_played&id='+id,
		success:function(){}
		});
	$("#jquery_jplayer").changeAndPlay(src);
});

//文章列表
if($.browser.version>6){
	$(".content .ul li").mouseover(function(){
		var index=$(this).index();
		$(".content .ul li:eq("+index+")").css({'background':'#fff'});
	});
	$(".content .ul li").mouseout(function(){
		var index=$(this).index();
		$(".content .ul li:eq("+index+")").css({'background':'#eee'});
	});
}
get_otherlike();
get_maybelike();
get_maybelike_artist();
get_otherlike_artist();
get_maybelike_();

$("#comment_ .ls li").mouseover(function(){
	var index=$(this).index();
	$("#comment_ .ls li:eq("+index+")").css({'background':'#fff','color':'#e7999a'});
});

$("#comment_ .ls li").mouseout(function(){
	var index=$(this).index();
	$("#comment_ .ls li:eq("+index+")").css({'background':'#eee','color':'#626262'});
});

$("#hot_c .ls li").mouseover(function(){
	var index=$(this).index();
	$("#hot_c .ls li:eq("+index+")").css({'background':'#fff','color':'#e7999a'});
});

$("#hot_c .ls li").mouseout(function(){
	var index=$(this).index();
	$("#hot_c .ls li:eq("+index+")").css({'background':'#eee','color':'#626262'});
});

//注册提交检查
$('#reg_submit').click(function(){
	var username = $('#reg_username').val();
	if(!username){
		alert('请输你的名字！');
		return false;
	}
	if(username.length > 15){
		alert('你的名字过长，想个短点又好记的吧！');
		return false;
	}
	if(username.match('\\s+')){
		alert('输入有非法字符！');
		return false;
	}
	var email = $('#reg_email').val();
	if(!email){
		alert('请输入E-Mail地址！');
		return false;
	}
	if(!email.match('\\w+[@]\\w+[.]\\w+')){
		alert('E-Mail地址不合法！');
		return false;
	}
	var pwd1 = $('#reg_pwd1').val();
	if(!pwd1){
		alert('密码不能为空！');
		return false;
	}
	var pwd2 = $('#reg_pwd2').val();
	if(!pwd2){
		alert('密码不能为空！');
		return false;
	}
	if(pwd1 != pwd2){
		alert('两次输入密码不一致！');
		return false;
	}
})

//登录
$('#login_submit').click(function(){
	var username = $('#login_username').val();
	if(!username){
		alert('请输你的帐号！');
		return false;
	}
	var pwd = $('#login_pwd').val();
	if(!pwd){
		alert('密码不能为空！');
		return false;
	}
})

//修改签名
$('.edit_sign').click(function(){
	$(this).css('display','none');
	$('.sign').css('display','none');
	$('.sign_area').css('display','block');
	$('.edit_sign_submit').css('display','block');
});

$('.edit_sign_submit').click(function(){
	var old_sign = $.trim($('.sign').html());
	var sign = $.trim($('#sign_content').val());
	if(sign.length>100){
		alert('亲，你的签名长度大于100！');
		return false;
	}
	if(old_sign != sign){
		$.ajax({
			type:'post',
			url:'do.php',
			data:'action=update_sign&uid='+uid+'&sign='+sign,
			success:function(data){
				if(sign){
					$('.sign').html(sign);
					location.reload();
				}
			}
		});
	}
	$(this).css('display','none');
	$('.edit_sign').css('display','block');
	$('.sign').css('display','block');
	$('.sign_area').css('display','none');
});

//编辑个人资料
$('#step1').click(function(){
	var nick = $('#nick').val();
	if(!nick){
		alert('请输入你的昵称！');
		return false;
	}
});

//选择自己喜欢的音乐类型
select_my_favourite('.music_style_btn','#music_style','style');
//选择自己喜欢的个歌手
select_my_favourite('.music_srtist_btn','#music_srtist','artist');

$('#next_artist_').click(function(){
	$.ajax({
		type:'post',
		url:'do.php',
		data:'action=next_artist',
		success:function(data){
			var myobj=eval(data);
			var html='';
			if(myobj){
				if(myobj.length>=1){
					for(var i=0;i<myobj.length;i++){
						html+='<li style="width:170px; text-align:left"><a href="javascript:void(0)" title="'+myobj[i].artist+'" class="music_srtist_btn">'+myobj[i].artist+'</a></li>';
					}
				}
				$('#music_srtist_list').html(html);
				select_my_favourite('.music_srtist_btn','#music_srtist');
			}
		}
	});
});

//删除好友
$('.friend_list .li .info .nick').mouseover(function(){
	var index = $(this).parent().parent().index();
	$('.friend_list .li:eq('+index+') .info .nick #delete_friend').css('display','block');
});
$('.friend_list .li .info .nick').mouseout(function(){
	var index = $(this).parent().parent().index();
	$('.friend_list .li:eq('+index+') .info .nick #delete_friend').css('display','none');
});

//换一批好友推荐
$('#next_friend_introduce').click(function(){
	var url = document.URL;
	var from = url.substring(url.indexOf('mod=')+4);
	$.ajax({
		type:'post',
		url:'do.php',
		data:'action=next_friend_introduce',
		success:function(data){
			var myobj=eval(data);
			if(myobj&&myobj.length>=1){
				var html = '';
				for(var i=0;i<myobj.length;i++){
					html += '<li><a href="'+myobj[i].url+'api.php?skin=gray&action=user&mod=index&look=true&uid='+myobj[i].id+'" title="点击查看'+myobj[i].nick+'的主页"><img src="'+myobj[i].avatar+'" width="70" height="70" border="0"/></a><br/><a href="'+myobj[i].url+'api.php?skin=gray&action=user&mod=add_friend&uid='+myobj[i].id+'&from='+from+'" title="加'+myobj[i].nick+'为好友">加好友</a></li>';
				}
				$('.friend_introduce ul').html(html);
			}
		}
	});
});

//增加一个专辑地址栏
$('#add_btn').click(function(){
	var number = $('.user_album_info .url').length + 1;
	var html = '<tr class="tr"><td colspan="3"><div><input type="text" name="song_url[]" class="text url" style="width:390px; margin-right:10px" value="请填写歌曲的url地址，目前只支持mp3文件..." onclick="this.value=\'http://\'"/><input type="text" name="song_title[]" class="text" style="width:140px;" value="请填写歌曲名称..." onclick="this.value=\'\'"/></div><div class="close" title="删除本地址栏" onClick="this.parentNode.parentNode.removeChild(this.parentNode)">&nbsp;x</div></td></tr>';
	$('.user_album_info').append(html);
});

//用户专辑提交前检查
$('#add_album_finish').click(function(){
	var name = $('#user_album_name').val();
	var artist = $('#user_album_artist').val();
	var category = $('#user_album_category').val();
	var url_obj = $('.url');
	var song_title_obj = $('.song_title');
	if(name==''){
		alert('请输入专辑名称！');
		return false;
	}
	if(artist==''){
		alert('请输入歌手名称！');
		return false;
	}
	if(category==''){
		alert('请选择专辑风格！');
		return false;
	}
	var size = url_obj.length;
	for(var i = 0; i < size; i++){
		var url = $('.url:eq('+i+')').val();
		if(!url.match('http://([\w-]+\.)+[\w-]+(/[\w- ./?%&=]*)?')){
			alert('你的第'+(i+1)+'首歌的url地址有误！');
			return false;
		}
		var title = $('.song_title:eq('+i+')').val();
		if(title==''){
			alert('你的第'+(i+1)+'首歌没有填写歌名！');
			return false;
		}
	}
});

//底部
});

function select_my_favourite(class_,id_,name_){
	$(class_).click(function(){
		var name = $.trim($(this).attr('title'));
		var tmp = '';
		var bool = true;
		$(id_+' .checkbox').each(function(index, element) {
			tmp = $.trim($(id_+' .checkbox:eq('+index+')').val());
			if(tmp == name){
				bool = false;
			}
		});
		if(!bool){
			return false;
		}
		var html = '<label style="color:#d06e6f;font-size:14px;margin:0 10px 0 0"><input checked="checked" name="'+name_+'[]" class="checkbox" type="checkbox" style="display:none" value="'+name+'"/>'+name+'<img src="templates/template/gray/images/x.png" width="12" height="12" style="cursor:pointer" title="取消'+name+'" onClick="this.parentNode.parentNode.removeChild(this.parentNode)"/></label><style>'+id_+'{width:100%;height:auto}</style>';
		$(id_).append(html);
	});
}
