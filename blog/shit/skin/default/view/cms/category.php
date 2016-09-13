<?php require view::dir().'head.php';?>
<?php require view::dir().'menu.php';?>

<form action="<?php echo url('cms','category');?>" method="post">
  <table class="table table-bordered table-model-2">
    <tr>
      <td width="120">文章类型</td>
      <td><input type="text" class="form-control" name="name"/></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td><input type="submit" class="btn btn-secondary btn-single" value="提交"></td>
    </tr>
  </table>
</form>
<ul class="nav nav-tabs" id="category">
  <?php foreach($category as $c):?>
  <?php if($id==$c['id']){?>
  <li class="active"><a><?php echo $c['name']?>(<?php echo $c['contents']?>)</a></li>
  <?php }else{?>
  <li><a href="<?php echo url('cms','category', 'id='.$c['id']);?>" title="查看“<?php echo $c['name']?>”分类下所有文章"><?php echo $c['name']?>(<?php echo $c['contents']?>)</a></li>
  <?php } endforeach;?>
</ul>
<div class="tab-content">
  <div class="tab-pane fade in active">
    <?php if(count($content) > 0):?>
    <table class="table mail-table">
      <tr>
        <th>文章标题</th>
        <th width="150">发表时间</th>
        <th width="100">操&nbsp;&nbsp;作</th>
      </tr>
      <?php foreach($content as $c):?>
      <tr>
        <td><a href="javascript:void(0)"><?php echo $c['title']?></a></td>
        <td><?php echo date('Y-m-d H:i:s', $c['create_time'])?></td>
        <td><a href="javascript:void(0)">删除</a></td>
      </tr>
      <?php endforeach;?>
    </table>
    <?php else:echo '什么都没有...';?>
    <?php endif;?>
    <?php require view::dir().'page.php';?>
  </div>
</div>
<?php require view::dir().'foot.php';?>