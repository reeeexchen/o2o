package com.imooc.o2o.web.shopadmin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imooc.o2o.dto.ShopExecution;
import com.imooc.o2o.entity.PersonInfo;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.enums.ShopStateEnum;
import com.imooc.o2o.service.ShopService;
import com.imooc.o2o.util.HttpServletRequestUtil;
import com.imooc.o2o.util.ImageUtil;
import com.imooc.o2o.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author:REX
 * @Date: Create in 23:57 2018/3/17
 */
@Controller
@RequestMapping("/shopadmin")
public class ShopManagementController {

	@Autowired
	private ShopService shopService;

	@RequestMapping(value = "/registershop" ,method = RequestMethod.POST)
	@ResponseBody
	private Map<String,Object> registerShop(HttpServletRequest request){
		Map<String,Object> modelMap = new HashMap<String, Object>();
		//1.接受并转化相应的参数,包括店铺信息以及图片信息
		String shopStr = HttpServletRequestUtil.getString(request,"shopStr");
		//json解析
		ObjectMapper mapper = new ObjectMapper(); // create once, reuse
		Shop shop = null;
		try{
			shop = mapper.readValue(shopStr,Shop.class);
		}catch (Exception e){
			modelMap.put("success",false);
			modelMap.put("errMsg",e.getMessage());
			return modelMap;
		}
		CommonsMultipartFile shopImg = null;
		//获取相关文件
		CommonsMultipartResolver commonsMultipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		if(commonsMultipartResolver.isMultipart(request)){
			//存在相应文件流 -> 文件转换
			MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest)request;
			//提取相对应文件流
			shopImg = (CommonsMultipartFile)multipartHttpServletRequest.getFile("shopImg");

		}else{
			modelMap.put("success",false);
			modelMap.put("errMsg","上传图片不能为空");
			return modelMap;
		}
		//2.注册店铺
		if(shop != null && shopImg != null){
			PersonInfo owner = new PersonInfo();
			owner.setUserId(1);
			shop.setOwner(owner);
			File shopImgFile = new File(PathUtil.getImgBasePath() + ImageUtil.getRandomFileName());
			try {
				shopImgFile.createNewFile();
			} catch (IOException e) {
				modelMap.put("success",false);
				modelMap.put("errMsg",e.getMessage());
				return modelMap;
			}
			try {
				InputStreamToFile(shopImg.getInputStream(),shopImgFile);
			} catch (IOException e) {
				modelMap.put("success",false);
				modelMap.put("errMsg",e.getMessage());
				return modelMap;
			}
			ShopExecution shopExecution = shopService.addShop(shop,shopImgFile);
			if(shopExecution.getState() == ShopStateEnum.CHECK.getState()){
				modelMap.put("success",true);
			}else{
				modelMap.put("success",false);
				modelMap.put("errMsg",shopExecution.getStateInfo());
			}
			return modelMap;
		}else{
			modelMap.put("success",false);
			modelMap.put("errMsg","请完成店铺信息");
			return modelMap;
		}
	}

	private static void InputStreamToFile(InputStream inputStream,File file){
		FileOutputStream outputStream = null;
		try {
			outputStream = new FileOutputStream(file);
			int bytesRead = 0;
			byte[] buffer = new byte[1024];
			while ((bytesRead = inputStream.read()) != -1){
				outputStream.write(buffer,0,bytesRead);
			}
		}catch (Exception e){
			throw new RuntimeException("调用InputStreamToFile产生异常 : " + e.getMessage());
		}finally {
			try {
				if (outputStream != null){
					outputStream.close();
				}
				if(inputStream != null){
					inputStream.close();
				}
			}catch (IOException e){
				throw new RuntimeException("InputStreamToFile关闭产生异常 : " + e.getMessage());
			}
		}
	}
}
