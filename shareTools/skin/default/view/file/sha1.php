<?php require view::dir().'head.php';?>

<div class="col-lg-12">
  <h1 class="page-header">文件哈希</h1>
  <form action="<?php echo url('file', 'sha1')?>" method="post" role="form" enctype="multipart/form-data">
    <div class="form-group">
      <input type="file" name="file">
    </div>
    <br>
    <br>
    <button type="submit" class="btn btn-success">提交</button>
  </form>
  <br>
  <?php if(!empty($result)) {echo '<pre>'.$result.'</pre>';}?>
</div>
<?php require view::dir().'foot.php';?>
