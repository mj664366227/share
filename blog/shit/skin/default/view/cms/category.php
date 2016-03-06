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
<ul class="nav nav-tabs">
<?php foreach($category as $c):?>
<?php if($id==$c['id']){?>
<li class="active"><?php echo $c['name']?>(<?php echo $c['contents']?>)</li>
<?php }else{?>
<li><a href="<?php echo url('cms','category', 'id='.$c['id']);?>" title="查看“<?php echo $c['name']?>”分类下所有文章"><?php echo $c['name']?>(<?php echo $c['contents']?>)</a></li>
<?php } endforeach;?>
</ul>
<div class="tab-content">
  <div id="pv_index" class="tab-pane fade<c:if test="${item=='pv_index'}"> in active</c:if>"></div>
  <div id="uv_index" class="tab-pane fade<c:if test="${item=='uv_index'}"> in active</c:if>"></div>
</div>
<?php require view::dir().'foot.php';?>
