<?php require view::dir().'head.php';?>
<style>
button {
	width: 89px;
	margin-top: 5px
}
</style>
<script type="text/javascript">
$(function(){
	$('#clear').click(function(){
		$('#consol').val('');
	});
	$('#div').click(function(){
		$('#consol').append('÷');
	});
	$('#mul').click(function(){
		$('#consol').append('X');
	});
	$('#backspace').click(function(){
		var str = $('#consol').val();
		$('#consol').val(str.substring(0,str.length-1));
	});
	$('#seven').click(function(){
		$('#consol').append('7');
	});
	$('#eight').click(function(){
		$('#consol').append('8');
	});
	$('#nine').click(function(){
		$('#consol').append('9');
	});
	$('#sub').click(function(){
		$('#consol').append('-');
	});
	$('#four').click(function(){
		$('#consol').append('4');
	});
	$('#five').click(function(){
		$('#consol').append('5');
	});
	$('#six').click(function(){
		$('#consol').append('6');
	});
})
</script>
<div class="col-lg-12" style="width:400px; float:left;">
  <h1 class="page-header">简易计算器</h1>
  <textarea class="form-control" rows="3" readonly="readonly" style="cursor:text; text-align:right; font-size:20px" id="consol"></textarea>
  <button type="button" class="btn btn-default" id="clear">清除</button>
  <button type="button" class="btn btn-default" id="div">÷</button>
  <button type="button" class="btn btn-default" id="mul">×</button>
  <button type="button" class="btn btn-default" id="backspace">←</button>
  <button type="button" class="btn btn-default" id="seven">7</button>
  <button type="button" class="btn btn-default" id="eight">8</button>
  <button type="button" class="btn btn-default" id="nine">9</button>
  <button type="button" class="btn btn-default" id="sub">-</button>
  <button type="button" class="btn btn-default" id="four">4</button>
  <button type="button" class="btn btn-default" id="five">5</button>
  <button type="button" class="btn btn-default" id="six">6</button>
  <button type="button" class="btn btn-default" id="add">+</button>
  <button type="button" class="btn btn-default" id="one">1</button>
  <button type="button" class="btn btn-default" id="two">2</button>
  <button type="button" class="btn btn-default" id="three">3</button>
  <button type="button" class="btn btn-default" id="kuohao">( )</button>
  <button type="button" class="btn btn-default" id="zero">0</button>
  <button type="button" class="btn btn-default" id="dot">.</button>
  <button type="button" class="btn btn-default" id="fact">!</button>
  <button type="button" class="btn btn-default" id="equal">=</button>
</div>
<?php require view::dir().'foot.php';?>
