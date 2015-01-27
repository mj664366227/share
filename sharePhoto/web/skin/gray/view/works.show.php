<?php require view::dir().'head.php';?>

<div class="sub_header">
  <div class="inner">
    <div class="user_info">
      <div class="l left"> <a href="show.html"><img src="http://127.0.0.1/sharePhoto/web/skin/gray/images/cover/01.jpg" width="90" height="90" alt="林淼淼" class="avatar" /></a> </div>
      <div class="c left">
        <p class="user_name">林淼淼</p>
        <p>http://weibo.com/oohme</p>
        <p>
          <button class="btn_common">加关注</button>
        </p>
      </div>
      <div class="clear"></div>
    </div>
  </div>
</div>
<script>
$(function(){
	$.ajax({
		type: "POST",
		url: '<?php echo $server?>',
		dataType: 'json',
		data: 'uid=<?php echo $uid?>&album_id=<?php echo $album_id?>',
		success: function(data){
			var html = '';
			for(var index in data){
				html += '<li><dl class="photo"><dt><a href="<?php echo url('works','one','uid='.$uid.'&album_id='.$album_id.'&file=')?>'+data[index].split('?')[1]+'"><img src="'+data[index]+'"/></a></dt><dd></dd></dl></li>';
			}
			$('.photolist').html(html);
		}
	});
})
</script>
<div class="main">
  <div class="inner">
    <ul class="photolist">
    </ul>
  </div>
</div>
<?php require view::dir().'foot.php';?>
