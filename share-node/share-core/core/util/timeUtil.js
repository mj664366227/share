// 时间类
import moment from 'moment';
module.exports = {
	/**
	 * 获取当前纳秒
	 */
	nanoTime: function () {
		var nano = process.hrtime();
		return nano[0] + "" + nano[1];
	},
	/**
	 * 获取当前时间(秒)
	 */
	now: function () {
		return parseInt(this.nowMillis() / 1000);
	},
	/**
	 * 获取当前时间(毫秒)
	 */
	nowMillis: function () {
		return moment.now();
	},
	/**
	 * 把当前时间按照指定格式输出
	 * @param format 格式
	 */
	date: function (format) {
		return moment().format(format);
	},
	/**
	 * 时间字符串转时间戳
	 * @param str 时间字符串
	 */
	str2Time: function (str) {
		return parseInt(moment(str).toDate().getTime() / 1000);
	},
	/**
	 * 天数转成秒
	 * @param day
	 */
	day2Second: function (day) {
		return moment.duration(day, "day").asSeconds()
	},
	/**
	 * 寻找最合适的单位来显示时间
	 * @param time 时间
	 */
	showTime: function (time) {
		var str = "";
		if (time > 0 && time <= 1000) {
			str = time + " ns";
		} else if (time > 1000 && time <= 1000000) {
			str = (time / 1000.0).toFixed(2) + " μs";
		} else if (time > 1000000 && time <= 1000000000) {
			str = (time / 1000000.0).toFixed(2) + " ms";
		} else if (time > 1000000000 && time < 60000000000) {
			str = (time / 1000000000.0).toFixed(2) + " s";
		} else if (time >= 60000000000 && time < 3600000000000) {
			str = (time / 60000000000).toFixed(2) + " min";
		} else {
			str = (time / 3600000000000).toFixed(2) + " h";
		}
		return str;
	}
};
