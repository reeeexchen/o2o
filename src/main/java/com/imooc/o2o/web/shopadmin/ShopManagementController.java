package com.imooc.o2o.web.shopadmin;

import com.imooc.o2o.util.HttpServletRequestUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author:REX
 * @Date: Create in 23:57 2018/3/17
 */
@Controller
@RequestMapping("/shopadmin")
public class ShopManagementController {

	@RequestMapping(value = "/registershop" ,method = RequestMethod.POST)
	@ResponseBody
	private Map<String,Object> registerShop(HttpServletRequest request){
		Map<String,Object> modelMap = new HashMap<String, Object>();
		//1.接受并转化相应的参数,包括店铺信息以及图片信息
		String shopStr = HttpServletRequestUtil.getString(request,"shopStr");

		//2.注册店铺
		//3.返回结果
	}
}
