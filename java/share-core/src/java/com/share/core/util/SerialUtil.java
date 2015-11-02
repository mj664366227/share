package com.share.core.util;

import org.apache.commons.lang3.reflect.MethodUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtobufIOUtil;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.google.protobuf.GeneratedMessage;

/**
 * Java 序列化工具类, 目前使用的序列化使用的Kryo
 */
@SuppressWarnings("unchecked")
public class SerialUtil {
	private static Logger logger = LoggerFactory.getLogger(SerialUtil.class);

	/**
	 * 对象序列化成字节
	 *
	 * @param object java对象
	 * @return 序列化后的数组
	 */
	public static byte[] toBytes(Object object) {
		LinkedBuffer buffer = LinkedBuffer.allocate(256);
		try {
			return ProtobufIOUtil.toByteArray(object, (com.dyuproject.protostuff.Schema<Object>) RuntimeSchema.getSchema(object.getClass()), buffer);
		} finally {
			buffer.clear();
		}
	}

	/**
	 * 反序列化
	 *
	 * @param bytes 已经被序列化的数组
	 * @param klass 需要反序列化成的类型
	 * @param <T>   不解释
	 * @return 反序列化后的实例
	 */
	public static <T> T fromBytes(byte[] bytes, Class<T> klass) {
		if (bytes == null) {
			return null;
		}
		try {
			if (GeneratedMessage.class.isAssignableFrom(klass)) {
				T obj = (T) MethodUtils.invokeStaticMethod(klass, "parseFrom", bytes);
				return obj;
			}
			T object = klass.newInstance();
			ProtobufIOUtil.mergeFrom(bytes, object, RuntimeSchema.getSchema(klass));
			return object;
		} catch (Exception e) {
			logger.error("byteStr:{}", new String(bytes));
			logger.error("反序列化失败:{}", e);
		}
		return null;
	}
}