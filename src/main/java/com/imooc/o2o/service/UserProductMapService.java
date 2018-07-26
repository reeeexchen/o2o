package com.imooc.o2o.service;

import com.imooc.o2o.dto.UserProductMapExecution;
import com.imooc.o2o.entity.UserProductMap;

/**
 * @Author: REX
 * @Date: Create in 14:51 2018/7/26
 */
public interface UserProductMapService {
	/**
	 * 根据传入的查询条件分页显示用户消费记录
	 * @param userProductCondition
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	UserProductMapExecution listUserProductMap(UserProductMap userProductCondition, Integer pageIndex, Integer pageSize);
}
