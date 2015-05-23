package com.share.soa.thrift.http;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.server.TServlet;

public class TServletExample extends TServlet {
	private static final long serialVersionUID = -6393044103559956922L;

	public TServletExample(TProcessor processor, TProtocolFactory protocolFactory) {
		super(processor, protocolFactory);
	}
}