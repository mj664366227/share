<?php /* Smarty version Smarty3-b8, created on 2012-09-06 17:16:42
         compiled from "F:\htdocs\smu/admin/templates/template/data.album.html" */ ?>
<?php /*%%SmartyHeaderCode:227375048da7acf5d44-98538100%%*/if(!defined('SMARTY_DIR')) exit('no direct access allowed');
$_smarty_tpl->decodeProperties(array (
  'file_dependency' => 
  array (
    '045a7e871930d87e9ebad32568670d7d1d817cc4' => 
    array (
      0 => 'F:\\htdocs\\smu/admin/templates/template/data.album.html',
      1 => 1343982345,
    ),
  ),
  'nocache_hash' => '227375048da7acf5d44-98538100',
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
<title>数据统计 - 专辑点击统计</title>
</head>
<body>
<script type="text/javascript">
		
			var chart;
			$(document).ready(function() {
				chart = new Highcharts.Chart({
					chart: {
						renderTo: 'container',
						plotBackgroundColor: null,
						plotBorderWidth: null,
						plotShadow: false
					},
					title: {
						text: '<?php echo $_smarty_tpl->getVariable('title')->value;?>
'
					},
					tooltip: {
						formatter: function() {
							return '<b>'+ this.point.name +'</b>: '+ this.y +' %';
						}
					},
					plotOptions: {
						pie: {
							allowPointSelect: true,
							cursor: 'pointer',
							dataLabels: {
								enabled: true,
								color: '#000000',
								connectorColor: '#000000',
								formatter: function() {
									return '<b>'+ this.point.name +'</b>: '+ this.y +' %';
								}
							}
						}
					},
				    series: [{
						type: 'pie',
						data: [<?php echo $_smarty_tpl->getVariable('data')->value;?>
]
					}]
				});
			});
				
		</script>
        <div class="main_right">
  <div class="title"><img src="templates/template/images/ico_clew_yes.gif" border="0"/>&nbsp;<a>专辑点击统计(括号内为专辑数，百分数表示点击数占总的比例，色块表示专辑数占总的比例)</a></div>
  <table cellspacing="0">
    <tr>
      <td>
      <?php  $_smarty_tpl->tpl_vars['m'] = new Smarty_Variable;
 $_smarty_tpl->tpl_vars['k'] = new Smarty_Variable;
 $_from = $_smarty_tpl->getVariable('menu')->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
if (count($_from) > 0){
    foreach ($_from as $_smarty_tpl->tpl_vars['m']->key => $_smarty_tpl->tpl_vars['m']->value){
 $_smarty_tpl->tpl_vars['k']->value = $_smarty_tpl->tpl_vars['m']->key;
?>
      <?php if ($_smarty_tpl->getVariable('type')->value==$_smarty_tpl->getVariable('k')->value){?>
      <font><?php echo $_smarty_tpl->getVariable('m')->value;?>
</font>
      <?php }else{ ?>
      <a href="data.php?action=album&type=<?php echo $_smarty_tpl->getVariable('k')->value;?>
"><?php echo $_smarty_tpl->getVariable('m')->value;?>
</a>
      <?php }?>
      <?php }} ?>
        <br/> 
      <div id="container" style="width:100%; height: 400px; margin: 0 auto"></div></td>
    </tr>
  </table>
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
</style>