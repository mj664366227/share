<?php require view::dir().'head.php';?>

<div class="col-lg-12">
  <h1 class="page-header">ip地址查询</h1>
  <form action="<?php echo url('server', 'ip')?>" method="post" role="form">
    <div class="form-group">
      <input type="text" class="form-control" name="ip" value="<?php echo $input;?>" id="ip"/>
    </div>
    <br>
    <br>
    <button type="submit" class="btn btn-success" id="button">提交</button>
  </form>
  <br>
  <?php 
  function p($array){
	  if(!is_array($array)){
          return '';
	  }
	  $str = '';
	  foreach($array as $k){
	       $str .= $k.'     ';
	  }
	  return trim($str);
  }
  if($result){?>
</div>
<div class="col-lg-12">
  <div class="panel panel-default">
    <div class="panel-heading"><?php echo 'ip地址：'.p($ip);?></div>
    <table class="table table-hover" cellpadding="5">
      <tbody>
        <?php foreach($result as $key => $value):?>
        <tr>
          <td width="200"><?php echo $key?></td>
          <td><?php echo $value?></td>
        </tr>
        <?php endforeach;?>
      </tbody>
    </table>
  </div>
</div>
<?php }?>
<?php require view::dir().'foot.php';?>
