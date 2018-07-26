package com.imooc.o2o.dao;

import com.imooc.o2o.entity.ProductCategory;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author:REX
 * @Date: Create in 23:20 2018/3/23
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductCategoryDaoTest {

	@Autowired
	private ProductCategoryDao productCategoryDao;

	@Test
	public void BqueryProductCategoryList() {
		long shopId = 1;
		List<ProductCategory> productCategoryList = productCategoryDao.queryProductCategoryList(shopId);
		System.out.println("店铺自定义类别数 : " + productCategoryList.size());
	}

	@Test
	public void AbatchInsertProductCategory() {
		ProductCategory productCategory = new ProductCategory();
		productCategory.setProductCategoryName("测试类别5");
		productCategory.setPriority(1);
		productCategory.setCreateTime(new Date());
		productCategory.setShopId(1L);

		ProductCategory productCategory2 = new ProductCategory();
		productCategory2.setProductCategoryName("测试类别6");
		productCategory2.setPriority(2);
		productCategory2.setCreateTime(new Date());
		productCategory2.setShopId(1L);

		List<ProductCategory> productCategoryList = new ArrayList<ProductCategory>();
		productCategoryList.add(productCategory);
		productCategoryList.add(productCategory2);
		int effectNum = productCategoryDao.batchInsertProductCategory(productCategoryList);
		assertEquals(2,effectNum);
	}

	@Test
	public void CdeleteProductCategory() {
		long shopId = 1;
		List<ProductCategory> productCategoryList = productCategoryDao.queryProductCategoryList(shopId);
		for(ProductCategory pc : productCategoryList){
			if("测试类别3".equals(pc.getProductCategoryName()) || "测试类别4".equals(pc.getProductCategoryName())){
				int effectNum = productCategoryDao.deleteProductCategory(pc.getProductCategoryId(),shopId);
				assertEquals(1,effectNum);
			}
		}
	}
}