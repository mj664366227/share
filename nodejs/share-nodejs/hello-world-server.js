var http = require('http');
http.createServer(function(req, res) {
	res.writeHead(404, {
		'Content-Type' : 'text/plain'
	});
	res.end('Hello World\n');
}).listen(13377);

console.log('Server running at http://127.0.0.1:13377/');