package com.imooc.o2o.web.frontend;

import com.imooc.o2o.dto.ProductExecution;
import com.imooc.o2o.entity.Product;
import com.imooc.o2o.entity.ProductCategory;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.service.ProductCategoryService;
import com.imooc.o2o.service.ProductService;
import com.imooc.o2o.service.ShopService;
import com.imooc.o2o.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:REX
 * @Date: Create in 11:02 2018/4/22
 */
@Controller
@RequestMapping(value = "/frontend")
public class ShopDetailController {
	@Autowired
	private ShopService shopService;
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductCategoryService productCategoryService;

	/**
	 * 获取店铺信息以及商品类别列表
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/listshopdetailpageinfo", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> listShopDetailPageInfo(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<>();
		// 获取前端的shopid
		long shopId = HttpServletRequestUtil.getLong(request, "shopId");
		Shop shop = null;
		List<ProductCategory> productCategoryList = null;
		if (shopId != -1) {
			shop = shopService.getByShopId(shopId);
			// 获取商品类别列表
			productCategoryList = productCategoryService.getProductCategoryList(shopId);
			modelMap.put("shop", shop);
			modelMap.put("productCategoryList", productCategoryList);
			modelMap.put("success", true);
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "ShopDetail-EMPTY_SHOPID");
		}
		return modelMap;
	}

	/**
	 * 根据查询条件分页列出店铺下所有商品
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/listproductsbyshop", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> listProductsByShop(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<>();
		// 获取页码
		int pageIndex = HttpServletRequestUtil.getInt(request, "pageIndex");
		// 获取一页条数
		int pageSize = HttpServletRequestUtil.getInt(request, "pageSize");
		// 获取店铺ID
		long shopId = HttpServletRequestUtil.getLong(request, "shopId");
		// 空值判断
		if ((pageIndex > -1) && (pageSize > -1) && (shopId > -1)) {
			// 获取商品类别id
			long productCategoryId = HttpServletRequestUtil.getLong(request, "productCategoryId");
			// 模糊查询商品名
			String productName = HttpServletRequestUtil.getString(request, "productName");
			// 组合查询条件
			Product productCondition = compactProductCondition4Search(shopId, productCategoryId, productName);
			// 返回分页 以及商品列表 总数
			ProductExecution pe = productService.getProductList(productCondition, pageIndex, pageSize);
			System.out.println(pe.getCount());
			modelMap.put("productList", pe.getProductList());
			modelMap.put("count", pe.getCount());
			modelMap.put("success", true);
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "ShopDetail-EMPTY-PAGESIZE-OR-PAGEINDEX-OR-SHOPID");
		}
		return modelMap;
	}

	/**
	 * 组合查询条件 封装到PRODUCTCONDITION
	 *
	 * @param shopId
	 * @param productCategoryId
	 * @param productName
	 * @return
	 */
	private Product compactProductCondition4Search(long shopId, long productCategoryId, String productName) {
		Product productCondition = new Product();
		Shop shop = new Shop();
		shop.setShopId(shopId);
		productCondition.setShop(shop);
		if (productCategoryId != -1L) {
			// 查询某商品类别下 所有商品列表
			ProductCategory productCategory = new ProductCategory();
			productCategory.setProductCategoryId(productCategoryId);
			productCondition.setProductCategory(productCategory);
		}
		if (productName != null) {
			productCondition.setProductName(productName);
		}
		productCondition.setEnableStatus(1);
		return productCondition;
	}

}
