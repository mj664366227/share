<?php require view::dir().'head.php';?>

<div class="col-lg-12">
  <h1 class="page-header">URL编码解码</h1>
  <form action="<?php echo url('string', 'url')?>" method="post" role="form">
    <div class="form-group">
      <textarea class="form-control" name="url" rows="10"><?php echo $input;?></textarea>
    </div>
    <br>
    <br>
    <button type="submit" class="btn btn-success">提交</button>
  </form>
  <br>
  <?php if($result):?>
  <pre>
urlencode：<?php echo $result['urlencode']?>
<br>
urldecode：<?php echo $result['urldecode']?> 
</pre>
  <?php endif;?>
</div>
<?php require view::dir().'foot.php';?>
