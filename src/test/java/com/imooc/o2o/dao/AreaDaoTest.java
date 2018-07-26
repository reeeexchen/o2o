package com.imooc.o2o.dao;

import com.imooc.o2o.entity.Area;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author:REX
 * @Date: Create in 23:20 2018/5/29
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AreaDaoTest {

	@Autowired
	private AreaDao areaDao;

	@Test
	public void queryArea() {
		List<Area> areaList = areaDao.queryArea();
		assertEquals(4,areaList.size());
		for(Area area : areaList){
			System.out.println(area.toString());
		}
	}
}