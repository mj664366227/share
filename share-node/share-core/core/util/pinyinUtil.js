// 拼音工具
import pinyin from"pinyin";

module.exports = {
	/**
	 * 汉字转拼音
	 * @param str 字符串
	 */
	toPinyin: function (str) {
		let pinyinString = '';
		let array = pinyin(str, {style: pinyin.STYLE_NORMAL});
		for (let i in array) {
			pinyinString += array[i][0].toString();
		}
		return pinyinString;
	}
};