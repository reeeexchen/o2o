package com.imooc.o2o.dao;

import com.imooc.o2o.entity.Area;
import com.imooc.o2o.entity.PersonInfo;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.entity.ShopCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author:REX
 * @Date: Create in 14:04 2018/3/17
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class ShopDaoTest {

	@Autowired
	private ShopDao shopDao;

	@Test
	public void insertShop() {
		Shop shop = new Shop();
		PersonInfo owner = new PersonInfo();
		Area area = new Area();
		ShopCategory shopCategory = new ShopCategory();
		owner.setUserId(1);
		area.setAreaId(4);
		shopCategory.setShopCategoryId(1);
		shop.setOwner(owner);
		shop.setArea(area);
		shop.setShopCategory(shopCategory);
		shop.setShopName("测试的店铺");
		shop.setShopDesc("test");
		shop.setShopAddr("test");
		shop.setPhone("test");
		shop.setShopImg("test");
		shop.setCreateTime(new Date());
		shop.setEnableStatus(0);
		shop.setAdvice("审核中");
		int effectedNum = shopDao.insertShop(shop);
		assertEquals(1,effectedNum);
	}

	@Test
	public void updateShop() {
		Shop shop = new Shop();
		shop.setShopId(1);
		shop.setShopDesc("测试描述");
		shop.setShopAddr("测试地址");
		shop.setEditTime(new Date());
		int effectedNum = shopDao.updateShop(shop);
		assertEquals(1,effectedNum);
	}

	@Test
	public void queryByShopId() {
		long shopId = 11;
		Shop shop = shopDao.queryByShopId(shopId);
		System.out.println("AREA_ID : " + shop.getArea().getAreaId());
		System.out.println("AREA_NAME : " + shop.getArea().getAreaName());
		System.out.println(shop);
	}

	@Test
	public void queryShopListAndCount(){
		Shop shopCondition = new Shop();
		PersonInfo owner = new PersonInfo();
		owner.setUserId(1);
		shopCondition.setOwner(owner);
		shopCondition.setShopName("咖啡");
		List<Shop> shopList = shopDao.queryShopList(shopCondition, 0, 5);
		int count = shopDao.queryShopCount(shopCondition);
		System.out.println(shopList.size());
		System.out.println(count);
		ShopCategory sc = new ShopCategory();
		sc.setShopCategoryId(3);
		shopCondition.setShopCategory(sc);
		shopList = shopDao.queryShopList(shopCondition, 0, 2);
		System.out.println(shopList.size());
		count = shopDao.queryShopCount(shopCondition);
		System.out.println(count);
	}
}