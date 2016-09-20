<?php /* Smarty version Smarty3-b8, created on 2012-08-09 10:00:19
         compiled from "F:\htdocs\smu/templates/template/gray\content.html" */ ?>
<?php /*%%SmartyHeaderCode:11849502319b3117027-49030412%%*/if(!defined('SMARTY_DIR')) exit('no direct access allowed');
$_smarty_tpl->decodeProperties(array (
  'file_dependency' => 
  array (
    'a117e9c1ec0a46d47111fba66950978901e017c5' => 
    array (
      0 => 'F:\\htdocs\\smu/templates/template/gray\\content.html',
      1 => 1343982379,
    ),
  ),
  'nocache_hash' => '11849502319b3117027-49030412',
  'function' => 
  array (
  ),
  'has_nocache_code' => false,
)); /*/%%SmartyHeaderCode%%*/?>
<?php if (!is_callable('smarty_modifier_strip')) include 'F:\htdocs\smu\lib\Smarty\plugins\modifier.strip.php';
if (!is_callable('smarty_modifier_truncate')) include 'F:\htdocs\smu\lib\Smarty\plugins\modifier.truncate.php';
if (!is_callable('smarty_modifier_url')) include 'F:\htdocs\smu\lib\Smarty\plugins\modifier.url.php';
if (!is_callable('smarty_modifier_trim_simhei')) include 'F:\htdocs\smu\lib\Smarty\plugins\modifier.trim_simhei.php';
?><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="<?php echo $_smarty_tpl->getVariable('r')->value['title'];?>
,飞车坊,华汽飞车坊,somethingmusic,lovemusic,流行音乐,小众音乐,音乐电台,jplayer,有内涵的音乐,somethingmusic音乐电台,最新最快最全的音乐。" />
<meta name="description" content="<?php echo smarty_modifier_truncate(smarty_modifier_strip($_smarty_tpl->getVariable('r')->value['content']),120);?>
" />
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
<?php  $_smarty_tpl->tpl_vars['r'] = new Smarty_Variable;
 $_from = $_smarty_tpl->getVariable('content')->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
