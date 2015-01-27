<?php require view::dir().'head.php';?>

<div class="nav">
  <center>
    <br>
    <h2>后台登录</h2>
    <br>
  </center>
  <center>
    <form method="post" action="<?php echo url('admin', 'login');?>">
      <table cellpadding="5" cellspacing="5" style="margin:0 auto">
        
          <td align="right">用户名：</td>
          <td><input type="text" name="user"/></td>
        </tr>
        <tr>
          <td align="right">密码：</td>
          <td><input type="password" name="pass"/></td>
        </tr>
        <?php if($tips){echo '<tr align="center"><td colspan="2"><font color="#FF0000">&nbsp;&nbsp;'.$tips.'</font></td></tr>';}?>
        <tr>
          <td align="right"><input type="reset" class="btn" value="重置"/></td>
          <td><input type="submit" class="btn" value="登录"/></td>
        </tr>
      </table>
    </form>
  </center>
</div>
<?php require view::dir().'foot.php';?>
