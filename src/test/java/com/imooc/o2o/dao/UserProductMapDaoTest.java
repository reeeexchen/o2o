package com.imooc.o2o.dao;

import com.imooc.o2o.entity.PersonInfo;
import com.imooc.o2o.entity.Product;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.entity.UserProductMap;
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
 * @Date: Create in 17:57 2018/7/24
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserProductMapDaoTest {
	@Autowired
	private UserProductMapDao userProductMapDao;

	@Test
	public void BqueryUserProductMapList() {
		UserProductMap userProductMap = new UserProductMap();
		PersonInfo customer = new PersonInfo();
		customer.setName("测试");
		userProductMap.setUser(customer);
		List<UserProductMap>userProductMapList = userProductMapDao.queryUserProductMapList(userProductMap,0,2);
		assertEquals(2,userProductMapList.size());
		int count = userProductMapDao.queryUserProductMapCount(userProductMap);
		assertEquals(4,count);
		Shop shop = new Shop();
		shop.setShopId(1L);
		userProductMap.setShop(shop);
		userProductMapList = userProductMapDao.queryUserProductMapList(userProductMap,0,3);
		assertEquals(3,userProductMapList.size());
		assertEquals(3,userProductMapDao.queryUserProductMapCount(userProductMap));
	}

	@Test
	public void AinsertUserProductMap() {
		UserProductMap userProductMap = new UserProductMap();
		PersonInfo customer = new PersonInfo();
		customer.setUserId(1L);
		userProductMap.setUser(customer);
		userProductMap.setOperator(customer);
		Product product = new Product();
		product.setProductId(1L);
		userProductMap.setProduct(product);
		Shop shop = new Shop();
		shop.setShopId(1L);
		userProductMap.setShop(shop);
		userProductMap.setCreateTime(new Date());
		int effect = userProductMapDao.insertUserProductMap(userProductMap);
		assertEquals(1,effect);

		UserProductMap userProductMap2 = new UserProductMap();
		userProductMap2.setUser(customer);
		userProductMap2.setOperator(customer);
		userProductMap2.setProduct(product);
		userProductMap2.setShop(shop);
		userProductMap2.setCreateTime(new Date());
		effect = userProductMapDao.insertUserProductMap(userProductMap2);
		assertEquals(1,effect);

		UserProductMap userProductMap3 = new UserProductMap();
		userProductMap3.setUser(customer);
		userProductMap3.setOperator(customer);
		Product product3 = new Product();
		product3.setProductId(3L);
		userProductMap3.setProduct(product3);
		userProductMap3.setShop(shop);
		userProductMap3.setCreateTime(new Date());
		effect = userProductMapDao.insertUserProductMap(userProductMap3);
		assertEquals(1,effect);

		UserProductMap userProductMap4 = new UserProductMap();
		userProductMap4.setUser(customer);
		userProductMap4.setOperator(customer);
		Product product4 = new Product();
		product4.setProductId(23L);
		userProductMap4.setProduct(product4);
		Shop shop4 = new Shop();
		shop4.setShopId(23L);
		userProductMap4.setShop(shop4);
		userProductMap4.setCreateTime(new Date());
		effect = userProductMapDao.insertUserProductMap(userProductMap4);
		assertEquals(1,effect);
	}
}