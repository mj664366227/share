<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title><?php echo SITE_TITLE?></title>
<?php view::load_short_icon();?>
<?php view::load_css('bootstrap.min');?>
<?php view::load_css('metisMenu.min');?>
<?php view::load_css('timeline');?>
<?php view::load_css('sb-admin-2');?>
<?php view::load_css('morris');?>
<?php view::load_css('font-awesome.min');?>
<?php view::load_js('jquery.min');?>
<?php view::load_js('bootstrap.min');?>
<?php view::load_js('metisMenu.min');?>
<?php view::load_js('sb-admin-2');?>
<script type="text/javascript">
$(function(){
	$(':input').attr('autocomplete', 'off');
})
</script>
</head>
<body>
<div id="wrapper">
<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
  <div class="navbar-header">
    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse"> <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </button>
    <a class="navbar-brand" href="<?php echo url()?>" title="<?php echo SITE_TITLE?>"><?php echo SITE_TITLE?></a> </div>
  <div class="navbar-default sidebar" role="navigation">
    <div class="sidebar-nav navbar-collapse">
      <ul class="nav" id="side-menu">
        <?php foreach($menu as $k => $m):?>
        <li>
          <?php $arr = explode('#', $k);?>
          <a href="javascript:void(0)"> <?php echo $arr[0]?><span class="fa arrow"></span></a>
          <ul class="nav nav-second-level">
            <?php foreach($m as $key => $name):?>
            <li><a href="<?php $arr = explode('.', $key);echo url($arr[0], $arr[1])?>" title="<?php echo $name?>"><?php echo $name?></a></li>
            <?php endforeach;?>
          </ul>
        </li>
        <?php endforeach;?>
      </ul>
    </div>
  </div>
</nav>
<div id="page-wrapper">
