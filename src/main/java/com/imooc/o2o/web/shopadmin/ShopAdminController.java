package com.imooc.o2o.web.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 路由转发规则
 *
 * @Author:REX
 * @Date: Create in 0:38 2018/3/18
 */
@Controller
@RequestMapping(value = "shopadmin", method = {RequestMethod.GET})
public class ShopAdminController {

	@RequestMapping(value = "/shopoperation")
	public String shopOperation() {
		return "shop/shopoperation";
	}

	@RequestMapping(value = "/shoplist")
	public String shopList() {
		//转发到店铺列表页面
		return "shop/shoplist";
	}

	@RequestMapping(value = "/shopmanagement")
	public String shopManagement() {
		//转发到店铺管理页面
		return "shop/shopmanagement";
	}

	@RequestMapping(value = "/productcategorymanagement",method = RequestMethod.GET)
	public String productCategoryManagement(){
		//转发到商品类别管理页面
		return "shop/productcategorymanagement";
	}

	@RequestMapping(value = "/productoperation")
	public String productOperation() {
		//转发到商品添加/编辑页面
		return "shop/productoperation";
	}

	@RequestMapping(value = "/productmanagement")
	public String productManagement(){
		// 转发至商品管理页面
		return "shop/productmanagement";
	}

	@RequestMapping(value = "/shopauthmanagement")
	public String shopAuthManagement(){
		// 转发店铺授权页面
		return "shop/shopauthmanagement";
	}

	@RequestMapping(value = "/shopauthedit")
	public String shopAuthEdit(){
		// 转发授权修改页面
		return "shop/shopauthedit";
	}

	@RequestMapping(value = "/operationsuccess",method = RequestMethod.GET)
	private String operationSuccess(){
		// 转发二维码授权操作成功页面
		return "shop/operationsuccess";
	}

	@RequestMapping(value = "/operationfail",method = RequestMethod.GET)
	private String operationFail(){
		// 转发二维码授权操作失败页面
		return "shop/operationfail";
	}

	@RequestMapping(value = "/productbuycheck",method = RequestMethod.GET)
	private String productBuyCheck(){
		// 转发店铺消费记录的页面
		return "shop/productbuycheck";
	}
}
