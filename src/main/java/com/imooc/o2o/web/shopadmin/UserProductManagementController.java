package com.imooc.o2o.web.shopadmin;

import com.imooc.o2o.dto.UserProductMapExecution;
import com.imooc.o2o.entity.Product;
import com.imooc.o2o.entity.ProductSellDaily;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.entity.UserProductMap;
import com.imooc.o2o.service.ProductSellDailyService;
import com.imooc.o2o.service.UserProductMapService;
import com.imooc.o2o.util.HttpServletRequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @Author: REX
 * @Date: Create in 15:02 2018/7/26
 */
@Controller
@RequestMapping("/shopadmin")
public class UserProductManagementController {
	@Autowired
	private UserProductMapService userProductMapService;
	@Autowired
	private ProductSellDailyService productSellDailyService;

	@RequestMapping(value = "/listuserproductmapsbyshop", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> listUserProductMapsByShop(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<>();
		int pageIndex = HttpServletRequestUtil.getInt(request, "pageIndex");
		int pageSize = HttpServletRequestUtil.getInt(request, "pageSize");
		Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
		if ((pageIndex > -1) && (pageSize > -1) && (currentShop != null) && (currentShop.getShopId() != null)) {
			UserProductMap userProductMapCondition = new UserProductMap();
			userProductMapCondition.setShop(currentShop);
			String productName = HttpServletRequestUtil.getString(request, "productName");
			if (productName != null) {
				Product product = new Product();
				product.setProductName(productName);
				userProductMapCondition.setProduct(product);
			}
			UserProductMapExecution ue = userProductMapService.listUserProductMap(userProductMapCondition, pageIndex, pageSize);
			modelMap.put("userProductMapList", ue.getUserProductMapList());
			modelMap.put("count", ue.getCount());
			modelMap.put("success", true);
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "EMPTY PAGESIZE OR PAGEINDEX OR SHOPID");
		}
		return modelMap;
	}

	@RequestMapping(value = "/listproductselldailyinfobyshop", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> listProductSellDailyInfoByShop(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<>();
		Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
		if((currentShop != null) && (currentShop.getShopId() != null)){
			ProductSellDaily productSellDailyCondition = new ProductSellDaily();
			productSellDailyCondition.setShop(currentShop);
			Calendar calendar = Calendar.getInstance();
			// 获取今天的日期
			Date beginTime = calendar.getTime();
			// 获取七天前的日期
			calendar.add(Calendar.DATE,-7);
			Date endTime = calendar.getTime();
			// 根据传入查询条件获取店铺的商品销售情况
			List<ProductSellDaily> productSellDailyList = productSellDailyService.listProductSellDaily(productSellDailyCondition,beginTime,endTime);
			modelMap.put("productSellDailyList", productSellDailyList);
			modelMap.put("success", true);
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "EMPTY BEGINTIME OR ENDTIME OR SHOPID");
		}
		return modelMap;
	}
}
