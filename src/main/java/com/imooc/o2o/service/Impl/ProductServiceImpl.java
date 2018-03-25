package com.imooc.o2o.service.Impl;

import com.imooc.o2o.dao.ProductDao;
import com.imooc.o2o.dao.ProductImgDao;
import com.imooc.o2o.dto.ImageHolder;
import com.imooc.o2o.dto.ProductExecution;
import com.imooc.o2o.entity.Product;
import com.imooc.o2o.entity.ProductImg;
import com.imooc.o2o.enums.ProductStateEnum;
import com.imooc.o2o.exception.ProductOperationException;
import com.imooc.o2o.service.ProductService;
import com.imooc.o2o.util.ImageUtil;
import com.imooc.o2o.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author:REX
 * @Date: Create in 23:37 2018/3/24
 */
@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductDao productDao;
	@Autowired
	private ProductImgDao productImgDao;

	@Override
	@Transactional
	/*
	* 1.处理缩略图，获取缩略图相对路径并赋值给product
	* 2.往tb_product写入商品信息,获取productId
	* 3.结合productId批量处理商品详细图
	* 4.将商品详细图列表批量插入tb_product_img
	* */
	public ProductExecution addProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgHolderList) throws ProductOperationException, IOException {
		//空值判断
		if(product != null && product.getShop() != null && product.getShop().getShopId() != null){
			//设置默认属性
			product.setCreateTime(new Date());
			product.setEditTime(new Date());
			//默认为上架的状态
			product.setEnableStatus(1);
			//若商品缩略图不为空 则添加
			if(thumbnail != null){
				addThumbnail(product,thumbnail);
			}
			try{
				//创建商品信息
				int effectedNum = productDao.insertProduct(product);
				if(effectedNum <= 0){
					throw new ProductOperationException("(addProduct)创建商品失败");
				}
			}catch (Exception e){
				throw new ProductOperationException("(addProduct)创建商品失败：" + e.toString());
			}
			//若商品详细图不为空 则添加
			if(productImgHolderList != null && productImgHolderList.size() > 0){
				addProductImgList(product,productImgHolderList);
			}
			return new ProductExecution(ProductStateEnum.SUCCESS,product);
		}else{
			return new ProductExecution(ProductStateEnum.EMPTY);
		}
	}

	/**
	 * 添加缩略图
	 * @param product
	 * @param thumbnail
	 * @throws IOException
	 */
	private void addThumbnail(Product product,ImageHolder thumbnail) throws IOException {
		String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
		String thumbnailAddr = ImageUtil.generateThumbnail(thumbnail,dest);
		product.setImgAddr(thumbnailAddr);
	}

	private void addProductImgList(Product product,List<ImageHolder> productImgHolderList){
		//获取图片存储路径 直接存放到相应店铺的文件夹
		String dest = PathUtil.getShopImagePath(product.getShop().getShopId());
		List<ProductImg> productImgList = new ArrayList<ProductImg>();
		//遍历图片一次去处理 并添加进productImg实体类中
		for(ImageHolder productImgHolder : productImgHolderList){
			String imgAddr = ImageUtil.generateNormalImg(productImgHolder,dest);
			ProductImg productImg = new ProductImg();
			productImg.setImgAddr(imgAddr);
			productImg.setProductId(product.getProductId());
			productImg.setCreateTime(new Date());
			productImgList.add(productImg);
		}
		//如果确实有图片需要添加 执行批量添加
		if(productImgList.size() > 0){
			try {
				int effectNum = productImgDao.batchInsertProductImg(productImgList);
				if(effectNum <= 0){
					throw new ProductOperationException("(addProductImgList)创建商品详细图失败");
				}
			}catch (Exception e){
				throw new ProductOperationException("(addProductImgList)创建商品详情图失败：" + e.toString());
			}
		}
	}
}
