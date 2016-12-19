<?php /* Smarty version Smarty3-b8, created on 2016-09-20 22:17:25
         compiled from "D:\nginx/www/share.git/somethingmusic/templates/template/gray\musician_look.html" */ ?>
<?php /*%%SmartyHeaderCode:1899157e144f5c626f3-66614285%%*/if(!defined('SMARTY_DIR')) exit('no direct access allowed');
$_smarty_tpl->decodeProperties(array (
  'file_dependency' => 
  array (
    'e37171f53293a2c3a72f2ae2e87b432d35f8ea86' => 
    array (
      0 => 'D:\\nginx/www/share.git/somethingmusic/templates/template/gray\\musician_look.html',
      1 => 1343982380,
    ),
  ),
  'nocache_hash' => '1899157e144f5c626f3-66614285',
  'function' => 
  array (
  ),
  'has_nocache_code' => false,
)); /*/%%SmartyHeaderCode%%*/?>
<?php  $_smarty_tpl->tpl_vars['a'] = new Smarty_Variable;
 $_from = $_smarty_tpl->getVariable('singer')->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
if (count($_from) > 0){
    foreach ($_from as $_smarty_tpl->tpl_vars['a']->key => $_smarty_tpl->tpl_vars['a']->value){
?>
<div class="artist">
<div id="gray">
    <div class="artist_pic"><img src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
thumbs/thumbs/cache/w184h184/admin/<?php echo $_smarty_tpl->getVariable('a')->value['pic'];?>
" border="0" width="190" height="190" alt="<?php echo $_smarty_tpl->getVariable('a')->value['name'];?>
"/></div>
    <div class="artist_details" id="<?php echo $_smarty_tpl->getVariable('a')->value['id'];?>
"> <span style="color:#e7999a;"><?php echo $_smarty_tpl->getVariable('a')->value['name'];?>
</span>
      <div id="musician"><?php echo $_smarty_tpl->getVariable('a')->value['introduce'];?>
</div>
    </div>
    <?php if ($_smarty_tpl->getVariable('like')->value!=$_smarty_tpl->getVariable('a')->value['id']){?>
    <div class="artist_like_out"></div>
    <div title="把<?php echo $_smarty_tpl->getVariable('a')->value['name'];?>
标记为喜欢" class="artist_like_on"></div>
    <?php }else{ ?>
    <div title="<?php echo $_smarty_tpl->getVariable('a')->value['name'];?>
已标记为喜欢" class="artist_like"></div>
    <?php }?>
<?php }} ?> 
<div class="details"> <a href="javascript:void(0)" title="点查看<?php echo $_smarty_tpl->getVariable('a')->value['name'];?>
的详细信息" id="expand">展开 >></a>
<a href="javascript:void(0)" title="点查看<?php echo $_smarty_tpl->getVariable('a')->value['name'];?>
的详细信息" id="up">收起 <<</a>
  <br/>
  <font style="color:#e7999a; font-size:12px">分享到</font> <a href="javascript:(function(){ window.open('http://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey?url='+ encodeURIComponent(location.href)+'&title='+encodeURIComponent('LoveMusic 试听下载<?php echo $_smarty_tpl->getVariable('s')->value['artist'];?>
