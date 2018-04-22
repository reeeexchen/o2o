package com.imooc.o2o.web.frontend;

import com.imooc.o2o.dao.ProductImgDao;
import com.imooc.o2o.entity.Product;
import com.imooc.o2o.entity.ProductImg;
import com.imooc.o2o.service.ProductService;
import com.imooc.o2o.util.HttpServletRequestUtil;
import com.sun.corba.se.spi.ior.ObjectKey;
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
 * @Date: Create in 16:53 2018/4/22
 */
@Controller
@RequestMapping(value = "/frontend")
public class ProductDetailController {
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductImgDao productImgDao;

	@RequestMapping(value = "/listproductdetailpageinfo",method = RequestMethod.GET)
	@ResponseBody
	private Map<String,Object> listProductDetailPageInfo(HttpServletRequest request){
		Map<String,Object> modelMap = new HashMap<>();
		// 获取前端productid
		long productId = HttpServletRequestUtil.getLong(request,"productId");
		Product product = null;
		if(productId != -1){
			product = productService.getProductById(productId);
			List<ProductImg> productImgList = productImgDao.queryProductImgList(productId);
			modelMap.put("productImgList",productImgList);
			modelMap.put("product",product);
			modelMap.put("success",true);
		}else{
			modelMap.put("success",false);
			modelMap.put("errMsg","EMPTY-PRODUCT-ID");
		}
		return modelMap;
	}
}
