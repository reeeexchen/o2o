package com.imooc.o2o.dao;

import com.imooc.o2o.entity.ShopCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author:REX
 * @Date: Create in 15:39 2018/3/18
 */
public interface ShopCategoryDao {
	/**
	 * 获取店铺相关类别 查询店铺分类
	 * @param shopCategoryCondition
	 * @return
	 */
	List<ShopCategory> queryShopCategory(
			@Param("shopCategoryCondition") ShopCategory shopCategoryCondition);

	/**
	 *
	 * @param shopCategoryId
	 * @return
	 */
	ShopCategory queryShopCategoryById(long shopCategoryId);

	/**
	 *
	 * @param shopCategoryIdList
	 * @return
	 */
	List<ShopCategory> queryShopCategoryByIds(List<Long> shopCategoryIdList);

	/**
	 *
	 * @param shopCategory
	 * @return
	 */
	int insertShopCategory(ShopCategory shopCategory);

	/**
	 *
	 * @param shopCategory
	 * @return
	 */
	int updateShopCategory(ShopCategory shopCategory);

	/**
	 *
	 * @param shopCategoryId
	 * @return
	 */
	int deleteShopCategory(long shopCategoryId);

	/**
	 *
	 * @param shopCategoryIdList
	 * @return
	 */
	int batchDeleteShopCategory(List<Long> shopCategoryIdList);
}
