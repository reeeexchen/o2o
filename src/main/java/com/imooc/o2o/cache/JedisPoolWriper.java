package com.imooc.o2o.cache;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Author:REX
 * @Date: Create in 23:55 2018/5/17
 */
public class JedisPoolWriper {
	// redis连接池对象
	private JedisPool jedisPool;

	public JedisPoolWriper(final JedisPoolConfig poolConfig,final String host,final int port,final int timeout,final String password){
		try {
			jedisPool = new JedisPool(poolConfig,host,port,timeout,password);
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * 获取redis连接池对象
	 * @return
	 */
	public JedisPool getJedisPool() {
		return jedisPool;
	}

	/**
	 * 注入redis连接池对象
	 * @param jedisPool
	 */
	public void setJedisPool(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}
}
