var express = require('express');
var app = express();
var captchapng = require('captchapng');
var random = require('../share-core/core/util/random');

app.get('/', function (req, res) {
	var p = new captchapng(80, 30, random.rand(100000, 999999)); // width,height,numeric captcha
	p.color(0, 0, 0, 0);  // First color: background (red, green, blue, alpha)
	p.color(80, 80, 80, 255); // Second color: paint (red, green, blue, alpha)

	var img = p.getBase64();
	var imgbase64 = new Buffer(img, 'base64');
	res.writeHead(200, {
		'Content-Type': 'image/png'
	});
	res.end(imgbase64);
});

app.listen(1234);
console.log('http server start at: 1234.');
