var express = require('express');
var app = express();

/* GET users listing. */
app.get('/user', function(req, res, next) {
  res.send('respond with a resource');
});

