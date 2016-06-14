const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');

module.exports = {
	// 入口
	entry: './src/main',
	// 输出
	output: {
		path: path.join(__dirname, './dist'),
		filename: 'main.js'
	},
	module: {
		// 加载器
		loaders: [
			{test: /\.vue$/, loader: 'vue'},
			{test: /\.js$/, loader: 'babel', exclude: /node_modules/},
			{test: /\.css$/, loader: 'style!css!autoprefixer'},
			{test: /\.less/, loader: 'style!css!autoprefixer!less'},
			{test: /\.(png|jpg|gif)$/, loader: 'url-loader'},
			{test: /\.(html|tpl)$/, loader: 'html-loader'},
		]
	},
	vue: {
		loaders: {
			css: 'style!css!autoprefixer!less'
		}
	},
	babel: {
		presets: ['es2015'],
		plugins: ['transform-runtime']
	},
	resolve: {
		// require时省略的扩展名，如：require('module') 不需要module.js
		extensions: ['', '.js', '.vue'],
		// 别名
		alias: {
			filter: path.join(__dirname, './src/filters'),
			components: path.join(__dirname, './src/components')
		}
	},
	// 生产环境不需要devtool
	devtool: false,
	plugins:[
		new HtmlWebpackPlugin({                        //根据模板插入css/js等生成最终HTML
			//favicon:'./images/favicon.ico', //favicon存放路径
			filename:'./index.html',    //生成的html存放路径，相对于 path
			template:'./src/index.html',    //html模板路径
			inject:true,    //允许插件修改哪些内容，包括head与body
			hash:true,    //为静态资源生成hash值
			chunks: ['vendor', 'app'], //需要引入的chunk，不配置就会引入所有页面的资源.名字来源于你的入口文件
			minify:{    //压缩HTML文件
				removeComments:true,    //移除HTML中的注释
				collapseWhitespace:true    //删除空白符与换行符
			}
		})
	]
};