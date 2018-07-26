package com.imooc.o2o.util.wechat;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.imooc.o2o.dto.UserAccessToken;
import com.imooc.o2o.dto.WechatUser;
import com.imooc.o2o.entity.PersonInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import java.io.*;
import java.net.ConnectException;
import java.net.URL;

/**
 * 提交https请求给微信获取用户信息
 * @Author:REX
 * @Date: Create in 23:14 2018/5/4
 */
public class WechatUtil {
	private static Logger log = LoggerFactory.getLogger(WechatUtil.class);

	public static UserAccessToken getUserAccessToken(String code) throws IOException{
		String appid = "wxecdc5fea07104459";
		String appsecret = "a4d139231f9b2de9133ae7f19072f9c9";
		log.debug("appid : " + appid);
		log.debug("appsecret : " + appsecret);
		// 根据传入的code 拼接访问的URL
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token"
				+ "?appid=" + appid
				+ "&secret=" + appsecret
				+ "&code=" + code
				+ "&grant_type=authorization_code";
		// 向URL请求 获取token
		String tokenStr = httpsRequest(url,"GET",null);
		log.debug("USER_ACCESS_TOKEN : " + tokenStr);
		UserAccessToken token = new UserAccessToken();
		ObjectMapper objectMapper = new ObjectMapper();
		try{
			// json转换为相应对象
			token = objectMapper.readValue(tokenStr,UserAccessToken.class);
		}catch (JsonParseException e){
			log.error("获取用户access_token失败 : " + e.getMessage());
			e.printStackTrace();
		}catch (JsonMappingException e){
			log.error("获取用户access_token失败 : " + e.getMessage());
			e.printStackTrace();
		}catch (IOException e){
			log.error("获取用户access_token失败 : " + e.getMessage());
			e.printStackTrace();
		}
		if(token == null){
			log.error("获取用户access_token失败" );
			return  null;
		}
		return token;
	}


	/**
	 * 获取WechatUser实体类
	 * @param accessToken
	 * @param openId
	 * @return
	 */
	public static WechatUser getUserInfo(String accessToken,String openId){
		String url = "https://api.weixin.qq.com/sns/userinfo" +
				"?access_token=" + accessToken
				+ "&openid=" + openId + "&lang=zh_CN";
		String userStr = httpsRequest(url,"GET",null);
		log.debug("USER INFO : " + userStr);
		WechatUser user = new WechatUser();
		ObjectMapper objectMapper = new ObjectMapper();
		try{
			// json转换为相应对象
			user = objectMapper.readValue(userStr,WechatUser.class);
		}catch (JsonParseException e){
			log.error("获取用户信息失败 : " + e.getMessage());
			e.printStackTrace();
		}catch (JsonMappingException e){
			log.error("获取用户信息失败 : " + e.getMessage());
			e.printStackTrace();
		}catch (IOException e){
			log.error("获取用户信息失败 : " + e.getMessage());
			e.printStackTrace();
		}
		if(user == null){
			log.error("获取用户信息失败");
			return null;
		}
		return user;

	}

	/**
	 * 发起https请求并获取结果
	 * @param requestUrl 请求地址
	 * @param requestMethod 请求方式（GET POST）
	 * @param outputStr 提交的数据
	 * @return json
	 */
	public static String httpsRequest(String requestUrl, String requestMethod, String outputStr) {
		StringBuffer buffer = new StringBuffer();
		try {
			// 创建SSL Context对象 并使用指定的信任管理器初始化
			TrustManager[] tm = {new MyX509TrustManager()};
			SSLContext sslContext = SSLContext.getInstance("SSL","SunJSSE");
			sslContext.init(null,tm,new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();

			URL url = new URL(requestUrl);
			HttpsURLConnection httpUrlConn = (HttpsURLConnection)url.openConnection();
			httpUrlConn.setSSLSocketFactory(ssf);
			httpUrlConn.setRequestMethod(requestMethod);

			if(requestMethod.equals("GET")){
				httpUrlConn.connect();
			}

			// 存在数据
			if(outputStr != null){
				OutputStream outputStream = httpUrlConn.getOutputStream();
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}

			// 返回的输入流转换字符串
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			String str = null;
			while ((str = bufferedReader.readLine()) != null){
				buffer.append(str);
			}
			// 释放资源
			bufferedReader.close();
			inputStreamReader.close();
			inputStream.close();
			inputStream = null;
			httpUrlConn.disconnect();
			log.debug("HTTPS BUFFER : " + buffer.toString());
		}catch (ConnectException e){
			log.error("WECHAT SERVER CONNECTION TIMED OUT");
		}catch (Exception e){
			log.error("HTTPS REQUEST ERROR : {}" ,e);
		}
		return buffer.toString();
	}

	public static PersonInfo getPersonInfoFromRequest(WechatUser user){
		PersonInfo personInfo = new PersonInfo();
		personInfo.setName(user.getNickname());
		personInfo.setGender(user.getSex()+"");
		personInfo.setProfileImg(user.getHeadimgurl());
		personInfo.setEnableStatus(1);
		return personInfo;

	}


}
