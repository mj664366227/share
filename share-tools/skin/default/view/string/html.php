<?php require view::dir().'head.php';?>

<div class="col-lg-12">
  <h1 class="page-header">html字符转义</h1>
  <form action="<?php echo url('string', 'html')?>" method="post" role="form">
    <div class="form-group">
      <textarea class="form-control" name="html" rows="10"><?php echo $input;?></textarea>
    </div>
    <br>
    <br>
    <button type="submit" class="btn btn-success">提交</button>
  </form>
  <br>
  <?php if($result) {echo '<pre>';echo '<xmp>'.$result.'</xmp>';echo '</pre>';}?>
</div>
<?php require view::dir().'foot.php';?>
