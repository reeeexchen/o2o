package com.imooc.o2o.dao;

import com.imooc.o2o.entity.ProductImg;

import java.util.List;

/**
 * @Author:REX
 * @Date: Create in 14:44 2018/3/24
 */
public interface ProductImgDao {

	List<ProductImg> queryProductImgList(long productId);

	/**
	 * 批量添加商品详情图片
	 * @param productImgList
	 * @return
	 */
	int batchInsertProductImg(List<ProductImg> productImgList);

	/**
	 * 删除指定商品的详情图
	 * @param productId
	 * @return
	 */
	int deleteProductImgByProductId(long productId);
}
