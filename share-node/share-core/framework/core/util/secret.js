// 加密类
import crypto from 'crypto';

module.exports = {
	/**
	 * md5加密
	 * @param str 字符串
	 */
	md5: function (str) {
		return crypto.createHash("md5").update(str).digest("hex");
	},
	/**
	 * 哈希加密
	 * @param str 字符串
	 */
	sha1: function (str) {
		return crypto.createHash("sha1").update(str).digest("hex");
	}
};