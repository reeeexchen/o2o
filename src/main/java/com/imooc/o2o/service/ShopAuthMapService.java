package com.imooc.o2o.service;

import com.imooc.o2o.dto.ShopAuthMapExecution;
import com.imooc.o2o.entity.ShopAuthMap;
import com.imooc.o2o.exception.ShopAuthMapOperationException;

/**
 * @Author: REX
 * @Date: Create in 14:31 2018/7/24
 */
public interface ShopAuthMapService {
	/**
	 * 根据店铺ID分页显示店铺的授权信息
	 * @param shopId
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	ShopAuthMapExecution listShopAuthMapByShopId(Long shopId,Integer pageIndex,Integer pageSize);

	/**
	 * 根据ShopAuthId返回对应的授权信息
	 * @param shopAuthId
	 * @return
	 */
	ShopAuthMap getShopAuthMapById(Long shopAuthId);

	/**
	 * 添加授权信息
	 * @param shopAuthMap
	 * @return
	 * @throws ShopAuthMapOperationException
	 */
	ShopAuthMapExecution addShopAuthMap(ShopAuthMap shopAuthMap) throws ShopAuthMapOperationException;

	/**
	 * 更新授权信息（职位、状态）
	 * @param shopAuthMap
	 * @return
	 * @throws ShopAuthMapOperationException
	 */
	ShopAuthMapExecution modifyShopAuthMap(ShopAuthMap shopAuthMap) throws ShopAuthMapOperationException;
}
