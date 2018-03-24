package com.imooc.o2o.enums;

/**
 * @Author:REX
 * @Date: Create in 23:41 2018/3/23
 */
public enum ProductCategoryStateEnum {

	INNER_ERROR()
	;


	private int state;
	private String stateInfo;

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
