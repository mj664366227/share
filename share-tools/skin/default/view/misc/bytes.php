<?php require view::dir().'head.php';?>

<div class="col-lg-12">
  <h1 class="page-header">字节数转换</h1>
  <form action="<?php echo url('misc', 'bytes')?>" method="post" role="form">
    <div class="form-group">
      <input type="text" class="form-control" name="bytes" value="<?php echo $input;?>"/>
    </div>
    <br>
    <br>
    <button type="submit" class="btn btn-success">提交</button>
  </form>
  <br>
  <?php if(!empty($result)) {echo '<pre>'.$result.'</pre>';}?>
</div>
<?php require view::dir().'foot.php';?>
