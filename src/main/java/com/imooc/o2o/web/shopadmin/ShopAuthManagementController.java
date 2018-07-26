package com.imooc.o2o.web.shopadmin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.imooc.o2o.dto.ShopAuthMapExecution;
import com.imooc.o2o.dto.UserAccessToken;
import com.imooc.o2o.dto.WechatInfo;
import com.imooc.o2o.entity.PersonInfo;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.entity.ShopAuthMap;
import com.imooc.o2o.entity.WechatAuth;
import com.imooc.o2o.enums.ShopAuthMapStateEnum;
import com.imooc.o2o.service.PersonInfoService;
import com.imooc.o2o.service.ShopAuthMapService;
import com.imooc.o2o.service.WechatAuthService;
import com.imooc.o2o.util.CodeUtil;
import com.imooc.o2o.util.HttpServletRequestUtil;
import com.imooc.o2o.util.ShortNetAddressUtil;
import com.imooc.o2o.util.wechat.WechatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: REX
 * @Date: Create in 16:25 2018/7/25
 */
@Controller
@RequestMapping("/shopadmin")
public class ShopAuthManagementController {
	@Autowired
	private ShopAuthMapService shopAuthMapService;
	@Autowired
	private PersonInfoService personInfoService;
	@Autowired
	private WechatAuthService wechatAuthService;

	@RequestMapping(value = "/listshopauthmapsbyshop", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> listShopAuthMapsByShop(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<>();
		int pageIndex = HttpServletRequestUtil.getInt(request, "pageIndex");
		int pageSize = HttpServletRequestUtil.getInt(request, "pageSize");
		// GET INFO FROM SESSION
		Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
		if ((pageIndex > -1) && (pageSize > -1) && (currentShop != null) && (currentShop.getShopId() != null)) {
			ShopAuthMapExecution se = shopAuthMapService.listShopAuthMapByShopId(currentShop.getShopId(), pageIndex, pageSize);
			modelMap.put("shopAuthMapList", se.getShopAuthMapList());
			modelMap.put("count", se.getCount());
			modelMap.put("success", true);
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "EMPTY SIZE || INDEX || SHOP-ID");
		}
		return modelMap;
	}

	@RequestMapping(value = "/getshopauthmapbyid", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> getShopAuthMapById(@RequestParam Long shopAuthId) {
		Map<String, Object> modelMap = new HashMap<>();
		if (shopAuthId != null && shopAuthId > -1) {
			ShopAuthMap shopAuthMap = shopAuthMapService.getShopAuthMapById(shopAuthId);
			modelMap.put("shopAuthMap", shopAuthMap);
			modelMap.put("success", true);
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "EMPTY SHOP-AUTH-ID");
		}
		return modelMap;
	}

