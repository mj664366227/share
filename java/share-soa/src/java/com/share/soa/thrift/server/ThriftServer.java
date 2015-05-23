package com.share.soa.thrift.server;

import javax.annotation.PostConstruct;

import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.server.TThreadPoolServer.Args;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.share.soa.thrift.protocol.ShareObjectService;
import com.share.soa.thrift.protocol.ShareObjectServiceImpl;

@Component
public class ThriftServer {
	/**
	 * logger
	 */
	private final static Logger logger = LoggerFactory.getLogger(ThriftServer.class);
	/**
	 * 启动端口
	 */
	@Value("${thrift.port}")
	private int port;

	@PostConstruct
	public void init() throws Exception {
		//设置传输通道，普通通道  
		TServerTransport serverTransport = new TServerSocket(port);

		//使用高密度二进制协议  
		TProtocolFactory proFactory = new TCompactProtocol.Factory();

		//设置处理器HelloImpl  
		ShareObjectService.Processor<ShareObjectServiceImpl> processor = new ShareObjectService.Processor<ShareObjectServiceImpl>(new ShareObjectServiceImpl());

		//创建服务器  
		TServer server = new TThreadPoolServer(new Args(serverTransport).protocolFactory(proFactory).processor(processor));

		logger.info("Start server on port " + port + "...");
		server.serve();
	}
}