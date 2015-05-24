package com.share.soa.thrift.server;

import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.server.TThreadPoolServer.Args;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.share.core.util.FileSystem;
import com.share.soa.thrift.protocol.ShareObjectService;

@Component
public class ThriftServer {
	/**
	 * logger
	 */
	private final static Logger logger = LoggerFactory.getLogger(ThriftServer.class);

	public static void main(String[] aaa) throws Exception {
		int port = FileSystem.getPropertyInt("thrift.port");

		//设置传输通道，普通通道  
		TServerTransport serverTransport = new TServerSocket(port);

		//使用高密度二进制协议  	
		TProtocolFactory proFactory = new TCompactProtocol.Factory();

		ShareObjectService.Processor<ShareObjectServiceImpl> processor = new ShareObjectService.Processor<ShareObjectServiceImpl>(new ShareObjectServiceImpl());

		//创建服务器  
		TServer server = new TThreadPoolServer(new Args(serverTransport).protocolFactory(proFactory).processor(processor));

		logger.info("start server on port " + port + "...");
		server.serve();
	}
}