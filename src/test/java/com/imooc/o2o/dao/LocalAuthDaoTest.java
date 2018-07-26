package com.imooc.o2o.dao;

import com.imooc.o2o.entity.LocalAuth;
import com.imooc.o2o.entity.PersonInfo;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.Date;

import static org.junit.Assert.*;

/**
 * @Author:REX
 * @Date: Create in 16:38 2018/5/19
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class LocalAuthDaoTest {
	@Autowired
	private LocalAuthDao localAuthDao;
	private static final String username = "testusername2";
	private static final String password = "testpassword2";

	@Test
	public void AinsertLocalAuth() {
		// 新增平台帐号
		LocalAuth localAuth = new LocalAuth();
		PersonInfo personInfo = new PersonInfo();
		personInfo.setUserId(2);
		localAuth.setPersonInfo(personInfo);

		localAuth.setUsername(username);
		localAuth.setPassword(password);
		localAuth.setCreateTime(new Date());
		int num = localAuthDao.insertLocalAuth(localAuth);
		assertEquals(1,num);
	}

	@Test
	public void BqueryLocalByUserNameAndPwd() {
		LocalAuth localAuth = localAuthDao.queryLocalByUserNameAndPwd(username,password);
		System.out.println(localAuth.toString());
		System.out.println(localAuth.getPersonInfo().toString());
		assertEquals("TEST PERSON INFO",localAuth.getPersonInfo().getName());
	}

	@Test
	public void CqueryLocalByUserId() {
		LocalAuth localAuth = localAuthDao.queryLocalByUserId(2L);
		System.out.println(localAuth.toString());
		System.out.println(localAuth.getPersonInfo().toString());
		assertEquals("TEST PERSON INFO",localAuth.getPersonInfo().getName());
	}

	@Test
	public void DupdateLocalAuth() {
		LocalAuth localAuth = localAuthDao.queryLocalByUserId(2L);
		System.out.println(localAuth.toString());

		int num = localAuthDao.updateLocalAuth(2L,username,password,password + "new",new Date());
		assertEquals(1,num);

		localAuth = localAuthDao.queryLocalByUserId(2L);
		System.out.println(localAuth.toString());
	}
}