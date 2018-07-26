package com.imooc.o2o.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.*;

/**
 * @Author: REX
 * @Date: Create in 21:17 2018/7/25
 */
public class ShortNetAddressUtil {
	private static Logger log = LoggerFactory.getLogger(ShortNetAddressUtil.class);
	public static int TIMEOUT = 30 * 1000;
	public static String ENCDOING = "UTF-8";

	/**
	 * 根据传入的URL，通过访问百度短视频的接口，将其转换成短URL
	 * @param originURL
	 * @return
	 */
	public static String getShortURL(String originURL){
		String tinyURL = null;
		try{
			// 指定百度短链接的接口
			URL url = new URL("http://dwz.cn/create.php");
			// 建立连接
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			// 设置连接参数
			// 使用连接进行输出
			connection.setDoOutput(true);
			// 使用连接进行输入
			connection.setDoInput(true);
			// 不使用缓存
			connection.setUseCaches(false);
			// 超时为30秒
			connection.setConnectTimeout(TIMEOUT);
			// 请求为POST
			connection.setRequestMethod("POST");
			// 设置POST信息，传入原始URL
			String postData = URLEncoder.encode(originURL,"UTF-8");
			// 输出原始URL
			connection.getOutputStream().write(("url=" + postData).getBytes());
			// 连接短视频接口
			connection.connect();
			// 获取返回的字符串
			String responseStr = getResponseStr(connection);
			log.info("RESPONSE STRING : " + responseStr);
			// 字符串中获取TINYURL 即短连接
			tinyURL = getValueByKey(responseStr,"tinyurl");
			log.info("TINY URL : " + tinyURL);
			// 关闭连接
			connection.disconnect();
		} catch (IOException e) {
			log.error(e.toString());
		}
		return tinyURL;
	}

	/**
	 * 从HTTP请求中获取返回的字符串
	 * @param connection
	 * @return
	 * @throws IOException
	 */
	private static String getResponseStr(HttpURLConnection connection) throws IOException{
		StringBuffer result = new StringBuffer();
		// 连接中获取HTTP状态码
		int responseCode = connection.getResponseCode();
		if(responseCode == HttpURLConnection.HTTP_OK){
			InputStream in = connection.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in,ENCDOING));
			String inputLine = "";
			while((inputLine = reader.readLine()) != null){
				result.append(inputLine);
			}
		}
		return String.valueOf(result);
	}

	/**
	 * JSON根据传入的KEY获取VALUE
	 * @param replyText
	 * @param key
	 * @return
	 */
	private static String getValueByKey(String replyText,String key){
		ObjectMapper mapper = new ObjectMapper();
		JsonNode node;
		String targetValue = null;
		try {
			// 把调用返回的信息串转换为JSON对象
			node = mapper.readTree(replyText);
			// 根据KEY从JSON中获取对于的值
			targetValue = node.get(key).asText();
		} catch (IOException e) {
			log.error("GET VALUE_BY_KEY_ERROR : " + e.toString());
		}
		return targetValue;
	}

	public static void main(String[] args) {
		getShortURL("https://mp.weixin.qq.com/debug/cgi-bin/sandbox?t=sandbox/login");
	}
}
