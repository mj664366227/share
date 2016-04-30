// 随机数

module.exports = {
	/**
	 * 生成 [min - max] 的随机数
	 * @param min 最小数
	 * @param max 最大数
	 */
	rand: function (min, max) {
		let range = max - min;
		let rand = Math.random();
		return min + Math.round(rand * range);
	}
};