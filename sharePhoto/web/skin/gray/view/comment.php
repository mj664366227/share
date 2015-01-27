<?php if(!$is_me){?>
<?php view::load_js('xheditor/xheditor-1.2.1.min');?>
<?php view::load_js('xheditor/zh-cn');?>

<div class="post_comment"> 
  <script>$(function(){
	$('#xheditor').xheditor({
		tools:'Emot',
		urlType:'rel'
	});
})

function comment(){
	$.ajax({
		type: "POST",
		url: '<?php echo url('works','comment','uid='.$uid.'&album_id='.$album_id.'&file='.$file)?>',
		dataType: 'json',
		data: 'uid=<?php echo $uid?>&album_id=<?php echo $album_id?>&file=<?php echo $file?>&comment='+$('#xheditor').val(),
		success: function(data){
			if(data){
				var html = '<li><div class="comment_wrap"><div class="avatar left"><a href="show.html"><img src="images/avatar/05.jpg" alt="'+data.nick+'" title="'+data.commentuid+'"/></a></div><dl class="comment_con"><dt><a href="show.html" target="_blank">'+data.nick+'</a> 发表于 '+get_day_before(data.time)+'</dt><dd>'+data.comment+'</dd></dl><div class="clear"></div></div></li>';
				$('.show_comment').prepend(html);
			}
		}
	});
}
</script>
  <div class="avatar left"><a href="show.html"><img src="images/avatar/04.jpg" alt="林淼淼" /></a></div>
  <div id="xheditor_box">
    <textarea id="xheditor"></textarea>
    <button class="btn_common right" onclick="comment()">发表</button>
  </div>
  <div class="clear"></div>
  <ul class="show_comment">
  <?php foreach($comment as $c):?>
  <li>
      <div class="comment_wrap">
        <div class="avatar left"><a href="show.html"><img src="images/avatar/05.jpg" alt="<?php echo $c['nick']?>" title="<?php echo $c['nick']?>"/></a></div>
        <dl class="comment_con">
          <dt> <a href="show.html" target="_blank"><?php echo $c['nick']?></a> 发表于 <?php echo time::get_day_before($c['time'])?> </dt>
          <dd><?php echo $c['comment']?></dd>
        </dl>
        <div class="clear"></div>
      </div>
    </li>
  <?php endforeach;?>
  </ul>
</div>
<?php }?>
