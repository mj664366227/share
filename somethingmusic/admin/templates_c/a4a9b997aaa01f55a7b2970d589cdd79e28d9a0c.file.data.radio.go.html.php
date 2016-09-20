<?php /* Smarty version Smarty3-b8, created on 2012-09-06 17:16:41
         compiled from "F:\htdocs\smu/admin/templates/template/data.radio.go.html" */ ?>
<?php /*%%SmartyHeaderCode:244635048da79ba3131-85461033%%*/if(!defined('SMARTY_DIR')) exit('no direct access allowed');
$_smarty_tpl->decodeProperties(array (
  'file_dependency' => 
  array (
    'a4a9b997aaa01f55a7b2970d589cdd79e28d9a0c' => 
    array (
      0 => 'F:\\htdocs\\smu/admin/templates/template/data.radio.go.html',
      1 => 1343982345,
    ),
  ),
  'nocache_hash' => '244635048da79ba3131-85461033',
  'function' => 
  array (
  ),
  'has_nocache_code' => false,
)); /*/%%SmartyHeaderCode%%*/?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" href="templates/template/css.css" type="text/css" />
<script type="text/javascript" src="templates/template/jquery-1.4a2.js"></script>
<script type="text/javascript" src="templates/template/highcharts.js"></script>
<script type="text/javascript" src="templates/template/calendar/WdatePicker.js"></script>
<script type="text/javascript">
		
			var chart;
			$(document).ready(function() {
				chart = new Highcharts.Chart({
					chart: {
						renderTo: 'container',
						defaultSeriesType: 'line'
					},
					title: {
						text: '<?php if ($_smarty_tpl->getVariable('st')->value==$_smarty_tpl->getVariable('et')->value){?><?php echo $_smarty_tpl->getVariable('et')->value;?>
 电台收听走势<?php }else{ ?><?php echo $_smarty_tpl->getVariable('st')->value;?>
 至 <?php echo $_smarty_tpl->getVariable('et')->value;?>
 电台 <?php echo $_smarty_tpl->getVariable('en')->value;?>
 收听走势<?php }?>'
					},
					xAxis: {
						categories: [<?php echo $_smarty_tpl->getVariable('time')->value;?>
]
					},
					yAxis: {
						title: {
							text: '收听人数'
						}
					},
					tooltip: {
						enabled: false,
						formatter: function() {
							return '<b>'+ this.series.name +'</b><br/>'+
								this.x +': '+ this.y +'°C';
						}
					},
					plotOptions: {
						line: {
							dataLabels: {
								enabled: true
							},
							enableMouseTracking: false
						}
					},
					series: [<?php echo $_smarty_tpl->getVariable('result')->value;?>
]
				});
				
				
			});
				
		</script>
<title>数据统计 - 电台收听走势</title>
</head>
<body>
<div class="main_right">
  <div class="title"><img src="templates/template/images/ico_clew_yes.gif" border="0"/>&nbsp;<a>电台收听走势</a></div>
  <form action="data.php" method="get">
  <table cellspacing="0">
  <input type="hidden" name="action" value="go"/>
  <?php if ($_smarty_tpl->getVariable('html')->value){?>
  <input type="hidden" name="st" value="<?php echo $_smarty_tpl->getVariable('st')->value;?>
"/>
  <input type="hidden" name="et" value="<?php echo $_smarty_tpl->getVariable('et')->value;?>
"/>
  <?php }?>
  <input type="hidden" name="channel" value="<?php echo $_smarty_tpl->getVariable('channel')->value;?>
"/>
  <tr><td>
  <?php if ($_smarty_tpl->getVariable('channel')->value=='all'){?>
       <font>总走势</font>
      <?php }else{ ?>
      <a href="data.php?action=go&channel=all">总走势</a>
      <?php }?>
  <?php  $_smarty_tpl->tpl_vars['m'] = new Smarty_Variable;
 $_smarty_tpl->tpl_vars['k'] = new Smarty_Variable;
 $_from = $_smarty_tpl->getVariable('radio')->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
if (count($_from) > 0){
    foreach ($_from as $_smarty_tpl->tpl_vars['m']->key => $_smarty_tpl->tpl_vars['m']->value){
 $_smarty_tpl->tpl_vars['k']->value = $_smarty_tpl->tpl_vars['m']->key;
?>
      <?php if ($_smarty_tpl->getVariable('channel')->value==$_smarty_tpl->getVariable('m')->value['en_name']){?>
      <font><?php echo $_smarty_tpl->getVariable('m')->value['name'];?>
</font>
      <?php }else{ ?>
      <a href="data.php?action=go&channel=<?php echo $_smarty_tpl->getVariable('m')->value['en_name'];?>
<?php echo $_smarty_tpl->getVariable('html')->value;?>
"><?php echo $_smarty_tpl->getVariable('m')->value['name'];?>
</a>
      <?php }?>
      <?php }} ?>
        <br/><br/><br/> 
  时间：<input type="text" name="st" onclick="WdatePicker()" value="<?php echo $_smarty_tpl->getVariable('st')->value;?>
" class="t"/> — <input type="text" name="et" onclick="WdatePicker()" value="<?php echo $_smarty_tpl->getVariable('et')->value;?>
" class="t" autocomplete="off"/>&nbsp;&nbsp;<input type="submit" class="button" value="提  交" autocomplete="off"/><br/><br/>
  <div id="container"></div>
  </td></tr> 
  </table>
  </form>
</div>
</body>
</html>
<style>
table tr td a {
	color:#09F;
	text-decoration:underline;
	line-height:20px
}
table tr td a:hover {
	color:#F00;
	font-weight:bold;
	text-decoration:none
}
.t{ width:65px}
</style>