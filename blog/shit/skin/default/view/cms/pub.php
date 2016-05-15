<?php require view::dir().'head.php';?>
<?php require view::dir().'menu.php';?>

<form action="<?php echo url('cms','pub');?>" method="post">
  <table class="table table-bordered table-model-2">
    <tr>
      <td width="150">选择文章类型</td>
      <td><div class="col-md-4">
          <select name="c" class="form-control">
            <option value="-1">=================</option>
            <?php foreach($category as $c):?>
            <option value="<?php echo $c['id'];?>"><?php echo $c['name'];?>(<?php echo $c['contents'];?>)</option>
            <?php endforeach;?>
          </select>
        </div></td>
    </tr>
    <tr>
      <td>文章题目</td>
      <td><input type="text" name="title" id="title"/></td>
    </tr>
    <tr>
      <td>文章内容</td>
      <td><textarea name="content" id="editor"></textarea></td>
    </tr>
    <tr>
      <td>&nbsp;</td>
      <td><input type="submit" class="btn btn-secondary btn-single" value="发表文章"></td>
    </tr>
  </table>
</form>
<?php require view::dir().'foot.php';?>
