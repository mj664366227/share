<?php /* Smarty version Smarty3-b8, created on 2013-09-07 15:28:18
         compiled from "E:\htdocs\music/templates/template/deafault\album.html" */ ?>
<?php /*%%SmartyHeaderCode:32069522ad592786256-67106326%%*/if(!defined('SMARTY_DIR')) exit('no direct access allowed');
$_smarty_tpl->decodeProperties(array (
  'file_dependency' => 
  array (
    'fae4f0ee74715c6af07954684a493f9e86a2ac77' => 
    array (
      0 => 'E:\\htdocs\\music/templates/template/deafault\\album.html',
      1 => 1343982380,
    ),
  ),
  'nocache_hash' => '32069522ad592786256-67106326',
  'function' => 
  array (
  ),
  'has_nocache_code' => false,
)); /*/%%SmartyHeaderCode%%*/?>
<?php if (!is_callable('smarty_modifier_trim')) include 'E:\htdocs\music\lib\Smarty\plugins\modifier.trim.php';
if (!is_callable('smarty_modifier_strip_tags')) include 'E:\htdocs\music\lib\Smarty\plugins\modifier.strip_tags.php';
if (!is_callable('smarty_modifier_strip')) include 'E:\htdocs\music\lib\Smarty\plugins\modifier.strip.php';
if (!is_callable('smarty_modifier_truncate')) include 'E:\htdocs\music\lib\Smarty\plugins\modifier.truncate.php';
if (!is_callable('smarty_modifier_stripslashes')) include 'E:\htdocs\music\lib\Smarty\plugins\modifier.stripslashes.php';
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
》专辑介绍：<?php echo smarty_modifier_truncate(smarty_modifier_strip(smarty_modifier_strip_tags($_smarty_tpl->getVariable('s')->value['text'])),150);?>
,somethingmusic音乐电台,最新最快最全的音乐。" />
<link rel="shortcut icon" href="favicon.ico"/>
<link rel="stylesheet" href="templates/template/deafault/css/css.css" type="text/css" />
<!--[if IE]><link rel="stylesheet" href="templates/template/deafault/css/all_ie.css" type="text/css" /><![endif]-->
<!--[if IE 6]><link rel="stylesheet" href="templates/template/deafault/css/ie6.css" type="text/css" /><![endif]-->
<!--[if IE 7]><link rel="stylesheet" href="templates/template/deafault/css/ie7.css" type="text/css" /><![endif]-->
<title><?php echo $_smarty_tpl->getVariable('s')->value['artist'];?>
-《<?php echo smarty_modifier_stripslashes(smarty_modifier_trim($_smarty_tpl->getVariable('s')->value['title']));?>
》  somethingmusic音乐电台 - 我们一直在努力！</title>
</head>
<body>
<!--初始化播放器-->
<div id="jquery_jplayer"></div>
<!--头部-->
<div class="header">
<a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
" title="somethingmusic音乐电台"><img src="templates/template/deafault/images/logo.png" width="107" height="35" alt="logo" border="0"/></a>
</div>
<!--导航栏-->
<div class="nav">
<ul>
<li><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
?skin=deafault" title="网站首页">首页</a></li>
<li><a href="radio.php?skin=deafault" title="音乐电台" target="_blank">电台</a></li>
<li><a href="resources.php?skin=deafault&action=all" title="本站资源"><font color="#ff0066">资源</font></a></li>
<li><a href="musician.php?skin=deafault&action=all" title="音乐人介绍">音乐人</a></li>
<li><a href="introduce.php?skin=deafault&action=all" title="音乐推荐文章">推荐</a></li>
</ul>
<!--搜索栏-->
<div class="search">
<form action="search.php?skin=deafault&action=search" method="post">
<input type="text" class="search_input" id="search_input" name="search_input" onclick="value=''" autocomplete="off"/>
<div class="submit" title="搜！"><input type="image" src="templates/template/deafault/images/search.png" width="18" height="18" id="search_submit"/></div>
</form></div>
</div>
<!--网站主体-->
<div class="body">
<div class="main">
<div class="result"></div><!--左边-->