if (count($_from) > 0){
    foreach ($_from as $_smarty_tpl->tpl_vars['r']->key => $_smarty_tpl->tpl_vars['r']->value){
?>
<title><?php echo $_smarty_tpl->getVariable('r')->value['title'];?>
 somethingmusic音乐电台 - 我们一直在努力！</title>
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
resources.php?skin=gray&action=all" title="本站资源"><li><?php if ($_smarty_tpl->getVariable('active')->value=='resources'||$_smarty_tpl->getVariable('active')->value=='album'||$_smarty_tpl->getVariable('active')->value=='search'){?><font color="#d06e6f">资源</font><?php }else{ ?>资源<?php }?></li></a>
<a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
musician.php?skin=gray&action=all" title="音乐人介绍"><li><?php if ($_smarty_tpl->getVariable('active')->value=='musician'){?><font color="#d06e6f">音乐人</font><?php }else{ ?>音乐人<?php }?></li></a>
<a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
introduce.php?skin=gray&action=all" title="音乐推荐文章"><li><font color="#d06e6f">推荐</font></a>
  </ul>
  <?php $_template = new Smarty_Internal_Template("login.html", $_smarty_tpl->smarty, $_smarty_tpl, $_smarty_tpl->cache_id, $_smarty_tpl->compile_id, null, null);
 echo $_template->getRenderedTemplate();?><?php $_template->updateParentVariables(0);?><?php unset($_template);?>

</div>
<div class="contents">
  <div class="title"><span><?php echo $_smarty_tpl->getVariable('r')->value['title'];?>
</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<!--[if IE 6]>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<![endif]--><font>添加时间：<?php echo $_smarty_tpl->getVariable('r')->value['time'];?>
&nbsp;&nbsp;&nbsp;阅读次数：<?php echo $_smarty_tpl->getVariable('r')->value['click'];?>
&nbsp;&nbsp;&nbsp;评论：<?php echo $_smarty_tpl->getVariable('num')->value;?>
</font></div>
  <?php echo smarty_modifier_trim_simhei(smarty_modifier_url($_smarty_tpl->getVariable('r')->value['content']));?>
 
  <br/>
  <div class="share"><font>分享到</font> <a href="javascript:(function(){ window.open('http://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey?url='+ encodeURIComponent(location.href)+'&title='+encodeURIComponent('亲！我刚刚路过看到somethingmusic音乐电台的《<?php echo $_smarty_tpl->getVariable('r')->value['title'];?>
》，你也来看看吧！'),'_blank'); } )()" title="分享到QQ空间"><img src="http://ctc.qzonestyle.gtimg.cn/qzonestyle/qzone_client_v5/img/favicon.ico" alt="分享到QQ空间" align="absmiddle" width="16" height="16" border="0"/></a> <a href="javascript:void(0);" onclick="window.open('http://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey?to=pengyou&desc=亲！我刚刚路过看到somethingmusic音乐电台的《<?php echo $_smarty_tpl->getVariable('r')->value['title'];?>
》，你也来看看吧！&title=somethingmusic音乐电台&url='+encodeURIComponent(document.location.href));return false;" title="分享到腾讯朋友"><img src="http://qzonestyle.gtimg.cn/ac/qzone_v5/app/qzshare/xy-icon.png" alt="分享到腾讯朋友" width="16" height="16" border="0" align="absmiddle" /></a> <a href="javascript:void(0)" onclick="postToWb();" style="height:16px;font-size:12px;line-height:16px;" title="分享到腾讯微博"><img src="http://v.t.qq.com/share/images/s/weiboicon16.png" align="absmiddle" border="0" width="16" height="16"/></a><script type="text/javascript">function postToWb(){ var _t = '亲！我刚刚路过看到somethingmusic音乐电台的《<?php echo $_smarty_tpl->getVariable('r')->value['title'];?>
》，你也来看看吧！';var _url = encodeURIComponent(document.location);var _appkey = encodeURI("appkey");var _pic = encodeURI('');var _site = '<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
';var _u = 'http://v.t.qq.com/share/share.php?url='+_url+'&appkey='+_appkey+'&site='+_site+'&pic='+_pic+'&title='+_t;window.open( _u,'', 'width=630, height=480, top=100, left=100, toolbar=no, menubar=no, scrollbars=no, location=yes, resizable=no, status=no' ); }</script> 
      <a rel="nofollow" class="fav_douban" href="javascript:void(function() { var%20d=document,e=encodeURIComponent,s1=window.getSelection,s2=d.getSelection,s3=d.selection,s=s1?s1():s2?s2():s3?s3.createRange().text:'',r='http://www.douban.com/recommend/?url='+e(d.location.href)+'&title='+e('亲！我刚刚路过看到somethingmusic音乐电台的《<?php echo $_smarty_tpl->getVariable('r')->value['title'];?>
》，你也来看看吧！')+'& sel='+e(s)+'&v=1',x=function() { if(!window.open(r,'douban','toolbar=0,resizable=1,scrollbars=yes,status=1,width=620,height=360'))location.href=r+'&amp;r=1' } ;if(/Firefox/.test(navigator.userAgent)) { setTimeout(x,0) } else { x() } } )()" title="分享到豆瓣音乐"><img src="http://img3.douban.com/favicon.ico" alt="分享到豆瓣音乐" width="16" height="16" border="0" align="absmiddle"/></a> <a href="javascript:(function() { window.open('http://v.t.sina.com.cn/share/share.php?appkey=<?php echo $_smarty_tpl->getVariable('sina_appid')->value;?>
&title=亲！我刚刚路过看到somethingmusic音乐电台的《<?php echo $_smarty_tpl->getVariable('r')->value['title'];?>
》，你也来看看吧！&url='+encodeURIComponent(location.href)+'&source=bookmark','_blank','width=620,height=350'); } )()" title="分享到新浪微博"><img src="http://t.sina.com.cn/favicon.ico"  alt="分享到新浪微博" border="0" width="16" height="16" align="absmiddle"></a> <a hidefocus="true" href="javascript:window.open('http://www.kaixin001.com/repaste/share.php?rtitle='+encodeURIComponent('亲！我刚刚路过看到somethingmusic音乐电台的《<?php echo $_smarty_tpl->getVariable('r')->value['title'];?>
》，你也来看看吧！')+'&amp;rurl='+encodeURIComponent(location.href)+'&amp;rcontent=LoveMusic 试听下载<?php echo $_smarty_tpl->getVariable('s')->value['artist'];?>
的专辑《<?php echo $_smarty_tpl->getVariable('s')->value['title'];?>
》','_blank','scrollbars=no,width=600,height=450,left=75,top=20,status=no,resizable=yes'); void 0" onclick="ad_c_m('bbs_pageshare_kaixin001')" id="fx_kx" title="分享到开心网"><img src="http://img1.kaixin001.com.cn/i/favicon.ico" alt="分享到开心网" border="0" height="16" width="16" align="absmiddle"/></a> <a href="javascript:void((function(s,d,e) { if(/xiaonei\.com/.test(d.location))return;var%20f='http://share.xiaonei.com/share/buttonshare.do?link=',u=d.location,l='亲！我刚刚路过看到somethingmusic音乐电台的《<?php echo $_smarty_tpl->getVariable('r')->value['title'];?>
》，你也来看看吧！',p=[e(u),'&amp;title=',e(l)].join('');function%20a() { if(!window.open([f,p].join(''),'xnshare',['toolbar=0,status=0,resizable=1,width=636,height=446,left=',(s.width-636)/2,',top=',(s.height-446)/2].join('')))u.href=[f,p].join(''); } ;if(/Firefox/.test(navigator.userAgent))setTimeout(a,0);else%20a(); } )(screen,document,encodeURIComponent));" title="分享人人网"><img src="http://s.xnimg.cn/favicon-rr.ico" alt="分享人人网" border="0" height="16" width="16" align="absmiddle"></a></div>
  </div>
  <?php $_template = new Smarty_Internal_Template("right.html", $_smarty_tpl->smarty, $_smarty_tpl, $_smarty_tpl->cache_id, $_smarty_tpl->compile_id, null, null);
 echo $_template->getRenderedTemplate();?><?php $_template->updateParentVariables(0);?><?php unset($_template);?>

  
   <!--文章评论--> 
  <?php if ($_smarty_tpl->getVariable('none')->value!=1){?>
  <div class="comment__">
    <div class="title_div"><span class="title" style="width:570px; color:#d06e6f">文章评论</span></div>
    <?php  $_smarty_tpl->tpl_vars['a'] = new Smarty_Variable;
 $_from = $_smarty_tpl->getVariable('comment')->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
if (count($_from) > 0){
    foreach ($_from as $_smarty_tpl->tpl_vars['a']->key => $_smarty_tpl->tpl_vars['a']->value){
?>
    <div class="comment_div" id="ok<?php echo $_smarty_tpl->getVariable('a')->value['id'];?>
">
      <div class="comment_div_left"><img src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
templates/template/gray/images/avatar.png" width="32" height="32"/>
        <?php echo $_smarty_tpl->getVariable('a')->value['name'];?>
<br />
          <font style="font-size:12px; color:#b3b3b3"><?php echo $_smarty_tpl->getVariable('a')->value['time'];?>
</font></p>
      </div>
      <div class="comment_text"><?php echo $_smarty_tpl->getVariable('a')->value['text'];?>
</div>
      <div class="clear"></div>
    </div>
    <?php }} ?> </div>
  <?php }?> 
  <!--用户评论-->
  <div class="say" id="say"> 
  <div class="title_div"><span class="title" style="width:575px; color:#d06e6f; font-size:16px">发表评论</span></div>
  <?php if ($_smarty_tpl->getVariable('type')->value=='topic'){?>
    <form action="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
comment.php?skin=gray&action=topic&topic=<?php echo $_smarty_tpl->getVariable('r')->value['id'];?>
&id=<?php echo $_smarty_tpl->getVariable('r')->value['topic_id'];?>
" method="post">
    <?php }else{ ?>
    <form action="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
comment.php?skin=gray&action=say&id=<?php echo $_smarty_tpl->getVariable('r')->value['id'];?>
" method="post">
      <?php }?>
      <table cellspacing="5" border="0">
        <tr>
          <td width="70"><font color="#FF0000">*</font>昵称：</td>
          <td><input name="usernickname" id="usernickname" type="text"/></td>
        </tr>
        <tr>
          <td><font color="#FF0000">*</font>验证码：</td>
          <td><input name="yanzhengma" id="yanzhengma" type="text"/>
            <script language="javascript">document.write("<img src='yanzhengma.php?'+Math.random()+'' onclick='this.src=this.src+Math.random();' title='看不清？点击更换' align='absmiddle' style='cursor:pointer'>");</script></td>
        </tr>
        <tr>
          <td></td>
          <td><textarea name="text" id="text" style="width:450px; height:150px; overflow-y:hidden"></textarea></td>
        </tr>
        <tr>
          <td></td>
          <td colspan="1"><input type="submit" value="提  交" class="submit" id="button"/></td>
          <td></td>
        </tr>
      </table>
    </form>
  
  
  
  </div>
  <?php }} ?>
  
  <?php $_template = new Smarty_Internal_Template("foot.html", $_smarty_tpl->smarty, $_smarty_tpl, $_smarty_tpl->cache_id, $_smarty_tpl->compile_id, null, null);
 echo $_template->getRenderedTemplate();?><?php $_template->updateParentVariables(0);?><?php unset($_template);?>
