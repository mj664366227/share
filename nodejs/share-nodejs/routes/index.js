var express = require('express');
var app = express();
app.use(express.static('public'));

app.get('/', function (req, res) {
	  res.render('index.jade', { title: 'Hey', message: 'Hello there!'});
	});

console.log(__dirname);
console.log('express server start at %d', 3000);
app.listen(3000);
module.exports = app;