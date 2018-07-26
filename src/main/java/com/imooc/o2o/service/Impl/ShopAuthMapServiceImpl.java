package com.imooc.o2o.service.Impl;

import com.imooc.o2o.dao.ShopAuthMapDao;
import com.imooc.o2o.dto.ShopAuthMapExecution;
import com.imooc.o2o.dto.ShopExecution;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.entity.ShopAuthMap;
import com.imooc.o2o.enums.ShopAuthMapStateEnum;
import com.imooc.o2o.enums.ShopCategoryStateEnum;
import com.imooc.o2o.exception.ShopAuthMapOperationException;
import com.imooc.o2o.service.ShopAuthMapService;
import com.imooc.o2o.util.PageCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Author: REX
 * @Date: Create in 14:35 2018/7/24
 */
@Service
public class ShopAuthMapServiceImpl implements ShopAuthMapService{
	@Autowired
	private ShopAuthMapDao shopAuthMapDao;

	@Override
	public ShopAuthMapExecution listShopAuthMapByShopId(Long shopId, Integer pageIndex, Integer pageSize) {
		if(shopId != null && pageIndex != null && pageSize != null){
			int beginIndex = PageCalculator.calculateRowIndex(pageIndex,pageSize);
			List<ShopAuthMap>shopAuthMapList = shopAuthMapDao.queryShopAuthMapListByShopId(shopId,pageIndex,pageSize);
			int count = shopAuthMapDao.queryShopAuthCountByShopId(shopId);
			ShopAuthMapExecution se = new ShopAuthMapExecution();
			se.setShopAuthMapList(shopAuthMapList);
			se.setCount(count);
			return se;
		}else{
			return null;
		}
	}

	@Override
	public ShopAuthMap getShopAuthMapById(Long shopAuthId) {
		return shopAuthMapDao.queryShopAuthMapById(shopAuthId);
	}

	@Override
	@Transactional
	public ShopAuthMapExecution addShopAuthMap(ShopAuthMap shopAuthMap) throws ShopAuthMapOperationException {
		if(shopAuthMap != null && shopAuthMap.getShop() != null && shopAuthMap.getShop().getShopId() != null
				&& shopAuthMap.getEmployee() != null && shopAuthMap.getEmployee().getUserId() != null){
			shopAuthMap.setCreateTime(new Date());
			shopAuthMap.setEditTime(new Date());
			shopAuthMap.setEnableStatus(1);
			try{
				// 添加授权信息
				int effect = shopAuthMapDao.insertShopAuthMap(shopAuthMap);
				if(effect <= 0){
					throw new ShopAuthMapOperationException("添加授权信息失败");
				}
				return new ShopAuthMapExecution(ShopAuthMapStateEnum.SUCCESS,shopAuthMap);
			}catch (Exception e){
				throw new ShopAuthMapOperationException("添加授权信息失败" + e.toString());
			}
		}else{
			return new ShopAuthMapExecution(ShopAuthMapStateEnum.NULL_SHOPAUTH_INFO);
		}
	}

	@Override
	@Transactional
	public ShopAuthMapExecution modifyShopAuthMap(ShopAuthMap shopAuthMap) throws ShopAuthMapOperationException {
		if(shopAuthMap == null || shopAuthMap.getShopAuthId() == null){
			return new ShopAuthMapExecution(ShopAuthMapStateEnum.NULL_SHOPAUTH_ID);
		}else{
			try {
				int effect = shopAuthMapDao.updateShopAuthMap(shopAuthMap);
				if(effect <= 0){
					return new ShopAuthMapExecution(ShopAuthMapStateEnum.INNER_ERROR);
				}else{
					return new ShopAuthMapExecution(ShopAuthMapStateEnum.SUCCESS,shopAuthMap);
				}
			}catch (Exception e){
				throw new ShopAuthMapOperationException("修改授权信息失败" + e.toString());
			}
		}
	}
}
