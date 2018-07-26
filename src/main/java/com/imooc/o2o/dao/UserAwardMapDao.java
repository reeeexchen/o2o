package com.imooc.o2o.dao;

import com.imooc.o2o.entity.UserAwardMap;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author: REX
 * @Date: Create in 16:09 2018/7/24
 */
public interface UserAwardMapDao {
	/**
	 * 根据查询条件，分页显示兑换记录
	 * @param userAwardMap
	 * @param rowIndex
	 * @param pageSize
	 * @return
	 */
	List<UserAwardMap> queryUserAwardMapList(@Param("userAwardCondition") UserAwardMap userAwardMap,
											 @Param("rowIndex")int rowIndex,@Param("pageSize")int pageSize);

	/**
	 * 配合List返回相同查询条件下的兑换总数
	 * @param userAwardCondition
	 * @return
	 */
	int queryUserAwardMapCount(@Param("userAwardCondition") UserAwardMap userAwardCondition);

	UserAwardMap queryUserAwardMapById(long userAwardId);
	int insertUserAwardMap(UserAwardMap userAwardMap);
	int updateUserAwardMap(UserAwardMap userAwardMap);

}
