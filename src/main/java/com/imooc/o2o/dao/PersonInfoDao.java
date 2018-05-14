package com.imooc.o2o.dao;

import com.imooc.o2o.entity.PersonInfo;

/**
 * @Author:REX
 * @Date: Create in 16:13 2018/5/13
 */
public interface PersonInfoDao {
	/**
	 * 通过用户ID查询用户
	 * @param userId
	 * @return
	 */
	PersonInfo queryPersonInfoById(long userId);

	/**
	 * 添加用户信息
	 * @param personInfo
	 * @return
	 */
	int insertPersonInfo(PersonInfo personInfo);
}
