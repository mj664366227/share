package com.share.core.ssdb;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ssdb分布式锁
 * @author ruan
 *
 */
public class SSDBDistributedLock implements Lock {
	/**
	 * logger
	 */
	private final static Logger logger = LoggerFactory.getLogger(SSDBDistributedLock.class);
	/**
	 * 锁名
	 */
	private String lockName = "L:";
	/**
	 * 锁的值
	 */
	private static final String lockValue = "1";
	/**
	 * ssdb对象
	 */
	private SSDB ssdb;

	/**
	 * 构造函数
	 * @param ssdb ssdb对象
	 */
	public SSDBDistributedLock(SSDB ssdb, String lockName) {
		this.ssdb = ssdb;
		this.lockName += lockName;
	}

	/**
	 * 锁
	 */
	public void lock() {
		while (true) {
			if (tryLock()) {
				return;
			}
			logger.warn("waiting to get lock {}", lockName);
		}
	}

	/**
	 * 锁
	 */
	public void lockInterruptibly() throws InterruptedException {
		if (!tryLock()) {
			throw new InterruptedException("failed to get lock " + lockName);
		}
	}

	/**
	 * 尝试锁
	 */
	public boolean tryLock() {
		return ssdb.KV.setnx(lockName, lockValue);
	}

	/**
	 * 尝试锁
	 */
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		long timeout = unit.toMillis(time);
		long now = System.currentTimeMillis();
		while (true) {
			if (tryLock()) {
				return true;
			}
			if (System.currentTimeMillis() - now > timeout) {
				logger.warn("try to get lock '{}' timeout!", lockName);
				return false;
			}
		}
	}

	/**
	 * 解锁
	 */
	public void unlock() {
		ssdb.KV.del(lockName);
	}

	@Deprecated
	public Condition newCondition() {
		return null;
	}
}