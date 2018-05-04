package com.imooc.o2o.web.shopadmin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imooc.o2o.dto.ImageHolder;
import com.imooc.o2o.dto.ProductExecution;
import com.imooc.o2o.dto.Result;
import com.imooc.o2o.entity.Product;
import com.imooc.o2o.entity.ProductCategory;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.enums.ProductStateEnum;
import com.imooc.o2o.exception.ProductOperationException;
import com.imooc.o2o.service.ProductCategoryService;
import com.imooc.o2o.service.ProductService;
import com.imooc.o2o.util.CodeUtil;
import com.imooc.o2o.util.HttpServletRequestUtil;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author:REX
 * @Date: Create in 14:03 2018/3/25
 */
@Controller
@RequestMapping("shopadmin")
public class ProductManagementController {
	@Autowired
	private ProductService productService;

	@Autowired
	private ProductCategoryService productCategoryService;
	//支持上传商品详细图的最大数量
	private static final int IMAGE_MAX_COUNT = 6;

	@RequestMapping(value = "/addproduct",method = RequestMethod.POST)
	@ResponseBody
	private Map<String,Object> addProduct(HttpServletRequest request) throws IOException {
		Map<String,Object> modelMap = new HashMap<String, Object>();
		//验证码校验
		if(!CodeUtil.checkVerifyCode(request)){
			modelMap.put("success",false);
			modelMap.put("errMsg","验证码输入错误");
			return modelMap;
		}
		//接收前端参数的变量的初始化，包括商品，缩略图，详情图列表实体类
		ObjectMapper mapper = new ObjectMapper();
		Product product = null;
		//json -> string
		String productStr = HttpServletRequestUtil.getString(request,"productStr");
		ImageHolder thumbnail = null;
		List<ImageHolder> productImgList = new ArrayList<ImageHolder>();
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		try {
			//若请求中存在文件流，则取出相关的文件（包括缩略图和详情图）
			if(multipartResolver.isMultipart(request)){
				thumbnail = handleImage(request, thumbnail, productImgList);
			}else{
				modelMap.put("success",false);
				modelMap.put("errMsg","上传商品图片不能为空");
				return modelMap;
			}
		}catch (Exception e){
			modelMap.put("success",false);
			modelMap.put("errMsg",e.toString());
			return modelMap;
		}
		//获得缩略图的文件流以及详情图的文件流之后 再生成product实例
		try {
			//获取前端传过来的表单String流 并转化为Product实体类
			product = mapper.readValue(productStr,Product.class);
		}catch (Exception e){
			modelMap.put("success",false);
			modelMap.put("errMsg",e.toString());
			return modelMap;
		}
		//若Product 缩略图 详情图 都为非空 则开始添加操作
		if(product != null && thumbnail != null && productImgList.size() > 0){
			try {
				//从session中获取店铺Id并赋值 减少对前端数据的依赖
				Shop currentShop = (Shop)request.getSession().getAttribute("currentShop");
				product.setShop(currentShop);
				//添加
				ProductExecution pe = productService.addProduct(product,thumbnail,productImgList);
				if(pe.getState() == ProductStateEnum.SUCCESS.getState()){
					modelMap.put("success",true);
				}else{
					modelMap.put("success",false);
					modelMap.put("errMsg",pe.getStateInfo());
				}
			}catch (ProductOperationException e){
				modelMap.put("success",false);
				modelMap.put("errMsg",e.toString());
				return modelMap;
			}
		}else{
			modelMap.put("success",false);
			modelMap.put("errMsg","请完成商品信息");

		}
		return modelMap;
	}

	private ImageHolder handleImage(HttpServletRequest request, ImageHolder thumbnail, List<ImageHolder> productImgList)
			throws IOException {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		// 取出缩略图并构建ImageHolder对象
		CommonsMultipartFile thumbnailFile = (CommonsMultipartFile) multipartRequest.getFile("thumbnail");
		if (thumbnailFile != null) {
			thumbnail = new ImageHolder(thumbnailFile.getOriginalFilename(), thumbnailFile.getInputStream());
		}
		// 取出详情图列表并构建List<ImageHolder>列表对象，最多支持六张图片上传
		for (int i = 0; i < IMAGE_MAX_COUNT; i++) {
			CommonsMultipartFile productImgFile = (CommonsMultipartFile) multipartRequest.getFile("productImg" + i);
			if (productImgFile != null) {
				// 若取出的第i个详情图片文件流不为空，则将其加入详情图列表
				ImageHolder productImg = new ImageHolder(productImgFile.getOriginalFilename(),
						productImgFile.getInputStream());
				productImgList.add(productImg);
			} else {
				// 若取出的第i个详情图片文件流为空，则终止循环
				break;
			}
		}
		return thumbnail;
	}

