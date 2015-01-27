var is_show = false; // 判断键盘是否已经打开

function virtualkeyboard(form) {
	if (is_show) {
		return
	}
	var input = $(form);
	var html = '<div style="width:700px;height:300px;border:#000 1px solid">uuu</div>';
	$('body').append(html);
	is_show = true
}