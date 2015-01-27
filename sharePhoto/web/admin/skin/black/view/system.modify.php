<?php include view::dir().'head.php';?>
<div class="table_head"><?php view::load_img('yes.gif');?>修改管理员信息</div>
<form action="<?php echo url('system', 'manager', 'modify=1&id='.$admin['id'])?>" method="post">
  <table cellpadding="0" cellspacing="0" class="table">
    <tr>
      <td width="100">登录名：</td>
      <td><?php echo $admin['name']?>
        <input type="hidden" class="text" name="name" id="add_manager_name" value="<?php echo $admin['name']?>"/></td>
    </tr>
    <tr>
      <td>昵称：</td>
      <td><input type="text" class="text" name="nick" id="add_manager_nick" value="<?php echo $admin['nick']?>"/></td>
    </tr>
    <tr>
      <td width="100">登录密码：</td>
      <td><input type="password" class="text" name="pass1" id="add_manager_pass1" value="<?php echo $admin['pass']?>"/></td>
    </tr>
    <tr>
      <td>再来一次：</td>
      <td><input type="password" class="text" name="pass2" id="add_manager_pass2"/></td>
    </tr>
    <tr>
      <td></td>
      <td><input type="submit" class="btn" value="提交"/>
        <input type="button" class="btn" value="返回"/></td>
    </tr>
  </table>
</form>
<?php include view::dir().'foot.php';?>
