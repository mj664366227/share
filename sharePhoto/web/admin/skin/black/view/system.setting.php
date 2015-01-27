<?php include view::dir().'head.php';?>

<div class="table_head">
  <?php view::load_img('yes.gif');?>
  系统设置</div>
<form action="<?php echo url('system', 'setting')?>" method="post">
  <table cellpadding="0" cellspacing="0" class="table">
    <tr>
      <td width="100">网站名称</td>
      <td><input type="text" class="longtext" id="title" name="title" value="<?php echo $setting['title']?>"/></td>
    </tr>
    <tr>
      <td>网站描述</td>
      <td><textarea type="text" class="textarea" id="description" name="description"/><?php echo $setting['description']?></textarea></td>
    </tr>
    <tr>
      <td>网站关键字</td>
      <td><textarea type="text" class="textarea" id="keywords" name="keywords"/><?php echo $setting['keywords']?></textarea></td>
    </tr>
    <tr>
      <td>图片服务器</td>
      <td><select name="server" id="server">
          <?php foreach($servers as $s):?>
          <option value="<?php echo $s['_id']?>"><?php echo $s['name']?></option>
          <?php endforeach?>
        </select></td>
    </tr>
    <tr>
      <td>浏览统计代码</td>
      <td><textarea type="text" class="textarea" id="statistics" name="statistics"/><?php echo $setting['statistics']?></textarea></td>
    </tr>
    <tr>
      <td></td>
      <td><input type="submit" class="btn" value="确定"/></td>
    </tr>
  </table>
</form>
<?php include view::dir().'foot.php';?>
