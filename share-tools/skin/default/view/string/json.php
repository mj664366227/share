<?php require view::dir().'head.php';?>

<div class="col-lg-12">
  <h1 class="page-header">json工具</h1>
  <form action="<?php echo url('string', 'json')?>" method="post" role="form">
    <div class="form-group">
      <textarea class="form-control" name="json" rows="10"><?php echo $input;?></textarea>
    </div>
    <br>
    <br>
    <button type="submit" class="btn btn-success">提交</button>
  </form>
  <br>
  <?php if($result) {echo '<pre>';print_r($result);echo '</pre>';}?>
</div>
<?php require view::dir().'foot.php';?>
