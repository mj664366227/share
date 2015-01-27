<?php require view::dir().'head.php';?>

<div id="left">
  <div class="card">
    <div class="title"> <span><?php echo SETTING?></span></div>
    <div class="content">
      <ul>
        <li>
          <?php if($act=='lang'):?>
          <span style="color:#0F0">■</span>&nbsp;
          <?php endif?>
          <a href="<?php echo url('view','setting','act=lang')?>" title="<?php echo SETTING_LANGUAGE?>"><?php echo SETTING_LANGUAGE?></a></li>
      </ul>
    </div>
  </div>
</div>
<?php
switch($act){
	case 'lang':
?>
<div id="right">
  <div class="card">
    <div class="title"><?php echo SETTING_LANGUAGE?></div>
    <div class="content">
      <form action="<?php echo url('view','setting','act=lang')?>" method="post">
        <table cellpadding="0" cellspacing="10" border="0">
          <tr>
            <td><?php echo SETTING_LANGUAGE_SELECT?></td>
            <td><select name="lang">
                <?php foreach($lang_list as $lang):$lang = str_replace('.php','',$lang);?>
                <option value="<?php echo $lang?>" <?php if($lang == $cookie_lang):?>selected="selected"<?php endif?>><?php echo $lang?></option>
                <?php endforeach?>
              </select></td>
            <td><input type="submit" class="btn" value="<?php echo OK;?>" name="lang_submit"/></td>
          </tr>
        </table>
      </form>
    </div>
  </div>
</div>
<?php
break;
}
?>
<?php require view::dir().'foot.php';?>
