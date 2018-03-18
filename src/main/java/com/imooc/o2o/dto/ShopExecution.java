package com.imooc.o2o.dto;

import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.enums.ShopStateEnum;

import java.util.List;

/**
 * @Author:REX
 * @Date: Create in 16:07 2018/3/17
 */
public class ShopExecution {
	//结果状态
	private int state;

	//状态标识
	private String stateInfo;

	//店铺数量
	private int count;

	//操作的店铺（增删改查）
	private Shop shop;

	//shop列表（查询店铺列表时所用）
	private List<Shop> shopList;

	public ShopExecution(){

	}

	//店铺操作失败
	public ShopExecution(ShopStateEnum stateEnum){
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}

	//店铺操作成功
	public ShopExecution(ShopStateEnum stateEnum, Shop shop) {
		this.state = state;
		this.stateInfo = stateInfo;
		this.shop = shop;
	}

	//店铺操作成功
	public ShopExecution(ShopStateEnum stateEnum, List<Shop> shopList) {
		this.state = state;
		this.stateInfo = stateInfo;
		this.shopList = shopList;
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

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public List<Shop> getShopList() {
		return shopList;
	}

	public void setShopList(List<Shop> shopList) {
		this.shopList = shopList;
	}

	@Override
	public String toString() {
		return "ShopExecution{" + "state=" + state + ", stateInfo='" + stateInfo + '\'' + ", count=" + count + ", shop=" + shop + ", shopList=" + shopList + '}';
	}
}
