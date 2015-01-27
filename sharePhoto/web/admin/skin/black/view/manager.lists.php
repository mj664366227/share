<?php include view::dir().'head.php';?>
<div class="table_head"><?php view::load_img('yes.gif');?>管理员列表</div>
<?php if(is_array($list)):?>
<table cellpadding="0" cellspacing="0" class="table">
  <tr>
    <th width="120">管理员帐号</th>
    <th width="120">管理员昵称</th>
    <th width="160">上次登录时间</th>
    <th width="100">上次登录IP</th>
    <th width="100">登录次数</th>
    <th>操作</th>
  </tr>
  <?php foreach($list as $admin):?>
  <tr>
    <td><?php echo $admin['name']?></td>
    <td><?php echo $admin['nick']?></td>
    <td><?php echo $admin['lass_login_time']?date('Y-m-d H:i:s',$admin['lass_login_time']):''?></td>
    <td><?php echo ip::long_to_ip($admin['ip'])?></td>
    <td><?php echo number_format($admin['times'])?></td>
    <td><a href="<?php echo url('system', 'manager', 'modify=1&id='.$admin['_id'])?>">修改</a>&nbsp;&nbsp;<a href="<?php echo url('manager', 'delete', 'id='.$admin['_id'].'&nick='.$admin['nick'])?>" onclick="return confirm('你真的要<?php echo $admin['nick']?>删除吗？')">删除</a></td>
  </tr>
  <?php endforeach?>
</table>
<div class="page">
  <?php echo $page?>
</div>
<?php else:?>
<div class="error"><?php echo NO_DATA?></div>
<?php endif?>
<?php include view::dir().'foot.php';?>
