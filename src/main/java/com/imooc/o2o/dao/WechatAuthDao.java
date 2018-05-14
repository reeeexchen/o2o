package com.imooc.o2o.dao;

import com.imooc.o2o.entity.WechatAuth;

/**
 * @Author:REX
 * @Date: Create in 16:15 2018/5/13
 */
public interface WechatAuthDao {
	/**
	 * 通过openid查询平台的微信帐号
	 * @param openId
	 * @return
	 */
	WechatAuth queryWechatInfoByOpenId(String openId);

	/**
	 * 添加平台的微信帐号
	 * @param wechatAuth
	 * @return
	 */
	int insertWechatAuth(WechatAuth wechatAuth);
}
