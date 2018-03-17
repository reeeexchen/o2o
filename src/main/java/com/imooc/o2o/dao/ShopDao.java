package com.imooc.o2o.dao;

import com.imooc.o2o.entity.Shop;

/**
 * @Author:REX
 * @Date: Create in 13:55 2018/3/17
 */
public interface ShopDao {

	/**
	 * 新增店铺
	 * @param shop
	 * @return
	 */
	int insertShop(Shop shop);

	/**
	 * 更新店铺信息
	 * @param shop
	 * @return
	 */
	int updateShop(Shop shop);
}
