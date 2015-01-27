<?php require view::dir().'head.php';?>

<div class="nav">
  <center>
    <br>
    <h2>初始化管理员</h2>
    <br>
  </center>
 <form method="post">
    <table cellpadding="5" cellspacing="5" style="margin:0 auto">
      <tr>
        <td align="right">管理员名称：</td>
        <td><input type="text" name="name"/></td>
      </tr>
      <tr>
        <td align="right">管理员密码：</td>
        <td><input type="password" name="pass"/></td>
      </tr>
      <tr>
        <td align="right">再确认密码：</td>
        <td><input type="password" name="comfirm"/></td>
      </tr>
      <tr>
        <td align="right"><input type="reset" class="btn" value="重置"/></td>
        <td><input type="submit" class="btn" value="完成"/></td>
      </tr>
    </table>
  </form>
</div>
<?php require view::dir().'foot.php';?>
