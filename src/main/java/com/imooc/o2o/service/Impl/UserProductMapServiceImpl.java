package com.imooc.o2o.service.Impl;

import com.imooc.o2o.dao.UserProductMapDao;
import com.imooc.o2o.dto.UserProductMapExecution;
import com.imooc.o2o.entity.UserProductMap;
import com.imooc.o2o.service.UserProductMapService;
import com.imooc.o2o.util.PageCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: REX
 * @Date: Create in 14:51 2018/7/26
 */
@Service
public class UserProductMapServiceImpl implements UserProductMapService{
	@Autowired
	private UserProductMapDao userProductMapDao;

	@Override
	public UserProductMapExecution listUserProductMap(UserProductMap userProductCondition, Integer pageIndex, Integer pageSize) {
		if(userProductCondition != null && pageIndex != null && pageSize != null){
			int beginIndex = PageCalculator.calculateRowIndex(pageIndex,pageSize);
			List<UserProductMap> userProductMapList = userProductMapDao.queryUserProductMapList(userProductCondition,pageIndex,pageSize);
			int count = userProductMapDao.queryUserProductMapCount(userProductCondition);
			UserProductMapExecution se = new UserProductMapExecution();
			se.setUserProductMapList(userProductMapList);
			se.setCount(count);
			return se;
		}else{
			return null;
		}
	}
}
