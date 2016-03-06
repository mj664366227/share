<?php require view::dir().'head.php';?>
<?php require view::dir().'menu.php';?>

<form action="<?php echo url('admin','setting');?>" method="post">
  <table class="table table-bordered table-model-2">
    <tr>
      <td width="120">网站关键字</td>
      <td><textarea class="form-control autogrow"><?php echo $setting['keywords'];?></textarea></td>
    </tr>
    <tr>
      <td>网站描述</td>
      <td><textarea class="form-control autogrow"><?php echo $setting['description'];?></textarea></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td><input type="submit" class="btn btn-secondary btn-single" value="提交"></td>
    </tr>
  </table>
</form>
<?php require view::dir().'foot.php';?>
