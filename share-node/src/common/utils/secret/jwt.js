const jwt = require('jsonwebtoken');
const fs = require("fs");

export default class {
	/**
	 * 构造函数
	 * @param publicKey 公钥文件
	 * @param privateKey 私钥文件
	 */
	constructor(publicKey, privateKey) {
		this.publicKey = fs.readFileSync(publicKey, "utf-8");
		this.privateKey = fs.readFileSync(privateKey, "utf-8");
	}

	/**
	 * jwt加密
	 * @param data 要加密的数据
	 * @param expiresIn 过期时间(单位：秒)
	 */
	async encrypt(data, expiresIn) {
		return await jwt.sign(data || {}, this.privateKey, {algorithm: 'RS256', expiresIn: expiresIn});
	}

	/**
	 * jwt解密
	 * @param data 待解密数据
	 */
	async decrypt(data) {
		try {
			return await jwt.verify(data, this.publicKey);
		} catch (e) {
			console.log(e);
			return null;
		}
	}
}
