var fs = require("fs");
var data = fs.readFileSync('D:/nginx/nsqd.851.dat', "utf-8");
console.log(data.toString());
console.log("Program Ended");