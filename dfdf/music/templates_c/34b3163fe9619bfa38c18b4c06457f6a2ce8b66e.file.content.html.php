<?php /* Smarty version Smarty3-b8, created on 2012-02-17 23:19:06
         compiled from "/home/libmill/domains/lovemusic.cc/public_html/templates/template/deafault/content.html" */ ?>
<?php /*%%SmartyHeaderCode:21154121284f3e6fea2b4a58-84307097%%*/if(!defined('SMARTY_DIR')) exit('no direct access allowed');
$_smarty_tpl->decodeProperties(array (
  'file_dependency' => 
  array (
    '34b3163fe9619bfa38c18b4c06457f6a2ce8b66e' => 
    array (
      0 => '/home/libmill/domains/lovemusic.cc/public_html/templates/template/deafault/content.html',
      1 => 1329490419,
    ),
  ),
  'nocache_hash' => '21154121284f3e6fea2b4a58-84307097',
  'function' => 
  array (
  ),
  'has_nocache_code' => false,
)); /*/%%SmartyHeaderCode%%*/?>
<?php if (!is_callable('smarty_modifier_truncate')) include '/home/libmill/domains/lovemusic.cc/public_html/lib/Smarty/plugins/modifier.truncate.php';
if (!is_callable('smarty_modifier_trim')) include '/home/libmill/domains/lovemusic.cc/public_html/lib/Smarty/plugins/modifier.trim.php';
?><?php  $_smarty_tpl->tpl_vars['r'] = new Smarty_Variable;
 $_from = $_smarty_tpl->getVariable('content')->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
