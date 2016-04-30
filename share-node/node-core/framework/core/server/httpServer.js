// http服务器
import logger from '../util/logUtil';
var LOGGER = logger.getLogger(module.filename);
import filesystem from '../util/filesystem';
import compression from 'compression'
import express from 'express';
import session from 'express-session';
import cookieParser from 'cookie-parser';
import bodyParser from 'body-parser'
import crypto from 'crypto';
import time from '../util/timeUtil';
import distributedSessionService from '../../../../node-service/service/distributedSessionService';

var app = express();

// 支持cookie、支持bodyParser
app.use(cookieParser());
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended: true}));
app.use(session({
	secret: properties["system.key"],
	name: secret.sha1(properties["project.name"] + properties["system.key"] + 'captcha'),   //这里的name值得是cookie的name，默认cookie的name是：connect.sid
	cookie: {maxAge: 300000},  //设置maxAge是80000ms，即80s后session和相应的cookie失效过期
	path: "/" + properties["project.name"].substring(3),
	resave: false,
	saveUninitialized: true
}));

// 白名单
global.whiteList = [];
whiteList.push(URLCommand.userLogin);
whiteList.push(URLCommand.userReg);

/**
 * nginx header头拦截器
 */
app.use(async function (req, res, next) {
	// 逼着客户端一定要通过tengine服务器本系统
	try {
		if ('gu-server' !== req.headers['x-gatherup']) {
			LOGGER.warn("must request via tengine, url: %s,  %s", req.url, req);
			var json = {};
			json['time'] = time.now();
			json['status'] = parseInt(errorCode.systemError.unknowError.errorCode);
			json['errorMsg'] = errorCode.systemError.unknowError.errorMessage.toString();
			res.json(json);
		} else {
			next();
		}
	} catch (e) {
		res.sendStatus(500);
		LOGGER.error(e);
	}
});

/**
 * cookies拦截器
 */
//app.use(async function (req, res, next) {
//	// 如果在白名单，不检查cookie
//	try {
//		var url = req.url.substring(0, req.url.indexOf('?'));
//		url = !url ? req.url : url;
//		if (whiteList.indexOf(url) <= -1) {
//			var sessionId = parseInt(await distributedSessionService.get(req, res, sessionKey));
//			if (!sessionId || sessionId <= 0) {
//				LOGGER.warn('missing session, redirect to login!');
//				var json = {};
//				json['time'] = time.now();
//				json['status'] = parseInt(errorCode.systemError.noLoginError.errorCode);
//				json['errorMsg'] = errorCode.systemError.noLoginError.errorMessage.toString();
//				res.json(json);
//			} else {
//				next();
//			}
//		} else {
//			next();
//		}
//	} catch (e) {
//		res.sendStatus(500);
//		LOGGER.error(e);
//	}
//});

/**
 * 启动http服务器
 */
module.exports.start = function (port, route) {
	// 遍历路由，映射到具体方法
	_.forEach(route, function (v_map, k_url) {
		var func = async function (req, res) {
			// 调用映射的方法处理并返回
			var json = {};
			json['time'] = time.now();

			// 调用处理方法
			try {
				var t = time.nanoTime();
				var param = _.merge(req.body, req.query);
				param.ip = req.header("X-Real-IP");
				LOGGER.info('%s\tparam: %s', k_url, JSON.stringify(param));
				var data = await v_map.func(req, res, param);
				t = showTime(time.nanoTime() - t);
				var isNumber = _.isNumber(data);
				if (isNumber || data) {
					if (data.errorCode) {
						json['status'] = parseInt(data.errorCode);
						json['errorMsg'] = data.errorMessage.toString();
					} else {
						json['status'] = 0;
						json['data'] = data;
					}
				} else {
					LOGGER.info('%s\texec: %s', k_url, t);
					res.sendStatus(500);
				}
			} catch (e) {
				res.sendStatus(500);
				LOGGER.error(e);
				return;
			}
			LOGGER.warn('%s\texec: %s', k_url, t);
			LOGGER.info('%s send data: %s', k_url, JSON.stringify(json));
			res.json(json);
		};
		switch (v_map.method) {
			case 'get':
				app.get(k_url, func);
				break;
			case 'post':
				app.post(k_url, func);
				break;
			default:
				app.use(k_url, func);
				break;
		}
	});

	// 启动http服务器，监听端口
	app.listen(port);

	// 屏蔽一些服务器参数
	app.disable('x-powered-by');
	app.disable('etag');
	LOGGER.warn('http server %s start success, bind port %s ...', properties["project.name"], port);
};

/**
 * 寻找最合适的单位来显示时间
 * @param time 时间
 */
function showTime(time) {
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