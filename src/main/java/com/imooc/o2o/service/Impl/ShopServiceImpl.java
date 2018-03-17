package com.imooc.o2o.service.Impl;

import com.imooc.o2o.dao.ShopDao;
import com.imooc.o2o.dto.ShopExecution;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.enums.ShopStateEnum;
import com.imooc.o2o.exception.ShopOperationException;
import com.imooc.o2o.service.ShopService;
import com.imooc.o2o.util.ImageUtil;
import com.imooc.o2o.util.PathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.util.Date;

/**
 * @Author:REX
 * @Date: Create in 16:23 2018/3/17
 */
@Service
public class ShopServiceImpl implements ShopService{

	@Autowired
	private ShopDao shopDao;

	@Override
	@Transactional
	public ShopExecution addShop(Shop shop, File shopImg) {
		//空值判断
		if(shop == null){
			return new ShopExecution(ShopStateEnum.NULL_SHOP);
		}
		try {
			//初始值
			shop.setEnableStatus(0);
			shop.setCreateTime(new Date());
			shop.setEditTime(new Date());
			//添加店铺信息
			int effectedNum = shopDao.insertShop(shop);
			if(effectedNum <= 0){
				throw new ShopOperationException("店铺创建失败");
			}else{
				if(shopImg != null){
					//存储图片
					try {
						addShopImg(shop,shopImg);
					} catch (Exception e) {
						throw new ShopOperationException("ADD_SHOP_IMG_ERROR : " + e.getMessage());
					}
					//更新店铺的图片地址
					effectedNum = shopDao.updateShop(shop);
					if(effectedNum <= 0){
						throw new ShopOperationException("更新店铺的图片失败");
					}
				}
			}
		}catch (Exception e){
			throw new ShopOperationException("ADD_SHOP_ERROR : " + e.getMessage());
		}
		return new ShopExecution(ShopStateEnum.CHECK,shop);
	}

	private void addShopImg(Shop shop, File shopImg) {
		//获取shop图片目录的相对值路径
		String dest = PathUtil.getShopImagePath(shop.getShopId());
		String shopImgAddr = ImageUtil.generateThumbnail(shopImg,dest);
		shop.setShopImg(shopImgAddr);
	}

	@Override
	public ShopExecution modifyShop(Shop shop, File shopImg) {
		return null;
	}

	@Override
	public Shop getByShopId(long shopId) {
		return null;
	}
}
