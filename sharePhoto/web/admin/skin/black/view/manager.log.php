<?php include view::dir().'head.php';?>
<div class="table_head"><?php view::load_img('yes.gif');?>管理员日志</div>
<table cellpadding="0" cellspacing="0" class="table">
  <tr>
    <th width="100">日志序号</th>
    <th>内容</th>
    <th width="100">操作者</th>
    <th width="150">记录时间</th>
  </tr>
  <?php foreach($list as $log):?>
  <tr>
    <td><?php echo $log['_id']?></td>
    <td><?php echo $log['content']?></td>
    <td><?php echo $log['user']?></td>
    <td><?php echo date('Y-m-d H:i:s',$log['time'])?></td>
  </tr>
  <?php endforeach?>
</table>
<div class="page">
  <?php echo $page?>
</div>
<?php include view::dir().'foot.php';?>
