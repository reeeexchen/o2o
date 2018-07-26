package com.imooc.o2o.dao;

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
 * @Date: Create in 16:35 2018/5/13
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonInfoDaoTest {

	@Autowired
	private PersonInfoDao personInfoDao;

	@Test
	public void insertPersonInfo() {
		PersonInfo personInfo = new PersonInfo();
		personInfo.setName("TEST PERSON INFO");
		personInfo.setGender("女");
		// 1-顾客
		personInfo.setUserType(1);
		personInfo.setCreateTime(new Date());
		personInfo.setEditTime(new Date());
		// 1-可用
		personInfo.setEnableStatus(1);
		int num = personInfoDao.insertPersonInfo(personInfo);
		assertEquals(1,num);
	}

	@Test
	public void queryPersonInfoById() {
		long userId = 2;
		PersonInfo person = personInfoDao.queryPersonInfoById(userId);
		System.out.println(person.getName());

	}

}