var LOGGER = logUtil.getLogger(module.filename);
module.exports = {
	/**
	 * 从redis取的数据转成对象
	 * @param jsonString jsonString
	 * @param T 对象方法
	 * @returns t
	 */
	decodeT: function (jsonString, T) {
		var json = JSON.parse(jsonString);
		var clazz = _.getPojoClass(T);
		var t = new T();
		var i = 0;
		_.forEach(json, function (v, k) {
			if (_.isUndefined(t[clazz][k])) {
				// 缓存有,程序没有的字段,忽略
				LOGGER.error("JSONObject.decodeT(jsonString, T) Error : t." + clazz + "." + k + "  is undefined");
				return;
			}
			t[clazz][k] = json[k];
			i = i + 1;
		});
		if (i > 2) {
			return t;
		} else {
			// 除id(主键)外,所有字段匹配不成功,返回空
			return null;
		}
	},
	/**
	 * 对象转成jsonString保存到redis
	 * @param t 对象实体
	 * @returns jsonString
	 */
	encodeT: function (t) {
		var clazz = _.keys(t);
		if (clazz.length != 1) {
			throw new Error("t is not a pojo Object: " + t);
		}
		if (!/^[D][A-Z]/.test(clazz[0])) {
			throw new Error("t is not a pojo Object: " + t);
		}
		return JSON.stringify(t[clazz[0]]);
	}
};
