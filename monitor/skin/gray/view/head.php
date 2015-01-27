<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=<?php echo CHARSET_HTML?>" />
<?php view::keywords('css', SYSTEM_TITLE);?>
<?php view::description('css', SYSTEM_TITLE);?>
<?php view::load_css('css');?>
<?php view::load_js('jquery');?>
<?php view::load_js('highcharts');?>
<?php view::load_js('rcalendar');?>
<?php view::load_js('function');?>
<?php view::load_short_icon();?>
<title><?php echo SYSTEM_TITLE?></title>
</head>
<body>
<!--logo-->
<div id="logo">
  <h2><a href="<?php echo url()?>" title="<?php echo SYSTEM_TITLE?>"><?php echo SYSTEM_TITLE?></a></h2>
</div>
<?php if(is_array($menu)):?>
<!--导航栏-->
<div class="nav">
  <ul>
    <?php foreach($menu as $k => $m):?>
    <li><a href="<?php echo url($class, $k)?>"<?php if($action===$k):?> class="current"<?php endif?> title="<?php echo $m?>"><?php echo $m?></a></li>
    <?php endforeach?>
  </ul>
</div>
<?php endif;?>
