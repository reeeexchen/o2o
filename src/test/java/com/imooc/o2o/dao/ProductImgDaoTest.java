package com.imooc.o2o.dao;

import com.imooc.o2o.entity.ProductImg;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author:REX
 * @Date: Create in 15:09 2018/3/24
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductImgDaoTest {
	@Autowired
	private ProductImgDao productImgDao;

	@Test
	public void AbatchInsertProductImg() {
		//productId为1的商品里添加两个详情图片记录
		ProductImg productImg1 = new ProductImg();
		productImg1.setImgAddr("IMG1");
		productImg1.setImgDesc("IMG1 DESC");
		productImg1.setPriority(1);
		productImg1.setCreateTime(new Date());
		productImg1.setProductId(1L);

		ProductImg productImg2 = new ProductImg();
		productImg2.setImgAddr("IMG2");
		productImg2.setImgDesc("IMG2 DESC");
		productImg2.setPriority(1);
		productImg2.setCreateTime(new Date());
		productImg2.setProductId(1L);

		List<ProductImg> productImgList = new ArrayList<ProductImg>();
		productImgList.add(productImg1);
		productImgList.add(productImg2);
		int effectNum = productImgDao.batchInsertProductImg(productImgList);
		assertEquals(2,effectNum);
	}

	@Test
	public void BqueryProductImgList() {
		//检查productId为1的商品是否仅有两张商品详情图片
		List<ProductImg> productImgList = productImgDao.queryProductImgList(1L);
		assertEquals(2,productImgList.size());
	}

	@Test
	public void CdeleteProductImgByProductId() throws Exception{
		//删除新增的两条商品详情图片记录
		long productId = 1;
		int effectNum = productImgDao.deleteProductImgByProductId(productId);
		assertEquals(2,effectNum);
	}
}