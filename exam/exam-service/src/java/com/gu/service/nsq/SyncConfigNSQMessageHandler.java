package com.gu.service.nsq;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.gu.core.annotation.NsqCallback;
import com.gu.core.common.NSQTopic;
import com.gu.core.interfaces.NsqMessageHandler;
import com.gu.service.cases.BonusService;
import com.gu.service.cases.CaseService;
import com.gu.service.company.CompanyService;
import com.gu.service.system.SystemService;
import com.gu.service.user.UserService;

/**
 * 配置同步
 * @author ruan
 */
@Component("WebSyncConfigNSQMessageHandler")
public class SyncConfigNSQMessageHandler implements NsqMessageHandler {
	/**
	* logger
	*/
	private final static Logger logger = LoggerFactory.getLogger(SyncConfigNSQMessageHandler.class);
	/**
	 * 方法map
	 */
	private Map<String, Method> methodMap = new ConcurrentHashMap<>(getClass().getDeclaredMethods().length - 1);
	/**
	 * context
	 */
	@Autowired
	private ApplicationContext context;
	@Autowired
	private CaseService caseService;
	@Autowired
	private BonusService bonusService;
	@Autowired
	private CompanyService companyService;
	@Autowired
	private UserService userService;
	@Autowired
	private SystemService systemService;
	private Object obj;

	private SyncConfigNSQMessageHandler() {
		// 表名做方法名字，用反射来做，这样就不同写那么多次case
		for (Method method : getClass().getDeclaredMethods()) {
			if ("handle".equals(method.getName())) {
				continue;
			}
			methodMap.put(method.getName(), method);
		}
	}

	@NsqCallback(topic = NSQTopic.syncConfig, channel = "sync_config", onlyChannel = false)
	public boolean handle(byte[] message) {
		Method method = methodMap.get(new String(message).trim());
		if (method == null) {
			logger.error("unknow method name: {}", new String(message).trim());
			return false;
		}

		// 获取本类的实例
		if (obj == null) {
			//这样是为了防止并发
			synchronized (this) {
				obj = context.getBean(getClass());
			}
		}

		try {
			method.invoke(obj);
		} catch (Exception e) {
			logger.error("", e);
			return false;
		}
		return true;
	}

	public void bonusPercentConfig() {
		bonusService.init();
	}

	public void caseExpire() {
		caseService.initCaseExpireConfig();
	}

	public void casePoints() {
		caseService.initCasePointsConfig();
	}

	public void caseType() {
		caseService.initCaseTypeConfig();
	}

	public void companyType() {
		companyService.init();
	}

	public void levelExpConfig() {
		userService.initLevelExpConfig();
	}

	public void userIdentityConfig() {
		userService.initUserIdentityConfig();
	}

	public void systemConfig() {
		systemService.init();
	}

	public void caseStyle() {
		caseService.initCaseStyleConfig();
	}
}