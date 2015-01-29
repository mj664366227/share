package com.share.test;

import java.io.IOException;

import com.google.gson.reflect.TypeToken;
import com.share.core.util.JSONObject;

public class Test {
	public static void main(String[] args) throws IOException {
		String json = "{\"a\":\"fdfdfd\"}";
		A a = JSONObject.decode(json, new TypeToken<A>() {
		}.getType());
		System.err.println(a);
	}

}

class A {
	private String a;
	private int b;

	public String getA() {
		return a;
	}

	public void setA(String a) {
		this.a = a;
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}
}