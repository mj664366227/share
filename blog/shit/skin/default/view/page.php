<?php $max = intval($max);?>
<?php $page = intval($page);?>
<?php if($max > 1):?>
<?php $cururl = cururl();?>
<?php $index = intval(strripos($cururl,'&page'));?>
<?php $url = $index <= 0 ? $cururl : substr($cururl,0,$index);?>
<div class="page">
  <ul class="pagination">
    <?php if($page > 1):?>
    <li><a href="<?php echo $url;?>&page=<?php echo $page-1?>" title="上一页"><span aria-hidden="true">&laquo;</span></a></li>
    <?php endif;?>
    <?php $begin = ($page - 5) > 0 ? ($page - 5) : 1;?>
    <?php $end = ($page + 5) > $max ? $max : ($page + 5);?>
    <?php for($i = $begin; $i <= $end; $i++):?>
    <?php if($page == $i){?>
    <li class="active"><a><?php echo $i;?></a></li>
    <?php }else{?>
    <li><a href="<?php echo $url;?>&page=<?php echo $i;?>" title="第<?php echo $i;?>页"><?php echo $i;?></a></li>
    <?php }?>
    <?php endfor;?>
    <?php if($page + 1 <= $max):?>
    <li><a href="<?php echo $url;?>&page=<?php echo $page+1?>" title="下一页"><span aria-hidden="true">&raquo;</span></a></li>
    <?php endif;?>
  </ul>
</div>
<?php endif;?>