<?php require view::dir().'head.php';?>

<div class="main">
  <div class="inner">
    <div class="container">
      <div class="do_box">
        <dl class="do_top">
          <dt>
            <h1>发布作品</h1>
          </dt>
          <dd></dd>
        </dl>
        <div class="clear"></div>
        <div class="do_list">
          <div class="center">
            <form action="<?php echo url('works','add');?>" method="post">
              <table width="100%" border="0" cellspacing="0" cellpadding="0" class="do_table">
                <tr>
                  <th>名称：</th>
                  <td><input type="text" class="do_input"  name="name"/></td>
                </tr>
                <tr>
                  <th>类别：</th>
                  <td><select class="do_select" name="type">
                      <option selected="selected">请选择</option>
                      <?php foreach($category as $c):?>
                      <option value="<?php echo $c['_id']?>"><?php echo $c['name']?></option>
                      <?php endforeach;?>
                    </select></td>
                </tr>
                <tr>
                  <th>描述：</th>
                  <td><textarea rows="6" class="do_textarea" name="description"></textarea></td>
                </tr>
                <tr>
                  <th>&nbsp;</th>
                  <td><button class="btn_login">创建相册</button></td>
                </tr>
              </table>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<?php require view::dir().'foot.php';?>
