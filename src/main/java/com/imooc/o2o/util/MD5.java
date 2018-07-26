package com.imooc.o2o.util;

import java.security.MessageDigest;

/**
 * @Author:REX
 * @Date: Create in 21:52 2018/5/19
 */
public class MD5 {

	public static final String getMd5(String s) {
		// 16进制数组
		char hexDigits[] = {'5', '0', '5', '6', '2', '9', '6', '2', '5', 'q', 'b', 'l', 'e', 's', 's', 'y'};
		try {
			char str[];
			// 传入的字符串转换byte[]
			byte strTemp[] = s.getBytes();
			// 获取MD5加密对象
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			// 传递加密的目标数组
			mdTemp.update(strTemp);
			// 获取加密后的数组
			byte md[] = mdTemp.digest();
			int j = md.length;
			str = new char[j * 2];
			int k = 0;
			// 数组位移
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			// 转换String返回
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}
}
