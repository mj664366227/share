package com.share.test;

import java.util.HashMap;

import com.share.core.util.RandomUtil;
import com.share.core.util.Time;
import com.share.test.timer.DemoTimer;

public class Test {
	public static void main(String[] args) throws Exception {
		Class<?> cla = DemoTimer.class;
		HashMap<String, String> map = new HashMap<String, String>();
		int max = 5000;
		for (int i = 0; i < max; i++) {
			map.put(String.valueOf(System.nanoTime()+RandomUtil.rand(10000000, 999999999)), String.valueOf(System.nanoTime()));
		}

		long t = System.nanoTime();
		for (int i = 0; i < max; i++) {
			 cla.getMethod("demoTimer");
		}
		System.err.println(Time.showTime(System.nanoTime() - t));

		t = System.nanoTime();
		for (int i = 0; i < max; i++) {
			map.get("a");
		}
		System.err.println(Time.showTime(System.nanoTime() - t));
	}
}