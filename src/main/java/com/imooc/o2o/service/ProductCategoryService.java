package com.imooc.o2o.service;

import com.imooc.o2o.entity.ProductCategory;

import java.util.List;

/**
 * @Author:REX
 * @Date: Create in 23:25 2018/3/23
 */
public interface ProductCategoryService {

	/**
	 * 根据shop id查询店铺所有商品类别信息
	 * @param shopId
	 * @return
	 */
	List<ProductCategory> getProductCategoryList(long shopId);
}
