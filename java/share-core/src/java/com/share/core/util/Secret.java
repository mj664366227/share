package com.share.core.util;

import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.sauronsoftware.base64.Base64;

/**
 * 加密类
 */
public final class Secret {
	/**
	 * logger
	 */
	private final static Logger logger = LoggerFactory.getLogger(Secret.class);
	/**
	 * 系统加密key
	 */
	private final static String systemKey = SystemUtil.getSystemKey();
	/**
	 * 加密算法RSA
	 */
	public final static String KEY_ALGORITHM = "RSA";
	/**
	 * 签名算法
	 */
	public final static String SIGNATURE_ALGORITHM = "MD5withRSA";
	/**
	 * 获取公钥的key
	 */
	private final static String PUBLIC_KEY = "RSAPublicKey";
	/**
	 * 获取私钥的key
	 */
	private final static String PRIVATE_KEY = "RSAPrivateKey";
	/**
	 * RSA最大加密明文大小
	 */
	private final static int MAX_ENCRYPT_BLOCK = 117;

	/**
	 * RSA最大解密密文大小
	 */
	private final static int MAX_DECRYPT_BLOCK = 1024;

	private Secret() {
	}

	/**
	 * des加密
	 * @param data
	 */
	public final static byte[] desEncode(byte[] data) {
		try {
			// 生成一个可信任的随机数源
			SecureRandom sr = new SecureRandom();

			// 从原始密钥数据创建DESKeySpec对象
			DESKeySpec dks = new DESKeySpec(systemKey.getBytes());

			// 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey securekey = keyFactory.generateSecret(dks);

			// 用密钥初始化Cipher对象
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
			return cipher.doFinal(data);
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}

	/**
	 * des解密
	 * @param data
	 */
	public final static byte[] desDecode(byte[] data) {
		try {
			// 生成一个可信任的随机数源
			SecureRandom sr = new SecureRandom();

			// 从原始密钥数据创建DESKeySpec对象
			DESKeySpec dks = new DESKeySpec(systemKey.getBytes());

			// 创建一个密钥工厂，然后用它把DESKeySpec转换成SecretKey对象
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey securekey = keyFactory.generateSecret(dks);

			// 用密钥初始化Cipher对象
			Cipher cipher = Cipher.getInstance("DES");
			cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
			return cipher.doFinal(data);
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}

	/**
	 * MD5加密
	 * 
	 * @param string
	 * @return String
	 */
	public final static String MD5(String string) {
		try {
			return byteArrayToHexString(MessageDigest.getInstance("MD5").digest(string.getBytes()));
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}

	/**
	 * 哈希加密
	 * 
	 * @param string
	 * @return String
	 */
	public final static String SHA(String string) {
		try {
			return byteArrayToHexString(MessageDigest.getInstance("SHA").digest(string.getBytes()));
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}

	/**
	 * base64加密
	 * @param str
	 */
	public final static String base64Encode(String str) {
		return Base64.encode(str);
	}

	/**
	 * base64解密
	 * @param str
	 */
	public final static String base64Decode(String str) {
		try {
			return Base64.decode(str);
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}

	/**
	 * 生成密钥对(公钥和私钥)
	 */
	public final static Map<String, Object> genKeyPair() {
		try {
			KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
			SecureRandom secrand = new SecureRandom();
			secrand.setSeed(SystemUtil.getSystemKey().getBytes()); // 初始化随机产生器
			keyPairGen.initialize(MAX_DECRYPT_BLOCK, secrand);
			KeyPair keyPair = keyPairGen.generateKeyPair();
			RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
			RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
			Map<String, Object> keyMap = new HashMap<String, Object>(2);
			keyMap.put(PUBLIC_KEY, publicKey);
			keyMap.put(PRIVATE_KEY, privateKey);
			return keyMap;
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}

	/**
	 * 用私钥对信息生成数字签名
	 * @param data 已加密数据
	 * @param privateKey 私钥(BASE64编码)
	 */
	public final static String sign(byte[] data, String privateKey) {
		try {
			byte[] keyBytes = Base64.decode(privateKey.getBytes());
			PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
			KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
			PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);
			Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
			signature.initSign(privateK);
			signature.update(data);
			return new String(Base64.encode(signature.sign()));
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}

	/**
	 * 校验数字签名
	 * @param data 已加密数据
	 * @param publicKey 公钥(BASE64编码)
	 * @param sign 数字签名
	 */
	public final static boolean verify(byte[] data, String publicKey, String sign) {
		try {
			byte[] keyBytes = Base64.decode(publicKey.getBytes());
			X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
			KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
			PublicKey publicK = keyFactory.generatePublic(keySpec);
			Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
			signature.initVerify(publicK);
			signature.update(data);
			return signature.verify(Base64.decode(sign.getBytes()));
		} catch (Exception e) {
			logger.error("", e);
			return false;
		}
	}

	/**
	 * 私钥解密
	 * @param encryptedData 已加密数据
	 * @param privateKey 私钥(BASE64编码)
	 */
	public final static byte[] decryptByPrivateKey(byte[] encryptedData, String privateKey) {
		try {
			byte[] keyBytes = Base64.decode(privateKey.getBytes());
			PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
			KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
			Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
			Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
			cipher.init(Cipher.DECRYPT_MODE, privateK);
			int inputLen = encryptedData.length;
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int offSet = 0;
			byte[] cache;
			int i = 0;
			// 对数据分段解密
			while (inputLen - offSet > 0) {
				if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
					cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
				} else {
					cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
				}
				out.write(cache, 0, cache.length);
				i++;
				offSet = i * MAX_DECRYPT_BLOCK;
			}
			byte[] decryptedData = out.toByteArray();
			out.close();
			return decryptedData;
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}

	/**
	 * 公钥解密
	 * @param encryptedData 已加密数据
	 * @param publicKey 公钥(BASE64编码)
	 */
	public final static byte[] decryptByPublicKey(byte[] encryptedData, String publicKey) {
		try {
			byte[] keyBytes = Base64.decode(publicKey.getBytes());
			X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
			KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
			Key publicK = keyFactory.generatePublic(x509KeySpec);
			Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
			cipher.init(Cipher.DECRYPT_MODE, publicK);
			int inputLen = encryptedData.length;
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int offSet = 0;
			byte[] cache;
			int i = 0;
			// 对数据分段解密
			while (inputLen - offSet > 0) {
				if (inputLen - offSet > MAX_DECRYPT_BLOCK) {
					cache = cipher.doFinal(encryptedData, offSet, MAX_DECRYPT_BLOCK);
				} else {
					cache = cipher.doFinal(encryptedData, offSet, inputLen - offSet);
				}
				out.write(cache, 0, cache.length);
				i++;
				offSet = i * MAX_DECRYPT_BLOCK;
			}
			byte[] decryptedData = out.toByteArray();
			out.close();
			return decryptedData;
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}

	/**
	 * 公钥加密
	 * @param data 源数据
	 * @param publicKey 公钥(BASE64编码)
	 */
	public final static byte[] encryptByPublicKey(byte[] data, String publicKey) {
		try {
			byte[] keyBytes = Base64.decode(publicKey.getBytes());
			X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
			KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
			Key publicK = keyFactory.generatePublic(x509KeySpec);
			// 对数据加密
			Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
			cipher.init(Cipher.ENCRYPT_MODE, publicK);
			int inputLen = data.length;
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int offSet = 0;
			byte[] cache;
			int i = 0;
			// 对数据分段加密
			while (inputLen - offSet > 0) {
				if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
					cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
				} else {
					cache = cipher.doFinal(data, offSet, inputLen - offSet);
				}
				out.write(cache, 0, cache.length);
				i++;
				offSet = i * MAX_ENCRYPT_BLOCK;
			}
			byte[] encryptedData = out.toByteArray();
			out.close();
			return encryptedData;
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}

	/**
	 * 私钥加密
	 * @param data 源数据
	 * @param privateKey 私钥(BASE64编码)
	 */
	public final static byte[] encryptByPrivateKey(byte[] data, String privateKey) {
		try {
			byte[] keyBytes = Base64.decode(privateKey.getBytes());
			PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
			KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
			Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
			Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
			cipher.init(Cipher.ENCRYPT_MODE, privateK);
			int inputLen = data.length;
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			int offSet = 0;
			byte[] cache;
			int i = 0;
			// 对数据分段加密
			while (inputLen - offSet > 0) {
				if (inputLen - offSet > MAX_ENCRYPT_BLOCK) {
					cache = cipher.doFinal(data, offSet, MAX_ENCRYPT_BLOCK);
				} else {
					cache = cipher.doFinal(data, offSet, inputLen - offSet);
				}
				out.write(cache, 0, cache.length);
				i++;
				offSet = i * MAX_ENCRYPT_BLOCK;
			}
			byte[] encryptedData = out.toByteArray();
			out.close();
			return encryptedData;
		} catch (Exception e) {
			logger.error("", e);
			return null;
		}
	}

	/**
	 * 获取私钥
	 * @param keyMap 密钥对
	 */
	public final static String getPrivateKey(Map<String, Object> keyMap) {
		Key key = (Key) keyMap.get(PRIVATE_KEY);
		return new String(Base64.encode(key.getEncoded()));
	}

	/**
	 * 获取公钥
	 * @param keyMap 密钥对
	 */
	public final static String getPublicKey(Map<String, Object> keyMap) {
		Key key = (Key) keyMap.get(PUBLIC_KEY);
		return new String(Base64.encode(key.getEncoded()));
	}

	/**
	 * byteArrayToHexString
	 * @author ruan
	 * @param bytes
	 * @return
	 */
	private final static String byteArrayToHexString(byte[] bytes) {
		StringBuilder buf = new StringBuilder(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			if (((int) bytes[i] & 0xff) < 0x10) {
				buf.append("0");
			}
			buf.append(Long.toString((int) bytes[i] & 0xff, 16));
		}
		buf.trimToSize();
		return buf.toString();
	}
}