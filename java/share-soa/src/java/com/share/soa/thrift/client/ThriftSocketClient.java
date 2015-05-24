package com.share.soa.thrift.client;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.thrift.TServiceClient;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.share.core.exception.IllegalPortException;
import com.share.core.util.Check;

/**
 * thrift socket 客户端
 */
public class ThriftSocketClient {
	/**
	 * logger
	 */
	private final static Logger logger = LoggerFactory.getLogger(ThriftSocketClient.class);
	/**
	 * 反射方法对象缓存map
	 */
	private static Map<String, Method> reflectMethodMap = new ConcurrentHashMap<>();

	/**
	 * 构造函数
	 * @param <T>
	 * @param host 服务器地址
	 * @param port 服务器端口
	 * @param tProtocol 数据传输协议，与客户端对应，有以下几种可选：	
	 * <pre>
	 * TBinaryProtocol     二进制格式
	 * TCompactProtocol    压缩格式
	 * TJSONProtocol       JSON格式
	 * TSimpleJSONProtocol 提供JSON只写协议，生成的文件很容易通过脚本语言解析
	 * TDebugProtocol      使用易懂的可读的文本格式以便于debug
	 * </pre>	
	 * @param tServiceClient 要处理的接口
	 * @param methodName 方法名
	 * @param parameters 参数列表
	 */
	public ThriftSocketClient(String host, int port, Class<? extends TProtocol> tProtocol, Class<? extends TServiceClient> tServiceClient, String methodName, Object... parameters) {
		if (!Check.isPort(port)) {
			throw new IllegalPortException("Illegal port: " + port);
		}

		// 连接thrift服务器
		TTransport transport = new TFramedTransport(new TSocket(host, port));

		try {
			// 通过反射，生成指定是协议传输通道
			Constructor<? extends TProtocol> tProtocolConstructor = tProtocol.getConstructor(new Class<?>[] { TTransport.class });
			TProtocol tp = tProtocolConstructor.newInstance(transport);

			// 通过反射，请求指定接口
			Constructor<? extends TServiceClient> tServiceClientConstructor = tServiceClient.getConstructor(new Class<?>[] { TProtocol.class });
			TServiceClient client = tServiceClientConstructor.newInstance(tp);

			// 开启传输通道
			transport.open();

			Method method = cacheMethod(tServiceClient, "test");

			System.err.println(tServiceClient + "\t" + method);
			//System.err.println(method.invoke(client, 1));
		} catch (Exception e) {
			logger.error("", e);
		} finally {
			// 关闭传输通道
			transport.close();
		}
	}

	/**
	 * 获取反射方法
	 * @author ruan
	 * @param clazz 类
	 * @param methodName 方法名
	 */
	private Method cacheMethod(Class<? extends TServiceClient> clazz, String methodName) {
		Method method = reflectMethodMap.get(clazz.toString() + methodName);
		if (method == null) {
			// 如果获取不到，就把整个类的方法都缓存一次
			for (Method m : clazz.getDeclaredMethods()) {
				reflectMethodMap.put(clazz.toString() + m.getName(), m);
				if (m.getName().equals(methodName)) {
					method = m;
				}
			}
		}
		return method;
	}
}