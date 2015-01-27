<html><head><meta http-equiv="Content-Type" content="text/html; charset=<?php echo CHARSET_HTML?>" />
<?php view::load_css('css');?>
<?php view::load_js('jquery');?>
<?php view::load_js('admin');?>
<?php view::load_short_icon();?>
<title><?php echo strip_tags(SITE_TITLE)?></title></head><body>
<?php if($result && view::get_display() !== 'admin.menu'):?>
<?php if($result === 'notice'):?><div class="notice"><?php echo $tips?></div><?php endif?>
<?php if($result === 'success'):?><div class="success"><?php echo $tips?></div><?php endif?>
<?php if($result === 'error'):?><div class="error"><?php echo $tips?></div><?php endif?>
<?php endif?>