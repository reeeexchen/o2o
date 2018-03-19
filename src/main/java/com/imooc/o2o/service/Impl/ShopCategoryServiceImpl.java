package com.imooc.o2o.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imooc.o2o.dao.ShopCategoryDao;
import com.imooc.o2o.dto.ShopCategoryExecution;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.entity.ShopCategory;
import com.imooc.o2o.service.ShopCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author:REX
 * @Date: Create in 15:38 2018/3/18
 */
@Service
public class ShopCategoryServiceImpl implements ShopCategoryService {

	@Autowired
	private ShopCategoryDao shopCategoryDao;

	private static String SCLISTKEY = "shopcategorylist";

	@Override
	public List<ShopCategory> getFirstLevelShopCategoryList() throws IOException {
		String key = SCLISTKEY;
		List<ShopCategory> shopCategoryList = null;
		ObjectMapper mapper = new ObjectMapper();
		//TODO
		return null;
	}

	@Override
	public List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition) throws IOException {
		return shopCategoryDao.queryShopCategory(shopCategoryCondition);
	}

	@Override
	public List<ShopCategory> getAllSecondLevelShopCategory() throws IOException {
		return null;
	}

	@Override
	public ShopCategory getShopCategoryById(Long shopCategoryId) {
		List<ShopCategory> shopCategoryList = new ArrayList<ShopCategory>();
		try{
			shopCategoryList = getFirstLevelShopCategoryList();
			shopCategoryList.addAll(getAllSecondLevelShopCategory());
		}catch (IOException e){
			e.printStackTrace();
		}
		for(ShopCategory sc: shopCategoryList){
			if(shopCategoryId == sc.getShopCategoryId()){
				return sc;
			}
		}
		ShopCategory sc = shopCategoryDao.queryShopCategoryById(shopCategoryId);
		if(sc != null){
			return sc;
		}else {
			return null;
		}
	}

	@Override
	public ShopCategoryExecution addShopCategory(ShopCategory shopCategory, CommonsMultipartFile thumbnail) {
		return null;
	}

	@Override
	public ShopCategoryExecution modifyShopCategory(ShopCategory shopCategory, CommonsMultipartFile thumbnail, boolean thumbnailChange) {
		return null;
	}

	@Override
	public ShopCategoryExecution removeShopCategory(long shopCategoryId) {
		return null;
	}

	@Override
	public ShopCategoryExecution removeShopCategoryList(List<Long> shopCategoryIdList) {
		return null;
	}
}
