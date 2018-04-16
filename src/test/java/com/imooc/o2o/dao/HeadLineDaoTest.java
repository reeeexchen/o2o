package com.imooc.o2o.dao;

import com.imooc.o2o.entity.HeadLine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author:REX
 * @Date: Create in 23:03 2018/4/15
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class HeadLineDaoTest {

	@Autowired
	private HeadLineDao headLineDao;

	@Test
	public void queryHeadLine() {
		List<HeadLine> headLineList  = headLineDao.queryHeadLine(new HeadLine());
		assertEquals(2,headLineList.size());
	}
}