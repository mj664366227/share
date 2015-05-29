package com.share.test;

public class Test {
	public static void main(String[] args) throws Exception {
		long t = System.nanoTime();
		Thread.sleep(11000);
		System.err.println(t);
		System.err.println(System.nanoTime());
	}
}