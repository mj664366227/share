<?php require view::dir().'head.php';?>

<div class="nav">
  <center>
    <br>
    <h2>安装进度</h2>
    <br>
    <select id="progress" size="30" style="width:600px;margin-bottom:50px">
    </select>
    <form action="?action=install.step3" method="post">
      <input type="submit" class="btn" value="下一步" style="display:none" id="next"/>
    </form>
    <br>
  </center>
</div>
<script type="text/javascript">
install();
ajax();
var timer = setInterval('ajax()', 1000);
function add(value){
	$("#progress").append('<option>'+value+'</option>');
}
function install(){
	$.ajax({
	   type: "POST",
	   data:'host=<?php echo $host?>&port=<?php echo $port?>&user=<?php echo $user?>&pass=<?php echo $pass?>&pre=<?php echo $pre?>&db=<?php echo $db?>',
	   url: "?action=install.go",
	   cache: false,
	   dataType:"text",
	   success: function(data){
	   }
	});
}
function ajax(){
	$.ajax({
	   type: "POST",
	   url: "?action=install.ajax",
	   cache: false,
	   dataType:"json",
	   success: function(data){
		   if(!data){
			   return;
		   }
		   var obj = eval(data);
		   var num = parseInt($('#progress option').length);
		   if(num <= 0) {
			   for(var i=0;i<obj.length;i++){
				   if(obj[i] == 'finish') {
					   add(obj[i]);
					   clearInterval(timer);
					   $('#next').css('display','block');
					   return;
					}
			         add('数据表'+obj[i]+'创建成功！');
		   	   }
		   } else {
			   var array = '';
			   for(var i = 0; i < num; i++) {
				   array += $('#progress option:eq('+i+')').text().match(/[a-zA-Z_]+/) + ',';
			   }
			   for(var i=0;i<obj.length;i++){
			       if(array.indexOf(obj[i]) > -1) {
				        continue;
				   }
				   if(obj[i] == 'finish') {
					   add(obj[i]);
					   clearInterval(timer);
					   $('#next').css('display','block');
					   return;
					}
				   add('数据表'+obj[i]+'创建成功！');
			   }
		   }
	   }
	});
}
</script>
<?php require view::dir().'foot.php';?>
