/**
 * 系统模块控制器
 */

import Captchapng from 'captchapng';
let LOGGER = logUtil.getLogger(module.filename);

module.exports = {
	/**
	 * 验证码
	 */
	captcha: {
		url: URLCommand.captcha,
		method: "get",
		func: async function (req, res, param) {
			let captcha = random.rand(100000, 999999);
			let p = new Captchapng(100, 50, captcha);
			p.color(random.rand(1, 255), random.rand(1, 255), random.rand(1, 255), 255);
			p.color(random.rand(1, 255), random.rand(1, 255), random.rand(1, 255), 255);
			p.color(random.rand(1, 255), random.rand(1, 255), random.rand(1, 255), 255);

			let img = p.getBase64();
			let imgbase64 = new Buffer(img, 'base64');
			res.writeHead(200, {
				'Content-Type': 'image/png'
			});
			req.session.captcha = captcha;
			res.end(imgbase64);
		}
	},
	/**
	 * 省份配置
	 */
	provinceConfig: {
		url: URLCommand.provinceConfig,
		method: "get",
		func: async function (req, res, param) {
			return provinceConfig;
		}
	},
	/**
	 * 城市配置
	 */
	cityConfig: {
		url: URLCommand.cityConfig,
		method: "get",
		func: async function (req, res, param) {
			return cityConfig;
		}
	},
	/**
	 * 区号配置
	 */
	areaCode: {
		url: URLCommand.areaCode,
		method: "get",
		func: async function (req, res, param) {
			return areaCode;
		}
	}
};