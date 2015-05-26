package com.share.soa.thrift.server;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Constructor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.thrift.TException;
import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TJSONProtocol;
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

	public ThriftHttpServlet(Class<? extends TProcessor> processor, String handler) {
		System.err.println(processor);
		try {
			//Constructor<?> constructor = Class.forName(processor).getConstructor(new Class<?>[] { com.share.soa.thrift.protocol.ShareObjectService.Iface.class });
			//this.processor = (TProcessor) constructor.newInstance(Class.forName(handler).newInstance());
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String protocol = request.getHeader("protocol");
		System.err.println("protocol:  " + protocol);
		TProtocolFactory protocolFactory = new TCompactProtocol.Factory();

		response.setContentType("application/x-thrift");
		OutputStream output = response.getOutputStream();
		TTransport transport = new TIOStreamTransport(request.getInputStream(), output);
		try {
			processor.process(protocolFactory.getProtocol(transport), protocolFactory.getProtocol(transport));
			output.flush();
		} catch (TException e) {
			throw new RuntimeException(e);
		}

	}
}