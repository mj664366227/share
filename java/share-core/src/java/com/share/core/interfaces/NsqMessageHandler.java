package com.share.core.interfaces;

/**
 * nsq消息处理
 * @author ruan
 *
 */
public interface NsqMessageHandler {
	/**
	 * 如果处理成功，返回true，否则返回false
	 * @author ruan 
	 * @param message
	 */
	public boolean handle(byte[] message);
}