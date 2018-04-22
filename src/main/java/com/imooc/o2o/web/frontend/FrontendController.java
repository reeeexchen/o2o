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

	/**
	 * 首页路由
	 * @return
	 */
	@RequestMapping(value = "/index",method = RequestMethod.GET)
	private String index(){
		return "frontend/index";
	}

	/**
	 * 店铺详情页路由
	 * @return
	 */
	@RequestMapping(value = "/shopdetail",method = RequestMethod.GET)
	private String showShopDetail(){
		return "frontend/shopdetail";
	}

	/**
	 * 商品详情路由
	 * @return
	 */
	@RequestMapping(value = "/productdetail" ,method = RequestMethod.GET)
	private String showProductDetail(){
		return "frontend/productdetail";
	}

	/**
	 * 商品列表路由
	 * @return
	 */
	@RequestMapping(value = "/shoplist",method = RequestMethod.GET)
	private String shopShopList(){
		return "frontend/shoplist";
	}
}
