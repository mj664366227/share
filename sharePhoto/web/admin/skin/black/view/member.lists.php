<?php include view::dir().'head.php';?>

<div class="table_head">
  <?php view::load_img('yes.gif');?>
  会员搜索</div>
<table cellpadding="0" cellspacing="0" class="table">
  <tr>
    <td><form action="<?php echo url('member','search')?>" method="post">
        <input type="text" class="text" name="nick" value="<?php echo $search_nick;?>"/>
        <input type="submit" value="搜索" class="btn"/>
      </form></td>
  </tr>
</table>
<div class="table_head">
  <?php view::load_img('yes.gif');?>
  会员列表</div>
<table cellpadding="0" cellspacing="0" class="table">
  <tr>
    <th width="45">用户id</th>
    <th width="90">电子邮件</th>
    <th width="50">昵称</th>
    <th width="30">经验</th>
    <th width="43">相册数</th>
    <th width="43">文章数</th>
    <th width="43">关注数</th>
    <th width="43">粉丝数</th>
    <th width="60">登录次数</th>
    <th width="90">注册时间</th>
    <th width="90">最后登录时间</th>
    <th width="80">最后登录IP</th>
    <th>状态</th>
  </tr>
  <?php if($list):foreach($list as $member):?>
  <tr>
    <td><?php echo $member['id']?></td>
    <td><?php echo $member['email']?></td>
    <td><?php echo $member['nick']?></td>
    <td><?php echo $member['exp']?></td>
    <td></td>
    <td></td>
    <td></td>
    <td></td>
    <td><?php echo number_format($member['login_times'])?></td>
    <td><?php echo date('Y-m-d H:i:s',$member['register_time'])?></td>
    <td><?php echo date('Y-m-d H:i:s',$member['last_login_time'])?></td>
    <td><?php echo ip::long_to_ip($member['last_login_ip'])?></td>
    <td><?php if($member['status']==1)echo '<font color="#449403">正常</font>';else echo '<font color="#F00">封号</font>';?></td>
  </tr>
  <?php endforeach;endif?>
</table>
<div class="page"> <?php echo $page?> </div>
<?php include view::dir().'foot.php';?>
