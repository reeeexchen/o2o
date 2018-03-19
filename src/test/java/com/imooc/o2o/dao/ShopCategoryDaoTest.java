package com.imooc.o2o.dao;

import com.imooc.o2o.entity.ShopCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author:REX
 * @Date: Create in 21:35 2018/3/18
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class ShopCategoryDaoTest {

	@Autowired
	private ShopCategoryDao shopCategoryDao;

	@Test
	public void queryShopCategory() {
		List<ShopCategory> shopCategoryList = shopCategoryDao.queryShopCategory(new ShopCategory());
		assertEquals(2,shopCategoryList.size());
		ShopCategory testCategory = new ShopCategory();
		ShopCategory parentCategory = new ShopCategory();
		parentCategory.setShopCategoryId(1);
		testCategory.setParent(parentCategory);
		shopCategoryList = shopCategoryDao.queryShopCategory(testCategory);
		assertEquals(1,shopCategoryList.size());
		System.out.println(shopCategoryList.get(0).getShopCategoryName());
	}
}