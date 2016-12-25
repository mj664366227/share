/**
 * http客户端
 */
import logger from '../util/logUtil';
let LOGGER = logger.getLogger(module.filename);
import request from 'request';

module.exports = {
	/**
	 * post获取数据(异步版)
	 * @param url 请求地址
	 * @param data 请求数据
	 */
	post: function (url, data) {
		try {
			request.post({url: url, form: data}, function (err, httpResponse, body) {
				LOGGER.warn(body);
			});
		} catch (e) {
			LOGGER.error(e);
		}
	},
	/**
	 * post获取数据(同步版)
	 * @param url 请求地址
	 * @param data 请求数据
	 */
	postAsync: function (url, data) {
		return new Promise(function (resolve, reject) {
			request.post({url: url, form: data}, function (err, httpResponse, body) {
				if (err) {
					reject(err);
					return;
				}
				resolve(body);
			})
		}).catch(function (e) {
			LOGGER.error(e);
		});
	},
	/**
	 * get获取数据(异步版)
	 * @param url 请求地址
	 * @param data 请求数据
	 */
	get: function (url, data) {
		try {
			request.get({url: url, form: data}, function (err, httpResponse, body) {
				LOGGER.warn(body);
			});
		} catch (e) {
			LOGGER.error(e);
		}
	},
	/**
	 * get获取数据(同步版)
	 * @param url 请求地址
	 * @param data 请求数据
	 */
	getAsync: function (url, data) {
		return new Promise(function (resolve, reject) {
			request.get({url: url, form: data}, function (err, httpResponse, body) {
				if (err) {
					reject(err);
					return;
				}
				resolve(body);
			})
		}).catch(function (e) {
			LOGGER.error(e);
		});
	}
};