package com.share.soa.thrift.server;

import org.apache.thrift.TProcessor;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadedSelectorServer;
import org.apache.thrift.server.TThreadedSelectorServer.Args;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TNonblockingServerTransport;

import com.share.core.exception.IllegalPortException;
import com.share.core.interfaces.AbstractServer;
import com.share.core.util.Check;
import com.share.core.util.FileSystem;
import com.share.core.util.SystemUtil;

/**
 * thrift socket 服务器
 */
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
	 * </pre>
	 * @param tProcessor 需要处理哪个接口
	 * 
	 * <pre>
	 * 例如：
	 * ShareObjectService.Processor<ShareObjectServiceHandler> processor = new ShareObjectService.Processor<ShareObjectServiceHandler>(new ShareObjectServiceHandler());
	 * ThriftSocketServer t = new ThriftSocketServer(10086, new TCompactProtocol.Factory(), processor);
	 * t.start();
	 * </pre>
	 */
	public ThriftSocketServer(int port, TProtocolFactory protocolFactory, TProcessor tProcessor) {
		if (!Check.isPort(port)) {
			throw new IllegalPortException("Illegal port: " + port);
		}
		this.port = port;

		try {
			// 设置传输通道，普通通道  
			TNonblockingServerTransport serverTransport = new TNonblockingServerSocket(port);

			// 构造连接参数
			Args args = new Args(serverTransport);
			args.transportFactory(new TFramedTransport.Factory());
			args.protocolFactory(protocolFactory);
			args.processor(tProcessor);

			// 设置selector线程和worker线程
			int selectorThreads = FileSystem.getPropertyInt("thrift.socket.selectorThreads");
			int workerThreads = FileSystem.getPropertyInt("thrift.socket.workerThreads");
			// 如果没有设置，就用cpu核心数x2
			if (selectorThreads <= 0) {
				selectorThreads = SystemUtil.getCore();
			}
			if (workerThreads <= 0) {
				workerThreads = SystemUtil.getCore();
			}

			args.selectorThreads(selectorThreads);
			args.workerThreads(workerThreads);
			logger.info("selectorThreads: {}, workerThreads: {}", selectorThreads, workerThreads);

			// 创建服务器 
			server = new TThreadedSelectorServer(args);
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
		logger.info("start thrift socket server on port {} ...", port);
		server.serve();
	}
}