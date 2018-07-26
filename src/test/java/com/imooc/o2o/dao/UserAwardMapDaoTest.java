package com.imooc.o2o.dao;

import com.imooc.o2o.entity.Award;
import com.imooc.o2o.entity.PersonInfo;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.entity.UserAwardMap;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author: REX
 * @Date: Create in 16:48 2018/7/24
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserAwardMapDaoTest {
	@Autowired
	private UserAwardMapDao userAwardMapDao;

	@Test
	public void AinsertUserAwardMap() {
		UserAwardMap userAwardMap = new UserAwardMap();
		PersonInfo customer = new PersonInfo();
		customer.setUserId(1L);
		userAwardMap.setUser(customer);
		userAwardMap.setOperator(customer);

		Award award = new Award();
		award.setAwardId(1L);
		userAwardMap.setAward(award);

		Shop shop = new Shop();
		shop.setShopId(1L);
		userAwardMap.setShop(shop);
		userAwardMap.setCreateTime(new Date());
		userAwardMap.setUsedStatus(1);
		userAwardMap.setPoint(1);
		int effect = userAwardMapDao.insertUserAwardMap(userAwardMap);
		assertEquals(1,effect);

		UserAwardMap userAwardMap2 = new UserAwardMap();
		PersonInfo customer2 = new PersonInfo();
		customer2.setUserId(1L);
		userAwardMap2.setUser(customer2);
		userAwardMap2.setOperator(customer2);

		Award award2 = new Award();
		award2.setAwardId(1L);
		userAwardMap2.setAward(award2);
		userAwardMap2.setShop(shop);
		userAwardMap2.setCreateTime(new Date());
		userAwardMap2.setUsedStatus(0);
		userAwardMap2.setPoint(1);
		effect = userAwardMapDao.insertUserAwardMap(userAwardMap2);
		assertEquals(1,effect);


	}

	@Test
	public void BqueryUserAwardMapList() {
		UserAwardMap userAwardMap = new UserAwardMap();
		// 测试QUERY_LIST
		List<UserAwardMap> userAwardMapList = userAwardMapDao.queryUserAwardMapList(userAwardMap,0,3);
		assertEquals(2,userAwardMapList.size());
		int count = userAwardMapDao.queryUserAwardMapCount(userAwardMap);
		assertEquals(2,count);
		PersonInfo customer = new PersonInfo();
		// 用户名模糊查询
		customer.setName("测试");
		userAwardMap.setUser(customer);
		userAwardMapList = userAwardMapDao.queryUserAwardMapList(userAwardMap,0,3);
		assertEquals(2,userAwardMapList.size());
		count = userAwardMapDao.queryUserAwardMapCount(userAwardMap);
		assertEquals(2,count);
		// 测试QUERY_BY_ID 按优先级排序返回第二个信息
		userAwardMap = userAwardMapDao.queryUserAwardMapById(userAwardMapList.get(0).getUserAwardId());
		assertEquals("第一个奖品",userAwardMap.getAward().getAwardName());

	}
	@Test
	public void CupdateUserAwardMap() {
		UserAwardMap userAwardMap = new UserAwardMap();
		PersonInfo customer = new PersonInfo();
		customer.setName("测试");
		userAwardMap.setUser(customer);
		List<UserAwardMap> userAwardMapList = userAwardMapDao.queryUserAwardMapList(userAwardMap,0,3);
		assertTrue("ERROR,积分不一致",0 == userAwardMapList.get(0).getUsedStatus());
		userAwardMapList.get(0).setUsedStatus(1);
		int effect = userAwardMapDao.updateUserAwardMap(userAwardMapList.get(0));
		assertEquals(1,effect);

	}
}