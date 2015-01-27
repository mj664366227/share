<?php require view::dir().'head.php';?>
<?php view::load_js('lib/time');?>
<script>
$(function(){
	$.ajax({
		type: "POST",
		url: '<?php echo $server?>',
		dataType: 'json',
		data: 'uid=<?php echo $uid?>&album_id=<?php echo $album_id?>&file=<?php echo $file?>',
		success: function(data){
			$('.photo_top span.left').html('第'+data.cur+'张 / 共'+data.total+'张');
			var prev = next = html = '';
			if(data.prev!=null){
				prev = '<a href="<?php echo url('works','one','uid='.$uid.'&album_id='.$album_id.'&file=')?>'+data.prev.split('.')[0]+'">上一张</a>';
			}
			if(data.next!=null){
				next = '<a href="<?php echo url('works','one','uid='.$uid.'&album_id='.$album_id.'&file=')?>'+data.next.split('.')[0]+'">下一张</a>';
			}
			if(prev && next){
				html = prev+' / '+next;
			} else {
				if(prev){
					html = prev;
				}
				if(next){
					html = next;
				}
			}
			$('.photo_top span.center').html(html);
			$('.photo_con').html('<img src="'+data.base+data.url+'"/>');
			$('.photo_bottom .time').html(new Date(parseInt(data.url.split('.')[0])/1000000).toLocaleString());
		}
	});
})
<?php if(!$is_like){?>
function like(){
	$.ajax({
		type: "POST",
		url: '<?php echo url('works','like','uid='.$uid.'&album_id='.$album_id.'&file='.$file)?>',
		dataType: 'json',
		data: 'uid=<?php echo $uid?>&album_id=<?php echo $album_id?>&file=<?php echo $file?>',
		success: function(data){
			if(data==1){
				$('#like_num').html(parseInt($('#like_num').html())+1);
				$('.photo_bottom .right .btn_like').removeAttr('onclick');
			}
		}
	});
}
<?php }?>
</script>

<div class="main">
  <div class="inner">
    <div class="user_info"> </div>
    <div class="photo_view">
      <div class="photo_top"> <span class="left"></span> <span class="center"></span> <span class="right"><a href="<?php echo url('works','show','uid='.$uid.'&album_id='.$album_id)?>">> 返回相册</a></span>
        <div class="clear"></div>
      </div>
      <div class="photo_con"> </div>
      <div class="photo_bottom">
        <div class="left" style="line-height:30px;"> <?php echo number_format(intval($browse))?>人浏览
          发布于 <span class="time"></span></div>
        <div class="right"> <span id="like_num"><?php echo intval($like)?></span>人喜欢
          <button class="btn_like" <?php if(!$is_like):?>onclick="like()"<?php endif?>>喜欢</button>
        </div>
        <div class="clear"></div>
      </div>
    </div>
    <div class="comments">
      <?php require view::dir().'comment.php';?>
    </div>
  </div>
</div>
<?php require view::dir().'foot.php';?>
