$(function(){
	//原来，自动播放要把代码放在第一行，而且是ready函数里面，哈哈哈
$("#jquery_jplayer").jPlayer({
	//自动播放
    ready: function () {
	$("ul.playlist_content li").addClass("playlist_current").siblings().removeClass("playlist_current");
    var name = $(".playlist_content li:eq("+0+")").attr("songname");
    $("#songname span").text(name);
	var songer = $("ul.playlist_content li").attr("songer");
	$("#singer span").text(songer);
	var album = $("ul.playlist_content li").attr("album");
	$("#album span").text(album);
	var src = $("ul.playlist_content li").attr("jplayer");
	$("ul.playlist_content li").addClass("playlist_current");
	$("#jquery_jplayer").changeAndPlay(src);
		},
		/*设置flash文件路径*/
		swfPath:"templates/template/deafault/js"
	});

/*按钮*/
$("#jquery_jplayer").jPlayerId("play", "player_play");
$("#jquery_jplayer").jPlayerId("pause", "player_pause");
$("#jquery_jplayer").jPlayerId("stop", "player_stop");
$("#jquery_jplayer").jPlayerId("loadBar", "player_progress_load_bar");
$("#jquery_jplayer").jPlayerId("playBar", "player_progress_play_bar");
$("#jquery_jplayer").jPlayerId("volumeMin", "player_volume_min");
$("#jquery_jplayer").jPlayerId("volumeMax", "player_volume_max");
$("#jquery_jplayer").jPlayerId("volumeBar", "player_volume_bar");
$("#jquery_jplayer").jPlayerId("volumeBarValue", "player_volume_bar_value");

/*播放*/
$("#player_play").click(function(){
	$('#jquery_jplayer').play();
	return false;
});
	
/*暂停*/
$("#player_pause").click(function(){
	$('#jquery_jplayer').pause(); 
	return false;
});

/*选择播放*/
$("ul.playlist_content li").click(function(){
	$(this).addClass("playlist_current").siblings().removeClass("playlist_current");
	var currentIndex=$(this).index();
    var name = $(".playlist_content li:eq("+currentIndex+")").attr("songname");
    $("#songname span").text(name);
	var songer = $(this).attr("songer");
	$("#singer span").text(songer);
	var album = $(this).attr("album");
	$("#album span").text(album);
	var src = $(this).attr("jplayer");
	$(this).addClass("playlist_current");
	$("#jquery_jplayer").changeAndPlay(src);
	return false;
});

/*自动下一首*/
$("#jquery_jplayer").onSoundComplete(function(){
	//$("#jquery_jplayer").play();/*单曲重播*/ 
	var total=$("ul.playlist_content li").length;
    //获取当前li的序号
	var currentIndex = $("ul.playlist_content li").index($(".playlist_current"));
	++currentIndex;
	if(currentIndex>total-1)
	{
		$("#jquery_jplayer").stop();
		return false;
	}
    var name = $(".playlist_content li:eq("+currentIndex+")").attr("songname");
    $("#songname span").text(name);
	var songer = $("ul.playlist_content li").attr("songer");
	$("#singer span").text(songer);
	var album = $("ul.playlist_content li").attr("album");
	$("#album span").text(album);
	var src = $("ul.playlist_content li").attr("jplayer");
	var src = $("ul.playlist_content li:eq("+currentIndex+")").attr("jplayer");
	$("ul.playlist_content li").addClass("playlist_current").siblings().removeClass("playlist_current");
	$("ul.playlist_content li:eq("+currentIndex+")").addClass("playlist_current");
	$("#jquery_jplayer").changeAndPlay(src);
	return false;
});

/*手动切换下一首*/
$("#player_next").click(function(){
	//获取列表总长度
	var total=$("ul.playlist_content li").length;
    //获取当前li的序号
	var currentIndex = $("ul.playlist_content li").index($(".playlist_current"));
	++currentIndex;
	if(currentIndex>total-1)
	{
		alert("这是最后一首歌！");
		return false;
	}
    var name = $(".playlist_content li:eq("+currentIndex+")").attr("songname");
    $("#songname span").text(name);
	var songer = $("ul.playlist_content li").attr("songer");
	$("#singer span").text(songer);
	var album = $("ul.playlist_content li").attr("album");
	$("#album span").text(album);
	var src = $("ul.playlist_content li:eq("+currentIndex+")").attr("jplayer");
	$("ul.playlist_content li").addClass("playlist_current").siblings().removeClass("playlist_current");
	$("ul.playlist_content li:eq("+currentIndex+")").addClass("playlist_current");
	$("#jquery_jplayer").changeAndPlay(src);
});

/*手动切换上一首*/
$("#player_prev").click(function(){
	//获取列表总长度
	var total=$("ul.playlist_content li").length;
    //获取当前li的序号
	var currentIndex = $("ul.playlist_content li").index($(".playlist_current"));
	--currentIndex;
	if(currentIndex<0)
	{
		alert("这是第一首歌！");
		return false;
	}
	var name = $(".playlist_content li:eq("+currentIndex+")").attr("songname");
    $("#songname span").text(name);
	var songer = $("ul.playlist_content li").attr("songer");
	$("#singer span").text(songer);
	var album = $("ul.playlist_content li").attr("album");
	$("#album span").text(album);
	var src = $("ul.playlist_content li:eq("+currentIndex+")").attr("jplayer");
	$("ul.playlist_content li").addClass("playlist_current").siblings().removeClass("playlist_current");
	$("ul.playlist_content li:eq("+currentIndex+")").addClass("playlist_current");
	$("#jquery_jplayer").changeAndPlay(src);
});

/*播放进度*/
/*
$("#jquery_jplayer").onProgressChange( function(loadPercent, playedPercentRelative, playedPercentAbsolute, playedTime, totalTime) {
var myPlayedTime = new Date(playedTime);
var ptMin = (myPlayedTime.getMinutes() < 10) ? "0" + myPlayedTime.getMinutes() : myPlayedTime.getMinutes();
var ptSec = (myPlayedTime.getSeconds() < 10) ? "0" + myPlayedTime.getSeconds() : myPlayedTime.getSeconds();
$("#play_time").text(ptMin+":"+ptSec);
var myTotalTime = new Date(totalTime);
var ttMin = (myTotalTime.getMinutes() < 10) ? "0" + myTotalTime.getMinutes() : myTotalTime.getMinutes();
var ttSec = (myTotalTime.getSeconds() < 10) ? "0" + myTotalTime.getSeconds() : myTotalTime.getSeconds();
$("#total_time").text(ttMin+":"+ttSec);
});
*/


});