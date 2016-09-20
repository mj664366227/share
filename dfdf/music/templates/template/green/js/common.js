/***********************************公共部分js***************************************/

$(function(){
//首页图片滑动效果

//初始化，显示第一幅图片
$('.slide_content_list ul li:eq('+0+')').fadeIn(400).addClass('current');
$('.slide_content_list span:eq('+0+')').slideDown(400).addClass('current1');
$('.slide_nav .dddd:eq('+0+')').addClass('mouseover');

//下一页
$('.next').click(function(){
	var len=$('.slide_content_list ul li').length;
	current=$('.slide_content_list ul li').index($('.current'));
	current++;
	if(current+1>len)
	{
		current=0;
	}
	$('.slide_content_list ul li:eq('+current+')').fadeIn(400).addClass('current').siblings('.current').removeClass("current").removeAttr('style');
	$('.slide_content_list span:eq('+current+')').slideDown(400).addClass('current1').siblings().removeClass("current1").removeAttr('style');
	$('.slide_nav .dddd:eq('+current+')').addClass('mouseover').siblings().removeClass("mouseover");
});

//上一页
$('.prev').click(function(){
	var len=$('.slide_content_list ul li').length;
	current=$('.slide_content_list ul li').index($('.current'));
	current--;
	if(current<0)
	{
		current=len-1;
	}
	$('.slide_content_list ul li:eq('+current+')').fadeIn(400).addClass('current').siblings('.current').removeClass("current").removeAttr('style');
	$('.slide_content_list span:eq('+current+')').slideDown(400).addClass('current1').siblings().removeClass("current1").removeAttr('style');
	$('.slide_nav .dddd:eq('+current+')').addClass('mouseover').siblings().removeClass("mouseover");
});

//点点鼠滑动标效果
$('.slide .slide_nav .dddd').mouseover(function(){
	$(this).addClass('pointer').siblings().removeClass('pointer');
	var current=$('.slide .slide_nav .pointer').index();
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
	var current=$('.slide .slide_nav .click').index();
	current--;
	$('.slide_content_list ul li:eq('+current+')').fadeIn(400).addClass('current').siblings('.current').removeClass("current").removeAttr('style');
	$('.slide_content_list span:eq('+current+')').slideDown(400).addClass('current1').siblings().removeClass("current1").removeAttr('style');
	$('.slide_nav .dddd:eq('+current+')').addClass('click mouseover').siblings().removeClass('click mouseover');	
});

//上一张，下一张
$('.slide .slide_nav .prev').mouseover(function(){
	$(this).addClass('pointer').addClass('left_arrow_blue');
});
$('.slide .slide_nav .prev').mouseout(function(){
	$(this).removeClass('pointer').removeClass('left_arrow_blue');
});

$('.slide .slide_nav .next').mouseover(function(){
	$(this).addClass('pointer').addClass('right_arrow_blue');
});
$('.slide .slide_nav .next').mouseout(function(){
	$(this).removeClass('pointer').removeClass('right_arrow_blue');
});

//自动跳转 
/*
function autoSlide()
{
    var len=$('.slide_content_list ul li').length;
	current=$('.slide_content_list ul li').index($('.current'));
	current++;
	if(current+1>len)
	{
		current=0;
	}
	$('.slide_content_list ul li:eq('+current+')').fadeIn(400).addClass('current').siblings('.current').removeClass("current").removeAttr('style');
	$('.slide_content_list span:eq('+current+')').slideDown(400).addClass('current1').siblings().removeClass("current1").removeAttr('style');
	$('.slide_nav .dddd:eq('+current+')').addClass('mouseover').siblings().removeClass("mouseover");
}
*/
//setInterval('autoSlide()',1000)；


/*****************************************************************************************************************/
/*****************************************************************************************************************/
/*****************************************************************************************************************/
/*****************************************************************************************************************/
/*****************************************************************************************************************/

//首页音乐推荐的效果

function show(father,child)
{
	$(father).mouseover(function(){
		$(child).animate({top:129},500);
		//$(child).css('display','block');
	})
}

//高度预处理
$('.music_introduction_list li a span').css('top','191px');

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
show('#on10','#show10');
show('#on11','#show11');
show('#on12','#show12');
show('#on13','#show13');
show('#on14','#show14');
show('#on15','#show15');
show('#on16','#show16');
show('#on17','#show17');
show('#on18','#show18');
show('#on19','#show19');
show('#on20','#show20');
show('#on21','#show21');
show('#on22','#show22');
show('#on23','#show23');
show('#on24','#show24');
show('#on25','#show25');

//鼠标离开的效果
$('.music_introduction_list img').mouseout(function(){
$('.music_introduction_list li a span').animate({top:191},500);
});

//专辑页面
/*全选*/
$('#selectall').click(function(){
$('[name=chk]:checkbox').attr('checked', true);
})

/*反选*/
$('#selectnotall').click(function(){
$('[name=chk]:checkbox').each(function(){
this.checked=!this.checked;
});
})

/*下载选中歌曲*/
$('#down').click(function(){
var str='';
$('[name=chk]:checkbox:checked').each(function(){
str+=$(this).attr('src')+"\n";
});
if(str=='')
{
	alert('你没有选中任何歌曲！');
	return false;
}
else
{
	clipboardData.setData('text',str);
}
});

/*播放选中歌曲*/
/*
$('#play').click(function(){
var str='';
$('[name=chk]:checkbox:checked').each(function(){
str+=$(this).val()+"\n";
});
if(str=='')
{
	alert('你没有选中任何歌曲！');
	return false;
}
else
{
	s=str.split("\n");
	alert(s);
}
})
*/

/*****************************************************************************************************************/
/*****************************************************************************************************************/
/*****************************************************************************************************************/
/*****************************************************************************************************************/
/*****************************************************************************************************************/

//资源页广告位
$('.ad .slide .slide_nav .ddd').click(function(){
	$(this).addClass('click').siblings().removeClass('click mouseover');
	var current=$('.slide .slide_nav .click').index();
	current;
	$('.slide_content_list ul li:eq('+current+')').fadeIn(400).addClass('adcurrent').siblings('.adcurrent').removeClass("adcurrent").removeAttr('style');
	$('.slide_nav .ddd:eq('+current+')').addClass('adcurrent').siblings().removeClass('adcurrent');	
});

//鼠滑动标效果
$('.ad .slide .slide_nav .ddd').mouseover(function(){
	$(this).addClass('pointer').siblings().removeClass('pointer');
	var current=$('.slide .slide_nav .pointer').index();
	current;
	$('.slide .slide_nav .ddd:eq('+current+')').addClass('onmouseover');
});
$('.ad .slide .slide_nav .ddd').mouseout(function(){
	var current=$('.slide .slide_nav .pointer').index();
	$('.slide .slide_nav .ddd').removeClass('onmouseover');
});
//底部
});