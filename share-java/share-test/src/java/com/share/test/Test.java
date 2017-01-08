package com.share.test;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.share.core.util.FileSystem;

public class Test {

	//第二类  绝对路径的配置文件
	public static void test1() {
		String path = FileSystem.getSystemDir() + ".." + File.separator + ".." + File.separator + "share-java" + File.separator + "share-core" + File.separator + "bin" + File.separator;
		System.err.println(path);
		//System.err.println(FileSystem.getSystemDir()+"../../share-java\share-core\bin");
		//我们将log4j2.xml放在D盘下
		//这是需要手动的加载
		//绝对路径配置文件		
		Logger logger = LogManager.getLogger(Test.class.getName());
		logger.trace("trace...");
		logger.debug("debug...");
		logger.info("info...");
		logger.warn("warn...");
		logger.error("error...");
		logger.fatal("fatal...");
		//一下是运行效果
		/*2014-09-01 16:03:07,331 DEBUG [main] test.ConfigTest (ConfigTest.java:42) - debug...
		2014-09-01 16:03:07,331 INFO  [main] test.ConfigTest (ConfigTest.java:43) - info...
		2014-09-01 16:03:07,331 WARN  [main] test.ConfigTest (ConfigTest.java:44) - warn...
		2014-09-01 16:03:07,331 ERROR [main] test.ConfigTest (ConfigTest.java:45) - error...
		2014-09-01 16:03:07,331 FATAL [main] test.ConfigTest (ConfigTest.java:46) - fatal...*/
	}

	public static void main(String[] args) {
		test1();
	}
}