<div class="left"> 
  <!--专辑详情开始-->
  <div class="album" id="album">
    <div class="title_div" style="width:575px;"><span class="title">专辑详情</span></div>
     <!-- <img src="admin/<?php echo $_smarty_tpl->getVariable('s')->value['picture'];?>
" width="190" height="190" alt="<?php echo $_smarty_tpl->getVariable('s')->value['title'];?>
"/>-->
   <img src="thumbs/thumbs/cache/w190h190/admin/<?php echo $_smarty_tpl->getVariable('s')->value['picture'];?>
" width="190" height="190" alt="<?php echo $_smarty_tpl->getVariable('s')->value['title'];?>
"/>
    <div class="album_sth">
      <h2><?php echo smarty_modifier_stripslashes(smarty_modifier_trim($_smarty_tpl->getVariable('s')->value['title']));?>
</h2>
      <p>歌手名称：<?php echo smarty_modifier_trim($_smarty_tpl->getVariable('s')->value['artist']);?>
</p>
      <p>音乐风格：<?php echo smarty_modifier_trim($_smarty_tpl->getVariable('s')->value['category']);?>
</p>
      <p>歌曲语种：<?php echo smarty_modifier_trim($_smarty_tpl->getVariable('s')->value['language']);?>
</p>
      <p>来源地区：<?php echo smarty_modifier_trim($_smarty_tpl->getVariable('s')->value['area']);?>
</p>
      <p>发行时间：<?php echo smarty_modifier_trim($_smarty_tpl->getVariable('s')->value['time']);?>
</p>
      <p>唱片公司：<?php echo smarty_modifier_trim($_smarty_tpl->getVariable('s')->value['company']);?>
</p>
    </div>
    <!--专辑详情结束--> 
    
    <!--专辑简介开始-->
    <div class="jianjie">
      <div class="title_div" style="width:575px;"><span class="title">专辑简介</span></div>
      <?php echo smarty_modifier_trim($_smarty_tpl->getVariable('s')->value['text']);?>

      <div class="clear"></div>
    </div>
    <!--专辑简介结束--> 
    <?php }} ?> 
    <!--分享开始-->
    <ul class="share">
   
    <li><a href="javascript:(function(){ window.open('http://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey?url='+ encodeURIComponent(location.href)+'&title='+encodeURIComponent('LoveMusic 试听下载<?php echo $_smarty_tpl->getVariable('s')->value['artist'];?>