if (count($_from) > 0){
    foreach ($_from as $_smarty_tpl->tpl_vars['r']->key => $_smarty_tpl->tpl_vars['r']->value){
?> 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="shortcut icon" href="favicon.ico"/>
<link rel="stylesheet" href="templates/template/deafault/css/css.css" type="text/css" />
<!--[if IE]><link rel="stylesheet" href="templates/template/deafault/css/all_ie.css" type="text/css" /><![endif]-->
<!--[if IE 6]><link rel="stylesheet" href="templates/template/deafault/css/ie6.css" type="text/css" /><![endif]-->
<!--[if IE 7]><link rel="stylesheet" href="templates/template/deafault/css/ie7.css" type="text/css" /><![endif]-->
<title><?php echo $_smarty_tpl->getVariable('r')->value['title'];?>
  somethingmusic音乐电台 - 我们一直在努力！</title>
</head>
<body>
<!--初始化播放器-->
<div id="jquery_jplayer"></div>
<!--头部-->
<div class="header">
<a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
/?skin=deafault" title="somethingmusic音乐电台"><img src="templates/template/deafault/images/logo.png" width="107" height="35" alt="logo" border="0"/></a>
</div>
<!--导航栏-->
<div class="nav">
<ul>
<li><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
/?skin=deafault" title="网站首页">首页</a></li>
<li><a href="radio.phpskin=deafault&" title="音乐电台" target="_blank">电台</a></li>
<li><a href="resources.php?skin=deafault&action=all" title="本站资源">资源</a></li>
<li><a href="musician.php?skin=deafault&action=all" title="音乐人介绍">音乐人</a></li>
<li><a href="introduce.php?skin=deafault&action=all" title="音乐推荐文章"><font color="#ff0066">推荐</font></a></li>
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
<div class="result"></div><style>
.left, .right {
	margin-left:20px
}
</style>
<!--[if IE 6]><style>.left{ margin-left:10px}</style><![endif]-->
<!--左边-->
<div class="left">
  <!--文章-->
  <div class="article">
    <div class="article_title"><?php echo $_smarty_tpl->getVariable('r')->value['title'];?>
</div>
    <div class="article_info">添加时间：<?php echo $_smarty_tpl->getVariable('r')->value['time'];?>
&nbsp;&nbsp;&nbsp;&nbsp;阅读次数：<?php echo $_smarty_tpl->getVariable('r')->value['click'];?>
</div>
    <div class="article_text"> <?php echo $_smarty_tpl->getVariable('r')->value['content'];?>
 </div>
    <div class="article_beside"> <?php if ($_smarty_tpl->getVariable('type')->value=='topic'){?>
      <div class="up">上一篇：<a href="introduce.php?skin=deafault&action=topic_content&id=<?php echo $_smarty_tpl->getVariable('prev')->value;?>
&topic=<?php echo $_smarty_tpl->getVariable('r')->value['id'];?>
" title="上一篇：<?php echo $_smarty_tpl->getVariable('prev_title')->value;?>
"><?php echo smarty_modifier_truncate($_smarty_tpl->getVariable('prev_title')->value,14);?>
</a></div>
      <div class="down">下一篇：<a href="introduce.php?skin=deafault&action=topic_content&id=<?php echo $_smarty_tpl->getVariable('next')->value;?>
&topic=<?php echo $_smarty_tpl->getVariable('r')->value['id'];?>
" title="下一篇：<?php echo $_smarty_tpl->getVariable('next_title')->value;?>
"><?php echo smarty_modifier_truncate($_smarty_tpl->getVariable('next_title')->value,14);?>
</a></div>
      <?php }else{ ?>
      <div class="up">上一篇：<a href="content.php?skin=deafault&id=<?php echo $_smarty_tpl->getVariable('prev')->value;?>
" title="上一篇：<?php echo $_smarty_tpl->getVariable('prev_title')->value;?>
"><?php echo smarty_modifier_truncate($_smarty_tpl->getVariable('prev_title')->value,14);?>
</a></div>
      <div class="down">下一篇：<a href="content.php?skin=deafault&id=<?php echo $_smarty_tpl->getVariable('next')->value;?>
" title="下一篇：<?php echo $_smarty_tpl->getVariable('next_title')->value;?>
"><?php echo smarty_modifier_truncate($_smarty_tpl->getVariable('next_title')->value,14);?>
</a></div>
      <?php }?> </div>
  </div>
   <!--分享开始-->
    <ul class="share" style="margin-top:10px">
   
    <li><a href="javascript:(function(){ window.open('http://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey?url='+ encodeURIComponent(location.href)+'&title='+encodeURIComponent('我刚刚在somethingmusic音乐电台看到音乐文章《<?php echo $_smarty_tpl->getVariable('r')->value['title'];?>
》，你也去看看吧！'),'_blank'); } )()" title="分享到QQ空间"><img src="http://qzonestyle.gtimg.cn/ac/qzone_v5/app/app_share/qz_logo.png" alt="分享到QQ空间" align="absmiddle" width="16" height="16" border="0"/></a></li>
    
    <li><a href="javascript:void(0);" onclick="window.open('http://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey?to=pengyou&url='+encodeURIComponent(document.location.href));return false;" title="分享到腾讯朋友"><img src="http://qzonestyle.gtimg.cn/ac/qzone_v5/app/qzshare/xy-icon.png" alt="分享到腾讯朋友" width="16" height="16" border="0" align="absmiddle" /></a></li>
    
    <li><a href="javascript:void(0)" onclick="postToWb();" style="height:16px;font-size:12px;line-height:16px;" title="分享到腾讯微博"><img src="http://v.t.qq.com/share/images/s/weiboicon16.png" align="absmiddle" border="0" width="16" height="16"/></a><script type="text/javascript">function postToWb(){ var _t = encodeURI(document.title);var _url = encodeURIComponent(document.location);var _appkey = encodeURI("appkey");var _pic = encodeURI('<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
admin/<?php echo $_smarty_tpl->getVariable('s')->value['picture'];?>
');var _site = '<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
';var _u = 'http://v.t.qq.com/share/share.php?url='+_url+'&appkey='+_appkey+'&site='+_site+'&pic='+_pic+'&title='+_t;window.open( _u,'', 'width=630, height=480, top=100, left=100, toolbar=no, menubar=no, scrollbars=no, location=yes, resizable=no, status=no' ); }</script></li>
    
    <li><a rel="nofollow" class="fav_douban" href="javascript:void(function() { var%20d=document,e=encodeURIComponent,s1=window.getSelection,s2=d.getSelection,s3=d.selection,s=s1?s1():s2?s2():s3?s3.createRange().text:'',r='http://www.douban.com/recommend/?url='+e(d.location.href)+'&title='+e('我刚刚在somethingmusic音乐电台看到音乐文章《<?php echo $_smarty_tpl->getVariable('r')->value['title'];?>
》，你也去看看吧！')+'& sel='+e(s)+'&v=1',x=function() { if(!window.open(r,'douban','toolbar=0,resizable=1,scrollbars=yes,status=1,width=620,height=360'))location.href=r+'&amp;r=1' } ;if(/Firefox/.test(navigator.userAgent)) { setTimeout(x,0) } else { x() } } )()" title="分享到豆瓣音乐"><img src="http://img3.douban.com/favicon.ico" alt="分享到豆瓣音乐" width="16" height="16" border="0" align="absmiddle"/></a></li>
    
    <li><a href="javascript:(function() { window.open('http://v.t.sina.com.cn/share/share.php?title=我刚刚在somethingmusic音乐电台看到音乐文章《<?php echo $_smarty_tpl->getVariable('r')->value['title'];?>
》，你也去看看吧！ &url='+encodeURIComponent(location.href)+'&source=bookmark','_blank','width=620,height=350'); } )()" title="分享到新浪微博"><img src="http://t.sina.com.cn/favicon.ico"  alt="分享到新浪微博" border="0" width="16" height="16" align="absmiddle"></a></li>
    
    <li><a hidefocus="true" href="javascript:window.open('http://www.kaixin001.com/repaste/share.php?rtitle='+encodeURIComponent('我刚刚在somethingmusic音乐电台看到音乐文章《<?php echo $_smarty_tpl->getVariable('r')->value['title'];?>
》，你也去看看吧！')+'&amp;rurl='+encodeURIComponent(location.href)+'&amp;rcontent=我刚刚在somethingmusic音乐电台看到<?php echo $_smarty_tpl->getVariable('s')->value['artist'];?>
的专辑《<?php echo $_smarty_tpl->getVariable('s')->value['title'];?>
》，你也去看看吧！ ','_blank','scrollbars=no,width=600,height=450,left=75,top=20,status=no,resizable=yes'); void 0" onclick="ad_c_m('bbs_pageshare_kaixin001')" id="fx_kx" title="分享到开心网"><img src="http://img1.kaixin001.com.cn/i/favicon.ico" alt="分享到开心网" border="0" height="16" width="16" align="absmiddle"/></a></li>
    
    <li><a href="javascript:void((function(s,d,e) { if(/xiaonei\.com/.test(d.location))return;var%20f='http://share.xiaonei.com/share/buttonshare.do?link=',u=d.location,l='我刚刚在somethingmusic音乐电台看到音乐文章《<?php echo $_smarty_tpl->getVariable('r')->value['title'];?>
》，你也去看看吧！',p=[e(u),'&amp;title=',e(l)].join('');function%20a() { if(!window.open([f,p].join(''),'xnshare',['toolbar=0,status=0,resizable=1,width=636,height=446,left=',(s.width-636)/2,',top=',(s.height-446)/2].join('')))u.href=[f,p].join(''); } ;if(/Firefox/.test(navigator.userAgent))setTimeout(a,0);else%20a(); } )(screen,document,encodeURIComponent));" title="分享人人网"><img src="http://s.xnimg.cn/favicon-rr.ico" alt="分享人人网" border="0" height="16" width="16" align="absmiddle"></a></li>

    </ul>
    <!--分享结束-->
  <!--文章评论--> 
  <?php if ($_smarty_tpl->getVariable('none')->value!=1){?>
  <div class="comment">
    <div class="title_div"><span class="title" style="width:575px">文章评论</span></div>
    <?php  $_smarty_tpl->tpl_vars['a'] = new Smarty_Variable;
 $_from = $_smarty_tpl->getVariable('comment')->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
if (count($_from) > 0){
    foreach ($_from as $_smarty_tpl->tpl_vars['a']->key => $_smarty_tpl->tpl_vars['a']->value){
?>
    <div class="comment_div" id="ok<?php echo $_smarty_tpl->getVariable('a')->value['id'];?>
">
      <div class="comment_div_left"><img src="templates/template/deafault/images/avatar.png" width="32" height="32"/>
        <p> <?php echo $_smarty_tpl->getVariable('a')->value['name'];?>
<br />
          <font style="font-size:12px; color:#b3b3b3"><?php echo $_smarty_tpl->getVariable('a')->value['time'];?>
</font></p>
      </div>
      <div class="comment_text"><?php echo $_smarty_tpl->getVariable('a')->value['text'];?>
</div>
      <div class="clear"></div>
    </div>
    <?php }} ?> </div>
  <?php if ($_smarty_tpl->getVariable('page')->value!=''){?>
  <div class="page"><?php echo $_smarty_tpl->getVariable('page')->value;?>
</div>
  <?php }?>
  <?php }?> 
  <!--用户评论-->
  <div class="say" id="say"> <?php if ($_smarty_tpl->getVariable('type')->value=='topic'){?>
    <form action="comment.php?skin=deafault&action=topic&topic=<?php echo $_smarty_tpl->getVariable('r')->value['id'];?>
&id=<?php echo $_smarty_tpl->getVariable('r')->value['topic_id'];?>
" method="post">
    <?php }else{ ?>
    <form action="comment.php?skin=deafault&action=say&id=<?php echo $_smarty_tpl->getVariable('r')->value['id'];?>
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
  <div class="clear"></div>
  <div class="article_up"><a href="#" title="返回顶部">返回顶部</a></div>
</div>

<!--右边-->
<div class="right"> 
  <!--随机专辑-->
  <div class="rand_album">
    <div class="title_div"><span class="title">随机专辑推荐</span></div>
    <ul>
      <?php  $_smarty_tpl->tpl_vars['s'] = new Smarty_Variable;
 $_from = $_smarty_tpl->getVariable('random')->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
if (count($_from) > 0){
    foreach ($_from as $_smarty_tpl->tpl_vars['s']->key => $_smarty_tpl->tpl_vars['s']->value){
?>
      <li><a href="album.php?skin=deafault&id=<?php echo $_smarty_tpl->getVariable('s')->value['id'];?>
" title="<?php echo $_smarty_tpl->getVariable('s')->value['artist'];?>
-<?php echo $_smarty_tpl->getVariable('s')->value['title'];?>
"><img src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
thumbs/?thumb=admin/<?php echo $_smarty_tpl->getVariable('s')->value['picture'];?>
&w=150&h=150&q=50" border="0" width="100" height="100" alt="<?php echo $_smarty_tpl->getVariable('s')->value['artist'];?>
-<?php echo $_smarty_tpl->getVariable('s')->value['title'];?>
"/></a></li>
      <?php }} ?>
    </ul>
  </div>
  
  <!--热门文章-->
  <div class="rand">
    <div class="title_div"><span class="title">热门文章</span></div>
    <ul>
      <?php  $_smarty_tpl->tpl_vars['s'] = new Smarty_Variable;
 $_from = $_smarty_tpl->getVariable('hot')->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
if (count($_from) > 0){
    foreach ($_from as $_smarty_tpl->tpl_vars['s']->key => $_smarty_tpl->tpl_vars['s']->value){
?>
      <li><a href="content.php?skin=deafault&id=<?php echo $_smarty_tpl->getVariable('s')->value['id'];?>
" title="《<?php echo $_smarty_tpl->getVariable('s')->value['title'];?>
》阅读次数：<?php echo $_smarty_tpl->getVariable('s')->value['click'];?>
"><?php echo smarty_modifier_trim($_smarty_tpl->getVariable('s')->value['title']);?>
(<?php echo $_smarty_tpl->getVariable('s')->value['click'];?>
)</a></li>
      <?php }} ?>
    </ul>
  </div>
  
  <!--最新评论-->
  <div class="rand">
    <div class="title_div"><span class="title">最新评论</span></div>
    <ul>
      <?php  $_smarty_tpl->tpl_vars['s'] = new Smarty_Variable;
 $_from = $_smarty_tpl->getVariable('read_comment')->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
if (count($_from) > 0){
    foreach ($_from as $_smarty_tpl->tpl_vars['s']->key => $_smarty_tpl->tpl_vars['s']->value){
?>
      <li><?php echo $_smarty_tpl->getVariable('s')->value['name'];?>
：<a href="content.php?skin=deafault&id=<?php echo $_smarty_tpl->getVariable('s')->value['content_id'];?>
#ok<?php echo $_smarty_tpl->getVariable('s')->value['id'];?>
" title="<?php echo $_smarty_tpl->getVariable('s')->value['name'];?>
说：<?php echo $_smarty_tpl->getVariable('s')->value['text'];?>
"><?php echo smarty_modifier_truncate($_smarty_tpl->getVariable('s')->value['text'],42);?>
</a></li>
      <?php }} ?>
    </ul>
  </div>
</div>
<?php $_template = new Smarty_Internal_Template('foot.html', $_smarty_tpl->smarty, $_smarty_tpl, $_smarty_tpl->cache_id, $_smarty_tpl->compile_id, null, null);
 echo $_template->getRenderedTemplate();?><?php $_template->updateParentVariables(0);?><?php unset($_template);?>
