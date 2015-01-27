<?php require view::dir().'head.php';?>
<!--左边-->

<div id="left">
  <div class="card">
    <div class="title"> <span><?php echo SYSTEM_SUMMARY?></span></div>
    <div class="content">
    <ul>
    <li><?php echo SYSTEM_NUMBETS_OF_MONITORING.count($server_list)?></li>
    </ul>
    </div>
  </div>
</div>
<!--服务器列表-->
<div id="right">
  <div class="card">
    <div class="title"><?php echo SERVER_LIST?></div>
    <div class="content">
      <table width="100%" cellpadding="0" cellspacing="0">
        <tr height="50" align="left" style="background:#f2f6f9">
          <th width="100" align="center"><label>
              <input type="checkbox" class="checkbox">
            </label></th>
          <th width="150" align="center"><?php echo SERVER_NAME?></th>
          <th width="100" align="center"><?php echo SERVER_STATUS?></th>
          <th width="100" align="center"><?php echo SERVER_IP?></th>
          <th width="120" align="center"><?php echo SERVER_TYPE?></th>
          <th>&nbsp;</th>
        </tr>
        <?php foreach($server_list as $s):?>
        <tr height="40" align="left" style="border-botton:#dbe5ea 1px solid">
          <td align="center"><label>
              <input type="checkbox" value="<?php echo $s['id']?>" class="checkbox">
            </label></td>
          <td align="center"><a href="<?php echo url('view', 'view', 'serverid='.$s['id'])?>"><?php echo $s['name']?></a></td>
          <td align="center"><span style="color:#0F0" title="<?php echo SERVER_OK?>">■</span><!--/<span style="color:#F00">■</span>--></td>
          <td align="center"><?php echo ip::long_to_ip($s['ip'])?></td>
          <td align="center"><?php view::load_img($s['type'].'.gif');?></td>
          <td align="left">[<a href="#"><?php echo MODIFY?></a>]&nbsp;[<a href="#"><?php echo DELETE?></a>]</td>
        </tr>
        <?php endforeach;?>
      </table>
    </div>
  </div>
</div>
<?php require view::dir().'foot.php';?>
