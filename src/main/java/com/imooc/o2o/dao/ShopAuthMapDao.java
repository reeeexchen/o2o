package com.imooc.o2o.dao;

import com.imooc.o2o.entity.ShopAuthMap;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: REX
 * @Date: Create in 14:36 2018/7/24
 */
public interface ShopAuthMapDao {
	/**
	 * 列出店铺包含的授权信息
	 * @param shopId
	 * @param rowIndex
	 * @param pageSize
	 * @return
	 */
	List<ShopAuthMap> queryShopAuthMapListByShopId(@Param("shopId") long shopId, @Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);

	/**
	 * 获取授权总数
	 * @param shopId
	 * @return
	 */
	int queryShopAuthCountByShopId(@Param("shopId")long shopId);

	int insertShopAuthMap(ShopAuthMap shopAuthMap);

	int updateShopAuthMap(ShopAuthMap shopAuthMap);

	int deleteShopAuthMap(long shopAuthId);

	/**
	 * 通过ID查询员工授权信息
	 * @param shopAuthId
	 * @return
	 */
	ShopAuthMap queryShopAuthMapById(long shopAuthId);
}
