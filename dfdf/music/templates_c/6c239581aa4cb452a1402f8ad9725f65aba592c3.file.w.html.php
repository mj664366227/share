<?php /* Smarty version Smarty3-b8, created on 2012-02-22 10:47:03
         compiled from "/home/libmill/domains/lovemusic.cc/public_html/templates/template/deafault/w.html" */ ?>
<?php /*%%SmartyHeaderCode:9253067884f445727587b29-49986613%%*/if(!defined('SMARTY_DIR')) exit('no direct access allowed');
$_smarty_tpl->decodeProperties(array (
  'file_dependency' => 
  array (
    '6c239581aa4cb452a1402f8ad9725f65aba592c3' => 
    array (
      0 => '/home/libmill/domains/lovemusic.cc/public_html/templates/template/deafault/w.html',
      1 => 1329490450,
    ),
  ),
  'nocache_hash' => '9253067884f445727587b29-49986613',
  'function' => 
  array (
  ),
  'has_nocache_code' => false,
)); /*/%%SmartyHeaderCode%%*/?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"><html xmlns="http://www.w3.org/1999/xhtml"><head><meta http-equiv="Content-Type" content="text/html; charset=utf-8" /><link rel="shortcut icon" href="favicon.ico"/><meta name="keywords" content="somethingmusic音乐电台,<?php  $_smarty_tpl->tpl_vars['c'] = new Smarty_Variable;
 $_from = $_smarty_tpl->getVariable('channel')->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
if (count($_from) > 0){
    foreach ($_from as $_smarty_tpl->tpl_vars['c']->key => $_smarty_tpl->tpl_vars['c']->value){
?><?php echo $_smarty_tpl->getVariable('c')->value['name'];?>
<?php echo $_smarty_tpl->getVariable('c')->value['en_name'];?>
,<?php }} ?>飞车坊,华汽飞车坊,somethingmusic,lovemusic,流行音乐,小众音乐,音乐电台,jplayer,有内涵的音乐" /><meta name="description" content="somethingmusic音乐电台,最新最快最全的音乐。" /><link rel="stylesheet" href="templates/template/deafault/css/css.css" type="text/css" /><!--[if IE]><link rel="stylesheet" href="templates/template/deafault/css/all_ie.css" type="text/css" /><![endif]--><!--[if IE 6]><link rel="stylesheet" href="templates/template/deafault/css/ie6.css" type="text/css" /><![endif]--><!--[if IE 7]><link rel="stylesheet" href="templates/template/deafault/css/ie7.css" type="text/css" /><![endif]--><title>somethingmusic音乐电台（窗口版）- 我们一直在努力！</title></head><body><div id="jquery_jplayer"></div><div style="background-color:#f4f4f4;font-size:13px; height:310px; margin-top:0px; width:450px"><br/><div style="width:190px; margin-top:10px; text-align:center; margin-left:30px"><div class="the_title">读取中...</div><div class="the_artist">读取中...</div><div class="the_pic"><embed src="templates/template/deafault/images/buffering.swf" width="190" height="190"></embed></div><div class="the_operation"><div class="radio_play" title="播放"></div><div class="radio_pause" title="暂停"></div><div class="radio_next" title="下一首"></div><div class="radio_select"><div class="current">读取中...</div><form><input type="hidden" value=""/></form><div class="change">换台</div><div class="channel" style="display:block"><?php  $_smarty_tpl->tpl_vars['c'] = new Smarty_Variable;
 $_from = $_smarty_tpl->getVariable('channel')->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
if (count($_from) > 0){
    foreach ($_from as $_smarty_tpl->tpl_vars['c']->key => $_smarty_tpl->tpl_vars['c']->value){
?><div class="c" value="<?php echo $_smarty_tpl->getVariable('c')->value['name'];?>
" title="<?php echo $_smarty_tpl->getVariable('c')->value['name'];?>
" en="<?php echo $_smarty_tpl->getVariable('c')->value['en_name'];?>
"><?php echo $_smarty_tpl->getVariable('c')->value['html'];?>
</div><?php }} ?><div class="radio_close" title="关闭" style="display:block;background:url(templates/template/deafault/images/radio_close.png)"></div></div></div></div></div></div><!--[if IE 6]><style>.current { position:absolute; left:-20px;width:100px }.c { position:relative;left:-5px; }.radio { margin-left:1px }</style><![endif]--><style>*{ background-color:#f4f4f4; }.radio_select{ position:relative;width:50px; float:right}.change{ cursor:pointer; width:50px; height:17px; background-color:#999;color:#FFF; padding-top:3px; margin-top:3px;}.channel{ position:absolute; left:58px; top:-146px; width:170px; height:170px; display:none; background-color:#dfdfdf; border:1px solid #ccc;}.c{ cursor:pointer; width:50px;height:42px; float:left;margin-top:5px;text-align:center; margin-left:5px; background-color:#999; font-size:15px; color:#fff; padding-top:8px;}.current{ float:left; margin-top:5px; font-size:14px; color:#666; margin-left:-70px}.radio_close{ width:25px; height:25px; position:absolute; right:-17px; top:-17px; cursor:pointer; z-index:-100px}.radio_unselect{ background-color:#999 } </style><script type="text/javascript" src="templates/template/deafault/js/jquery-1.5.1.min.js"></script><script type="text/javascript" src="templates/template/deafault/js/jquery.jplayer.js"></script><script type="text/javascript" src="templates/template/deafault/js/radio.js"></script><div style="display:none"><script src="http://s19.cnzz.com/stat.php?id=2888039&web_id=2888039" language="JavaScript"></script></div></body></html>