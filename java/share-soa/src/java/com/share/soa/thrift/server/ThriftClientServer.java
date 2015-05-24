package com.share.soa.thrift.server;

import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.TServlet;

import com.share.soa.thrift.impl.ShareObjectServiceHandler;
import com.share.soa.thrift.protocol.ShareObjectService;

/**
 * thrift http 服务器
 */
public class ThriftClientServer extends TServlet {
	private static final long serialVersionUID = 2832683504442203445L;

	public ThriftClientServer() {
		super(new ShareObjectService.Processor<ShareObjectServiceHandler>(new ShareObjectServiceHandler()), new TCompactProtocol.Factory());
	}

}