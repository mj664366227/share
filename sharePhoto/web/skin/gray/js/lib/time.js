//时间函数库
/**
 * 获取指定时间与当前时间的距离
 * @param time 时间(可以是时间戳或者是日期格式)
 * @return xxx分钟前，xxx小时前，xxx天前
 */
function get_day_before(time) {
	var timestamp = Date.parse(new Date()) / 1000;
	var left_time = timestamp - time;
	var d = Math.floor(left_time / 86400);
	left_time = left_time - d * 86400;
	var h = Math.floor(left_time / 3600);
	left_time = left_time - h * 3600;
	var m = Math.floor(left_time / 60);
	var s = left_time - m * 60;
	var str = '';
	if(d > 1){
		return date('Y-m-d H:i:s', time);
	}
	if (h > 0) {
		str += h + '小时';
	}
	if (m > 0) {
		str += m + '分钟';
	}
	if (s > 0) {
		str += s + '秒';
	}
	if(!str){
		return '刚刚';
	}
	str += '前';
	return str;
}