	@RequestMapping(value = "/getproductbyid",method = RequestMethod.GET)
	@ResponseBody
	private Map<String,Object> getProductById(@RequestParam Long productId){
		Map<String,Object> modelMap = new HashMap<String, Object>();
		//非空判断
		if(productId > -1){
			//获取商品信息
			Product product = productService.getProductById(productId);
			//获取店铺下的商品类别列表
			List<ProductCategory> productCategoryList = productCategoryService.getProductCategoryList(product.getShop().getShopId());
			modelMap.put("product",product);
			modelMap.put("productCategoryList",productCategoryList);
			modelMap.put("success",true);
		}else{
			modelMap.put("success",false);
			modelMap.put("errMsg","EMPTY_PRODUCT_ID");
		}
		return modelMap;
	}

	@RequestMapping(value = "/modifyproduct",method = RequestMethod.POST)
	@ResponseBody
	private Map<String,Object> modifyProduct(HttpServletRequest request){
		Map<String,Object> modelMap = new HashMap<String, Object>();
		//是商品编辑时候调用还是上下架操作的时候调用
		//若为前者 则进行验证码判断 后者则跳过
		boolean statusChange = HttpServletRequestUtil.getBoolean(request, "statusChange");
		//验证码判断
		if(!statusChange && !CodeUtil.checkVerifyCode(request)){
			modelMap.put("success", false);
			modelMap.put("errMsg", "验证码输入错误");
			return modelMap;
		}
		//接收前端参数的变量的初始化，包括商品，缩略图，详情图列表实体类
		ObjectMapper mapper = new ObjectMapper();
		Product product = null;
		ImageHolder thumbnail = null;
		List<ImageHolder> productImgList = new ArrayList<ImageHolder>();
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		try {
			//若请求中存在文件流，则取出相关的文件（包括缩略图和详情图）
			if(multipartResolver.isMultipart(request)){
				thumbnail = handleImage(request, thumbnail, productImgList);
			}
		}catch (Exception e){
			modelMap.put("success",false);
			modelMap.put("errMsg",e.toString());
			return modelMap;
		}
		try{
			String productStr = HttpServletRequestUtil.getString(request,"productStr");
			//尝试获取前端传过来的表单的string流并转换成Product实体类
			product = mapper.readValue(productStr,Product.class);
		}catch (Exception e){
			modelMap.put("success",false);
			modelMap.put("errMsg",e.toString());
			return modelMap;
		}
		//非空判断
		if(product !=null){
			try {
				//从session中获取当前店铺的ID并赋值给product，减少对前端数据的依赖
				Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
				product.setShop(currentShop);
				//开始商品信息修改
				ProductExecution pe = productService.modifyProduct(product,thumbnail,productImgList);
				if(pe.getState() == ProductStateEnum.SUCCESS.getState()){
					modelMap.put("success",true);
				}else{
					modelMap.put("success",false);
					modelMap.put("errMsg",pe.getStateInfo());
				}
			}catch (RuntimeException e){
				modelMap.put("success",false);
				modelMap.put("errMsg",e.toString());
				return modelMap;
			}
		}else{
			modelMap.put("success",false);
			modelMap.put("errMsg","(modifyProduct)输入商品信息");
		}
		return modelMap;
	}

	@RequestMapping(value = "/getproductlistbyshop",method = RequestMethod.GET)
	@ResponseBody
	private Map<String,Object> getProductListByShop(HttpServletRequest request){
		Map<String,Object> modelMap = new HashMap<>();
		int pageIndex = HttpServletRequestUtil.getInt(request,"pageIndex");
		int pageSize = HttpServletRequestUtil.getInt(request,"pageSize");
		// 获取session中店铺信息
		Shop currentShop = (Shop) request.getSession().getAttribute("currentShop");
		// 空值判断
		if((pageIndex > -1) && (pageSize > -1) && (currentShop != null) && (currentShop.getShopId() != null)){
			long productCategoryId = HttpServletRequestUtil.getLong(request,"productCategoryId");
			String productName = HttpServletRequestUtil.getString(request,"productName");
			Product productCondition = compactProductCondition(currentShop.getShopId(),productCategoryId,productName);
			// 传入查询条件 返回商品列表 总数
			ProductExecution pe = productService.getProductList(productCondition,pageIndex,pageSize);
			modelMap.put("productList",pe.getProductList());
			modelMap.put("count",pe.getCount());
			modelMap.put("success",true);
		}else{
			modelMap.put("success",false);
			modelMap.put("errMsg","EMPTY");
		}
		return modelMap;
	}

	private Product compactProductCondition(Long shopId, long productCategoryId, String productName) {
		Product productCondition = new Product();
		Shop shop = new Shop();
		shop.setShopId(shopId);
		productCondition.setShop(shop);
		// 指定类别
		if(productCategoryId != -1L){
			ProductCategory productCategory = new ProductCategory();
			productCategory.setProductCategoryId(productCategoryId);
			productCondition.setProductCategory(productCategory);
		}
		if(productName != null){
			productCondition.setProductName(productName);
		}
		return productCondition;
	}

}
