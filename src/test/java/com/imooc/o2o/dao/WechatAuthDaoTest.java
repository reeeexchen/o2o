package com.imooc.o2o.dao;

import com.imooc.o2o.entity.PersonInfo;
import com.imooc.o2o.entity.WechatAuth;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;

/**
 * @Author:REX
 * @Date: Create in 16:35 2018/5/13
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class WechatAuthDaoTest {

	@Autowired
	private WechatAuthDao wechatAuthDao;

	@Test
	public void insertWechatAuth() {
		WechatAuth wechatAuth = new WechatAuth();
		PersonInfo personInfo = new PersonInfo();
		personInfo.setUserId(1L);
		wechatAuth.setPersonInfo(personInfo);
		wechatAuth.setOpenId("dasdasdasda");
		wechatAuth.setCreateTime(new Date());
		int num = wechatAuthDao.insertWechatAuth(wechatAuth);
		assertEquals(1,num);
	}

	@Test
	public void queryWechatInfoByOpenId() {
		WechatAuth wechatAuth = wechatAuthDao.queryWechatInfoByOpenId("dasdasdasda");
		assertEquals("测试",wechatAuth.getPersonInfo().getName());
	}


}