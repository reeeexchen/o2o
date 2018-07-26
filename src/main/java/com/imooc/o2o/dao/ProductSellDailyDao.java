package com.imooc.o2o.dao;

import com.imooc.o2o.entity.ProductSellDaily;
import com.imooc.o2o.entity.UserAwardMap;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * @Author: REX
 * @Date: Create in 21:12 2018/7/24
 */
public interface ProductSellDailyDao {
	/**
	 * 根据查询条件，分页返回商品日销售统计
	 *
	 * @param productSellDailyCondition
	 * @param beginTime
	 * @param endTime
	 * @return
	 */
	List<ProductSellDaily> queryProductSellDailyList(
			@Param("productSellDailyCondition") ProductSellDaily productSellDailyCondition,
			@Param("beginTime") Date beginTime, @Param("endTime") Date endTime);

	/**
	 * 对于平台所有商品的日销售量统计
	 * @return
	 */
	int insertProductSellDaily();
	int insertDefaultProductSellDaily();
}
