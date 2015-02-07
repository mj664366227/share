<?php require view::dir().'head.php';?>

<div style="margin:10px 0 0 15px">当前位置：<a href="<?php echo url('file','md')?>">root</a> <?php echo $p;?>
  <?php if(!empty($file)){echo '/ '.$file;}?>
</div>
<?php if(empty($html)):?>
<?php if(is_array($path)):?>
<div class="panel-body">
  <div class="table-responsive">
    <table class="table table-striped table-bordered table-hover">
      <tbody>
        <?php foreach($path as $value):?>
        <tr>
          <td><a href="<?php echo url('file','md', 'p='.$p.(intval(strpos($value,'.'))>0?'&file='.$value:'/'.$value));?>" title="dd"><?php echo $value?></a></td>
        </tr>
        <?php endforeach?>
      </tbody>
    </table>
  </div>
</div>
<?php endif?>
<?php else:?>
<div style="width:100%;margin-left:-30px"><?php echo $html;?></div>
<?php endif?>
<?php require view::dir().'foot.php';?>
