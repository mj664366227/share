require("babel-register");
require("babel-polyfill");
global.AppDir = __dirname;
require("./index")();
process.on('uncaughtException', function (err) {
	console.log('uncaughtException : ', err);
});