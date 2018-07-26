package com.imooc.o2o.dao;

import com.imooc.o2o.entity.ProductSellDaily;
import com.imooc.o2o.entity.Shop;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author: REX
 * @Date: Create in 22:23 2018/7/24
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductSellDailyDaoTest {
	@Autowired
	private ProductSellDailyDao productSellDailyDao;

	@Test
	public void AinsertProductSellDaily() {
		int effect = productSellDailyDao.insertProductSellDaily();
		assertEquals(3,effect);
	}
	@Test
	public void BinsertDefaultProductSellDaily() {
		int effect = productSellDailyDao.insertDefaultProductSellDaily();
		assertEquals(10,effect);
	}

	@Test
	public void CqueryProductSellDailyList() {
		ProductSellDaily productSellDaily = new ProductSellDaily();
		Shop shop = new Shop();
		shop.setShopId(1L);
		productSellDaily.setShop(shop);
		List<ProductSellDaily> productSellDailyList = productSellDailyDao.queryProductSellDailyList(productSellDaily,null,null);
		assertEquals(2,productSellDailyList.size());
	}

}