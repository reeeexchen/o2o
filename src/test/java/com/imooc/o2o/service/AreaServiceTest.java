package com.imooc.o2o.service;

import com.imooc.o2o.entity.Area;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Author:REX
 * @Date: Create in 23:59 2018/5/29
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AreaServiceTest {

	@Autowired
	private AreaService areaService;
	@Autowired
	private CacheService cacheService;

	@Test
	public void getAreaList() {
		List<Area> areaList = areaService.getAreaList();
		Assert.assertEquals(4,areaList.size());
		Assert.assertEquals("会同美食街",areaList.get(0).getAreaName());

		cacheService.removeFromCache(areaService.AREALISTKEY);
		areaList = areaService.getAreaList();
		Assert.assertEquals(4,areaList.size());
	}
}