<?php /* Smarty version Smarty3-b8, created on 2013-12-20 12:35:55
         compiled from "E:\htdocs\music/templates/template/gray\user.album_info.html" */ ?>
<?php /*%%SmartyHeaderCode:2731652b3c92b64efe6-51836816%%*/if(!defined('SMARTY_DIR')) exit('no direct access allowed');
$_smarty_tpl->decodeProperties(array (
  'file_dependency' => 
  array (
    '4b925b97d4679c057970eae7d0da01871c1f5d48' => 
    array (
      0 => 'E:\\htdocs\\music/templates/template/gray\\user.album_info.html',
      1 => 1343982380,
    ),
  ),
  'nocache_hash' => '2731652b3c92b64efe6-51836816',
  'function' => 
  array (
  ),
  'has_nocache_code' => false,
)); /*/%%SmartyHeaderCode%%*/?>
<?php if (!is_callable('smarty_modifier_trim')) include 'E:\htdocs\music\lib\Smarty\plugins\modifier.trim.php';
if (!is_callable('smarty_modifier_stripslashes')) include 'E:\htdocs\music\lib\Smarty\plugins\modifier.stripslashes.php';
if (!is_callable('smarty_modifier_truncate')) include 'E:\htdocs\music\lib\Smarty\plugins\modifier.truncate.php';
if (!is_callable('smarty_modifier_date_format')) include 'E:\htdocs\music\lib\Smarty\plugins\modifier.date_format.php';
?><div class="album">
  <div class="album_card" style="width:600px">
    <div class="album_pic"><img src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
thumbs/thumbs/cache/w200h200/<?php echo $_smarty_tpl->getVariable('album')->value['pic'];?>
" width="190" height="190" alt="<?php echo $_smarty_tpl->getVariable('s')->value['title'];?>
"/></div>
    <div class="album_detail" album_id="<?php echo $_smarty_tpl->getVariable('s')->value['id'];?>
"> <span title="《<?php echo smarty_modifier_stripslashes(smarty_modifier_trim($_smarty_tpl->getVariable('album')->value['name']));?>
》">《<?php echo smarty_modifier_truncate(smarty_modifier_stripslashes(smarty_modifier_trim($_smarty_tpl->getVariable('album')->value['name'])),38);?>
》</span>
      <p><font>[谁添加的]</font> <?php echo smarty_modifier_trim($_smarty_tpl->getVariable('album')->value['nick']);?>
</p>
      <p><font>[歌手名称]</font> <?php echo smarty_modifier_trim($_smarty_tpl->getVariable('album')->value['artist']);?>
</p>
      <p><font>[添加时间]</font> <?php echo smarty_modifier_date_format(smarty_modifier_trim($_smarty_tpl->getVariable('album')->value['time']),"Y-m-d H:i:s");?>
</p>
      <p><font>[音乐风格]</font> <?php echo smarty_modifier_trim($_smarty_tpl->getVariable('album')->value['category']);?>
</p>
    </div></div>
  <div class="album_intro">
    <div class="title"><img src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
