<?php require view::dir().'head.php';?>
<style>
button {
	width: 89px;
	margin-top: 5px
}
</style>
<script type="text/javascript">
document.onkeydown = keyevent;

$(function(){
	$('#clear').click(function(){
		clear();
	});
	$('#add').click(function(){
		add();
	});
	$('#sub').click(function(){
		subb();
	});
	$('#div').click(function(){
		div();
	});
	$('#mul').click(function(){
		mul();
	});
	$('#backspace').click(function(){
		backspace();
	});
	$('#equal').click(function(){
		equal();
	});
	$('#dot').click(function(){
		dot();
	});
	$('#fact').click(function(){
		fact();
	});
	$('#kuohao').click(function(){
		kuohao();
	});
	
	$('#one').click(function(){
		one();
	});
	$('#two').click(function(){
		two();
	});
	$('#three').click(function(){
		three();
	});
	$('#four').click(function(){
		four();
	});
	$('#five').click(function(){
		five();
	});
	$('#six').click(function(){
		six();
	});
	$('#seven').click(function(){
		seven();
	});
	$('#eight').click(function(){
		eight();
	});
	$('#nine').click(function(){
		nine();
	});
	$('#zero').click(function(){
		zero();
	});
})
// 清屏
function clear(){
	$('#consol').val('');
}
// 退格
function backspace(){
	var str = $('#consol').val();
	$('#consol').val(str.substring(0,str.length-1));
}
// 加
function add(){
	append('+');
}
// 减
function subb(){
	append('-');
}
// 乘
function mul(){
	append('X');
}
// 除
function div(){
	append('÷');
}
// 等于
function equal(){
	var str = String($('#consol').val());
	if(str.indexOf('!') > -1){
		str = str.replace('!', '');
		$('#consol').val(doFact(str));
	} else {
		str = str.replace('÷', '/');
		str = str.replace('X', '*');
		$('#consol').val(eval(str));
	}
}
// 小数点
function dot(){
	append('.');
}
// 阶乘
function fact(){
	append('!');
}
// 括号
function kuohao(){
	var str = String($('#consol').val());
	$('#consol').val('(' + str + ')');
}
function doFact(value){
	value = parseInt(value);
	if (value <= 0) {
		return value;
	}
	var result = 1;
	while (value > 0) {
		result *= value;
		value -= 1;
	}
	return result;
}
// 追加
function append(value){
	var str = String($('#consol').val());
	$('#consol').val(str + value);
}

function one(){
	append('1');
}
function two(){
	append('2');
}
function three(){
	append('3');
}
function four(){
	append('4');
}
function five(){
	append('5');
}
function six(){
	append('6');
}
function seven(){
	append('7');
}
function eight(){
	append('8');
}
function nine(){
	append('9');
}
function zero(){
	append('0');
}

function keyevent(){
	switch(event.keyCode){
		case 67:clear();break;
		case 111:div();break;
		case 106:mul();break;
		case 8:backspace();break;
		case 109:subb();break;
		case 107:add();break;
		case 187:equal();break;
		case 96:zero();break;
		case 97:one();break;
		case 98:two();break;
		case 99:three();break;
		case 100:four();break;
		case 101:five();break;
		case 102:six();break;
		case 103:seven();break;
		case 104:eight();break;
		case 105:nine();break;
		case 48:zero();break;
		case 49:one();break;
		case 50:two();break;
		case 51:three();break;
		case 52:four();break;
		case 53:five();break;
		case 54:six();break;
		case 55:seven();break;
		case 56:eight();break;
		case 57:nine();break;
		case 110:dot();break;
		case 190:dot();break;
		case 13:equal();break;
	}
}
</script>
<div class="col-lg-12" style="width:400px; float:left;">
  <h1 class="page-header">简易计算器</h1>
  <textarea class="form-control" rows="3" readonly="readonly" style="cursor:text; text-align:right; font-size:20px" id="consol"></textarea>
  <button type="button" class="btn btn-default" id="clear">C</button>
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
