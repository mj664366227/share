<?php /* Smarty version Smarty3-b8, created on 2016-09-20 21:24:16
         compiled from "D:\nginx/www/share.git/somethingmusic/templates/template/gray\album.html" */ ?>
<?php /*%%SmartyHeaderCode:263457e13880f084c8-03392984%%*/if(!defined('SMARTY_DIR')) exit('no direct access allowed');
$_smarty_tpl->decodeProperties(array (
  'file_dependency' => 
  array (
    '366f5f82ebb27384d4f8b3e23f8bd58786b6862d' => 
    array (
      0 => 'D:\\nginx/www/share.git/somethingmusic/templates/template/gray\\album.html',
      1 => 1343982380,
    ),
  ),
  'nocache_hash' => '263457e13880f084c8-03392984',
  'function' => 
  array (
  ),
  'has_nocache_code' => false,
)); /*/%%SmartyHeaderCode%%*/?>
<?php if (!is_callable('smarty_modifier_trim')) include 'D:\nginx\www\share.git\somethingmusic\lib\Smarty\plugins\modifier.trim.php';
if (!is_callable('smarty_modifier_strip_tags')) include 'D:\nginx\www\share.git\somethingmusic\lib\Smarty\plugins\modifier.strip_tags.php';
if (!is_callable('smarty_modifier_strip')) include 'D:\nginx\www\share.git\somethingmusic\lib\Smarty\plugins\modifier.strip.php';
if (!is_callable('smarty_modifier_truncate')) include 'D:\nginx\www\share.git\somethingmusic\lib\Smarty\plugins\modifier.truncate.php';
if (!is_callable('smarty_modifier_stripslashes')) include 'D:\nginx\www\share.git\somethingmusic\lib\Smarty\plugins\modifier.stripslashes.php';
if (!is_callable('smarty_modifier_trim_simhei')) include 'D:\nginx\www\share.git\somethingmusic\lib\Smarty\plugins\modifier.trim_simhei.php';
?><?php  $_smarty_tpl->tpl_vars['s'] = new Smarty_Variable;
 $_from = $_smarty_tpl->getVariable('song')->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
