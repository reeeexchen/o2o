package com.imooc.o2o.service;

/**
 * @Author:REX
 * @Date: Create in 15:41 2018/5/19
 */
public interface CacheService {

	/**
	 * 根据key前缀删除匹配的key-value
	 * @param keyPrefix
	 */
	void removeFromCache(String keyPrefix);
}
