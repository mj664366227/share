<?php require view::dir().'head.php';?>

<div class="main">
  <div class="inner">
    <div class="container">
      <div class="profile">
        <div class="l left"> <a href="show.html"><img src="<?php echo $user['head120']?>" id="head120"/></a> </div>
        <div class="c left">
          <p class="user_name"><?php echo $nick?></p>
          <p>http://weibo.com/oohme</p>
          <p>
            <button class="btn_common">加关注</button>
          </p>
        </div>
        <div class="r left"></div>
        <div class="clear"></div>
      </div>
      <?php foreach($my_albums as $album):?>
      <a href="<?php echo url('works','show','uid='.$myuid.'&album_id='.$album['_id'])?>"><?php echo $album['name']?></a>
	  <?php endforeach;?>
    </div>
  </div>
</div>
<?php require view::dir().'foot.php';?>
