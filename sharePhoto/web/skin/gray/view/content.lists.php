<?php require view::dir().'head.php';?>

<div class="sub_header">
  <div class="inner">
    <div class="tags">
      <dl>
        <dt>类别：</dt>
        <dd> <a href="<?php echo url('content','lists','order='.$order)?>"<?php if($cid==0):?> class="selected"<?php endif?>>全部</a>
          <?php foreach($category as $c):?>
          <a href="<?php echo url('content','lists','category='.$c['_id'])?>"<?php if($cid==$c['_id']):?> class="selected"<?php endif?>><?php echo $c['name']?></a>
          <?php endforeach?>
        </dd>
      </dl>
      <dl>
        <dt>排序：</dt>
        <dd> <a href="<?php echo url('content','lists', 'order=new'.($cid?'&category='.$cid:''))?>"<?php if($order=='new'):?> class="selected"<?php endif?>>最新发表</a> <a href="<?php echo url('content','lists', 'order=introduce'.($cid?'&category='.$cid:''))?>"<?php if($order=='introduce'):?> class="selected"<?php endif?>>编辑推荐</a> </dd>
      </dl>
    </div>
  </div>
</div>
<div class="main">
  <div class="inner">
    <ul class="articlelist">
      <?php foreach($list['list'] as $c):?>
      <li> <a href="show.html" class="left"> <img src="<?php echo $userinfo[$c['uid']]['head']?str_replace('?', '', $setting['server_url'][$userinfo[$c['uid']]['server']]).'user/'.$c['uid'].'/'.$c['uid'].'_64_64.'.$userinfo[$c['uid']]['head']:url().'skin/'.view::get_skin().'/images/default_64_64.png';?>" width="64" height="64"/> </a>
        <div class="article">
          <div><a href="show.html" class="by"><?php echo $userinfo[$c['uid']]['nick']?></a>&nbsp;&nbsp;发布于<?php echo time::get_day_before($c['time'])?></div>
          <div class="cover_box"><a href="article.html" ><img src="<?php echo str_replace('?','',$setting['server_url'][$c['server']])?>frontcover/<?php echo $c['uid']?>/<?php echo $c['thumbnails']?>" alt="<?php echo $c['title']?>" title="<?php echo $c['title']?>" class="articlecover" width="120" height="120"/></a></div>
          <div class="text_box">
            <p><a href="article.html" class="articletitle"><?php echo $c['title']?></a></p>
            <div class="con_box"><?php echo trim(preg_replace('/<br\s*\/>/','',$c['content']))?></div>
          </div>
          <div class="clear"></div>
        </div>
      </li>
      <?php endforeach;?>
    </ul>
    <div class="page"> <?php echo $page?></div>
  </div>
</div>
<?php require view::dir().'foot.php';?>
