package com.share.soa.thrift.server;

import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.server.TThreadPoolServer.Args;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

import com.share.soa.thrift.protocol.ShareObjectService;
import com.share.soa.thrift.protocol.ShareObjectServiceImpl;

public class ThriftServer {
	public static void main(String[] aaa) throws Exception {
		//设置传输通道，普通通道  
        TServerTransport serverTransport = new TServerSocket(9394);  
          
        //使用高密度二进制协议  
        TProtocolFactory proFactory = new TCompactProtocol.Factory();  
          
        //设置处理器HelloImpl  
        ShareObjectService.Processor<ShareObjectServiceImpl> processor = new ShareObjectService.Processor<ShareObjectServiceImpl>(new ShareObjectServiceImpl());  
          
        //创建服务器  
        TServer server = new TThreadPoolServer(  
                new Args(serverTransport)  
                .protocolFactory(proFactory)  
                .processor(processor)  
            );  
          
        System.out.println("Start server on port 9394...");  
        server.serve();
	}
}
