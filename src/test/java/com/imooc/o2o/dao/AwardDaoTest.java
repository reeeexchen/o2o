package com.imooc.o2o.dao;

import com.imooc.o2o.entity.Award;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author: REX
 * @Date: Create in 14:59 2018/7/24
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AwardDaoTest {

	@Autowired
	private AwardDao awardDao;

	@Test
	public void AinsertAward() {
		long shopId = 1;
		Award award1 = new Award();
		award1.setAwardName("测试1");
		award1.setAwardImg("img1");
		award1.setPoint(5);
		award1.setPriority(1);
		award1.setEnableStatus(1);
		award1.setCreateTime(new Date());
		award1.setEditTime(new Date());
		award1.setShopId(shopId);
		int effectedNum = awardDao.insertAward(award1);
		assertEquals(1,effectedNum);

		Award award2 = new Award();
		award2.setAwardName("测试2");
		award2.setAwardImg("img2");
		award2.setPoint(2);
		award2.setPriority(2);
		award2.setEnableStatus(0);
		award2.setCreateTime(new Date());
		award2.setEditTime(new Date());
		award2.setShopId(shopId);
		effectedNum = awardDao.insertAward(award2);
		assertEquals(1,effectedNum);
	}

	@Test
	public void BqueryAwardList() {
		Award award = new Award();
		List<Award> awardList = awardDao.queryAwardList(award,0,3);
		assertEquals(3,awardList.size());

		int count = awardDao.queryAwardCount(award);
		assertEquals(4,count);

		award.setAwardName("测试");
		awardList = awardDao.queryAwardList(award,0,3);
		assertEquals(2,awardList.size());
		count = awardDao.queryAwardCount(award);
		assertEquals(2,count);
	}

	@Test
	public void CqueryAwardByAwardId() {
		Award awardCondition = new Award();
		awardCondition.setAwardName("测试1");
		List<Award> awardList = awardDao.queryAwardList(awardCondition,0,1);
		assertEquals(1,awardList.size());

		Award award = awardDao.queryAwardByAwardId(awardList.get(0).getAwardId());
		assertEquals("测试1",award.getAwardName());
	}

	@Test
	public void DupdateAward() {
		Award awardCondition = new Award();
		awardCondition.setAwardName("测试1");
		List<Award> awardList = awardDao.queryAwardList(awardCondition,0,1);

		awardList.get(0).setAwardName("测试11");
		int effectedNum = awardDao.updateAward(awardList.get(0));
		assertEquals(1,effectedNum);

		Award award = awardDao.queryAwardByAwardId(awardList.get(0).getAwardId());
		assertEquals("测试11",award.getAwardName());
	}


	@Test
	public void EdeleteAward() {
		Award awardCondition = new Award();
		awardCondition.setAwardName("测试");

		List<Award> awardList = awardDao.queryAwardList(awardCondition,0,3);
		assertEquals(2,awardList.size());
		for(Award award:awardList){
			int effectedNum = awardDao.deleteAward(award.getAwardId(),award.getShopId());
			assertEquals(1,effectedNum);
		}
	}
}