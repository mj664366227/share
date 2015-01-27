<?php include view::dir().'head.php';?>

<div class="table_head">
  <?php view::load_img('yes.gif');?>
  添加服务器</div>
<form action="<?php echo url('system','servers');?>" method="post">
  <table cellpadding="0" cellspacing="0" class="table">
    <tr>
      <td width="100">服务器名称</td>
      <td><input name="name" type="text" class="text" id="name"/></td>
    </tr>
    <tr>
      <td>服务器地址</td>
      <td><input name="url" type="text" class="text" id="url"/></td>
    </tr>
    <tr>
      <td></td>
      <td><input type="submit" class="btn" value="提交"/></td>
    </tr>
  </table>
</form>
<div class="table_head">
  <?php view::load_img('yes.gif');?>
  服务器列表</div>
<table cellpadding="0" cellspacing="0" class="table">
  <tr>
    <th width="150">服务器id</th>
    <th width="200">服务器名称</th>
    <th width="400">服务器地址</th>
    <th>操作</th>
  </tr>
  <?php foreach($servers as $server):?>
  <tr>
    <td><?php echo $server['_id']?></td>
    <td><?php echo $server['name']?></td>
    <td><?php echo $server['url']?></td>
    <td><a href="#">修改</a>&nbsp;&nbsp;<a href="#">删除</a></td>
  </tr>
  <?php endforeach;?>
</table>
<?php include view::dir().'foot.php';?>
