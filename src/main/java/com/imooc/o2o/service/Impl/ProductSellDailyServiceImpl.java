package com.imooc.o2o.service.Impl;

import com.imooc.o2o.dao.ProductSellDailyDao;
import com.imooc.o2o.entity.ProductSellDaily;
import com.imooc.o2o.service.ProductSellDailyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author: REX
 * @Date: Create in 14:17 2018/7/26
 */
@Service
public class ProductSellDailyServiceImpl implements ProductSellDailyService {
	@Autowired
	private ProductSellDailyDao productSellDailyDao;

	private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Override
	public void dailyCalculate() {
		log.info("Quartz Running");
		productSellDailyDao.insertProductSellDaily();
		productSellDailyDao.insertDefaultProductSellDaily();
	}

	@Override
	public List<ProductSellDaily> listProductSellDaily(ProductSellDaily productSellDailyCondition, Date beginTime, Date endTime) {
		return productSellDailyDao.queryProductSellDailyList(productSellDailyCondition,beginTime,endTime);
	}
}
