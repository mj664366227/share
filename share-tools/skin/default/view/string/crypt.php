<?php require view::dir().'head.php';?>

<div class="col-lg-12">
  <h1 class="page-header">crypt加密</h1>
  <form action="<?php echo url('string', 'crypt')?>" method="post" role="form">
    <div class="form-group">
      <input type="text" class="form-control" name="crypt" value="<?php echo $input;?>"/>
    </div>
    <br>
    <br>
    <button type="submit" class="btn btn-success">提交</button>
  </form>
  <br>
  <?php if($result) {echo '<pre>'.$result.'</pre>';}?>
</div>
<?php require view::dir().'foot.php';?>
