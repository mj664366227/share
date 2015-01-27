<?php require view::dir().'head.php';?>

<div class="main">
  <div class="inner">
    <div class="container">
      <div class="do_box">
        <dl class="do_top">
          <dt>
            <h1>登录社区</h1>
          </dt>
          <dd></dd>
        </dl>
        <div class="clear"></div>
        <div class="do_list">
          <div class="do_form left">
          <form action="<?php echo url('user', 'login')?>" method="post">
            <table width="100%" border="0" cellspacing="0" cellpadding="0" class="login_con">
              <tr>
                <th width="130">E-mail：</th>
                <td width="280"><input type="text" class="do_input" name="email"/></td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <th>密码：</th>
                <td><input type="password" class="do_input" name="pass"/></td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <th>&nbsp;</th>
                <td><button class="btn_login">登录</button></td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <th>&nbsp;</th>
                <td><span class="left">
                  <input type="checkbox" />
                  下次自动登录</span> <span class="right"><a href="get_password.html">忘记密码？</a></span>
                  <div class="clear"></div></td>
                <td>&nbsp;</td>
              </tr>
              </form>
              <tr>
                <th>&nbsp;</th>
                <td>&nbsp;</td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <th>&nbsp;</th>
                <td><h3>使用以下合作网站账号登录：</h3>
                  <ul class="login_otherway">
                    <li>
                      <button>QQ</button>
                    </li>
                    <li>
                      <button>新浪微博</button>
                    </li>
                    <li>
                      <button>豆瓣</button>
                    </li>
                  </ul></td>
                <td>&nbsp;</td>
              </tr>
            </table>
          </div>
          <div class="do_other right">
            <ul class="sidezone">
              <li>还没有注册素米账号？</li>
              <li>
                <button class="btn_sidezone" onclick="location.href='<?php echo url('user','reg')?>'">快速注册</button>
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
