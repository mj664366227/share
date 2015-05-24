package com.share.soa.thrift;

import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.server.TThreadPoolServer.Args;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

import com.share.core.exception.IllegalPortException;
import com.share.core.interfaces.AbstractServer;
import com.share.core.util.Check;
import com.share.soa.thrift.protocol.ShareObjectService;
import com.share.soa.thrift.server.ShareObjectServiceImpl;

public class ThriftSocketServer extends AbstractServer {
	private TServer server;
	private int port;

	/**
	 * 构造函数
	 * @param port 端口
	 * @param protocolFactory 数据传输协议，与客户端对应，有以下几种可选：
	 * <pre>
	 * TBinaryProtocol     二进制格式
	 * TCompactProtocol    压缩格式
	 * TJSONProtocol       JSON格式
	 * TSimpleJSONProtocol 提供JSON只写协议，生成的文件很容易通过脚本语言解析
	 * TDebugProtocol      使用易懂的可读的文本格式以便于debug
	 * 
	 * 例如：new ThriftSocketServer(10086, new TCompactProtocol.Factory());
	 * </pre>
	 */
	public ThriftSocketServer(int port, TProtocolFactory protocolFactory) {
		this.port = port;
		if (!Check.isPort(port)) {
			throw new IllegalPortException("Illegal port: " + port);
		}

		try {
			// 设置传输通道，普通通道  
			TServerTransport serverTransport = new TServerSocket(port);

			ShareObjectService.Processor<ShareObjectServiceImpl> processor = new ShareObjectService.Processor<ShareObjectServiceImpl>(new ShareObjectServiceImpl());

			// 构造连接参数
			Args args = new Args(serverTransport);
			args.protocolFactory(protocolFactory);
			args.processor(processor);

			// 创建服务器 
			server = new TThreadPoolServer(args);
		} catch (Exception e) {
			logger.error("", e);
			System.exit(0);
		}

	}

	/**
	 * 启动服务器
	 * @author ruan
	 */
	public void start() {
		logger.info("start server on port {} ...", port);
		server.serve();
	}
}