// 七牛

import qiniu from "qiniu";
let LOGGER = logUtil.getLogger(module.filename);

// 七牛配置
qiniu.conf.ACCESS_KEY = properties['qiniu.accesskey'];
qiniu.conf.SECRET_KEY = properties['qiniu.secretkey'];

// 名空间配置
global.bucketConfig = [];
global.bucketConfig.push('file');
global.bucketConfig.push('image');
global.bucketConfig.push('report');

module.exports = {
	/**
	 * 返回上传策略
	 * @param bucket 名空间
	 * @param filename 文件名
	 */
	uptoken: function (bucket, filename) {
		return new qiniu.rs.PutPolicy(bucket + ":" + filename).token();
	},
	/**
	 * 上传文件
	 * @param bucket 名空间(file：文件, image：图片, report：报告)
	 * @param filepath 文件路径
	 * @return 文件地址
	 */
	upload: function (bucket, filepath) {
		if (bucketConfig.indexOf(bucket) <= -1) {
			throw new Error('error bucket, ' + bucket);
		}
		let uploadToken = this.uptoken;
		return new Promise(function (resolve, reject) {
			let extra = new qiniu.io.PutExtra();
			let filename = timeUtil.nowMillis();
			filename += '_';
			filename += random.rand(1000000000, 2147483647);
			filename += '_';
			filename += random.rand(1000000000, 2147483647);
			filename += '.';
			filename += filepath.substring(filepath.lastIndexOf(".") + 1).toLowerCase();
			let uptoken = uploadToken(bucket, filename);
			qiniu.io.putFile(uptoken, filename, filepath, extra, function (err, ret) {
				if (!err) {
					let link = 'http://';
					link += bucket;
					link += '.gatherup.cc/';
					link += ret.key;
					resolve(link);
				} else {
					// 上传失败， 处理返回代码
					LOGGER.error(err);
					reject(err);
				}
			});
		}).catch(function (error) {
			LOGGER.error(error);
		});
	}
};