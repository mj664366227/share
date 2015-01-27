<?php include view::dir().'head.php';?>
<?php if($login != 1):?>
<!--主菜单-->
<div class="left" id="left">
  <div class="website">
    <div class="title"><a href="<?php echo url('admin', 'admin')?>" title="后台首页" target="right"> <?php echo SITE_TITLE?> </a></div>
    <div class="welcome">亲爱的 <?php echo $nick?> ，欢迎回来！ <br/>
      您的IP： <?php echo ip::get_user_ip();?> &nbsp;</div>
    <div class="operation"><a href="<?php echo url()?>../" target="_blank">网站首页</a>&nbsp;<a>|</a>&nbsp;<a href="<?php echo url('login', 'logout')?>" onclick="return confirm('你真的要退出吗？')" target="_top">退出后台</a></div>
  </div>
  <?php foreach($menu as $key => $m):?>
  <div class="menu">
    <?php $arr = explode('#', $key); $key = $arr[0];?>
    <div class="title"><span title="<?php echo $key?>" onclick="$('.menu .<?php echo $arr[1]?>').toggle(60);"><?php echo $key?></span></div>
    <ul class="<?php echo $arr[1]?>">
      <?php foreach($m as $k => $mm):?>
      <?php if(stripos($k, '_') === false):?>
      <?php $action = explode('/', $k);?>
      <li<?php if($k==sharePHP::get_class().'/'.sharePHP::get_method() || $current):?> class="current"<?php endif?>><a href="<?php echo url($action[0], $action[1]);$arr=explode('_',$mm);$title=$arr[0]?>" title="<?php echo $title?>" target="<?php if($arr[1]):echo $arr[1]?><?php else:?>right<?php endif?>"><?php echo $title?></a></li>
      <?php endif;?>
      <?php endforeach;?>
    </ul>
  </div>
  <?php endforeach;?>
</div>
<?php endif?>
<?php include view::dir().'foot.php';?>
<style>
body{ background:#0e0e0e}
</style>
