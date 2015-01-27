<?php include view::dir().'head.php';?>
<style>
body {
	background: #0e0e0e
}
</style>
<table class="login" cellpadding="0" cellspacing="10">
  <form action="<?php echo url('login', 'login');?>" method="post">
    <tr>
      <td>用&nbsp;&nbsp;&nbsp;户：</td>
      <td><input type="text" name="user" class="text"/>
        <?php if($status == -1):?>
        &nbsp;&nbsp;<font color="#FF0000"> <?php echo USER_NOT_EXISTS?> </font>
        <?php endif?></td>
    </tr>
    <tr>
      <td>密&nbsp;&nbsp;&nbsp;码：</td>
      <td><input type="password" name="pass" class="text"/>
        <?php if($status == -2):?>
        &nbsp;&nbsp;<font color="#FF0000"> <?php echo PASSWORD_ERROR?> </font>
        <?php endif?></td>
    </tr>
    <?php if(!FORCE_LOGIN === true):?>
    <tr>
      <td>验证码：</td>
      <td><input type="text" name="yzm" class="text" style="width:40px"/>
        &nbsp;&nbsp;&nbsp;&nbsp;<img src="<?php echo url('login', 'yzm');?>" alt="验证码" align='absmiddle' onclick="this.src=this.src" style="cursor:pointer" title="看不清？点击重新获取！"/>
        <?php if($status == -3):?>
        &nbsp;&nbsp;<font color="#FF0000"> <?php echo YZM_ERROR?> </font>
        <?php endif?></td>
    </tr>
    <?php endif?>
    <tr>
      <td></td>
      <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <input type="submit" value="登录" class="btn" title="登录"/>
        &nbsp;&nbsp;&nbsp;&nbsp;
        <input type="reset" value="重置" class="btn" title="重置"/></td>
    </tr>
  </form>
</table>
</div>
<?php include view::dir().'foot.php';?>
