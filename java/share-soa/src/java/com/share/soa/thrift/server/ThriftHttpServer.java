package com.share.soa.thrift.server;

import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.TServlet;

import com.share.soa.thrift.impl.ShareObjectServiceHandler;
import com.share.soa.thrift.protocol.ShareObjectService;

/**
 * thrift http 服务器
 */
public class ThriftHttpServer extends TServlet {
	private static final long serialVersionUID = 2832683504442203445L;

	/**
	 * 配置初始化
	 */
	public void init(ServletConfig config) throws ServletException {
		Enumeration<String> ss = config.getInitParameterNames();
		while (ss.hasMoreElements()) {
			String str = ss.nextElement();
			System.err.println(str + "\t" + config.getInitParameter(str));
		}
	}

	/**
	 * 构造函数
	 */
	public ThriftHttpServer() {
		super(new ShareObjectService.Processor<ShareObjectServiceHandler>(new ShareObjectServiceHandler()), new TCompactProtocol.Factory());
	}

}