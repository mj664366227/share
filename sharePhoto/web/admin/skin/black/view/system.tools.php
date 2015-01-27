<?php include view::dir().'head.php';?>
<?php if($result):?>

<div class="success">计算结果：<?php echo $result?></div>
<?php endif?>
<form action="<?php echo url('system', 'tools')?>" method="post">
  <div class="table_head"><?php view::load_img('yes.gif');?>加密类</div>
  <table cellpadding="0" cellspacing="0" class="table">
    <tr>
      <td width="80">md5加密：</td>
      <td width="200"><input type="text" name="md5" class="text"/>
        <input type="submit" value="加密" class="btn"/></td>
      <td width="80">sha1加密：</td>
      <td width="200"><input type="text" name="sha1" class="text"/>
        <input type="submit" value="加密" class="btn"/></td>
      <td width="80">crypt加密：</td>
      <td><input type="text" name="crypt" class="text"/>
        <input type="submit" value="加密" class="btn"/></td>
    </tr>
  </table>
  <div class="table_head"><?php view::load_img('yes.gif');?>文件类</div>
  <table cellpadding="0" cellspacing="0" class="table">
    <tr>
      <td width="140">文件的md5散列值：</td>
      <td width="200"><input type="text" name="md5_file" class="text"/>
        <input type="submit" value="计算" class="btn"/></td>
      <td width="140">文件的sha1散列值：</td>
      <td><input type="text" name="sha1_file" class="text"/>
        <input type="submit" value="计算" class="btn"/></td>
    </tr>
  </table>
  <div class="table_head"><?php view::load_img('yes.gif');?>字符串类</div>
  <table cellpadding="0" cellspacing="0" class="table">
    <tr>
      <td width="100" rowspan="7">字符串转json：</td>
      <td width="480" rowspan="7"><textarea type="text" name="str2json" style="float:left" cols="65" rows="10"/></textarea>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <br/>
        <input type="submit" value="转换" class="btn"/></td>
    <tr>
      <td width="100">urlencode：</td>
      <td><input type="text" name="urlencode" class="text"/>
        <input type="submit" value="转换" class="btn"/></td>
    </tr>
    <tr>
      <td>urldecode：</td>
      <td><input type="text" name="urldecode" class="text"/>
        <input type="submit" value="转换" class="btn"/></td>
    </tr>
    <tr>
      <td>html转字符：</td>
      <td><input type="text" name="htmlspecialchars" class="text"/>
        <input type="submit" value="转换" class="btn"/></td>
    </tr>
    <tr>
      <td>字符转html：</td>
      <td><input type="text" name="htmlspecialchars_decode" class="text"/>
        <input type="submit" value="转换" class="btn"/></td>
    </tr>
    <tr>
      <td>字符转ASCII：</td>
      <td><input type="text" name="ord" class="text"/>
        <input type="submit" value="转换" class="btn"/></td>
    </tr>
    <tr>
      <td>ASCII转字符：</td>
      <td><input type="text" name="chr" class="text"/>
        <input type="submit" value="转换" class="btn"/></td>
    </tr>
      </tr>
    
  </table>
</form>
<?php include view::dir().'foot.php';?>
