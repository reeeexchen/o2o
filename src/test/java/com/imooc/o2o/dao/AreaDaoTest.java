package com.imooc.o2o.dao;

import com.imooc.o2o.entity.Area;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author:REX
 * @Date: Create in 10:43 2018/3/17
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class AreaDaoTest {

	@Autowired
	private AreaDao areaDao;

	@Test
	public void queryArea() {
		List<Area> areaList = areaDao.queryArea();
		assertEquals(2,areaList.size());
		for(Area area : areaList){
			System.out.println(area);
		}
	}
}