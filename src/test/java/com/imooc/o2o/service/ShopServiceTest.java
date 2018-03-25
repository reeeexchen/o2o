package com.imooc.o2o.service;

import com.imooc.o2o.dto.ImageHolder;
import com.imooc.o2o.dto.ShopExecution;
import com.imooc.o2o.entity.Area;
import com.imooc.o2o.entity.PersonInfo;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.entity.ShopCategory;
import com.imooc.o2o.enums.ShopStateEnum;
import com.imooc.o2o.exception.ShopOperationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author:REX
 * @Date: Create in 16:53 2018/3/17
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml"})
public class ShopServiceTest {
	@Autowired
	private ShopService shopService;

	@Test
	public void addShop() throws ShopOperationException,FileNotFoundException {
		Shop shop = new Shop();
		PersonInfo owner = new PersonInfo();
		Area area = new Area();
		ShopCategory shopCategory = new ShopCategory();
		owner.setUserId(1);
		area.setAreaId(2);
		shopCategory.setShopCategoryId(1);
		shop.setOwner(owner);
		shop.setArea(area);
		shop.setShopCategory(shopCategory);
		shop.setShopName("测试的店铺3");
		shop.setShopDesc("test3");
		shop.setShopAddr("test3");
		shop.setPhone("test3");
		shop.setCreateTime(new Date());
		shop.setEnableStatus(ShopStateEnum.CHECK.getState());
		shop.setAdvice("审核中");
		File shopImg = new File("D:\\JavaDemo\\images\\test1.png");
		InputStream is = new FileInputStream(shopImg);
		ImageHolder imageHolder = new ImageHolder(shopImg.getName(),is);
		ShopExecution shopExecution = shopService.addShop(shop,imageHolder);
		assertEquals(ShopStateEnum.CHECK.getState(),shopExecution.getState());
	}

	@Test
	public void modifyShop() throws ShopOperationException,FileNotFoundException{
		Shop shop = new Shop();
		shop.setShopId(1);
		shop.setShopName("修改测试店铺名称1");
		File shopImg = new File("D:\\JavaDemo\\images\\test1.png");
		InputStream is = new FileInputStream(shopImg);
		ImageHolder imageHolder = new ImageHolder("test1.png",is);
		ShopExecution shopExecution = shopService.modifyShop(shop,imageHolder);
		System.out.println("NEW IMAGE ADDRESS : " + shopExecution.getShop().getShopImg());
		System.out.println(shopExecution);
	}

	@Test
	public void getByShopId() {
	}

	@Test
	public void getShopList(){
		Shop shopCondition = new Shop();
		ShopCategory sc = new ShopCategory();
		sc.setShopCategoryId(3);
		shopCondition.setShopCategory(sc);
		ShopExecution se = shopService.getShopList(shopCondition,1,5);
		System.out.println("店铺列表数为 ： " + se.getShopList().size());
		System.out.println("店铺总数为 ： " + se.getCount());
	}
}