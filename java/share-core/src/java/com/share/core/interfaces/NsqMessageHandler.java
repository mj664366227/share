package com.share.core.interfaces;

/**
 * nsq消息处理
 * @author ruan
 *
 */
public interface NsqMessageHandler {
	public void handle(byte[] message);
}