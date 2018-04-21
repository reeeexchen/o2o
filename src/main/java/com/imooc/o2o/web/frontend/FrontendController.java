package com.imooc.o2o.web.frontend;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author:REX
 * @Date: Create in 20:58 2018/4/20
 */
@Controller
@RequestMapping("/frontend")
public class FrontendController {
	// 路由转发规则
	@RequestMapping(value = "/index",method = RequestMethod.GET)
	private String index(){
		return "frontend/index";
	}

	@RequestMapping(value = "/shopdetail",method = RequestMethod.GET)
	private String showShopDetail(){
		return "frontend/shopdetail";
	}

	@RequestMapping(value = "/productdetail" ,method = RequestMethod.GET)
	private String showProductDetail(){
		return "frontend/productdetail";
	}

	@RequestMapping(value = "/shoplist",method = RequestMethod.GET)
	private String shopShopList(){
		return "frontend/shoplist";
	}
}
