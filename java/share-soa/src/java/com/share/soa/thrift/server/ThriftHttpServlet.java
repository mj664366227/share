package com.share.soa.thrift.server;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Constructor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.TIOStreamTransport;
import org.apache.thrift.transport.TTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * thrift http servlet
 */
public class ThriftHttpServlet extends HttpServlet {
	private static final long serialVersionUID = -2355023383614264213L;
	private static final Logger logger = LoggerFactory.getLogger(ThriftHttpServlet.class);
	private TProcessor processor;
	private TProtocolFactory protocolFactory;

	/**
	 * 构造函数
	 * @param serviceClassName 接口类名
	 * @param tProtocolClassName 通讯协议类名
	 * @param handlerClassName 处理器类名
	 */
	public ThriftHttpServlet(String serviceClassName, String tProtocolClassName, String handlerClassName) {
		try {
			// 初始化通讯协议
			protocolFactory = (TProtocolFactory) Class.forName(tProtocolClassName + "$Factory").newInstance();

			// 初始化Iface
			Class<?> IfaceClazz = Class.forName(serviceClassName + "$Iface");

			// 初始化处理器
			Constructor<?> constructor = Class.forName(serviceClassName + "$Processor").getConstructor(new Class<?>[] { IfaceClazz });
			processor = (TProcessor) constructor.newInstance(Class.forName(handlerClassName).newInstance());
		} catch (Exception e) {
			logger.error("", e);
			System.exit(0);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OutputStream output = response.getOutputStream();
		TTransport transport = new TIOStreamTransport(request.getInputStream(), output);
		try {
			processor.process(protocolFactory.getProtocol(transport), protocolFactory.getProtocol(transport));
			output.flush();
		} catch (Exception e) {
			logger.error("", e);
		}
	}
}