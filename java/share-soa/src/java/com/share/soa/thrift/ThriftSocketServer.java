package com.share.soa.thrift;

import com.share.core.exception.IllegalPortException;
import com.share.core.interfaces.AbstractServer;
import com.share.core.util.Check;

public class ThriftSocketServer extends AbstractServer {
	/**
	 * 构造函数
	 * @param port 端口
	 */
	public ThriftSocketServer(int port) {
		if (!Check.isPort(port)) {
			throw new IllegalPortException("Illegal port: " + port);
		}
	}

	/**
	 * 启动服务器
	 * @author ruan
	 */
	public void start() {

	}
}