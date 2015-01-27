<?php require view::dir().'head.php'?>

<div class="col-lg-12">
  <h1 class="page-header">时间戳转换</h1>
  <form action="<?php echo url('time', 'timestamp')?>" method="post" role="form">
    <div class="form-group"> <span style="float:left; margin-right:10px; margin-top:5px; width:120px">当前时间戳：</span>
      <input type="text" class="form-control" value="<?php echo $_SERVER['REQUEST_TIME'];?>" style="width:20%;float:left; cursor:text" readonly="readonly"/>
      &nbsp;&nbsp; </div>
    <br>
    <br>
    <div class="form-group"> <span style="float:left; margin-right:10px; margin-top:5px; width:120px">时间戳转日期：</span>
      <input type="text" class="form-control" name="timestamp" value="<?php echo $timestamp_input;?>" style="width:20%;float:left; margin-right:20px"/>
      &nbsp;&nbsp;
      <?php if($timestamp_result){?>
      <input type="text" class="form-control" value="<?php echo $timestamp_result;?>" style="width:20%;float:left; cursor:text" readonly="readonly"/>
      <?php }?>
    </div>
    <br>
    <br>
    <div class="form-group"> <span style="float:left; margin-right:10px; margin-top:5px; width:120px">日期转时间戳：</span>
      <input type="text" class="form-control" name="date" value="<?php echo $date_input;?>" style="width:20%;float:left; margin-right:20px" placeholder="格式：年-月-日 时:分:秒"/>
      &nbsp;&nbsp;
      <?php if($date_result){?>
      <input type="text" class="form-control" value="<?php echo $date_result;?>" style="width:20%;float:left; cursor:text" readonly="readonly"/>
      <?php }?>
    </div>
    <br>
    <br>
    <button type="submit" class="btn btn-success">提交</button>
  </form>
  <br>
</div>
<?php require view::dir().'foot.php'?>
