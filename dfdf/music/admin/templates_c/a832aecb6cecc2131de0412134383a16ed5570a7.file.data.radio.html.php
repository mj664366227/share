<?php /* Smarty version Smarty3-b8, created on 2013-03-02 05:04:07
         compiled from "F:/PHPNOW/vhosts/music/admin/templates/template/data.radio.html" */ ?>
<?php /*%%SmartyHeaderCode:32725131884760f1c5-06015493%%*/if(!defined('SMARTY_DIR')) exit('no direct access allowed');
$_smarty_tpl->decodeProperties(array (
  'file_dependency' => 
  array (
    'a832aecb6cecc2131de0412134383a16ed5570a7' => 
    array (
      0 => 'F:/PHPNOW/vhosts/music/admin/templates/template/data.radio.html',
      1 => 1343982346,
    ),
  ),
  'nocache_hash' => '32725131884760f1c5-06015493',
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
<title>数据统计 - 音乐电台统计</title>
</head>
<body>
<script type="text/javascript">
Highcharts.visualize = function(table, options) {
				// the categories
				options.xAxis.categories = [];
				$('tbody th', table).each( function(i) {
					options.xAxis.categories.push(this.innerHTML);
				});
				
				// the data series
				options.series = [];
				$('tr', table).each( function(i) {
					var tr = this;
					$('th, td', tr).each( function(j) {
						if (j > 0) { // skip first column
							if (i == 0) { // get the name and init the series
								options.series[j - 1] = { 
									name: this.innerHTML,
									data: []
								};
							} else { // add values
								options.series[j - 1].data.push(parseFloat(this.innerHTML));
							}
						}
					});
				});
				
				var chart = new Highcharts.Chart(options);
			}
$(document).ready(function() {			
				var table = document.getElementById('datatable'),
				options = {
					   chart: {
					      renderTo: 'container',
					      defaultSeriesType: 'column'
					   },
					   title: {
					      text: '音乐电台统计'
					   },
					   xAxis: {
					   },
					   yAxis: {
					      title: {
					         text: ''
					      }
					   },
					   tooltip: {
					      formatter: function() {
					         return '<b>'+ this.series.name +'</b><br/><br/>'+
					            this.y +' '+ this.x.toLowerCase();
					      }
					   }
					};					
				Highcharts.visualize(table, options);
			});
		</script>
        <div class="main_right">
  <div class="title"><img src="templates/template/images/ico_clew_yes.gif" border="0"/>&nbsp;<a>音乐电台统计</a></div>
  <table cellspacing="0">
    <tr>
      <td>
      <div id="container" style="width:100%; height: 400px; margin: 0 auto"></div></td>
    </tr>
  </table>
  <table id="datatable" style="display:none">
  <thead><tr><th></th><th>歌曲数</th><th>点击数</th></tr></thead>
  <tbody>
  <?php  $_smarty_tpl->tpl_vars['o'] = new Smarty_Variable;
 $_from = $_smarty_tpl->getVariable('output')->value; if (!is_array($_from) && !is_object($_from)) { settype($_from, 'array');}
if (count($_from) > 0){
    foreach ($_from as $_smarty_tpl->tpl_vars['o']->key => $_smarty_tpl->tpl_vars['o']->value){
?>
  <tr>
  <th><?php echo $_smarty_tpl->getVariable('o')->value['name'];?>
</th>
  <td><?php echo $_smarty_tpl->getVariable('o')->value['count'];?>
</td>
  <td><?php echo $_smarty_tpl->getVariable('o')->value['click'];?>
</td>
	</tr>
  <?php }} ?>
			</tbody>
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