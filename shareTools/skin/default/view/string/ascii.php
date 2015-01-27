<?php require view::dir().'head.php';?>

<div class="col-lg-12">
  <h1 class="page-header">ASCII编码</h1>
  <form action="<?php echo url('string', 'ascii')?>" method="post" role="form">
    <div class="form-group">
      <input type="text" class="form-control" name="str" value="<?php echo $input;?>"/>
    </div>
    <br>
    <br>
    <button type="submit" class="btn btn-success">提交</button>
  </form>
  <br>
  <?php if(is_array($result)) {echo '<pre>ASCII码：'.$result['ord'].'<br>字符串：'.$result['chr'].'</pre>';}?>
</div>
<?php require view::dir().'foot.php';?>
