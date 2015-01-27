<?php require view::dir().'head.php';?>

<div class="nav">
  <center>
    <br>
    <h2>安装程序</h2>
    <br>
  </center>
  <?php if(!$is_install):?>
  <form method="post" action="?action=install.step2">
    <table cellpadding="5" cellspacing="5" style="margin:0 auto">
      <tr>
        <td align="right">数据库地址：</td>
        <td><input type="text" name="host" value="127.0.0.1"/></td>
      </tr>
      <tr>
        <td align="right">数据库端口：</td>
        <td><input type="text" name="port" value="3306"/></td>
      </tr>
      <tr>
        <td align="right">数据库用户名：</td>
        <td><input type="text" name="user" value="root"/></td>
      </tr>
      <tr>
        <td align="right">数据库密码：</td>
        <td><input type="password" name="pass" value="root"/></td>
      </tr>
      <tr>
        <td align="right">数据表前缀：</td>
        <td><input type="text" name="pre" value="m_"/></td>
      </tr>
      <tr>
        <td align="right">数据库名：</td>
        <td><input type="text" name="db" value="monitor"/></td>
      </tr>
      <tr>
        <td align="right"><input type="reset" class="btn" value="重置"/></td>
        <td><input type="submit" class="btn" value="下一步"/></td>
      </tr>
    </table>
  </form>
  <?php else:?>
  <center>
    系统已经安装，<a href="../">请浏览主页</a>或者<a href="../admin">登录后台</a>
  </center>
  <?php endif;?>
</div>
<?php require view::dir().'foot.php';?>
