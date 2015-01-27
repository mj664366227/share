<div id="copyright">&copy; ruanzhijun</div>
<script type="text/javascript">
change_right_div_width = function(){
	var clientWidth = document.documentElement.clientWidth;
	var leftWidth = document.getElementById('left').scrollWidth;
	var clientHeight = parseInt(document.documentElement.clientHeight);
	document.getElementById('right').style.width = (clientWidth - leftWidth - 40)+'px';
	$('#copyright').css('top',clientHeight);
}
window.onresize = function(){
	change_right_div_width();
}
change_right_div_width();
</script>
</body></html>