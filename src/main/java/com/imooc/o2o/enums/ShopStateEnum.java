package com.imooc.o2o.enums;

/**
 * @Author:REX
 * @Date: Create in 16:10 2018/3/17
 */
public enum ShopStateEnum {
	CHECK(0,"审核中"),
	OFFLINE(-1,"非法店铺"),
	SUCCESS(1,"成功注册"),
	PASS(2,"通过认证"),
	INNER_ERROR(-1001,"系统内部错误"),
	NULL_SHOP_ID(-1002,"SHOP-ID为空"),
	NULL_SHOP(-1003,"SHOP店铺信息为空");

	private int state;
	private String stateInfo;

	ShopStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}
	/*
	* 依据传入的state返回相应的enum值
	* */
	public static ShopStateEnum stateOf(int state){
		for(ShopStateEnum stateEnum : values()){
			if(stateEnum.getState() == state){
				return stateEnum;
			}
		}
		return null;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}
}
