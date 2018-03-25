package com.imooc.o2o.service;

import com.imooc.o2o.dto.ProductCategoryExecution;
import com.imooc.o2o.entity.ProductCategory;
import com.imooc.o2o.exception.ProductCategoryOperationException;

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

	/**
	 * @param productCategoryList
	 * @return
	 * @throws ProductCategoryOperationException
	 */
	ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList)
		throws ProductCategoryOperationException;

	/**
	 * 将此类别下的商品里的类别ID置空，再删除该商品类别
	 * @param productCategoryId
	 * @param shopId
	 * @return
	 * @throws ProductCategoryOperationException
	 */
	ProductCategoryExecution deleteProductCategory(long productCategoryId,long shopId)
			throws ProductCategoryOperationException;
}
