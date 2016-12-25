'use strict';

export default {
	port: 56001, //服务监听的端口
	encoding: "utf-8", //项目编码
	hook_on: true,  //是否开启 hook
	timeout: 120, //120 seconds
	auto_reload: true, //自动重新加载修改的文件，development 模式下使用

	log_error: true, //是否打印错误日志
	log_request: true, //是否打印请求的日志

	deny_module_list: [], //禁用的模块列表
	default_module: "home", //默认模块
	default_controller: "index",  //默认的控制器
	default_action: "index", //默认的 Action
	json_content_type: "application/json", //json 输出时设置的 Content-Type
}