<?php include view::dir().'head.php';?>

<div class="table_head">
  <?php view::load_img('yes.gif');?>
  添加文章分类</div>
<form action="<?php echo url('content','add');?>" method="post">
  <table cellpadding="0" cellspacing="0" class="table">
  <tr>
  <td colspan="2">
  <?php foreach($category as $cat):?>
  <a href="<?php echo url('content','delete','id='.$cat['_id']);?>" title="点击删除“<?php echo $cat['name'];?>”" onclick="return confirm('删除文章类型有可能会影响到用户的数据！你确定要删除“<?php echo $cat['name'];?>”吗？')"><?php echo $cat['name'];?></a>
  <?php endforeach?>
  </td>
  </tr>
    <tr>
      <td width="100">分类名称</td>
      <td><input name="name" type="text" class="text" id="name"/></td>
    </tr>
    <tr>
      <td></td>
      <td><input type="submit" class="btn" value="提交"/></td>
    </tr>
  </table>
</form>
<?php include view::dir().'foot.php';?>