的专辑《<?php echo $_smarty_tpl->getVariable('s')->value['title'];?>
》'),'_blank'); } )()" title="分享到QQ空间"><img src="http://qzonestyle.gtimg.cn/ac/qzone_v5/app/app_share/qz_logo.png" alt="分享到QQ空间" align="absmiddle" width="16" height="16" border="0"/></a></li>
    
    <li><a href="javascript:void(0);" onclick="window.open('http://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey?to=pengyou&url='+encodeURIComponent(document.location.href));return false;" title="分享到腾讯朋友"><img src="http://qzonestyle.gtimg.cn/ac/qzone_v5/app/qzshare/xy-icon.png" alt="分享到腾讯朋友" width="16" height="16" border="0" align="absmiddle" /></a></li>
    
    <li><a href="javascript:void(0)" onclick="postToWb();" style="height:16px;font-size:12px;line-height:16px;" title="分享到腾讯微博"><img src="http://v.t.qq.com/share/images/s/weiboicon16.png" align="absmiddle" border="0" width="16" height="16"/></a><script type="text/javascript">function postToWb(){ var _t = encodeURI(document.title);var _url = encodeURIComponent(document.location);var _appkey = encodeURI("appkey");var _pic = encodeURI('<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
admin/<?php echo $_smarty_tpl->getVariable('s')->value['picture'];?>
');var _site = '<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
';var _u = 'http://v.t.qq.com/share/share.php?url='+_url+'&appkey='+_appkey+'&site='+_site+'&pic='+_pic+'&title='+_t;window.open( _u,'', 'width=630, height=480, top=100, left=100, toolbar=no, menubar=no, scrollbars=no, location=yes, resizable=no, status=no' ); }</script></li>
    
    <li><a rel="nofollow" class="fav_douban" href="javascript:void(function() { var%20d=document,e=encodeURIComponent,s1=window.getSelection,s2=d.getSelection,s3=d.selection,s=s1?s1():s2?s2():s3?s3.createRange().text:'',r='http://www.douban.com/recommend/?url='+e(d.location.href)+'&title='+e('LoveMusic 试听下载<?php echo $_smarty_tpl->getVariable('s')->value['artist'];?>
的专辑《<?php echo $_smarty_tpl->getVariable('s')->value['title'];?>
》')+'& sel='+e(s)+'&v=1',x=function() { if(!window.open(r,'douban','toolbar=0,resizable=1,scrollbars=yes,status=1,width=620,height=360'))location.href=r+'&amp;r=1' } ;if(/Firefox/.test(navigator.userAgent)) { setTimeout(x,0) } else { x() } } )()" title="分享到豆瓣音乐"><img src="http://img3.douban.com/favicon.ico" alt="分享到豆瓣音乐" width="16" height="16" border="0" align="absmiddle"/></a></li>
    
    <li><a href="javascript:(function() { window.open('http://v.t.sina.com.cn/share/share.php?appkey=791368876&title=LoveMusic 试听下载<?php echo $_smarty_tpl->getVariable('s')->value['artist'];?>
的专辑《<?php echo $_smarty_tpl->getVariable('s')->value['title'];?>
》&url='+encodeURIComponent(location.href)+'&source=bookmark','_blank','width=620,height=350'); } )()" title="分享到新浪微博"><img src="http://t.sina.com.cn/favicon.ico"  alt="分享到新浪微博" border="0" width="16" height="16" align="absmiddle"></a></li>
    
    <li><a hidefocus="true" href="javascript:window.open('http://www.kaixin001.com/repaste/share.php?rtitle='+encodeURIComponent('LoveMusic 试听下载<?php echo $_smarty_tpl->getVariable('s')->value['artist'];?>
的专辑《<?php echo $_smarty_tpl->getVariable('s')->value['title'];?>
》')+'&amp;rurl='+encodeURIComponent(location.href)+'&amp;rcontent=LoveMusic 试听下载<?php echo $_smarty_tpl->getVariable('s')->value['artist'];?>
的专辑《<?php echo $_smarty_tpl->getVariable('s')->value['title'];?>
》','_blank','scrollbars=no,width=600,height=450,left=75,top=20,status=no,resizable=yes'); void 0" onclick="ad_c_m('bbs_pageshare_kaixin001')" id="fx_kx" title="分享到开心网"><img src="http://img1.kaixin001.com.cn/i/favicon.ico" alt="分享到开心网" border="0" height="16" width="16" align="absmiddle"/></a></li>
    
    <li><a href="javascript:void((function(s,d,e) { if(/xiaonei\.com/.test(d.location))return;var%20f='http://share.xiaonei.com/share/buttonshare.do?link=',u=d.location,l='LoveMusic 试听下载<?php echo $_smarty_tpl->getVariable('s')->value['artist'];?>
的专辑《<?php echo $_smarty_tpl->getVariable('s')->value['title'];?>
》',p=[e(u),'&amp;title=',e(l)].join('');function%20a() { if(!window.open([f,p].join(''),'xnshare',['toolbar=0,status=0,resizable=1,width=636,height=446,left=',(s.width-636)/2,',top=',(s.height-446)/2].join('')))u.href=[f,p].join(''); } ;if(/Firefox/.test(navigator.userAgent))setTimeout(a,0);else%20a(); } )(screen,document,encodeURIComponent));" title="分享人人网"><img src="http://s.xnimg.cn/favicon-rr.ico" alt="分享人人网" border="0" height="16" width="16" align="absmiddle"></a></li>
    <li><a href="javascript:void(0)" title="打包下载" id="down"><img src="templates/template/deafault/images/down.png" width="16" height="16" border="0"/></a></li>
    </ul>
    <!--分享结束-->
    
    <!--专辑曲目开始-->
    <div class="clear"></div>
    <div class="song">
      <div class="title_div" style="width:575px;"><span class="title">专辑曲目</span></div>
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
            <a href="<?php echo $_smarty_tpl->getVariable('m')->value['url'];?>
">
            <div class="songdown" title="下载次数：<?php echo smarty_modifier_trim($_smarty_tpl->getVariable('m')->value['download']);?>
"></div>
            </a>
            <div class="img" title="点播次数：<?php echo $_smarty_tpl->getVariable('m')->value['have_played'];?>
"></div>
            <div class="pause" title="暂停播放《<?php echo smarty_modifier_trim($_smarty_tpl->getVariable('m')->value['title']);?>
》"></div>
            <div class="play" title="继续播放《<?php echo smarty_modifier_trim($_smarty_tpl->getVariable('m')->value['title']);?>
》"></div>
          </div>
        </li>
        <?php }} ?>
      </ul>
    </div>
    <!--专辑曲目结束--> 
  </div>
