// demo控制器

exports.start = function() {
	console.log("request start");
	return "start";
};

exports.upload = function() {
	console.log("request upload");
	return JSON.stringify({
		a : 1,
		b : 2
	});
};