的专辑《<?php echo $_smarty_tpl->getVariable('s')->value['title'];?>
》'),'_blank'); } )()" title="分享到QQ空间"><img src="http://ctc.qzonestyle.gtimg.cn/qzonestyle/qzone_client_v5/img/favicon.ico" alt="分享到QQ空间" align="absmiddle" width="16" height="16" border="0"/></a> <a href="javascript:void(0);" onclick="window.open('http://sns.qzone.qq.com/cgi-bin/qzshare/cgi_qzshare_onekey?to=pengyou&url='+encodeURIComponent(document.location.href));return false;" title="分享到腾讯朋友"><img src="http://qzonestyle.gtimg.cn/ac/qzone_v5/app/qzshare/xy-icon.png" alt="分享到腾讯朋友" width="16" height="16" border="0" align="absmiddle" /></a> <a href="javascript:void(0)" onclick="postToWb();" style="height:16px;font-size:12px;line-height:16px;" title="分享到腾讯微博"><img src="http://v.t.qq.com/share/images/s/weiboicon16.png" align="absmiddle" border="0" width="16" height="16"/></a><script type="text/javascript">function postToWb(){ var _t = encodeURI(document.title);var _url = encodeURIComponent(document.location);var _appkey = encodeURI("appkey");var _pic = encodeURI('<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
admin/<?php echo $_smarty_tpl->getVariable('s')->value['picture'];?>
');var _site = '<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
';var _u = 'http://v.t.qq.com/share/share.php?url='+_url+'&appkey='+_appkey+'&site='+_site+'&pic='+_pic+'&title='+_t;window.open( _u,'', 'width=630, height=480, top=100, left=100, toolbar=no, menubar=no, scrollbars=no, location=yes, resizable=no, status=no' ); }</script> 
  <a rel="nofollow" class="fav_douban" href="javascript:void(function() { var%20d=document,e=encodeURIComponent,s1=window.getSelection,s2=d.getSelection,s3=d.selection,s=s1?s1():s2?s2():s3?s3.createRange().text:'',r='http://www.douban.com/recommend/?url='+e(d.location.href)+'&title='+e('LoveMusic 试听下载<?php echo $_smarty_tpl->getVariable('s')->value['artist'];?>
的专辑《<?php echo $_smarty_tpl->getVariable('s')->value['title'];?>
》')+'& sel='+e(s)+'&v=1',x=function() { if(!window.open(r,'douban','toolbar=0,resizable=1,scrollbars=yes,status=1,width=620,height=360'))location.href=r+'&amp;r=1' } ;if(/Firefox/.test(navigator.userAgent)) { setTimeout(x,0) } else { x() } } )()" title="分享到豆瓣音乐"><img src="http://img3.douban.com/favicon.ico" alt="分享到豆瓣音乐" width="16" height="16" border="0" align="absmiddle"/></a>
   <a href="javascript:(function() { window.open('http://v.t.sina.com.cn/share/share.php?appkey=791368876&title=LoveMusic 试听下载<?php echo $_smarty_tpl->getVariable('s')->value['artist'];?>
的专辑《<?php echo $_smarty_tpl->getVariable('s')->value['title'];?>
》&url='+encodeURIComponent(location.href)+'&source=bookmark','_blank','width=620,height=350'); } )()" title="分享到新浪微博"><img src="http://t.sina.com.cn/favicon.ico"  alt="分享到新浪微博" border="0" width="16" height="16" align="absmiddle"></a> <a hidefocus="true" href="javascript:window.open('http://www.kaixin001.com/repaste/share.php?rtitle='+encodeURIComponent('LoveMusic 试听下载<?php echo $_smarty_tpl->getVariable('s')->value['artist'];?>
的专辑《<?php echo $_smarty_tpl->getVariable('s')->value['title'];?>
》')+'&amp;rurl='+encodeURIComponent(location.href)+'&amp;rcontent=LoveMusic 试听下载<?php echo $_smarty_tpl->getVariable('s')->value['artist'];?>
的专辑《<?php echo $_smarty_tpl->getVariable('s')->value['title'];?>
》','_blank','scrollbars=no,width=600,height=450,left=75,top=20,status=no,resizable=yes'); void 0" onclick="ad_c_m('bbs_pageshare_kaixin001')" id="fx_kx" title="分享到开心网"><img src="http://img1.kaixin001.com.cn/i/favicon.ico" alt="分享到开心网" border="0" height="16" width="16" align="absmiddle"/></a>
   <a href="javascript:void((function(s,d,e) { if(/xiaonei\.com/.test(d.location))return;var%20f='http://share.xiaonei.com/share/buttonshare.do?link=',u=d.location,l='LoveMusic 试听下载<?php echo $_smarty_tpl->getVariable('s')->value['artist'];?>
