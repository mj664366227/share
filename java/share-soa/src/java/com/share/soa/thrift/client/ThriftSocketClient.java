package com.share.soa.thrift.client;

import org.apache.thrift.TServiceClient;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

import com.share.core.exception.IllegalPortException;
import com.share.core.util.Check;
import com.share.soa.thrift.protocol.ShareObjectService;

/**
 * thrift socket 客户端
 */
public class ThriftSocketClient {
	/**
	 * 地址
	 */
	private String host;
	/**
	 * 端口
	 */
	private int port;

	/**
	 * 构造函数
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
	 */
	public ThriftSocketClient(String host, int port, TProtocol tProtocol, TServiceClient tServiceClient) {
		if (!Check.isPort(port)) {
			throw new IllegalPortException("Illegal port: " + port);
		}
		this.port = port;

		// 连接thrift服务器
		TTransport transport = new TFramedTransport(new TSocket(host, port));
		ShareObjectService.Client client = new ShareObjectService.Client(tProtocol);
		//transport.open();
		
		//System.err.println(client.test(1));
		//transport.close();
	}
}