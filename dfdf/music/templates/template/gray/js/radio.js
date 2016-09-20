var px = 0;
$(function(){
	
	/*音乐电台*/
	$("#jquery_jplayer").jPlayer({
		ready:function(){ 
		var obj = $('.radio .channel .channel_list .current');
		index = obj.index();
		px += -index*31;
		$('.radio .channel .channel_list').css({'margin-top':px});
		obj.css({'margin-top':-px});
		obj = $('.radio .channel .channel_list .current');
		change_channel(obj.attr('channel'),obj.attr('radio'),$('#init_song').attr('value'));
	},
	/*设置flash文件路径*/
	swfPath:"templates/template/gray/js"
	});
	
	
	//换台
	$('#change').click(function(){
		$('.radio .channel .channel_list li').css('display','block');
		$('.radio .channel .channel_list').css('margin-top', px+'px');
		$('.radio .channel .channel_list .current').css({'margin-top':0});
		$('#change').css('display','none');
		$('#close').css('display','block');
	});
	$('#close').click(function(){
		$('.radio .channel .channel_list .common').css('display','none');
		$('.radio .channel .channel_list').css('margin-top', 0);
		$('#change').css('display','block');
		$('#close').css('display','none');
	});
	
	//切换频道
	$('.radio .channel .channel_list li').click(function(){
		index=$(this).index();
		last_index=$('.radio .channel .channel_list .current').index();
		if(index==last_index){
			return false;
		}
		$('.radio .channel .channel_list li').removeClass('current').removeClass('onmouse').addClass('common');
		if($(this).attr('class').split(' ')[0]=='last_channel'){
			$('.radio .channel .channel_list li:eq('+index+')').removeClass('last_channel').removeClass('onmouse').removeClass('common').addClass('current').addClass('onmouse').addClass('last_channel');
		} else {
			$('.radio .channel .channel_list li:eq('+index+')').removeClass('onmouse').removeClass('common').addClass('current').addClass('onmouse');}
		px += (last_index-index)*31;
		$('.radio .channel .channel_list').animate({'margin-top':px},800);
		var obj = $('.radio .channel .channel_list .current');
		change_channel(obj.attr('channel'),obj.attr('radio'),0);
	});
	
	//鼠标滑过
	$('.radio .channel .channel_list li').mouseover(function(){
		$(this).addClass('onmouse').removeClass('common');
	});
	$('.radio .channel .channel_list li').mouseout(function(){
		if($(this).attr('class').split(' ')[0]!='current'){
			$(this).addClass('common').removeClass('onmouse');
		}
	});


//播放
$('.radio_play').click(function(){
	play();
});
//暂停
$('.radio_pause').click(function(){
	pause();
});
//下一首
$('.radio_next').click(function(){
	var obj = $('.radio .channel .channel_list .current');
	change_channel(obj.attr('channel'),obj.attr('radio'),0);
});







$("#jquery_jplayer").onSoundComplete(function(){
	var obj = $('.radio .channel .channel_list .current');
	change_channel(obj.attr('channel'),obj.attr('radio'),0);
});


/**
 * 播放进度
 */
$("#jquery_jplayer").onProgressChange( function(loadPercent, playedPercentRelative, playedPercentAbsolute, playedTime, totalTime) {
var myPlayedTime = new Date(playedTime);
var ptMin = (myPlayedTime.getMinutes() < 10) ? "0" + myPlayedTime.getMinutes() : myPlayedTime.getMinutes();
var ptSec = (myPlayedTime.getSeconds() < 10) ? "0" + myPlayedTime.getSeconds() : myPlayedTime.getSeconds();
$("#play_time").text(ptMin+":"+ptSec);
});


$("#jquery_jplayer").jPlayerId("loadBar", "player_progress_load_bar");
$("#jquery_jplayer").jPlayerId("playBar", "player_progress_play_bar");

//点击进度条，继续播放
$('#player_progress_load_bar').click(function(){
	play();
});

$('#player_progress_play_bar').click(function(){
	play();
});


});



/**
 * 切换频道
 */
function change_channel(channel,radio,init){
	$.ajax({
		type:'post',
		url:'do.php',
		data:'action=radio&channel='+channel+'&radio='+radio+'&id='+init,
		success:function(data)
		{	
			$('#init_song').attr('value',0);
			$('.radio_play').css('display','none');
			$('.radio_pause').css('display','block');
			data = data.split('||');
			title = data[0];
			artist = data[1];
			pic = data[2];
			album = data[5];
			src = data[3];
			id = data[6];
			album_id = data[4];
			base_url = data[7];
			$("#jquery_jplayer").changeAndPlay(src);
			$('.song_title').html(title);
			$('.songer').html(artist);
			var obj = $('.radio_right_top_album');
			obj.html('<a href="'+base_url+'album.php?skin=gray&id='+album_id+'" target="_blank" title="点击查看专辑《'+album+'》" onClick="pause()">专辑《'+album+'》</a>');
			$('.radio_pic').html('<a href="'+base_url+'album.php?skin=gray&id='+album_id+'" target="_blank" title="点击查看专辑《'+album+'》"><img src="'+base_url+'thumbs/thumbs/cache/w190h190/admin/'+pic+'" width="190" height="190" onClick="pause()"/></a>');
			obj.attr('this_id',id);
			$.ajax({
				type:'post',
				url:'do.php',
				data:'action=count&channel='+channel,
				success:function(){}
			})
		}
	});
}

function pause(){
	$("#jquery_jplayer").pause();
	$('.radio_pause').css('display','none');
	$('.radio_play').css('display','block');
}

function play(){
	$("#jquery_jplayer").play();
	$('.radio_play').css('display','none');
	$('.radio_pause').css('display','block');
}
