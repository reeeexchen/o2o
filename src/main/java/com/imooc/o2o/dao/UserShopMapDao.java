package com.imooc.o2o.dao;

import com.imooc.o2o.entity.UserShopMap;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: REX
 * @Date: Create in 11:17 2018/7/25
 */
public interface UserShopMapDao {
	List<UserShopMap> queryUserShopMapList(@Param("userShopCondition")UserShopMap userShopMapCondition,
										   @Param("rowIndex")int rowIndex,@Param("pageSize")int pageSize);

	int queryUserShopMapCount(@Param("userShopCondition")UserShopMap userShopCondition);

	UserShopMap queryUserShopMap(@Param("userId")long userId,@Param("shopId")long shopId);

	/**
	 * 添加一条用户在店铺的积分记录
	 * @param userShopMap
	 * @return
	 */
	int insertUserShopMap(UserShopMap userShopMap);

	/**
	 * 更新用户在店铺的积分记录
	 * @param userShopMap
	 * @return
	 */
	int updateUserShopMapPoint(UserShopMap userShopMap);
}