</div>
<!--右边-->
<div class="right">
<!--广告开始-->
<div class="ad">
  <div class="slide">
    <div class="slide_content">
      <div class="slide_nav"> <?php  $_smarty_tpl->tpl_vars['a'] = new Smarty_Variable;
 $_from = $_smarty_tpl->getVariable('ad')->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
if (count($_from) > 0){
    foreach ($_from as $_smarty_tpl->tpl_vars['a']->key => $_smarty_tpl->tpl_vars['a']->value){
?>
        <?php if ($_smarty_tpl->getVariable('a')->value['show']==1){?>
        <dd class="ddd" title="<?php echo $_smarty_tpl->getVariable('a')->value['title'];?>
"><?php echo $_smarty_tpl->getVariable('a')->value['show_id'];?>
</dd>
        <?php }?>
        <?php }} ?> </div>
    </div>
    <div class="slide_content_list">
      <ul>
        <?php  $_smarty_tpl->tpl_vars['a'] = new Smarty_Variable;
 $_from = $_smarty_tpl->getVariable('ad')->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
if (count($_from) > 0){
    foreach ($_from as $_smarty_tpl->tpl_vars['a']->key => $_smarty_tpl->tpl_vars['a']->value){
?>
        <?php if ($_smarty_tpl->getVariable('a')->value['show']==1){?>
        <li><a<?php if ($_smarty_tpl->getVariable('a')->value['url']!=''){?> href="<?php echo $_smarty_tpl->getVariable('a')->value['url'];?>
" title="<?php echo smarty_modifier_trim($_smarty_tpl->getVariable('a')->value['title']);?>
" target="_blank"<?php }?>><img src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
thumbs/?thumb=admin/<?php echo $_smarty_tpl->getVariable('a')->value['picture'];?>
&w=320&h=150&q=85" border="0" width="320" height="150" alt="<?php echo $_smarty_tpl->getVariable('a')->value['title'];?>
"/></a></li>
        <?php }?>
        <?php }} ?>
      </ul>
    </div>
  </div>
</div>
<!--广告结束--> 
<!--其他专辑开始-->
<div class="other">
  <div class="title_div" style="width:320px;"><span class="title">你可能感兴趣的专辑</span></div>
  <div class="clear"></div>
  <?php  $_smarty_tpl->tpl_vars['a'] = new Smarty_Variable;
 $_from = $_smarty_tpl->getVariable('about_artist')->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
if (count($_from) > 0){
    foreach ($_from as $_smarty_tpl->tpl_vars['a']->key => $_smarty_tpl->tpl_vars['a']->value){
?>
  <div class="about"> <a href="album.php?skin=deafault&id=<?php echo $_smarty_tpl->getVariable('a')->value['id'];?>
" title="<?php echo smarty_modifier_trim($_smarty_tpl->getVariable('a')->value['artist']);?>
-《<?php echo smarty_modifier_trim($_smarty_tpl->getVariable('a')->value['title']);?>
》"><!--<img src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
thumbs/?thumb=admin/<?php echo $_smarty_tpl->getVariable('a')->value['picture'];?>
&w=170&h=170&q=10" width="60" height="60" alt="<?php echo $_smarty_tpl->getVariable('a')->value['artist'];?>
"/ border="0">--><img src="thumbs/thumbs/cache/w170h170/admin/<?php echo $_smarty_tpl->getVariable('a')->value['picture'];?>
" width="60" height="60" alt="<?php echo smarty_modifier_trim($_smarty_tpl->getVariable('a')->value['artist']);?>
"/ border="0"></a></div>
  <?php }} ?> 
  <?php  $_smarty_tpl->tpl_vars['aa'] = new Smarty_Variable;
 $_from = $_smarty_tpl->getVariable('about_artist1')->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
if (count($_from) > 0){
    foreach ($_from as $_smarty_tpl->tpl_vars['aa']->key => $_smarty_tpl->tpl_vars['aa']->value){
?>
  <div class="about"> <a href="album.php?skin=deafault&id=<?php echo $_smarty_tpl->getVariable('aa')->value['id'];?>
" title="<?php echo smarty_modifier_trim($_smarty_tpl->getVariable('aa')->value['artist']);?>
-《<?php echo smarty_modifier_trim($_smarty_tpl->getVariable('aa')->value['title']);?>
》"><img src="thumbs/thumbs/cache/w170h170/admin/<?php echo $_smarty_tpl->getVariable('aa')->value['picture'];?>
" width="60" height="60" alt="<?php echo smarty_modifier_trim($_smarty_tpl->getVariable('aa')->value['artist']);?>
"/ border="0"></a></div>
  <?php }} ?> 
  <?php  $_smarty_tpl->tpl_vars['aaa'] = new Smarty_Variable;
 $_from = $_smarty_tpl->getVariable('about_artist2')->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
if (count($_from) > 0){
    foreach ($_from as $_smarty_tpl->tpl_vars['aaa']->key => $_smarty_tpl->tpl_vars['aaa']->value){
?>
  <div class="about"> <a href="album.php?skin=deafault&id=<?php echo $_smarty_tpl->getVariable('aaa')->value['id'];?>
" title="<?php echo smarty_modifier_trim($_smarty_tpl->getVariable('aaa')->value['artist']);?>
-《<?php echo smarty_modifier_trim($_smarty_tpl->getVariable('aaa')->value['title']);?>
》"><img src="thumbs/thumbs/cache/w170h170/admin/<?php echo $_smarty_tpl->getVariable('aaa')->value['picture'];?>
" width="60" height="60" alt="<?php echo smarty_modifier_trim($_smarty_tpl->getVariable('aaa')->value['artist']);?>
"/ border="0"></a></div>
  <?php }} ?> 
  <!--其他专辑结束--> 
</div>
<?php $_template = new Smarty_Internal_Template("foot.html", $_smarty_tpl->smarty, $_smarty_tpl, $_smarty_tpl->cache_id, $_smarty_tpl->compile_id, null, null);
 echo $_template->getRenderedTemplate();?><?php $_template->updateParentVariables(0);?><?php unset($_template);?>
