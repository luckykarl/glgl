package com.guanshaoye.mylibrary.http;

import java.security.MessageDigest;

public class MD5 {
	//encode
	private static final String ALGORITHM = "MD5";
	// 16进制格式
	private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
	public static String MD(String str) {
		return encode(ALGORITHM, str);
	}
	// 加密
	public static String encode(String algorithm, String str) {
		if (str == null) {
			return null;
		}
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
			messageDigest.update(str.getBytes());
			return getFormattedText(messageDigest.digest());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	//加密格式
	private static String getFormattedText(byte[] bytes){
		int len = bytes.length;
		StringBuilder buf = new StringBuilder(len * 2);
		//把密文转换成十六进制的字符串形式
		for (int j = 0; j < len; j++) {
			buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
			buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
		}
		return buf.toString();
	}

}
