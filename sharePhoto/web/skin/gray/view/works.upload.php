<?php require view::dir().'head.php';?>

<div class="main">
  <div class="inner">
    <div class="container">
      <div class="do_box">
        <dl class="do_top">
          <dt>
            <h1>上传照片</h1>
          </dt>
          <dd></dd>
        </dl>
        <div class="clear"></div>
        <div class="do_list">
          <div class="center">
            <?php view::load_js('uploadify/jquery.uploadify.min');?>
            <?php view::load_css('uploadify');?>
            <table width="100%" border="0" cellspacing="0" cellpadding="0" class="do_table">
              <tr>
                <th>&nbsp;</th>
                <td>上传文件仅支持jpg、gif、png格式，最大支持2M的照片。</td>
              </tr>
              <tr>
                <th>&nbsp;</th>
                <td><form>
                    <div id="queue"></div>
                    <input id="file_upload" name="file_upload" type="file" multiple="true">
                  </form>
                  <script type="text/javascript">
		<?php $timestamp = time();?>
		$(function() {
			$('#file_upload').uploadify({
				'formData'     : {
					'album_id' : '<?php echo $album_id;?>',
					'uid' : '<?php echo $uid;?>'
				},
				'queueID' 			 : 'queue',
				'method'  			 : 'post',
				'auto'     			 : true,
				'fileSizeLimit'		 : '2MB',
				'fileTypeExts'	 	 : '*.jpg;*.gif;*.png',
				'multi'    			 : true,
				'swf'      			 : '<?php echo url().'skin/'.view::get_skin().'/js/uploadify/';?>uploadify.swf',
				'uploader' 			 : '<?php echo $setting['server_url']?>upload',
				'onQueueComplete': function () {
                    window.location='<?php echo url('works', 'show','uid='.$uid.'&album_id='.$album_id)?>';
                }
			});
		});
	</script></td>
              </tr>
            </table>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<?php require view::dir().'foot.php';?>
