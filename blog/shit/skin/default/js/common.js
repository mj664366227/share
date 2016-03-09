$(function() {
    $(':input').attr('autocomplete','off');
	
	showTime(); 
	setInterval('showTime()',1000);
	
	KindEditor.ready(function(K) {
		var kindEditorConfig = {
			width: '100%',
			height: '600px',
			allowFileManager: false,
			items: ['HTML','preview','undo','redo','cut','copy','paste','plainpaste','wordpaste','Word','selectall','justifyleft','justifycenter','justifyright','justifyfull','insertorderedlist','insertunorderedlist','indent','outdent','subscript','superscript','formatblock','fontname','fontsize','forecolor','hilitecolor','bold','italic','underline','strikethrough','removeformat','image','media','table','hr','link','unlink','fullscreen','code','lineheight','clearhtml','HTML','pagebreak','quickformat','anchor']
		}
		window.editor = K.create('#editor', kindEditorConfig);
	});
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