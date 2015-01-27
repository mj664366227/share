<?php require view::dir().'head.php';?>

<div id="left">
  <div class="card">
    <div class="title"> <span><?php echo LOG_FILES_LIST?></span></div>
    <div class="content">
      <ul>
        <?php foreach($log_files as $log):?>
        <li>
          <?php $date = str_replace('.log', '', $log);if($file==$date):?>
          <span style="color:#0F0">■</span>&nbsp;
          <?php endif?>
          <a href="<?php echo url('view', 'log', 'date='.$date)?>" title="<?php echo $date;?>"><?php echo $date;?></a></li>
        <?php endforeach?>
      </ul>
    </div>
  </div>
</div>
<div id="right">
  <div class="card">
    <div class="title"><?php echo LOG_FILE_CONTENT?></div>
    <div class="content">
      <pre class="pre">
<?php echo $file_content?>
    </pre>
    </div>
  </div>
</div>
<?php require view::dir().'foot.php';?>
