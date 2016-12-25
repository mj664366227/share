const Base = think.controller('api');
const config = think.config('token');
const jwt = require('jsonwebtoken');
const fs = require("fs");
const private_cert = fs.readFileSync(`${think.ROOT_PATH}/cert/private.pem`, "utf-8");
const public_cert = fs.readFileSync(`${think.ROOT_PATH}/cert/public.pem`, "utf-8");
export default class extends Base {
	/**
	 * some base method in here
	 */
	mz_member = {};//全局用户信息

	//最先执行的方法
	async __before() {
		// 跳过鉴权白名单
		if (this.whiteList.indexOf(this.http.action) >= 0) {
			LOG.info(`white list action: ${this.http.action}`);
			return;
		}

		let ok = await this.auth();
		if (!ok) {
			return this.showError('SYSTEM_NOT_FIND_RESPONSE_ERROR');
		}
	}

	/**
	 * 鉴权
	 */
	async auth() {
		let token = this.header('token') || false;
		if (!token) {
			return this.showError('SYSTEM_NOT_FIND_RESPONSE_ERROR');
		}
		try {
			this.mz_member = await this.decryptToken(token);
		} catch (e) {
			return this.showError('SYSTEM_REQUEST_FAILE_ERROR');
		}
		if (think.isEmpty(this.mz_member)) {
			return this.showError('SYSTEM_NOT_FIND_RESPONSE_ERROR');
		}

		// token环境判断
		if(this.mz_member.env !== config.env){
			LOG.error(`token env error, the cilent token env is: ${this.mz_member.env}, but the server env is: ${config.env}`);
			return this.showError('SYSTEM_NOT_FIND_RESPONSE_ERROR');
		}

		// 判断token是否过期
		// 如果过期，返回错误
		let expires = parseInt(this.mz_member.exp) * 1000 || 0;
		let now = moment.now();
		if (expires <= now) {
			LOG.warn('token expire! uid: %s', this.mz_member.uid);
			return this.showError('SYSTEM_REQUEST_FAILE_ERROR');
		}

		// 如果已经创建过了7天，自动续期
		let iat = parseInt(this.mz_member.iat) * 1000 || 0;
		if (iat + config.renewCheck <= now) {
			let res = think.extend({}, this.mz_member);// 深复制，防止引用

			// 验证密码是否正确
			let UserService = think.service('user', 'user');
			let userService = new UserService();
			let data = await userService.getUserByPassport(res.passport);
			if (data && data.passport === res.password) {
				LOG.warn(`token's password error, token: ${token}`);
				return this.showError('SYSTEM_NOT_FIND_RESPONSE_ERROR');
			}
			let token = await this.encryptToken(data);
			this.header('token', token);
		}

		return true;
	}

	/**
	 * 检查密码是否正确
	 * @param dbpwd 数据库已加密密码
	 * @param status 用户状态
	 * @param reqpwd 要验证的密码
	 * @returns {boolean}
	 */
	check(dbpwd, status, reqpwd) {
		let salt = this.des3Decrypt(dbpwd.substring(40));// 获取加密的盐
		let encryptedPassword = this.encryptPassword(reqpwd, salt);
		//判断 密码是否正确 是否被封号
		return dbpwd == encryptedPassword && status != MZ.AccountConstants.STATUS_BANNED;
	}

	/**
	 * 加密密码
	 * @param password 密码
	 * @param salt 盐
	 */
	encryptPassword(password, salt) {
		if (!salt) {
			LOG.error("salt是空值");
			return this.showError("USER_SALT_IS_EMPTY_ERROR");
		}
		//根据 提交密码 和 盐 算出 加密密码
		let simple = this.hmac_sha1(password, password) + salt;
		let complex = this.hmac_sha1(simple);
		let multiplex = this.hmac_sha1(complex, complex);
		let des_salt = this.des3Encrypt(salt);
		return multiplex + des_salt;
	}

	/**
	 * 生成token
	 * @param data 要加密的数据
	 * @param expiresIn 过期时间(单位：秒)
	 */
	async encryptToken(data, expiresIn = config.expire) {
		data = data || {};
		data.env = config.env;// token环境隔离
		return await jwt.sign(data, private_cert, {algorithm: 'RS256', expiresIn: expiresIn});
	}

	/**
	 * token解析
	 * @param token
	 */
	async decryptToken(token) {
		try {
			return await jwt.verify(token, public_cert);
		} catch (e) {
			LOG.error(e);
			this.http.status(403);
			return this.showError('USER_INFO_NOT_EXISTS_ERROR', -10);
		}
	}
}