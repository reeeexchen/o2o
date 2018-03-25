package com.imooc.o2o.service;

import com.imooc.o2o.dto.ImageHolder;
import com.imooc.o2o.dto.ShopExecution;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.exception.ShopOperationException;

import java.io.File;
import java.io.InputStream;


/**
 * @Author:REX
 * @Date: Create in 16:22 2018/3/17
 */
public interface ShopService {

	/**
	 * 创建店铺
	 *
	 * @param shop
	 * @param thumbnail
	 * @return
	 * @throws ShopOperationException
	 */
	ShopExecution addShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException;

	/**
	 * 更新店铺信息（商家）
	 *
	 * @param shop
	 * @param thumbnail
	 * @return
	 * @throws ShopOperationException
	 */
	ShopExecution modifyShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException;

	/**
	 * 通过店铺ID查询指定店铺信息
	 *
	 * @param shopId
	 * @return
	 */
	Shop getByShopId(long shopId);

	/**
	 * 根据shopCondition分页返回相应店铺列表
	 * @param shopCondition
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	ShopExecution getShopList(Shop shopCondition,int pageIndex,int pageSize);

}
