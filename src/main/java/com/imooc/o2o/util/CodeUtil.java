package com.imooc.o2o.util;

import com.google.code.kaptcha.Constants;
import com.google.zxing.*;
import com.google.zxing.common.BitMatrix;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author:REX
 * @Date: Create in 15:54 2018/3/18
 */
public class CodeUtil {
	/**
	 * 验证码检验
	 * @param request
	 * @return
	 */
	public static boolean checkVerifyCode(HttpServletRequest request) {
		String verifyCodeExpected = (String) request.getSession()
				.getAttribute(Constants.KAPTCHA_SESSION_KEY);
		String verifyCodeActual = HttpServletRequestUtil.getString(request, "verifyCodeActual");
		if (verifyCodeActual == null || !verifyCodeActual.equalsIgnoreCase(verifyCodeExpected)) {
			return false;
		}
		return true;
	}


	public static BitMatrix generateQRCodeStream(String content, HttpServletResponse resp){
		// 响应信息
		resp.setHeader("Cache-Controle","no-store");
		resp.setHeader("Pragma","no-cache");
		resp.setDateHeader("Expires",0);
		resp.setContentType("image/png");
		// 图片的文字编码以及内边
		Map<EncodeHintType,Object> hints = new HashMap<>();
		hints.put(EncodeHintType.CHARACTER_SET,"UTF-8");
		hints.put(EncodeHintType.MARGIN,0);
		BitMatrix bitMatrix;
		try{
			// 参数包括：编码内容，编码类型，图片宽度，图片高度，设置参数
			bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE,300,300,hints);
		}catch (WriterException e){
			e.printStackTrace();
			return null;
		}
		return bitMatrix;
	}
}
