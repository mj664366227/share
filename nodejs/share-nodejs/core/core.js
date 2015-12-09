exports.hello = function hello(name) { 
	console.log('hello, '+ name); 
} 

var PI = Math.PI;
exports.area = function (r) {
    return PI * r * r;
};
exports.circumference = function (r) {
    return 2 * PI * r;
};

exports.config = {
	'a': 1,
	'b': 2
}
