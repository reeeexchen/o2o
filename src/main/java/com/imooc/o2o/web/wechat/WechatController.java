package com.imooc.o2o.web.wechat;

import com.imooc.o2o.util.wechat.SignUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author:REX
 * @Date: Create in 21:00 2018/5/1
 */
@Controller
@RequestMapping("wechat")
public class WechatController {
	private static Logger log = LoggerFactory.getLogger(WechatController.class);

	@RequestMapping(method = {RequestMethod.GET})
	public void doGet(HttpServletRequest request, HttpServletResponse response){
		log.debug("WECHAT GET ---");
		// 微信加密签名 signature结合开发者填写的token参数和请求中的timestamp nonce
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");
		// 通过校验signature 对请求进行校验 若成功则鸳鸯返回echostr 表示接入成功 否则接入失败
		PrintWriter out = null;
		try{
			out = response.getWriter();
			if(SignUtil.checkSignature(signature,timestamp,nonce)){
				log.debug("WECHAT GET SUCCESS ---");
				out.print(echostr);
			}
		}catch (IOException e){
			e.printStackTrace();
		}finally {
			if(out != null){
				out.close();
			}
		}
	}


}
