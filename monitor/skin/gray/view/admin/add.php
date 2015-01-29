<?php require view::dir().'head.php';?>

<div id="right">
  <div class="content">
    <form method="post">
      <table width="100%" cellpadding="0" cellspacing="10">
        <?php if($tips){?>
        <tr class="tips">
          <td align="right">&nbsp;</td>
          <td><font color="#FF0000"><?php echo $tips;?></font></td>
        </tr>
        <?php }?>
        <tr>
          <td align="right">服务器名称：</td>
          <td><input type="text" class="text" name="server"/></td>
        </tr>
        <tr>
          <td align="right">IP地址：</td>
          <td><input type="text" class="text" name="ip"/></td>
        </tr>
        <tr>
          <td align="right">端口：</td>
          <td><input type="text" class="text" name="port"/></td>
        </tr>
        <tr>
          <td align="right">操作系统：</td>
          <td><select name="os">
              <option value="linux">linux</option>
              <option value="windows">windows</option>
            </select></td>
        </tr>
        <tr>
          <td align="right">snmp用户名：</td>
          <td><input type="text" class="text" name="security_name"/></td>
        </tr>
        <tr>
          <td align="right">snmp密码：</td>
          <td><input type="text" class="text" name="pass_phrase"/></td>
        </tr>
        <tr>
          <td align="right">snmp身份验证加密方式：</td>
          <td><select name="auth_protocol">
              <option value="MD5">MD5</option>
              <option value="SHA">SHA</option>
            </select></td>
        </tr>
        <tr>
          <td align="right">加密/解密协议：</td>
          <td><select name="priv_protocol">
              <option value="DES">DES</option>
              <option value="AES">AES</option>
            </select></td>
        </tr>
        <tr>
          <td align="right"><input type="reset" class="btn" value="重填" onclick="$('.tips').remove();"/></td>
          <td><input type="submit" class="btn" value="提交" name="submit"/></td>
        </tr>
      </table>
    </form>
  </div>
</div>
<?php require view::dir().'foot.php';?>
