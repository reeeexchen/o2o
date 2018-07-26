package com.imooc.o2o.dao;

import com.imooc.o2o.entity.PersonInfo;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.entity.ShopAuthMap;
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
 * @Date: Create in 14:42 2018/7/25
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ShopAuthMapDaoTest {
	@Autowired
	private ShopAuthMapDao shopAuthMapDao;

	@Test
	public void AinsertShopAuthMap() {
		ShopAuthMap shopAuthMap1 = new ShopAuthMap();
		PersonInfo employee = new PersonInfo();
		employee.setUserId(1);
		shopAuthMap1.setEmployee(employee);
		Shop shop = new Shop();
		shop.setShopId(1);
		shopAuthMap1.setShop(shop);
		shopAuthMap1.setTitle("CEO");
		shopAuthMap1.setTitleFlag(1);
		shopAuthMap1.setCreateTime(new Date());
		shopAuthMap1.setEditTime(new Date());
		shopAuthMap1.setEnableStatus(1);
		int effect = shopAuthMapDao.insertShopAuthMap(shopAuthMap1);
		assertEquals(1,effect);

		ShopAuthMap shopAuthMap2 = new ShopAuthMap();
		shopAuthMap2.setEmployee(employee);
		shop.setShopId(23L);
		shopAuthMap2.setShop(shop);
		shopAuthMap2.setTitle("工作人员");
		shopAuthMap2.setTitleFlag(2);
		shopAuthMap2.setCreateTime(new Date());
		shopAuthMap2.setEditTime(new Date());
		shopAuthMap2.setEnableStatus(0);
		effect = shopAuthMapDao.insertShopAuthMap(shopAuthMap2);
		assertEquals(1,effect);
	}

	@Test
	public void BqueryShopAuthMap() {
		// queryShopAuthMapListByShopId
		List<ShopAuthMap> shopAuthMapList = shopAuthMapDao.queryShopAuthMapListByShopId(1,0,3);
		assertEquals(1,shopAuthMapList.size());
		// queryShopAuthMapById
		ShopAuthMap shopAuthMap = shopAuthMapDao.queryShopAuthMapById(shopAuthMapList.get(0).getShopAuthId());
		assertEquals("CEO",shopAuthMap.getTitle());
		System.out.println(shopAuthMap.getEmployee().getName());
		System.out.println(shopAuthMap.getShop().getShopName());
		// queryShopAuthCountByShopId
		assertEquals(1,shopAuthMapDao.queryShopAuthCountByShopId(1));
	}

	@Test
	public void CupdateShopAuthMap() {
		List<ShopAuthMap> shopAuthMapList = shopAuthMapDao.queryShopAuthMapListByShopId(1,0,3);
		shopAuthMapList.get(0).setTitle("COO");
		shopAuthMapList.get(0).setTitleFlag(2);
		assertEquals(1,shopAuthMapDao.updateShopAuthMap(shopAuthMapList.get(0)));

	}

	@Test
	public void DdeleteShopAuthMap() {
		List<ShopAuthMap> shopAuthMapList1 = shopAuthMapDao.queryShopAuthMapListByShopId(1,0,3);
		List<ShopAuthMap> shopAuthMapList2 = shopAuthMapDao.queryShopAuthMapListByShopId(23,0,3);
		assertEquals(1,shopAuthMapDao.deleteShopAuthMap(shopAuthMapList1.get(0).getShopAuthId()));
		assertEquals(1,shopAuthMapDao.deleteShopAuthMap(shopAuthMapList2.get(0).getShopAuthId()));
	}

}