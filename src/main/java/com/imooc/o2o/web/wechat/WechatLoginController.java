package com.imooc.o2o.web.wechat;

import com.imooc.o2o.dto.UserAccessToken;
import com.imooc.o2o.dto.WechatAuthExecution;
import com.imooc.o2o.dto.WechatUser;
import com.imooc.o2o.entity.PersonInfo;
import com.imooc.o2o.entity.WechatAuth;
import com.imooc.o2o.enums.WechatAuthStateEnum;
import com.imooc.o2o.service.PersonInfoService;
import com.imooc.o2o.service.WechatAuthService;
import com.imooc.o2o.util.wechat.WechatUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author:REX
 * @Date: Create in 22:41 2018/5/4
 */
@Controller
@RequestMapping("wechatlogin")
public class WechatLoginController {

	// 获取已关注此微信号的用户信息并做相应处理
	// 访问连接 请求地址：
	// https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxecdc5fea07104459&redirect_uri=http://o2o.rextech.top/o2o/wechatlogin/logincheck&role_type=1&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect
	// 获取code 通过code获取access_token 获取用户信息

	private static Logger log = LoggerFactory.getLogger(WechatLoginController.class);
	private static final String FRONTEND = "1";
	private static final String SHOPEND = "2";

	@Autowired
	private PersonInfoService personInfoService;

	@Autowired
	private WechatAuthService wechatAuthService;


	@RequestMapping(value = "/logincheck",method = {RequestMethod.GET})
	public String doGet(HttpServletRequest request, HttpServletResponse response){
		log.debug("WECHAT LOGIN GET--");
		// 获取公众号传过来的code 通过code 获取access_token 获取用户信息
		String code = request.getParameter("code");
		// state用于传递自定义信息 方便程序调用 也可以不使用
		String roleType = request.getParameter("state");
		log.debug("WECHAT LOGIN CODE : " + code);
		WechatUser user = null;
		String openId = null;
		// 创建微信对象
		WechatAuth wechatAuth = null;

		if(code != null){
			UserAccessToken token;
			try{
				// 通过code获取aaccess_token
				token = WechatUtil.getUserAccessToken(code);
				log.debug("WECHAT LOGIN TOKEN : " + token.toString());
				// 通过token获取access_token
				String accessToken = token.getAccessToken();
				// 通过token获取openId
				openId = token.getOpenId();
				// 通过access_token和openId获取用户信息
				user = WechatUtil.getUserInfo(accessToken,openId);
				log.debug("WECHAT LOGIN USER : " + user.toString());
				request.getSession().setAttribute("openId",openId);
				// 获取微信用户信息
				wechatAuth = wechatAuthService.getWechatAuthByOpenId(openId);
			}catch (IOException e){
				log.error("ERROR IN GET_USER_ACCESS_TOKEN OR GET_USER_INFO OR FIND_BY_OPENID");
				e.printStackTrace();
			}
		}
		// 前面获取到openId后，可以通过它去数据库判断该微信帐号是否在我们网站里有对应的帐号了
		// 没有的话这里可以自动创建上，直接实现微信与网站的无缝对接
		if(wechatAuth == null){
			PersonInfo personInfo = WechatUtil.getPersonInfoFromRequest(user);
			wechatAuth = new WechatAuth();
			wechatAuth.setOpenId(openId);
			// 用户类型 1-用户 2-店家
			if(FRONTEND.equals(roleType)){
				personInfo.setUserType(1);
			}else{
				personInfo.setUserType(2);
			}
			wechatAuth.setPersonInfo(personInfo);
			WechatAuthExecution we = wechatAuthService.register(wechatAuth);
			if(we.getState() != WechatAuthStateEnum.SUCCESS.getState()){
				// 创建用户失败
				return null;
			}else{
				// 创建用户成功
				personInfo = personInfoService.getPersonInfoById(wechatAuth.getPersonInfo().getUserId());
				request.getSession().setAttribute("user",personInfo);
			}
		}
		// 用户点击公众号中不同的按钮 跳转不同页面
		if(FRONTEND.equals(roleType)){
			return "frontend/index";
		}else{
			return "shopadmin/shoplist";
		}
	}
}
