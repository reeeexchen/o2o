package com.imooc.o2o.util;

/**
 * @Author:REX
 * @Date: Create in 15:18 2018/3/17
 */
public class PathUtil {

	private static String seperator = System.getProperty("file.separator");

	public static String getImgBasePath(){
		String os = System.getProperty("os.name");
		String basePath = "";
		if(os.toLowerCase().startsWith("win")){
			basePath = "D:/JavaDemo/images/";
		}else{
			basePath = "";
		}
		basePath = basePath.replace("/",seperator);
		return basePath;
	}

	public static String getShopImagePath(long shopId){
		String imagePath = "upload/item/shop/" + shopId + "/";
		return imagePath.replace("/",seperator);
	}
}
