package com.exam.core.interfaces;

import com.exam.core.util.JSONObject;

public abstract class SResponse {
	public String toString() {
		return JSONObject.encode(this);
	}
}
