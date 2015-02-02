<div id="copyright">&copy; <a href="https://github.com/ruanzhijun" target="_blank" title="访问我的github主页">ruanzhijun</a>&nbsp;&nbsp;2012 - <?php echo date('Y');?></div>
<script type="text/javascript">
change_right_div_width = function(){
	var clientWidth = document.documentElement.clientWidth;
	var leftWidth = document.getElementById('left').scrollWidth;
	var clientHeight = parseInt(document.documentElement.clientHeight);
	document.getElementById('right').style.width = (clientWidth - leftWidth - 40)+'px';
	$('#copyright').css('margin-top',clientHeight / 2.5);
}
window.onresize = function(){
	change_right_div_width();
}
change_right_div_width();
</script>
</body></html>