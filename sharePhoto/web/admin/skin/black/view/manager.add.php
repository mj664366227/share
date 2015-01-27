<?php include view::dir().'head.php';?>
<div class="table_head"><?php view::load_img('yes.gif');?>添加管理员</div>
<form action="<?php echo url('manager','add');?>" method="post">
<table cellpadding="0" cellspacing="0" class="table">
  <tr>
    <td width="100">管理员登录名</td>
    <td><input name="name" type="text" class="text" id="add_manager_name"/></td>
  </tr>
  <tr>
    <td>管理员昵称</td>
    <td><input name="nick" type="text" class="text" id="add_manager_nick"/></td>
  </tr>
  <tr>
    <td>管理员密码</td>
    <td><input name="pass1" type="password" class="text" id="add_manager_pass1"/></td>
  </tr>
  <tr>
    <td>再输入一次</td>
    <td><input name="pass2" type="password" class="text" id="add_manager_pass2"/></td>
  </tr>
  <tr>
    <td>管理员权限</td>
    <td>
	<?php foreach($rank as $key => $rank_group):?>
      <?php $title = explode('#',$key);?>
      <div class="rank" id="rank_<?php echo $title[1];?>"><strong><?php echo $title[0];?></strong><br>
        <ul>
		<?php foreach($rank_group as $rank_key => $rank_name):?>
        <li><label title="<?php echo $rank_name;?>"><input type="checkbox" class="checkbox" name="rank[]" value="<?php echo str_replace('/','.',$rank_key);?>" checked="checked"/><?php echo $rank_name;?></label></li>
        <?php endforeach;?>
      </ul></div><br>
      <?php endforeach;?></td>
  </tr>
  <tr>
    <td></td>
    <td><input type="submit" class="btn" value="提交" id="add_manager_submit"/></td>
  </tr>
</table>
</form>
<?php include view::dir().'foot.php';?>