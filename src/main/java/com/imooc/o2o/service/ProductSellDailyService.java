package com.imooc.o2o.service;

import com.imooc.o2o.entity.ProductSellDaily;

import java.util.Date;
import java.util.List;

/**
 * @Author: REX
 * @Date: Create in 14:17 2018/7/26
 */
public interface ProductSellDailyService {
	/**
	 * 每日定时店铺商品销量统计
	 */
	void dailyCalculate();

	/**
	 * 根据查询条件返回商品日销售统计列表
	 * @param productSellDailyCondition
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	List<ProductSellDaily> listProductSellDaily(ProductSellDaily productSellDailyCondition, Date beginTime,Date endTime);
}
