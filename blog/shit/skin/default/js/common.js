$(function() {
    $(':input').attr('autocomplete','off');
	
	showTime(); 
	setInterval('showTime()',1000);
});

function showTime(){
	time += 1000;
	var day = new Date(time);
	var hr = day.getHours();
    var minu=day.getMinutes();
	var second=day.getSeconds();
	if(minu <10){
		minu='0'+minu;
	}
	if(hr <10){
		hr='0'+hr;
	}
	if(second <10){
		second='0'+second;
	}
    var s="现在的时间是：";
    s=s+day.getFullYear()+'年'+(day.getMonth()+1)+'月'+day.getDate()+"日"+hr+"点"+minu+"分"+second+"秒";
	$('#showTime').html(s);
}