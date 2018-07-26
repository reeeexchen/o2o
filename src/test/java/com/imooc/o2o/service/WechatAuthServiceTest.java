package com.imooc.o2o.service;

import com.imooc.o2o.dto.WechatAuthExecution;
import com.imooc.o2o.entity.PersonInfo;
import com.imooc.o2o.entity.WechatAuth;
import com.imooc.o2o.enums.WechatAuthStateEnum;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.Serializable;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * @Author:REX
 * @Date: Create in 23:59 2018/5/29
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WechatAuthServiceTest {

	@Autowired
	private WechatAuthService wechatAuthService;

	@Test
	public void getWechatAuthByOpenId() {
	}

	@Test
	public void register() {
		// NEW WECHAT ACCOUNT
		WechatAuth wechatAuth = new WechatAuth();
		PersonInfo personInfo = new PersonInfo();
		String openId = "123asdasjkl&*%$&@$";

		personInfo.setCreateTime(new Date());
		personInfo.setName("SpringBoot测试");
		personInfo.setUserType(1);

		wechatAuth.setPersonInfo(personInfo);
		wechatAuth.setOpenId(openId);
		wechatAuth.setCreateTime(new Date());
		WechatAuthExecution wae = wechatAuthService.register(wechatAuth);
		Assert.assertEquals(WechatAuthStateEnum.SUCCESS.getState(),wae.getState());
		wechatAuth = wechatAuthService.getWechatAuthByOpenId(openId);
		System.out.println(wechatAuth.getPersonInfo().getName());
	}
}