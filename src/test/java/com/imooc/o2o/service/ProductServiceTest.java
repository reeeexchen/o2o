package com.imooc.o2o.service;

import com.imooc.o2o.dto.ImageHolder;
import com.imooc.o2o.dto.ProductExecution;
import com.imooc.o2o.entity.Product;
import com.imooc.o2o.entity.ProductCategory;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.enums.ProductStateEnum;
import com.imooc.o2o.exception.ShopOperationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author:REX
 * @Date: Create in 10:50 2018/3/25
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"
		,"classpath:spring/spring-service.xml"})
public class ProductServiceTest {

	@Autowired
	private ProductService productService;

	@Test
	public void addProduct() throws ShopOperationException, IOException {
		//创建shopId为1 productCategoryId为1的商品实例 给成员变量赋值
		Product product = new Product();
		Shop shop = new Shop();
		ProductCategory pc = new ProductCategory();
		shop.setShopId(1L);
		pc.setProductCategoryId(1L);
		product.setShop(shop);
		product.setProductCategory(pc);
		product.setProductName("测试商品1");
		product.setProductDesc("测试商品1 DESC");
		product.setPriority(20);
		product.setCreateTime(new Date());
		product.setEnableStatus(ProductStateEnum.SUCCESS.getState());
		//创建缩略图文件流
		File thumbnailFile = new File("D:\\JavaDemo\\images\\product1.jpg");
		InputStream is  = new FileInputStream(thumbnailFile);
		ImageHolder thumbnail = new ImageHolder(thumbnailFile.getName(),is);
		//创建两个商品详情图 并添加
		File productImg1 = new File("D:\\JavaDemo\\images\\product1.jpg");
		InputStream is1 = new FileInputStream(productImg1);
		File productImg2 = new File("D:\\JavaDemo\\images\\product2.jpg");
		InputStream is2 = new FileInputStream(productImg2);
		List<ImageHolder> productImgList = new ArrayList<ImageHolder>();
		productImgList.add(new ImageHolder(productImg1.getName(),is1));
		productImgList.add(new ImageHolder(productImg2.getName(),is2));
		//添加商品并验证
		ProductExecution pe = productService.addProduct(product,thumbnail,productImgList);
		assertEquals(ProductStateEnum.SUCCESS.getState(),pe.getState());
	}
}