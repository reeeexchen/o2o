package com.imooc.o2o.util;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

/**
 * @Author:REX
 * @Date: Create in 23:16 2018/5/13
 */
public class EncryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {
	// 加密的字段数组
	private String [] encryptPropNames = {"jdbc.username","jdbc.password"};

	@Override
	protected String convertProperty(String propertyName, String propertyValue) {
		if(isEncryptProp(propertyName)){
			// DECODE
			String decryptValue = DESUtil.getDcryptString(propertyValue);
			return decryptValue;
		}else{
			return propertyValue;
		}
	}

	private boolean isEncryptProp(String propertyName) {
		for(String enecyptPropertyName : encryptPropNames){
			if(enecyptPropertyName.equals(propertyName)){
				return true;
			}
		}
		return false;
	}
}
