// http服务器
import logger from '../util/logUtil';
var LOGGER = logger.getLogger(module.filename);
import compression from 'compression'
import express from 'express';
import session from 'express-session';
import cookieParser from 'cookie-parser';
import bodyParser from 'body-parser';
import fs from 'fs';
import filesystem from '../util/filesystem';

var app = express();

// 支持cookie、支持bodyParser
app.use(compression({level: 9}));
app.use(cookieParser());
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({extended: true}));
app.use(session({
	secret: '1234',
	name: 'ruanzhijun',   //这里的name值得是cookie的name，默认cookie的name是：connect.sid
	cookie: {maxAge: 300000},  //设置maxAge是80000ms，即80s后session和相应的cookie失效过期
	path: '/',
	resave: false,
	saveUninitialized: true
}));


/**
 * 启动http服务器
 */
module.exports.start = function (port) {
	var fileList = filesystem.ls(ControllerDir);
	fileList.forEach(function (item) {
		var file = ControllerDir + "/" + item;
		var stats = fs.statSync(file);
		if (!stats.isDirectory()) {
			var controller = require(file);
			_.forEach(controller, function (v_map, k_funcName) {
				console.log(v_map);
				console.log(k_funcName);
				var funObj = v_map.func;
				if (_.isFunction(funObj)) {
					let url = v_map.url;
					let method = v_map.method.toString().toLowerCase();
					//检查url不能为空
					if (!url) {
						process.exit();
					}
					if (urlMap[url] == url) {
						process.exit();
					}
					// 如果是get方法，自动进入白名单
					if (method === 'get') {
						whiteList.push(url);
					}
					route[url] = v_map;
					urlMap[url] = url;
					LOGGER.info('request mapping url: [%s], method: [%s] on %s function %s()', url, method, file, k_funcName);
				}
			});
		}
	});







	// 遍历路由，映射到具体方法
	// _.forEach(route, function (v_map, k_url) {
	// 	var func = async function (req, res) {
	// 		// 调用映射的方法处理并返回
	// 		var json = {};
	// 		json['time'] = time.now();
	//
	// 		// 调用处理方法
	// 		try {
	// 			var t = time.nanoTime();
	// 			var param = _.merge(req.body, req.query);
	// 			param.ip = req.header("X-Real-IP");
	// 			LOGGER.info('%s\tparam: %s', k_url, JSON.stringify(param));
	// 			var data = await v_map.func(req, res, param);
	// 			t = showTime(time.nanoTime() - t);
	// 			var isNumber = _.isNumber(data);
	// 			if (isNumber || data) {
	// 				if (data.errorCode) {
	// 					json['status'] = parseInt(data.errorCode);
	// 					json['errorMsg'] = data.errorMessage.toString();
	// 				} else {
	// 					json['status'] = 0;
	// 					json['data'] = data;
	// 				}
	// 			} else {
	// 				LOGGER.info('%s\texec: %s', k_url, t);
	// 				res.sendStatus(500);
	// 			}
	// 		} catch (e) {
	// 			res.sendStatus(500);
	// 			LOGGER.error(e);
	// 			return;
	// 		}
	// 		LOGGER.warn('%s\texec: %s', k_url, t);
	// 		LOGGER.info('%s send data: %s', k_url, JSON.stringify(json));
	// 		res.json(json);
	// 	};
	// 	switch (v_map.method) {
	// 		case 'get':
	// 			app.get(k_url, func);
	// 			break;
	// 		case 'post':
	// 			app.post(k_url, func);
	// 			break;
	// 		default:
	// 			app.use(k_url, func);
	// 			break;
	// 	}
	// });

	// 启动http服务器，监听端口
	app.listen(port);

	// 屏蔽一些服务器参数
	app.disable('x-powered-by');
	app.disable('etag');
	LOGGER.warn('http server start success, bind port %s ...', port);
};