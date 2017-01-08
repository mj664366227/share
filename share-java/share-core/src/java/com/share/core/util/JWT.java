package com.share.core.util;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.auth0.jwt.Algorithm;
import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTSigner.Options;
import com.auth0.jwt.JWTVerifier;

/**
 * jwt
 * @author ruan
 */
public class JWT {
	/**
	 * logger
	 */
	private final static Logger logger = LoggerFactory.getLogger(JWT.class);
	/**
	 * 加密密匙
	 */
	private final static String secret = FileSystem.getPropertyString("system.key");
	/**
	 * 加密选项
	 */
	private final static Options options = new Options().setAlgorithm(Algorithm.HS512);

	/**
	 * 生成token
	 * @param data 要加密的数据
	 */
	public final static String sign(Map<String, Object> data) {
		return new JWTSigner(secret).sign(data, options);
	}

	/**
	 * 把token反解析成map
	 * @param token
	 */
	public final static Map<String, Object> verify(String token) {
		try {
			return new JWTVerifier(secret).verify(token);
		} catch (Exception e) {
			logger.error("", e);
		}
		return null;
	}
}