if (count($_from) > 0){
    foreach ($_from as $_smarty_tpl->tpl_vars['s']->key => $_smarty_tpl->tpl_vars['s']->value){
?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="<?php echo $_smarty_tpl->getVariable('s')->value['artist'];?>
-《<?php echo $_smarty_tpl->getVariable('s')->value['title'];?>
》,飞车坊,华汽飞车坊,somethingmusic,lovemusic,流行音乐,小众音乐,音乐电台,jplayer,有内涵的音乐,somethingmusic音乐电台,最新最快最全的音乐。" />
<meta name="description" content="<?php echo $_smarty_tpl->getVariable('s')->value['artist'];?>
-《<?php echo smarty_modifier_trim($_smarty_tpl->getVariable('s')->value['title']);?>
》专辑介绍：<?php echo smarty_modifier_truncate(smarty_modifier_strip(smarty_modifier_strip_tags($_smarty_tpl->getVariable('s')->value['text'])),250);?>
,somethingmusic音乐电台,最新最快最全的音乐。" />
<link rel="shortcut icon" href="favicon.ico"/>
<link rel="stylesheet" href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
templates/template/gray/css/css.css" type="text/css" />
<!--[if IE 6]>
<link rel="stylesheet" href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
templates/template/gray/css/ie6.css" type="text/css" />
<script type="text/javascript" src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
templates/template/gray/js/DD_belatedPNG_0.0.8a.js"></script><script type="text/javascript">DD_belatedPNG.fix('*');</script>
<![endif]-->
<script type="text/javascript" src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
templates/template/gray/js/jquery-1.5.1.min.js"></script>
<script type="text/javascript" src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
templates/template/gray/js/common.js"></script>
<script type="text/javascript" src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
templates/template/gray/js/jquery.jplayer.js"></script>
<title><?php echo $_smarty_tpl->getVariable('s')->value['artist'];?>
-《<?php echo smarty_modifier_stripslashes(smarty_modifier_trim($_smarty_tpl->getVariable('s')->value['title']));?>
》  somethingmusic音乐电台 - 我们一直在努力！</title>
</head>
<body>
<div id="jquery_jplayer"></div>
<div class="pms"></div>
<div class="body">
<div class="header"> 
  <!--logo-->
  <div class="logo"><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
?skin=gray" title="<?php echo $_smarty_tpl->getVariable('web_title')->value;?>
"><img src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
templates/template/gray/images/logo.png" border="0"/></a></div>
  <!--搜索框-->
  <div class="search">
    <form action="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
search.php?skin=gray&action=search" method="post">
      <input type="text" class="search_input" id="search_input" name="search_input" onclick="value=''" autocomplete="off" <?php if ($_smarty_tpl->getVariable('key_words')->value){?>value="<?php echo $_smarty_tpl->getVariable('key_words')->value;?>
"<?php }?> x-webkit-speech lang="zh-CN" x-webkit-grammar="bUIltin:search"/>
      <div id="submit" title="搜！">
        <input type="image" src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
templates/template/gray/images/search.png" width="24" height="24" id="search_submit"/>
      </div>
      <div class="result"></div>
    </form>
  </div>
  <!--顶部链接-->
  <div class="top">
<ul>
      <a href="http://vod.libmill.com" target="_blank">
      <li class="vod" title="视频点播">视频点播</li>
      </a> <a href="http://libmill.com" target="_blank">
      <li class="dh" title="校内导航">校内导航</li>
      </a> <a href="http://lib.gcu.edu.cn" target="_blank">
      <li class="lib" title="图书馆">图书馆</li>
      </a> <a href="http://www.gcu.edu.cn" target="_blank">
      <li class="gzauto" title="学院主页">学院主页</li>
      </a>
    </ul>
  </div>
</div>
<!--导航-->
<div class="nav">
  <ul>
    <a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
?skin=gray" title="网站首页"><li><?php if ($_smarty_tpl->getVariable('active')->value=='index'){?><font color="#d06e6f">首页</font><?php }else{ ?>首页<?php }?></li></a>
<a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
radio.php?skin=gray" title="音乐电台" target="_blank"><li>电台</li></a>
<a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
resources.php?skin=gray&action=all" title="本站资源"><li><?php if ($_smarty_tpl->getVariable('active')->value==''){?><font color="#d06e6f">资源</font><?php }else{ ?>资源<?php }?></li></a>
<a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
musician.php?skin=gray&action=all" title="音乐人介绍"><li><?php if ($_smarty_tpl->getVariable('active')->value=='musician'){?><font color="#d06e6f">音乐人</font><?php }else{ ?>音乐人<?php }?></li></a>
<a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
introduce.php?skin=gray&action=all" title="音乐推荐文章"><li><?php if ($_smarty_tpl->getVariable('active')->value=='introduce'||$_smarty_tpl->getVariable('active')->value=='content'){?><font color="#d06e6f">推荐</font><?php }else{ ?>推荐<?php }?></li></a>
  </ul>
  <?php $_template = new Smarty_Internal_Template("login.html", $_smarty_tpl->smarty, $_smarty_tpl, $_smarty_tpl->cache_id, $_smarty_tpl->compile_id, null, null);
 echo $_template->getRenderedTemplate();?><?php $_template->updateParentVariables(0);?><?php unset($_template);?>

</div>
<!--专辑详细-->
<div class="album">
  <div class="album_card">
    <div class="album_pic"><img src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
thumbs/thumbs/cache/w190h190/admin/<?php echo $_smarty_tpl->getVariable('s')->value['picture'];?>
" width="190" height="190" alt="<?php echo $_smarty_tpl->getVariable('s')->value['title'];?>
"/></div>
    <div class="album_detail" album_id="<?php echo $_smarty_tpl->getVariable('s')->value['id'];?>
"> <span title="《<?php echo smarty_modifier_stripslashes(smarty_modifier_trim($_smarty_tpl->getVariable('s')->value['title']));?>
》">《<?php echo smarty_modifier_truncate(smarty_modifier_stripslashes(smarty_modifier_trim($_smarty_tpl->getVariable('s')->value['title'])),38);?>
》</span>
      <p><font>[歌手名称]</font> <?php echo smarty_modifier_trim($_smarty_tpl->getVariable('s')->value['artist']);?>
</p>
      <p><font>[音乐风格]</font> <?php echo smarty_modifier_trim($_smarty_tpl->getVariable('s')->value['category']);?>
</p>
      <p><font>[歌曲语种]</font> <?php echo smarty_modifier_trim($_smarty_tpl->getVariable('s')->value['language']);?>
</p>
      <p><font>[来源地区]</font> <?php echo smarty_modifier_trim($_smarty_tpl->getVariable('s')->value['area']);?>
</p>
      <p><font>[发行时间]</font> <?php echo smarty_modifier_trim($_smarty_tpl->getVariable('s')->value['time']);?>
</p>
      <p><font>[唱片公司]</font> <?php echo smarty_modifier_trim($_smarty_tpl->getVariable('s')->value['company']);?>
</p>
    </div>
    <?php if ($_smarty_tpl->getVariable('like')->value!=$_smarty_tpl->getVariable('s')->value['id']){?>
    <div class="album_like_out"></div>
    <div class="album_like_on" title="把《<?php echo smarty_modifier_stripslashes(smarty_modifier_trim($_smarty_tpl->getVariable('s')->value['title']));?>
》标记为喜欢"></div>
    <?php }else{ ?>
    <div class="album_like" title="《<?php echo smarty_modifier_stripslashes(smarty_modifier_trim($_smarty_tpl->getVariable('s')->value['title']));?>
》已标记为喜欢"></div>
    <?php }?> </div>
  <div class="album_intro">
    <div class="title"><img src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
templates/template/gray/images/album_detail.png" border="0" width="60" height="20" align="absmiddle"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font>分享到</font> <a href="javascript:(function(){ window.open('http://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey?url='+ encodeURIComponent(location.href)+'&title='+encodeURIComponent('LoveMusic 试听下载<?php echo $_smarty_tpl->getVariable('s')->value['artist'];?>
的专辑《<?php echo $_smarty_tpl->getVariable('s')->value['title'];?>
》'),'_blank'); } )()" title="分享到QQ空间"><img src="http://ctc.qzonestyle.gtimg.cn/qzonestyle/qzone_client_v5/img/favicon.ico" alt="分享到QQ空间" align="absmiddle" width="16" height="16" border="0"/></a> <a href="javascript:void(0);" onclick="window.open('http://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey?to=pengyou&url='+encodeURIComponent(document.location.href));return false;" title="分享到腾讯朋友"><img src="http://qzonestyle.gtimg.cn/ac/qzone_v5/app/qzshare/xy-icon.png" alt="分享到腾讯朋友" width="16" height="16" border="0" align="absmiddle" /></a> <a href="javascript:void(0)" onclick="postToWb();" style="height:16px;font-size:12px;line-height:16px;" title="分享到腾讯微博"><img src="http://v.t.qq.com/share/images/s/weiboicon16.png" align="absmiddle" border="0" width="16" height="16"/></a><script type="text/javascript">function postToWb(){ var _t = encodeURI(document.title);var _url = encodeURIComponent(document.location);var _appkey = encodeURI("appkey");var _pic = encodeURI('<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
admin/<?php echo $_smarty_tpl->getVariable('s')->value['picture'];?>
');var _site = '<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
';var _u = 'http://v.t.qq.com/share/share.php?url='+_url+'&appkey='+_appkey+'&site='+_site+'&pic='+_pic+'&title='+_t;window.open( _u,'', 'width=630, height=480, top=100, left=100, toolbar=no, menubar=no, scrollbars=no, location=yes, resizable=no, status=no' ); }</script> 
      <a rel="nofollow" class="fav_douban" href="javascript:void(function() { var%20d=document,e=encodeURIComponent,s1=window.getSelection,s2=d.getSelection,s3=d.selection,s=s1?s1():s2?s2():s3?s3.createRange().text:'',r='http://www.douban.com/recommend/?url='+e(d.location.href)+'&title='+e('LoveMusic 试听下载<?php echo $_smarty_tpl->getVariable('s')->value['artist'];?>
的专辑《<?php echo $_smarty_tpl->getVariable('s')->value['title'];?>
》')+'& sel='+e(s)+'&v=1',x=function() { if(!window.open(r,'douban','toolbar=0,resizable=1,scrollbars=yes,status=1,width=620,height=360'))location.href=r+'&amp;r=1' } ;if(/Firefox/.test(navigator.userAgent)) { setTimeout(x,0) } else { x() } } )()" title="分享到豆瓣音乐"><img src="http://img3.douban.com/favicon.ico" alt="分享到豆瓣音乐" width="16" height="16" border="0" align="absmiddle"/></a> <a href="javascript:(function() { window.open('http://v.t.sina.com.cn/share/share.php?appkey=<?php echo $_smarty_tpl->getVariable('sina_appid')->value;?>
&title=LoveMusic 试听下载<?php echo $_smarty_tpl->getVariable('s')->value['artist'];?>
的专辑《<?php echo $_smarty_tpl->getVariable('s')->value['title'];?>
》&url='+encodeURIComponent(location.href)+'&source=bookmark&pic=<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
thumbs/thumbs/cache/w190h190/admin/<?php echo $_smarty_tpl->getVariable('s')->value['picture'];?>
','_blank','width=620,height=350'); } )()" title="分享到新浪微博"><img src="http://t.sina.com.cn/favicon.ico"  alt="分享到新浪微博" border="0" width="16" height="16" align="absmiddle"></a> <a hidefocus="true" href="javascript:window.open('http://www.kaixin001.com/repaste/share.php?rtitle='+encodeURIComponent('LoveMusic 试听下载<?php echo $_smarty_tpl->getVariable('s')->value['artist'];?>
的专辑《<?php echo $_smarty_tpl->getVariable('s')->value['title'];?>
》')+'&amp;rurl='+encodeURIComponent(location.href)+'&amp;rcontent=LoveMusic 试听下载<?php echo $_smarty_tpl->getVariable('s')->value['artist'];?>
的专辑《<?php echo $_smarty_tpl->getVariable('s')->value['title'];?>
》','_blank','scrollbars=no,width=600,height=450,left=75,top=20,status=no,resizable=yes'); void 0" onclick="ad_c_m('bbs_pageshare_kaixin001')" id="fx_kx" title="分享到开心网"><img src="http://img1.kaixin001.com.cn/i/favicon.ico" alt="分享到开心网" border="0" height="16" width="16" align="absmiddle"/></a> <a href="javascript:void((function(s,d,e) { if(/xiaonei\.com/.test(d.location))return;var%20f='http://share.xiaonei.com/share/buttonshare.do?link=',u=d.location,l='LoveMusic 试听下载<?php echo $_smarty_tpl->getVariable('s')->value['artist'];?>
的专辑《<?php echo $_smarty_tpl->getVariable('s')->value['title'];?>
》',p=[e(u),'&amp;title=',e(l)].join('');function%20a() { if(!window.open([f,p].join(''),'xnshare',['toolbar=0,status=0,resizable=1,width=636,height=446,left=',(s.width-636)/2,',top=',(s.height-446)/2].join('')))u.href=[f,p].join(''); } ;if(/Firefox/.test(navigator.userAgent))setTimeout(a,0);else%20a(); } )(screen,document,encodeURIComponent));" title="分享人人网"><img src="http://s.xnimg.cn/favicon-rr.ico" alt="分享人人网" border="0" height="16" width="16" align="absmiddle"></a> <a href="javascript:void(0)" title="打包下载" id="down"><img src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
templates/template/gray/images/down.png" width="16" height="16" border="0" align="absmiddle"/></a> </div>
    <br/>
    <br/>
    <br/>
    <?php echo smarty_modifier_trim_simhei(smarty_modifier_trim($_smarty_tpl->getVariable('s')->value['text']));?>
 </div>
  <script type="text/javascript" src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
templates/template/gray/js/webThunderDetect.js"></script>
  <div class="song">
    <div class="title"><img src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
templates/template/gray/images/album_song_list.png" border="0" width="60" height="20" align="absmiddle"/></div>
    <ul>
      <?php  $_smarty_tpl->tpl_vars['m'] = new Smarty_Variable;
 $_from = $_smarty_tpl->getVariable('songlist')->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
if (count($_from) > 0){
    foreach ($_from as $_smarty_tpl->tpl_vars['m']->key => $_smarty_tpl->tpl_vars['m']->value){
?>
        <li jplayer="<?php echo $_smarty_tpl->getVariable('m')->value['url'];?>
" id="<?php echo $_smarty_tpl->getVariable('m')->value['id'];?>
">
        <div class="li">
          <div class="songid"><?php echo smarty_modifier_trim($_smarty_tpl->getVariable('m')->value['show_id']);?>
</div>
          <div class="songtitle"><?php echo smarty_modifier_trim($_smarty_tpl->getVariable('m')->value['title']);?>
</div>
          <div class="songdowntext">下载</div>
          <a href="javascript:void(0)" thunderHref="<?php echo $_smarty_tpl->getVariable('m')->value['url'];?>
" thunderPid="<?php echo $_smarty_tpl->getVariable('m')->value['id'];?>
" onClick="return OnDownloadClick_Simple(this,2,4)" oncontextmenu="ThunderNetwork_SetHref(this)">
          <div class="songdown" title="下载次数：<?php echo smarty_modifier_trim($_smarty_tpl->getVariable('m')->value['download']);?>
"></div>
          </a>
          <div class="songplaytext">播放</div>
          <div class="songimg" title="点播次数：<?php echo $_smarty_tpl->getVariable('m')->value['have_played'];?>
"></div>
          <div class="songpausetext">暂停</div>
          <div class="songpause" title="暂停播放《<?php echo smarty_modifier_trim($_smarty_tpl->getVariable('m')->value['title']);?>
》"></div>
          <div class="songplay" title="继续播放《<?php echo smarty_modifier_trim($_smarty_tpl->getVariable('m')->value['title']);?>
》"></div>
        </div>
      </li>
      <?php }} ?>
    </ul>
  </div>
</div>
<div class="otherlike">
  <div class="title"><img src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
templates/template/gray/images/otherlike.png" border="0" width="120" height="20" align="absmiddle"/></div>
  <div class="next"><a href="javascript:void(0)" id="otherlike" title="换一批"><img src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
templates/template/gray/images/next.png" border="0"/></a></div>
  <ul>
    <li class="onlyone">加载中...</li>
  </ul>
</div>
<div class="maybelike">
  <div class="title"><img src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
templates/template/gray/images/maybelike.png" border="0" width="120" height="20" align="absmiddle"/></div>
  <div class="next"><a href="javascript:void(0)" id="maybelike" title="换一批"><img src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
templates/template/gray/images/next.png" border="0"/></a></div>
  <ul><li class="onlyone">加载中...</li></ul>
</div>
<?php }} ?> 
<?php $_template = new Smarty_Internal_Template("foot.html", $_smarty_tpl->smarty, $_smarty_tpl, $_smarty_tpl->cache_id, $_smarty_tpl->compile_id, null, null);
 echo $_template->getRenderedTemplate();?><?php $_template->updateParentVariables(0);?><?php unset($_template);?>
 
