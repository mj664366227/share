function size(size, round_){
	size = parseInt(size, 10);
	if(size >= 0 && size < 1024){
		size = size + ' KB';
	} else if(size >= 1024 && size < 1048576){
		size = (size / 1024).toFixed(round_) + ' MB';
	} else if(size >= 1048576 && size < 1073741824){
		size = (size / 1048576).toFixed(round_) + ' GB';
	} else if(size >= 1073741824 && size < 1099511627776){
		size = (size / 1073741824).toFixed(round_) + ' TB';
	}
	return size;
}

$(function(){
	$(':input').attr('autocomplete', 'off');
});