<?php require view::dir().'head.php';?>

<div class="main">
  <div class="inner">
    <div class="container">
      <div class="do_box">
        <dl class="do_top">
          <dt>
            <h1>注册素米账号</h1>
          </dt>
          <dd></dd>
        </dl>
        <div class="clear"></div>
        <div class="do_list">
          <div class="do_form left">
          <form action="<?php echo url('user','reg')?>" method="post">
            <table width="100%" border="0" cellspacing="0" cellpadding="0" class="login_con">
              <tr>
                <th width="130">E-mail：</th>
                <td width="280"><input type="text" class="do_input" name="email"/></td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <th>昵称：</th>
                <td><input type="text" class="do_input" name="nick"/></td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <th>密码：</th>
                <td><input type="password" class="do_input" name="pass1"/></td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <th>确认密码：</th>
                <td><input type="password" class="do_input" name="pass2"/></td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <th>&nbsp;</th>
                <td><input type="checkbox" checked="checked" />
                  已阅读并接受<a href="###" target="_blank">《素米用户协议》</a></td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <th>&nbsp;</th>
                <td><button class="btn_login">注册</button></td>
                <td>&nbsp;</td>
              </tr>
            </table>
            </form>
          </div>
          <div class="do_other right">
            <ul class="sidezone">
              <li>已有素米账号？</li>
              <li>
                <button class="btn_sidezone" onclick="location.href='<?php echo url('user','login')?>'">登录</button>
              </li>
            </ul>
          </div>
          <div class="clear"></div>
        </div>
      </div>
    </div>
  </div>
</div>
<?php require view::dir().'foot.php';?>