的专辑《<?php echo $_smarty_tpl->getVariable('s')->value['title'];?>
》',p=[e(u),'&amp;title=',e(l)].join('');function%20a() { if(!window.open([f,p].join(''),'xnshare',['toolbar=0,status=0,resizable=1,width=636,height=446,left=',(s.width-636)/2,',top=',(s.height-446)/2].join('')))u.href=[f,p].join(''); } ;if(/Firefox/.test(navigator.userAgent))setTimeout(a,0);else%20a(); } )(screen,document,encodeURIComponent));" title="分享人人网"><img src="http://s.xnimg.cn/favicon-rr.ico" alt="分享人人网" border="0" height="16" width="16" align="absmiddle"></a>&nbsp;</div>
  </div>
<div class="publish">
  <div class="title"><img src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
templates/template/gray/images/publish.png" border="0" width="60" height="20"/></div>
  <div class="zhuanji">
    <ul>
      <?php if ($_smarty_tpl->getVariable('no_album')->value=='0'){?>
      <li style="width:560px; height:150px">我们正在努力添加中...</li>
      <?php }else{ ?>
      <?php  $_smarty_tpl->tpl_vars['a'] = new Smarty_Variable;
 $_from = $_smarty_tpl->getVariable('album')->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
if (count($_from) > 0){
    foreach ($_from as $_smarty_tpl->tpl_vars['a']->key => $_smarty_tpl->tpl_vars['a']->value){
?>
      <li><a href="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
album.php?skin=gray&id=<?php echo $_smarty_tpl->getVariable('a')->value['id'];?>
" title="<?php echo $_smarty_tpl->getVariable('a')->value['artist'];?>
-<?php echo $_smarty_tpl->getVariable('a')->value['title'];?>
"><img src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
thumbs/thumbs/cache/w190h190/admin/<?php echo $_smarty_tpl->getVariable('a')->value['picture'];?>
" border="0" width="176" height="176" alt="<?php echo $_smarty_tpl->getVariable('a')->value['artist'];?>
-<?php echo $_smarty_tpl->getVariable('a')->value['title'];?>
"/></a></li>
      <?php }} ?>
      <?php }?>
    </ul>
  </div>
</div>

<?php if ($_smarty_tpl->getVariable('page')->value!=''){?> <div class="page"><?php echo $_smarty_tpl->getVariable('page')->value;?>
</div><?php }?>  
  
</div>



<div class="otherlike">
  <div class="title"><img src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
templates/template/gray/images/other_artist.png" border="0" align="absmiddle"/></div>
  <div class="next"><a href="javascript:void(0)" id="otherlike_artist" title="换一批"><img src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
templates/template/gray/images/next.png" border="0"/></a> </div>
  <ul class="ul"><li class="onlyone">加载中...</li>
</ul>
</div>





<div class="maybelike">
  <div class="title"><img src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
templates/template/gray/images/xgyyr.png" border="0" width="75" height="20" align="absmiddle"/></div>
  <div class="next"><a href="javascript:void(0)" id="maybelike_artist" title="换一批"><img src="<?php echo $_smarty_tpl->getVariable('base_url')->value;?>
templates/template/gray/images/next.png" border="0"/></a></div>
  <ul class="ul"><li class="onlyone" style="font-size:12px; text-align:center; width:350px">加载中...</li></ul>
</div>


<?php $_template = new Smarty_Internal_Template("ad.html", $_smarty_tpl->smarty, $_smarty_tpl, $_smarty_tpl->cache_id, $_smarty_tpl->compile_id, null, null);
 echo $_template->getRenderedTemplate();?><?php $_template->updateParentVariables(0);?><?php unset($_template);?>



<?php $_template = new Smarty_Internal_Template("foot.html", $_smarty_tpl->smarty, $_smarty_tpl, $_smarty_tpl->cache_id, $_smarty_tpl->compile_id, null, null);
 echo $_template->getRenderedTemplate();?><?php $_template->updateParentVariables(0);?><?php unset($_template);?>
