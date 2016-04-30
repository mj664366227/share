module.exports = {
	/**
	 * 项目详情
	 */
	proInfo: {
		url: URLCommand.proInfo,
		method: "get",
		func: async function (req, res, param) {
			var p = new captchapng(80, 30, random.rand(100000, 999999)); // width,height,numeric captcha
			p.color(0, 0, 0, 0);  // First color: background (red, green, blue, alpha)
			p.color(80, 80, 80, 255); // Second color: paint (red, green, blue, alpha)

			var img = p.getBase64();
			var imgbase64 = new Buffer(img, 'base64');
			res.writeHead(200, {
				'Content-Type': 'image/png'
			});
			res.end(imgbase64);
		}
	}
};