	@RequestMapping(value = "/modifyshopauthmap", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> modifyShopAuthMap(String shopAuthMapStr, HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<>();
		// 授权编辑调用(验证码)，还是删除/回复操作(无验证码)
		boolean statusChange = HttpServletRequestUtil.getBoolean(request, "statusChange");
		if (!statusChange && !CodeUtil.checkVerifyCode(request)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "验证码错误");
			return modelMap;
		}
		ObjectMapper mapper = new ObjectMapper();
		ShopAuthMap shopAuthMap = null;
		try {
			// 前台传入的字符串JSON转换成实例
			shopAuthMap = mapper.readValue(shopAuthMapStr, ShopAuthMap.class);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.toString());
			return modelMap;
		}
		if (shopAuthMap != null && shopAuthMap.getShopAuthId() != null) {
			try {
				// 被操作对象（店家不可修改）
				if (!checkPermission(shopAuthMap.getShopAuthId())) {
					modelMap.put("success", false);
					modelMap.put("errMsg", "店家本身不可做授权修改");
					return modelMap;
				}
				ShopAuthMapExecution se = shopAuthMapService.modifyShopAuthMap(shopAuthMap);
				if (se.getState() == ShopAuthMapStateEnum.SUCCESS.getState()) {
					modelMap.put("success", true);
				} else {
					modelMap.put("success", false);
					modelMap.put("errMsg", se.getStateInfo());
				}
			} catch (RuntimeException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.toString());
				return modelMap;
			}
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请输入修改的授权信息");
		}
		return modelMap;
	}

	private boolean checkPermission(Long shopAuthId) {
		ShopAuthMap grantedPerson = shopAuthMapService.getShopAuthMapById(shopAuthId);
		return grantedPerson.getTitleFlag() != 0;
	}

	private static String urlPrefix;
	private static String urlMiddle;
	private static String urlSuffix;
	private static String authUrl;

	@Value("${wechat.prefix}")
	public void setUrlPrefix(String urlPrefix) {
		ShopAuthManagementController.urlPrefix = urlPrefix;
	}

	@Value("${wechat.middle}")
	public void setUrlMiddle(String urlMiddle) {
		ShopAuthManagementController.urlMiddle = urlMiddle;
	}

	@Value("${wechat.suffix}")
	public void setUrlSuffix(String urlSuffix) {
		ShopAuthManagementController.urlSuffix = urlSuffix;
	}

	@Value("${wechat.auth.url}")
	public void setAuthUrl(String authUrl) {
		ShopAuthManagementController.authUrl = authUrl;
	}

	/**
	 * 生成带有URL的二维码
	 *
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/generateqrcode4shopauth", method = RequestMethod.GET)
	@ResponseBody
	private void generateQRCode4ShopAuth(HttpServletRequest request, HttpServletResponse response) {
		Shop shop = (Shop) request.getSession().getAttribute("currentShop");
		if (shop != null && shop.getShopId() != null) {
			long timeStamp = System.currentTimeMillis();
			String content = "{aaashopIdaaa:" + shop.getShopId() + ",aaacreateTimeaaa:" + timeStamp + "}";
			try {
				String longUrl = urlPrefix + authUrl + urlMiddle + URLEncoder.encode(content, "UTF-8") + urlSuffix;
				String shortUrl = ShortNetAddressUtil.getShortURL(longUrl);
				BitMatrix QRCodeImg = CodeUtil.generateQRCodeStream(shortUrl, response);
				assert QRCodeImg != null;
				MatrixToImageWriter.writeToStream(QRCodeImg, "png", response.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 根据微信传回的参数，添加店铺的授权信息
	 *
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value = "/addshopauthmap", method = RequestMethod.GET)
	private String addShopAuthMap(HttpServletRequest request, HttpServletResponse response) throws IOException {
		WechatAuth auth = getEmployeeInfo(request);
		if (auth != null) {
			PersonInfo user = personInfoService.getPersonInfoById(auth.getPersonInfo().getUserId());
			request.getSession().setAttribute("user", user);
			String qrCodeInfo = new String(URLDecoder.decode(HttpServletRequestUtil.getString(request, "state"), "UTF-8"));
			ObjectMapper mapper = new ObjectMapper();
			WechatInfo wechatInfo = null;
			try {
				wechatInfo = mapper.readValue(qrCodeInfo.replace("aaa", "\""), WechatInfo.class);
			} catch (Exception e) {
				return "shop/operationfail";
			}
			// 校验二维码是否过期
			if (!checkQRCodeInfo(wechatInfo)) {
				return "shop/operationfail";
			}
			// 去重校验 获取店铺下所有授权信息
			ShopAuthMapExecution allMapList = shopAuthMapService.listShopAuthMapByShopId(wechatInfo.getShopId(),0,500);
			List<ShopAuthMap> shopAuthList = allMapList.getShopAuthMapList();
			for(ShopAuthMap sm:shopAuthList){
				if(sm.getEmployee().getUserId() == user.getUserId())
					return "shop/operationfail";
			}
			// 根据获取的内容，添加微信授权信息
			try{
				ShopAuthMap shopAuthMap = new ShopAuthMap();
				Shop shop = new Shop();
				shop.setShopId(wechatInfo.getShopId());
				shopAuthMap.setShop(shop);
				shopAuthMap.setEmployee(user);
				shopAuthMap.setTitle("员工");
				shopAuthMap.setTitleFlag(1);
				ShopAuthMapExecution se = shopAuthMapService.addShopAuthMap(shopAuthMap);
				if(se.getState() == ShopAuthMapStateEnum.SUCCESS.getState()){
					return "shop/operationsuccess";
				}else{
					return "shop/operationfail";
				}
			}catch (RuntimeException e){
				return "shop/operationfail";
			}
		}
		return "shop/operationfail";
	}

	private WechatAuth getEmployeeInfo(HttpServletRequest request) {
		String code = request.getParameter("code");
		WechatAuth auth = null;
		if (code != null) {
			UserAccessToken token;
			try {
				token = WechatUtil.getUserAccessToken(code);
				String openId = token.getOpenId();
				request.getSession().setAttribute("openId", openId);
				auth = wechatAuthService.getWechatAuthByOpenId(openId);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return auth;
	}

	/**
	 * 根据二维码所携带的CreateTime判断是否过期
	 *
	 * @param wechatInfo
	 * @return
	 */
	private boolean checkQRCodeInfo(WechatInfo wechatInfo) {
		if (wechatInfo != null && wechatInfo.getShopId() != null && wechatInfo.getCreateTime() != null) {
			long nowTime = System.currentTimeMillis();
			if ((nowTime - wechatInfo.getCreateTime()) <= 600000) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

}
