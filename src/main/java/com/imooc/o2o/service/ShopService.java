package com.imooc.o2o.service;

import com.imooc.o2o.dto.ShopExecution;
import com.imooc.o2o.entity.Shop;

import java.io.File;


/**
 * @Author:REX
 * @Date: Create in 16:22 2018/3/17
 */
public interface ShopService {
	/**
	 * 创建店铺
	 * @param shop
	 * @param shopImg
	 * @return
	 */
	ShopExecution addShop(Shop shop,File shopImg);

	/**
	 * 更新店铺信息（商家）
	 * @param shop
	 * @param shopImg
	 * @return
	 */
	ShopExecution modifyShop(Shop shop,File shopImg);

	/**
	 * 查询指定店铺信息
	 * @param shopId
	 * @return
	 */
	Shop getByShopId(long shopId);

}
