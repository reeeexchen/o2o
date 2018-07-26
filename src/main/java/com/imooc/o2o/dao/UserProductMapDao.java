package com.imooc.o2o.dao;

import com.imooc.o2o.entity.UserProductMap;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: REX
 * @Date: Create in 17:45 2018/7/24
 */
public interface UserProductMapDao {
	/**
	 * 根据查询条件也分返回购买记录
	 * @param userProductCondition
	 * @param rowIndex
	 * @param pageSize
	 * @return
	 */
	List<UserProductMap> queryUserProductMapList(@Param("userProductCondition") UserProductMap userProductCondition,
												 @Param("rowIndex")int rowIndex,@Param("pageSize")int pageSize);

	int queryUserProductMapCount(@Param("userProductCondition") UserProductMap userProductCondition);
	int insertUserProductMap(UserProductMap userProductMap);
}
