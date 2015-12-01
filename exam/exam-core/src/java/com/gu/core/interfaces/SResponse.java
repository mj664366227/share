package com.gu.core.interfaces;

import com.gu.core.util.JSONObject;

public abstract class SResponse {
	public String toString() {
		return JSONObject.encode(this);
	}
}
