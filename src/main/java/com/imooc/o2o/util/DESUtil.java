package com.imooc.o2o.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.security.Key;
import java.security.SecureRandom;

/**
 * DES对称加密算法
 * @Author:REX
 * @Date: Create in 22:56 2018/5/13
 */
public class DESUtil {
	private static Key key;
	// 设置密钥
	private static String KEY_STR = "o2oKey";
	private static String CHARSETNAME = "UTF-8";
	private static String ALGORITHM = "DES";

	static {
		try {
			// 生成DES算法对象
			KeyGenerator generator = KeyGenerator.getInstance(ALGORITHM);
			// 运用SHA1安全策略
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
			// 设置密钥种子
			secureRandom.setSeed(KEY_STR.getBytes());
			// 初始化基于SHA1的算法对象
			generator.init(secureRandom);
			// 生成密钥对象
			key = generator.generateKey();
			generator = null;
		}catch (Exception e){
			throw new RuntimeException(e);
		}
	}

	/**
	 * 获取加密后信息
	 * @param str
	 * @return
	 */
	public static String getEncrypString(String str){
		// 基于BASE64编码 接受byte[] 转换成 String
		BASE64Encoder base64Encoder = new BASE64Encoder();
		try{
			// UTF编码
			byte[] bytes = str.getBytes(CHARSETNAME);
			// 获取加密对象
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			// 初始化密码信息
			cipher.init(Cipher.ENCRYPT_MODE,key);
			// 加密
			byte[] doFinal = cipher.doFinal(bytes);
			// ENCODE成String 并返回
			return base64Encoder.encode(doFinal);
		}catch (Exception e){
			throw new RuntimeException(e);
		}
	}

	public static String getDcryptString(String str){
		// 基于BASE64编码 接受byte[] 转换成 String
		BASE64Decoder base64Decoder = new BASE64Decoder();
		try {
			// 将String DECODE byte[]
			byte[] bytes = base64Decoder.decodeBuffer(str);
			// 获取解密对象
			Cipher cipher = Cipher.getInstance(ALGORITHM);
			// 初始化解密信息
			cipher.init(Cipher.DECRYPT_MODE,key);
			// 解密
			byte[] doFinal = cipher.doFinal(bytes);
			// 返回解密信息
			return new String(doFinal,CHARSETNAME);
		}catch (Exception e){
			throw new RuntimeException(e);
		}
	}


}
