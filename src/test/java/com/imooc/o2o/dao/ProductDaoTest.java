package com.imooc.o2o.dao;

import com.imooc.o2o.entity.Product;
import com.imooc.o2o.entity.ProductCategory;
import com.imooc.o2o.entity.ProductImg;
import com.imooc.o2o.entity.Shop;
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
 * @Date: Create in 15:53 2018/3/24
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductDaoTest {
	@Autowired
	private ProductDao productDao;

	@Autowired
	private ProductImgDao productImgDao;

	@Test
	public void AinsertProduct() {
		Shop shop1 = new Shop();
		shop1.setShopId(1);
		Shop shop2 = new Shop();
		shop2.setShopId(8);
		ProductCategory pc1 = new ProductCategory();
		pc1.setProductCategoryId(1L);
		//初始化三个商品实例并添加进shopId为1的店铺中
		//同时商品类别ID也为1
		Product product1 = new Product();
		product1.setProductName("TEST1");
		product1.setProductDesc("TEST DESC 1");
		product1.setImgAddr("TEST IMG 1");
		product1.setPriority(1);
		product1.setEnableStatus(1);
		product1.setCreateTime(new Date());
		product1.setEditTime(new Date());
		product1.setShop(shop1);
		product1.setProductCategory(pc1);

		Product product2 = new Product();
		product2.setProductName("TEST2");
		product2.setProductDesc("TEST DESC 2");
		product2.setImgAddr("TEST IMG 2");
		product2.setPriority(2);
		product2.setEnableStatus(0);
		product2.setCreateTime(new Date());
		product2.setEditTime(new Date());
		product2.setShop(shop1);
		product2.setProductCategory(pc1);

		Product product3 = new Product();
		product3.setProductName("TEST3");
		product3.setProductDesc("TEST DESC 3");
		product3.setImgAddr("TEST IMG 3");
		product3.setPriority(3);
		product3.setEnableStatus(1);
		product3.setCreateTime(new Date());
		product3.setEditTime(new Date());
		product3.setShop(shop2);
		product3.setProductCategory(pc1);

		//判断是否添加成功
		int effectNUm = productDao.insertProduct(product1);
		assertEquals(1, effectNUm);
		effectNUm = productDao.insertProduct(product2);
		assertEquals(1, effectNUm);
		effectNUm = productDao.insertProduct(product3);
		assertEquals(1, effectNUm);
	}

	@Test
	public void BqueryProductList() {
		Product product = new Product();
		//取0页 每页3条记录
		List<Product> productList = productDao.queryProductList(product,0,3);
		assertEquals(3,productList.size());
		//查询总共记录
		int count = productDao.queryProductCount(product);
		assertEquals(6,count);

		product.setProductName("TEST");
		//取0页 每页3条记录 条件:商品名
		productList = productDao.queryProductList(product,0,3);
		assertEquals(2,productList.size());
		//查询总共记录 条件:商品名
		count = productDao.queryProductCount(product);
		assertEquals(6,count);

		Shop shop = new Shop();
		shop.setShopId(8);
		product.setShop(shop);
		//取0页 每页3条记录 条件:商铺ID
		productList = productDao.queryProductList(product,0,3);
		assertEquals(1,productList.size());
		//查询总共记录 条件:商铺ID
		count = productDao.queryProductCount(product);
		assertEquals(1,count);
	}

	@Test
	public void CqueryProductById() {
		long productId = 1;
		ProductImg productImg1 = new ProductImg();
		productImg1.setImgAddr("图片1");
		productImg1.setImgDesc("测试图片1");
		productImg1.setPriority(1);
		productImg1.setCreateTime(new Date());
		productImg1.setProductId(productId);
		ProductImg productImg2 = new ProductImg();

		productImg2.setImgAddr("图片2");
		productImg2.setPriority(1);
		productImg2.setCreateTime(new Date());
		productImg2.setProductId(productId);

		List<ProductImg> productImgList = new ArrayList<ProductImg>();
		productImgList.add(productImg1);
		productImgList.add(productImg2);
		//批量插入productId的图片
		int effectedNum = productImgDao.batchInsertProductImg(productImgList);
		assertEquals(2, effectedNum);
		//查询productId的product
		Product product = productDao.queryProductById(productId);
		//查询productId为相应id的图片数目
		assertEquals(2, product.getProductImgList().size());
		//删除productId的图片
		effectedNum = productImgDao.deleteProductImgByProductId(productId);
		assertEquals(2, effectedNum);
	}


	@Test
	public void DupdateProduct() {
		Product product = new Product();
		ProductCategory pc = new ProductCategory();
		Shop shop = new Shop();
		shop.setShopId(1);
		pc.setProductCategoryId(2L);
		product.setShop(shop);
		product.setProductId(1L);
		product.setProductCategory(pc);
		product.setProductName("第2个产品");
		int effectNum = productDao.updateProduct(product);
		assertEquals(1,effectNum);
	}

	@Test
	public void EupdateProductCategoryToNull() {
	}

	@Test
	public void FdeleteProduct() {
		int effectNum = productDao.deleteProduct(2,1);
		assertEquals(1,effectNum);
	}

	@Test
	public void queryProductCount() {
	}


}