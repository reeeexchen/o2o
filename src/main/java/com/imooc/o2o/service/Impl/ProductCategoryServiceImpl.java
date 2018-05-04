package com.imooc.o2o.service.Impl;

import com.imooc.o2o.dao.ProductCategoryDao;
import com.imooc.o2o.dao.ProductDao;
import com.imooc.o2o.dto.ProductCategoryExecution;
import com.imooc.o2o.entity.ProductCategory;
import com.imooc.o2o.enums.ProductCategoryStateEnum;
import com.imooc.o2o.exception.ProductCategoryOperationException;
import com.imooc.o2o.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author:REX
 * @Date: Create in 23:26 2018/3/23
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

	@Autowired
	private ProductCategoryDao productCategoryDao;
	@Autowired
	private ProductDao productDao;

	@Override
	public List<ProductCategory> getProductCategoryList(long shopId) {
		return productCategoryDao.queryProductCategoryList(shopId);
	}

	@Override
	public ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategoryList) throws ProductCategoryOperationException {
		//空值判断
		if(productCategoryList != null && productCategoryList.size() > 0){
			try{
				int effectNum = productCategoryDao.batchInsertProductCategory(productCategoryList);
				if(effectNum <= 0){
					throw new ProductCategoryOperationException("商品类别创建失败");
				}else{
					return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS,productCategoryList);
				}
			}catch (Exception e){
				throw new ProductCategoryOperationException("batchAddProductCategory ERROR" + e.getMessage());
			}
		}else{
			return new ProductCategoryExecution(ProductCategoryStateEnum.EMPTY_LIST);
		}
	}

	@Override
	@Transactional
	public ProductCategoryExecution deleteProductCategory(long productCategoryId, long shopId) throws ProductCategoryOperationException {
		// 将此商品类别下的商品的类别ID置为空 -> ProductDao
		try{
			int effectedNum = productDao.updateProductCategoryToNull(productCategoryId);
			if(effectedNum < 0){
				throw new RuntimeException("商品类别更新失败");
			}
		}catch (Exception e){
			throw new RuntimeException("DELETE PRODUCT_CATEGORY ERROR : " + e.getMessage());
		}
		// 删除productCategory
		try {
			int effectNum = productCategoryDao.deleteProductCategory(productCategoryId,shopId);
			if(effectNum <= 0){
				throw new ProductCategoryOperationException("店铺商品类别删除失败");
			}else{
				return new ProductCategoryExecution(ProductCategoryStateEnum.SUCCESS);
			}
		}catch (Exception e){
			throw new ProductCategoryOperationException("DELETE PRODUCT_CATEGORY ERROR : " + e.getMessage());
		}
	}
}
