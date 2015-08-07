<?php require view::dir().'head.php';?>


<div id="right">
  <div class="card">
    <div class="content">
      <table width="100%" cellpadding="0" cellspacing="0" style="width:100%">
        <tr height="50" align="left" style="background:#f2f6f9">
          <th width="150" align="center"><?php echo USER_NAME?></th>
          <th><?php echo OPERATION?></th>
        </tr>
        <?php foreach($userlist as $u):?>
        <tr height="40" align="left" style="border-botton:#dbe5ea 1px solid">
          <td align="center"><?php echo $u['name'];?></td>
          <td align="left">[<a href="#-<?php echo $u['id'];?>">修改</a>]&nbsp;[<a href="#-<?php echo $u['id'];?>">删除</a>]</td>
        </tr>
        <?php endforeach;?>
      </table>
    </div>
  </div>
</div>
<?php require view::dir().'foot.php';?>
