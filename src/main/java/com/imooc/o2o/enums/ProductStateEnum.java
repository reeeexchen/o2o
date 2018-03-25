package com.imooc.o2o.enums;

/**
 * @Author:REX
 * @Date: Create in 23:40 2018/3/24
 */
public enum ProductStateEnum {
	CHECK(0,"审核中"),
	OFFLINE(-1,"非法商品"),
	SUCCESS(1,"操作成功"),
	INNER_ERROR(-1001,"操作失败"),
	EMPTY(-1002,"添加数目少于1")
	;

	private int state;
	private String stateInfo;

	ProductStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public static ProductStateEnum stateOf(int index) {
		for (ProductStateEnum state : values()) {
			if (state.getState() == index) {
				return state;
			}
		}
		return null;
	}
}
