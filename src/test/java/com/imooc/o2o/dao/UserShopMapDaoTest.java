package com.imooc.o2o.dao;

import com.imooc.o2o.entity.PersonInfo;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.entity.UserShopMap;
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
 * @Date: Create in 12:04 2018/7/25
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserShopMapDaoTest {
	@Autowired
	private UserShopMapDao userShopMapDao;

	@Test
	public void AinsertUserShopMap() {
		UserShopMap userShopMap1 = new UserShopMap();
		PersonInfo customer = new PersonInfo();
		customer.setUserId(1L);
		userShopMap1.setUser(customer);
		Shop shop = new Shop();
		shop.setShopId(1L);
		userShopMap1.setShop(shop);
		userShopMap1.setCreateTime(new Date());
		userShopMap1.setPoint(1);
		int effect = userShopMapDao.insertUserShopMap(userShopMap1);
		assertEquals(1,effect);

		UserShopMap userShopMap2 = new UserShopMap();
		PersonInfo customer2 = new PersonInfo();
		customer2.setUserId(4L);
		userShopMap2.setUser(customer2);
		Shop shop2 = new Shop();
		shop2.setShopId(23L);
		userShopMap2.setShop(shop2);
		userShopMap2.setCreateTime(new Date());
		userShopMap2.setPoint(1);
		effect = userShopMapDao.insertUserShopMap(userShopMap2);
		assertEquals(1,effect);
	}

	@Test
	public void BqueryUserShopMap() {
		UserShopMap userShopMap = new UserShopMap();
		// QUERY_ALL
		List<UserShopMap> userShopMapList = userShopMapDao.queryUserShopMapList(userShopMap,0,2);
		assertEquals(2,userShopMapList.size());
		int count = userShopMapDao.queryUserShopMapCount(userShopMap);
		assertEquals(2,count);
		// QUERY_BY_SHOP
		Shop shop = new Shop();
		shop.setShopId(1L);
		userShopMap.setShop(shop);
		userShopMapList = userShopMapDao.queryUserShopMapList(userShopMap,0,3);
		assertEquals(1,userShopMapList.size());
		count = userShopMapDao.queryUserShopMapCount(userShopMap);
		assertEquals(1,count);
		// QUERY_BY_USER_ID
		userShopMap = userShopMapDao.queryUserShopMap(1,1);
		assertEquals("测试",userShopMap.getUser().getName());

	}

	@Test
	public void CupdateUserShopMapPoint() {
		UserShopMap userShopMap = new UserShopMap();
		userShopMap = userShopMapDao.queryUserShopMap(1,1);
		assertTrue("ERROR,积分不一致",1 == userShopMap.getPoint());
		userShopMap.setPoint(2);
		int effect = userShopMapDao.updateUserShopMapPoint(userShopMap);
		assertEquals(1,effect);
	}
}