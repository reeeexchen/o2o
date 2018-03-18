package com.imooc.o2o.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author:REX
 * @Date: Create in 15:54 2018/3/18
 */
public class CodeUtil {
	public static boolean checkVerifyCode(HttpServletRequest request){
		String verifyCodeExpected = (String)request.getSession().getAttribute(
				com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		String verifyCodeActual = HttpServletRequestUtil.getString(request,"verifyCodeActual");
		if(verifyCodeActual == null
				|| !verifyCodeActual.equalsIgnoreCase(verifyCodeExpected)){
			return false;
		}
		return true;
	}
}
