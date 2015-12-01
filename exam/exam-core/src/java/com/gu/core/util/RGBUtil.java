package com.gu.core.util;

import java.awt.Color;

/**
 * rgb工具
 * @author ruan
 */
public final class RGBUtil {
	private RGBUtil() {
	}

	/**
	 * 把rgb3元色转成16进制
	 * @author ruan 
	 * @param r
	 * @param g
	 * @param b
	 */
	public final static String rgb2BrowserHex(int r, int g, int b) {
		Color color = new Color(r, g, b);
		return toHex(color.getRed(), color.getGreen(), color.getBlue());
	}

	private final static String toHex(int r, int g, int b) {
		return "#" + toBrowserHexValue(r) + toBrowserHexValue(g) + toBrowserHexValue(b);
	}

	private final static String toBrowserHexValue(int number) {
		StringBuilder builder = new StringBuilder(Integer.toHexString(number & 0xff));
		while (builder.length() < 2) {
			builder.append("0");
		}
		return builder.toString().toUpperCase();
	}
}