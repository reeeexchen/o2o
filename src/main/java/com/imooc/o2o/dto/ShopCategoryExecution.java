package com.imooc.o2o.dto;

import com.imooc.o2o.entity.ShopCategory;
import com.imooc.o2o.enums.ShopCategoryStateEnum;

import java.util.List;

/**
 * @Author:REX
 * @Date: Create in 16:35 2018/3/18
 */
public class ShopCategoryExecution {

	private int state;
	private String stateInfo;
	private ShopCategory shopCategory;
	private List<ShopCategory> shopCategoryList;

	public ShopCategoryExecution() {
	}

	public ShopCategoryExecution(ShopCategoryStateEnum stateEnum) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}

	public ShopCategoryExecution(ShopCategoryStateEnum stateEnum, ShopCategory shopCategory) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.shopCategory = shopCategory;
	}

	public ShopCategoryExecution(ShopCategoryStateEnum stateEnum,List<ShopCategory> shopCategoryList) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.shopCategoryList = shopCategoryList;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}

	public ShopCategory getShopCategory() {
		return shopCategory;
	}

	public void setShopCategory(ShopCategory shopCategory) {
		this.shopCategory = shopCategory;
	}

	public List<ShopCategory> getShopCategoryList() {
		return shopCategoryList;
	}

	public void setShopCategoryList(List<ShopCategory> shopCategoryList) {
		this.shopCategoryList = shopCategoryList;
	}
}
