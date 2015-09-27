function a(){
	var aa = 1;
}

//module.exports = a;
console.log(__filename);
console.log(__dirname);
console.log(module);
var os = require('os');
console.log(os.type());

var crypto = require('crypto');
console.log(crypto.createHash("md5"));
var util = require('util');
util.debuglog('dsds');