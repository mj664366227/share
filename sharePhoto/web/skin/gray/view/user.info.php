<?php require view::dir().'head.php';?>

<div class="inner">
  <div class="container">
    <div class="do_box">
      <dl class="do_top">
        <dt>
          <h1>个人资料</h1>
        </dt>
        <dd></dd>
      </dl>
      <div class="clear"></div>
      <div class="do_list">
        <div class="center">
          <?php view::load_js('uploadify/jquery.uploadify.min');?>
          <?php view::load_css('uploadify');?>
          <script>
$(function(){	
	$('#file_upload').uploadify({
				'formData'     : {
					'uid' : <?php echo $user['_id'];?>,
				},
				'queueID' 			 : 'queue',
				'method'  			 : 'post',
				'auto'     			 : true,
				'fileSizeLimit'		 : '500KB',
				'fileTypeExts'	 	 : '*.jpg;*.gif;*.png',
				'multi'    			 : false,
				'swf'      			 : '<?php echo url().'skin/'.view::get_skin().'/js/uploadify/';?>uploadify.swf',
				'uploader' 			 : '<?php echo $setting['server_url'][$user['server']]?>user',
				'onUploadSuccess':function(file, data, response){
                   $.ajax({
						type: "post",
						url: '<?php echo url('user','updatehead')?>',
						dataType: 'json',
						data: 'type=head&t='+data,
						success: function(msg){
							if(msg.head30 && msg.head64){
								$('#head30').attr('src', msg.head30);
								$('#head64').attr('src', msg.head64);
							}
						}
					});
                }
			});
})
</script>
          <form method="post" action="<?php echo url('user','info')?>">
            <table width="100%" border="0" cellspacing="0" cellpadding="0" class="personalinfo_con">
              <tr>
                <th>&nbsp;</th>
                <td><img src="<?php echo $user['head64']?>" id="head64"/>
                  <div id="queue"></div>
                  <input id="file_upload" name="file_upload" type="file" multiple="false">
                  <div class="clear"></div></td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <th>昵称：</th>
                <td><input type="text" class="do_input" value="<?php echo $new['nick']?$new['nick']:$user['nick']?>" name="nick"/></td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <th>E-mail：</th>
                <td><input type="text" class="do_input" value="<?php echo $new['email']?$new['email']:$user['email']?>" name="email"/></td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <th>密码：</th>
                <td><input type="text" class="do_input" value="" name="pass1"/></td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <th>确认密码：</th>
                <td><input type="text" class="do_input" value="" name="pass2"/></td>
                <td>&nbsp;</td>
              </tr>
              <tr>
                <th>&nbsp;</th>
                <td><button class="btn_login">保存</button></td>
                <td>&nbsp;</td>
              </tr>
            </table>
          </form>
        </div>
      </div>
    </div>
  </div>
</div>
<?php require view::dir().'foot.php';?>
