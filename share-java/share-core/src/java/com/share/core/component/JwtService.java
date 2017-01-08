package com.share.core.component;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.share.core.util.FileSystem;

/**
 * jwt
 */
public class JwtService {
	/**
	 * logger
	 */
	private final static Logger logger = LoggerFactory.getLogger(JwtService.class);
	/**
	 * 加密密匙
	 */
	private final static String secret = FileSystem.getPropertyString("system.key");
	/**
	 * 加密算法
	 */
	private String algorithm;

	/**
	 * 私有化构造函数，只有spring才可以实例化
	 */
	private JwtService() {
	}

	/**
	 * 初始化
	 */
	@PostConstruct
	public void init() {
		System.err.println(algorithm);
	}

	/**
	 * 设置加密算法
	 * @param algorithm 加密算法名称
	 */
	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}

	/**
	 * 生成token
	 * @param data 要加密的数据
	 */
	public String sign(Map<String, Object> data) {
		try {
			JWTCreator.Builder jwt = JWT.create();
			jwt.withIssuer("auth0");
			return jwt.sign(Algorithm.HMAC256(secret));
		} catch (Exception e) {
			logger.error("", e);
		}
		return "";
	}

	/**
	 * 把token反解析成map
	 * @param token
	 */
	public Map<String, Object> verify(String token) {
		try {
			JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret)).withIssuer("auth0").build();
			DecodedJWT jwt = verifier.verify(token);
		} catch (Exception e) {
			logger.error("", e);
		}
		return null;
	}
}