package com.imooc.o2o.util.wechat;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * @Author:REX
 * @Date: Create in 17:01 2018/5/1
 */

// 微信请求校验工具类
public class SignUtil {
	// 与接口配置信息中的token信息一致
	private static String token = "o2o";

	/**
	 *  验证签名
	 * @param signature
	 * @param timestamp
	 * @param nonce
	 * @return
	 */
	public static boolean checkSignature(String signature,String timestamp,String nonce){
		String [] arr = new String[]{token,timestamp,nonce};
		// 将token timestamp nonce三个参数进行字典序排序
		Arrays.sort(arr);
		StringBuilder content = new StringBuilder();
		for(int i = 0;i < arr.length;i++){
			content.append(arr[i]);
		}
		MessageDigest md = null;
		String tmpStr = null;

		try{
			md = MessageDigest.getInstance("SHA-1");
			// SHA-1加密
			byte[] digest = md.digest(content.toString().getBytes());
			tmpStr = byteToStr(digest);
		}catch (NoSuchAlgorithmException e){
			e.printStackTrace();
		}
		content = null;
		// 将SHA-1加密后 与 signature对比 标识该请求来源微信
		return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;

	}

	/**
	 * 字节数组转换为十六进制字符串
	 * @param bytes
	 * @return
	 */
	private static String byteToStr(byte[] bytes){
		String strDigest = "";
		for(int i = 0;i < bytes.length;i++){
			strDigest += byteToHexStr(bytes[i]);
		}
		return strDigest;
	}

	private static String byteToHexStr(byte Byte){
		char[] Digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		char[] tempArr = new char[2];
		tempArr[0] = Digit[(Byte >>> 4) & 0X0F];
		tempArr[1] = Digit[Byte & 0X0F];
		String s = new String(tempArr);
		return s;
	}
}
