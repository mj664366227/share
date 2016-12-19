$(function(){
	
	/*音乐电台*/
	$("#jquery_jplayer").jPlayer({
		ready:function(){
		var str=$('.c:eq(0)').attr('en');//获取频道
		var channel=$('.c:eq(0)').attr('value');//获取频道
		$.ajax({
		type:'post',
		url:'do.php',
		data:'action=radio&channel='+str,
		success:function(data)
		{
			$('form input').attr('value',str);
			$('.current').text(channel);
			do_my_job(data);
				$.ajax({
					type:'post',
					url:'do.php',
					data:'action=count&channel='+str,
					success:function(){}
				})
	}
		
		
	
	});
	
	
	},
	/*设置flash文件路径*/
	swfPath:"templates/template/deafault/js"
	});

$('.change').click(function(){
	$('.channel').fadeIn(700);
	$('.radio_close').css('background','url(templates/template/deafault/images/radio_close.png)');
})

$(".c").click(function(){
	var index=$(this).index();
	var str=$('.c:eq('+index+')').attr('value');
	var c=$('.c:eq('+index+')').attr('en');//获取频道
	$('form input').attr('value',c);
	$('.current').text(str);
	var current=$('form input').attr('value');
	$.ajax({
		type:'post',
		url:'do.php',
		data:'action=radio&channel='+c,
		success:function(data)
		{
			do_my_job(data);
		}
	})
	
	$.ajax({
		type:'post',
		url:'do.php',
		data:'action=count&channel='+c,
		success:function(){}
	})
});


$('.c').mouseover(function(){
	$(this).css('background','#b9b9b9');
})

$('.c').mouseout(function(){
	$(this).css('background','#999');
})

$('.radio_close').mouseover(function(){
	$(this).css('background','url(templates/template/deafault/images/radio_close1.png)');
})

$('.radio_close').mouseout(function(){
	$(this).css('background','url(templates/template/deafault/images/radio_close.png)');
})

$('.radio_close').click(function(){
	$('.channel').fadeOut(700);
})

$("#jquery_jplayer").onSoundComplete(function(){
	var c=$('form input').attr('value');
    $.ajax({
		type:'post',
		url:'do.php',
		data:'action=radio&channel='+c,
		success:function(data)
		{
			do_my_job(data);
		}
	});
});

$(".radio_play").click(function(){
	$("#jquery_jplayer").play();
	$(".radio_play").css('display','none');
	$(".radio_pause").css('display','block');
});

$(".radio_pause").click(function(){
	$("#jquery_jplayer").pause();
	$(".radio_play").css('display','block');
	$(".radio_pause").css('display','none');
});

$(".radio_next").click(function(){
	var c=$('form input').attr('value');
    $.ajax({
		type:'post',
		url:'do.php',
		data:'action=radio&channel='+c,
		success:function(data)
		{
			do_my_job(data);
		}
		});
	});
	
//弹窗
$(".window").click(function(){
	$("#jquery_jplayer").pause();
	$(".radio_play").css('display','block');
	$(".radio_pause").css('display','none');
});

function do_my_job(data)
{
	s=data.split("||");
	$('.the_title').html('<a href="'+s[3]+'" title="我要下载《'+s[0]+'》！"><font color="#ff0066">'+s[0]+'</font></a><style>.the_title a{text-decoration:none}.the_title a:hover{text-decoration:underline}.the_pic img{border:none}</style><!--[if IE 6]><style>.radio_pause{margin-left:3px}</style><![endif]-->');
	$('.the_artist').text(s[1]);
	$('.the_pic').html('<a href="album.php?id='+s[4]+'#'+s[6]+'" title="'+s[5]+'" target="_blank"><img src="http://lovemusic.cc/thumbs/thumbs/cache/w190h190/admin/'+s[2]+'" width="190" height="190"/></a>');
	$("#jquery_jplayer").changeAndPlay(s[3]);
	$(".radio_pause").css('display','block');
	$(".radio_play").css('display','none');
}
});
