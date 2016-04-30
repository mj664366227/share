import framework from '../node-core/index';
import http from '../node-core/framework/core/server/httpServer';
import route from '../node-core/framework/core/server/route';
module.exports = function () {
	http.start(properties['http.project.port'], route());
};