package com.share.core.server;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.util.thread.QueuedThreadPool;
import org.eclipse.jetty.webapp.WebAppContext;

import com.share.core.exception.IllegalPortException;
import com.share.core.interfaces.AbstractServer;
import com.share.core.util.Check;

/**
 * jetty http 服务器
 * @author ruan
 *
 */
public final class HttpServer extends AbstractServer {
	/**
	 * jetty server 对象
	 */
	private Server server;
	/**
	 * webapp路径
	 */
	private List<String> webappPaths = new ArrayList<>();
	/**
	 * web.xml路径
	 */
	private final static String webXmlPath = "/WEB-INF/web.xml";

	/**
	 * 构造函数(默认80端口)
	 */
	public HttpServer() {
		this(80);
	}

	/**
	 * 构造函数
	 * @param port 端口
	 */
	public HttpServer(int port) {
		if (!Check.isPort(port)) {
			throw new IllegalPortException("Illegal port: " + port);
		}

		webappPaths.add("lib/webapp");
		webappPaths.add("src/webapp");

		server = new Server(new QueuedThreadPool(500));
		String webappPath = getWebappPath();
		WebAppContext webAppContext = new WebAppContext(webappPath + webXmlPath, "/");
		webAppContext.setResourceBase(webappPath);
		server.setHandler(webAppContext);

		ServerConnector serverConnector = new ServerConnector(server);
		serverConnector.setReuseAddress(true);
		serverConnector.setPort(port);
		server.addConnector(serverConnector);

		server.setStopAtShutdown(true);
		logger.info("http server bind port " + port);
	}

	/**
	 * 获取webapp路径
	 */
	private String getWebappPath() {
		for (String webappPath : webappPaths) {
			File webappFile = new File(webappPath, webXmlPath);
			if (webappFile.exists()) {
				logger.warn("find " + webappFile.getAbsolutePath());
				return webappPath;
			}
		}
		throw new IllegalStateException("not find any webappPath");
	}

	/**
	 * 启动服务器
	 * @author ruan
	 */
	public void start() {
		try {
			server.start();
		} catch (Exception e) {
			logger.error("", e);
			System.exit(0);
		} finally {
			logger.info("http server started...");
		}
	}
}
