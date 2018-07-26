package com.imooc.o2o.service.Impl;

import com.imooc.o2o.cache.JedisUtil;
import com.imooc.o2o.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @Author:REX
 * @Date: Create in 15:44 2018/5/19
 */
@Service
public class CacheServiceImpl implements CacheService{

	@Autowired
	private JedisUtil.Keys jedisKeys;

	@Override
	public void removeFromCache(String keyPrefix) {
		Set<String >keySet = jedisKeys.keys(keyPrefix + "*");
		for(String key : keySet){
			jedisKeys.del(key);
		}
	}
}