templates/template/gray/images/album_detail.png" border="0" width="60" height="20" align="absmiddle"/> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font>分享到</font> <a href="javascript:(function(){ window.open('http://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey?url='+ encodeURIComponent(location.href)+'&title='+encodeURIComponent('LoveMusic 试听下载专辑《<?php echo smarty_modifier_trim($_smarty_tpl->getVariable('album')->value['name']);?>
》'),'_blank'); } )()" title="分享到QQ空间"><img src="http://ctc.qzonestyle.gtimg.cn/qzonestyle/qzone_client_v5/img/favicon.ico" alt="分享到QQ空间" align="absmiddle" width="16" height="16" border="0"/></a> <a href="javascript:void(0);" onclick="window.open('http://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey?to=pengyou&url='+encodeURIComponent(document.location.href));return false;" title="分享到腾讯朋友"><img src="http://qzonestyle.gtimg.cn/ac/qzone_v5/app/qzshare/xy-icon.png" alt="分享到腾讯朋友" width="16" height="16" border="0" align="absmiddle" /></a> <a href="javascript:void(0)" onclick="postToWb();" style="height:16px;font-size:12px;line-height:16px;" title="分享到腾讯微博"><img src="http://v.t.qq.com/share/images/s/weiboicon16.png" align="absmiddle" border="0" width="16" height="16"/></a><script type="text/javascript">function postToWb(){ var _t = encodeURI(document.title);var _url = encodeURIComponent(document.location);var _appkey = encodeURI("appkey");var _pic = encodeURI('<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
admin/<?php echo $_smarty_tpl->getVariable('s')->value['picture'];?>
');var _site = '<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
';var _u = 'http://v.t.qq.com/share/share.php?url='+_url+'&appkey='+_appkey+'&site='+_site+'&pic='+_pic+'&title='+_t;window.open( _u,'', 'width=630, height=480, top=100, left=100, toolbar=no, menubar=no, scrollbars=no, location=yes, resizable=no, status=no' ); }</script> 
      <a rel="nofollow" class="fav_douban" href="javascript:void(function() { var%20d=document,e=encodeURIComponent,s1=window.getSelection,s2=d.getSelection,s3=d.selection,s=s1?s1():s2?s2():s3?s3.createRange().text:'',r='http://www.douban.com/recommend/?url='+e(d.location.href)+'&title='+e('LoveMusic 试听下载专辑《<?php echo smarty_modifier_trim($_smarty_tpl->getVariable('album')->value['name']);?>
》')+'& sel='+e(s)+'&v=1',x=function() { if(!window.open(r,'douban','toolbar=0,resizable=1,scrollbars=yes,status=1,width=620,height=360'))location.href=r+'&amp;r=1' } ;if(/Firefox/.test(navigator.userAgent)) { setTimeout(x,0) } else { x() } } )()" title="分享到豆瓣音乐"><img src="http://img3.douban.com/favicon.ico" alt="分享到豆瓣音乐" width="16" height="16" border="0" align="absmiddle"/></a> <a href="javascript:(function() { window.open('http://v.t.sina.com.cn/share/share.php?appkey=<?php echo $_smarty_tpl->getVariable('sina_appid')->value;?>
&title=LoveMusic 试听下载专辑《<?php echo smarty_modifier_trim($_smarty_tpl->getVariable('album')->value['name']);?>
》&url='+encodeURIComponent(location.href)+'&source=bookmark&pic=<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
thumbs/thumbs/cache/w200h200/<?php echo $_smarty_tpl->getVariable('album')->value['pic'];?>
','_blank','width=620,height=350'); } )()" title="分享到新浪微博"><img src="http://t.sina.com.cn/favicon.ico"  alt="分享到新浪微博" border="0" width="16" height="16" align="absmiddle"></a> <a hidefocus="true" href="javascript:window.open('http://www.kaixin001.com/repaste/share.php?rtitle='+encodeURIComponent('LoveMusic 试听下载专辑《<?php echo smarty_modifier_trim($_smarty_tpl->getVariable('album')->value['name']);?>
》')+'&amp;rurl='+encodeURIComponent(location.href)+'&amp;rcontent=LoveMusic 试听下载专辑《<?php echo smarty_modifier_trim($_smarty_tpl->getVariable('album')->value['name']);?>
》','_blank','scrollbars=no,width=600,height=450,left=75,top=20,status=no,resizable=yes'); void 0" onclick="ad_c_m('bbs_pageshare_kaixin001')" id="fx_kx" title="分享到开心网"><img src="http://img1.kaixin001.com.cn/i/favicon.ico" alt="分享到开心网" border="0" height="16" width="16" align="absmiddle"/></a> <a href="javascript:void((function(s,d,e) { if(/xiaonei\.com/.test(d.location))return;var%20f='http://share.xiaonei.com/share/buttonshare.do?link=',u=d.location,l='LoveMusic 试听下载<?php echo $_smarty_tpl->getVariable('s')->value['artist'];?>
的专辑《<?php echo smarty_modifier_trim($_smarty_tpl->getVariable('album')->value['name']);?>
》',p=[e(u),'&amp;title=',e(l)].join('');function%20a() { if(!window.open([f,p].join(''),'xnshare',['toolbar=0,status=0,resizable=1,width=636,height=446,left=',(s.width-636)/2,',top=',(s.height-446)/2].join('')))u.href=[f,p].join(''); } ;if(/Firefox/.test(navigator.userAgent))setTimeout(a,0);else%20a(); } )(screen,document,encodeURIComponent));" title="分享人人网"><img src="http://s.xnimg.cn/favicon-rr.ico" alt="分享人人网" border="0" height="16" width="16" align="absmiddle"></a> <a href="javascript:void(0)" title="打包下载" id="down"><img src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
templates/template/gray/images/down.png" width="16" height="16" border="0" align="absmiddle"/></a> </div>
    <br/>
    <br/>
    <br/>
    <font style="font-size:14px">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<?php echo smarty_modifier_trim($_smarty_tpl->getVariable('album')->value['text']);?>
</font> </div>
  <script type="text/javascript" src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
templates/template/gray/js/webThunderDetect.js"></script>
  <div class="song">
    <div class="title"><img src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
templates/template/gray/images/album_song_list.png" border="0" width="60" height="20" align="absmiddle"/></div>
    <ul>
      <?php  $_smarty_tpl->tpl_vars['m'] = new Smarty_Variable;
 $_smarty_tpl->tpl_vars['key'] = new Smarty_Variable;
 $_from = $_smarty_tpl->getVariable('list')->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
if (count($_from) > 0){
    foreach ($_from as $_smarty_tpl->tpl_vars['m']->key => $_smarty_tpl->tpl_vars['m']->value){
 $_smarty_tpl->tpl_vars['key']->value = $_smarty_tpl->tpl_vars['m']->key;
?>
       <li jplayer="<?php echo $_smarty_tpl->getVariable('m')->value['url'];?>
" id="<?php echo $_smarty_tpl->getVariable('m')->value['id'];?>
">
        <div class="li">
          <div class="songid"><?php echo $_smarty_tpl->getVariable('key')->value+1;?>
</div>
          <div class="songtitle"><?php echo smarty_modifier_trim($_smarty_tpl->getVariable('m')->value['name']);?>
</div>
          <div class="songdowntext">下载</div>
          <a href="javascript:void(0)" thunderHref="<?php echo $_smarty_tpl->getVariable('m')->value['url'];?>
" thunderPid="<?php echo $_smarty_tpl->getVariable('m')->value['id'];?>
" onClick="return OnDownloadClick_Simple(this,2,4)" oncontextmenu="ThunderNetwork_SetHref(this)">
          <div class="songdown"></div>
          </a>
          <div class="songplaytext">播放</div>
          <div class="songimg"></div>
          <div class="songpausetext">暂停</div>
          <div class="songpause" title="暂停播放《<?php echo smarty_modifier_trim($_smarty_tpl->getVariable('m')->value['name']);?>
》"></div>
          <div class="songplay" title="继续播放《<?php echo smarty_modifier_trim($_smarty_tpl->getVariable('m')->value['name']);?>
》"></div>
        </div>
      </li>
      <?php }} ?>
    </ul>
  </div>
</div>

<div class="maybelike">
  <div class="title"><img src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
templates/template/gray/images/maybelike.png" border="0" width="120" height="20" align="absmiddle"/></div>
  <div class="next"><a href="javascript:void(0)" id="maybelike" title="换一批"><img src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
templates/template/gray/images/next.png" border="0"/></a></div>
  <ul><li class="onlyone">加载中...</li></ul>
</div>