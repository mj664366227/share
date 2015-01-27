<?php include view::dir().'head.php';?>
<div class="table_head"><?php view::load_img('yes.gif');?>服务器基本信息</div>
<table cellpadding="0" cellspacing="0" class="table">
  <tr>
    <td width="200">操作系统： <?php echo $info['os']?></td>
    <td width="200">磁盘总容量： <?php echo $info['disk_total']?></td>
    <td width="200">磁盘剩余容量： <?php echo $info['disk_free']?></td>
    <td>临时文件路径： <?php echo $info['temp_dir']?></td>
  </tr>
  <tr>
    <td>PHP版本： <?php echo $info['php_version']?></td>
    <td>Zend版本： <?php echo $info['zend_version']?></td>
    <td><?php echo DB_TYPE;?>版本： <?php echo $info['db_version']?></td>
    <td>http服务器： <?php echo $info['sapi_name']?></td>
  </tr>
  <tr>
    <td>上传文件最大值： <?php echo $info['post_max_size']?></td>
    <td>内存限制： <?php echo $info['memory_limit']?></td>
    <td>脚本最大执行时间： <?php echo $info['max_execution_time']?></td>
    <td>提交数据最大时间： <?php echo $info['max_input_time']?></td>
  </tr>
  <tr>
    <td>是否支持文件上传： <?php echo $info['file_uploads']?></td>
    <td>上传文件的最大大小： <?php echo $info['upload_max_filesize']?></td>
    <td>文件上传最大个数： <?php echo $info['max_file_uploads']?></td>
    <td>后台物理地址： <?php echo $info['base_dir']?></td>
  </tr>
  <tr>
    <td>服务器IP： <?php echo $info['server_ip']?></td>
    <td>http端口： <?php echo $info['http_port']?></td>
    <td></td>
    <td></td>
  </tr>
</table>
<div class="table_head"><?php view::load_img('yes.gif');?>xhediter demo</div>
<table cellpadding="0" cellspacing="0" class="table">
  <tr>
    <td>
      <textarea id="xhediter" name="xhediter" rows="12" cols="80" style="width:80%"></textarea></td>
  </tr>
</table>
<table cellpadding="0" cellspacing="0" class="table">
  <tr>
    <td><div class="page"><?php echo $page?></div></td>
  </tr>
</table>
<?php include view::dir().'foot.php';?>
