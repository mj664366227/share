<?php require view::dir().'head.php';?>

<div class="col-lg-12">
  <h1 class="page-header">base64加密解密</h1>
  <form action="<?php echo url('string', 'base64')?>" method="post" role="form">
    <div class="form-group">
      <input type="text" class="form-control" name="base64" value="<?php echo $input;?>"/>
    </div>
    <br>
    <br>
    <button type="submit" class="btn btn-success">提交</button>
  </form>
  <br>
  <?php if($result):?>
  <pre>
base64 encode：<?php echo $result['encode']?>
<br>
base64 decode：<?php echo $result['decode']?> 
</pre>
  <?php endif;?>
</div>
<?php require view::dir().'foot.php';?>
