package com.imooc.o2o.service;

import com.imooc.o2o.entity.PersonInfo;

/**
 * @Author:REX
 * @Date: Create in 21:36 2018/5/13
 */
public interface PersonInfoService {
	/**
	 * 根据用户ID获取信息
	 * @param userId
	 * @return
	 */
	PersonInfo getPersonInfoById(Long userId);
}
