package com.imooc.o2o.enums;

/**
 * @Author: REX
 * @Date: Create in 14:23 2018/7/24
 */
public enum ShopAuthMapStateEnum {
	SUCCESS(1,"操作成功"),
	INNER_ERROR(-1001,"操作失败"),
	NULL_SHOPAUTH_ID(-1002,"SHOP-AUTH-ID为空"),
	NULL_SHOPAUTH_INFO(-1003,"SHOP-AUTH-INFO为空")
	;
	private int state;
	private String stateInfo;

	ShopAuthMapStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public static ShopAuthMapStateEnum stateOf(int index) {
		for (ShopAuthMapStateEnum state : values()) {
			if (state.getState() == index) {
				return state;
			}
		}
		return null;
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
}
