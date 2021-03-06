package com.imooc.o2o.enums;

/**
 * @Author:REX
 * @Date: Create in 23:41 2018/3/23
 */
public enum ProductCategoryStateEnum {

	CHECK(0,"审核中"),
	OFFLINE(-1,"非法店铺"),
	SUCCESS(1,"操作成功"),
	INNER_ERROR(-1001,"操作失败"),
	EMPTY_LIST(-1002,"添加数目少于1")
	;


	private int state;
	private String stateInfo;

	ProductCategoryStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public static ProductCategoryStateEnum stateOf(int index) {
		for (ProductCategoryStateEnum state : values()) {
			if (state.getState() == index) {
				return state;
			}
		}
		return null;
	}
}
