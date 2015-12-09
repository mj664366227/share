// http服务器
var http = require("http");
var url = require("url");

// 启动http服务器
exports.start = function start(port, handle) {
	function onRequest(request, response) {
		var pathname = url.parse(request.url).pathname;
		console.log("Request for " + pathname + " received.");
		response.writeHeader(200, {
			'Content-Type' : 'text/plain'
		});
		var content = route(handle, pathname, response).trim();
		if (content) {
			response.write(content);
		}
		response.end();
	}
	http.createServer(onRequest).listen(port);
	console.log("http server has started at " + port);
}

// 路由
function route(handle, pathname, response) {
	if (typeof handle[pathname] === "function") {
		return handle[pathname]();
	} else {
		var status = 404;
		response.writeHeader(status, {
			'Content-Type' : 'text/html'
		});
		return showError(status, pathname);
	}
}

// 输出错误信息
function showError(status, url) {
	return "<html><head><meta http-equiv=\"Content-Type\" content=\"text/html;charset=utf-8\"/><title>Error "
			+ status
			+ " Not Found</title></head><body><h2>HTTP ERROR "
			+ status
			+ "</h2><p>Problem accessing "
			+ url
			+ ". Reason:<pre>    Not Found</pre></p><hr><i><small>Powered by share node js://</small></i><hr/></body></html>";
}