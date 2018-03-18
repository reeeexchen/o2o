package com.imooc.o2o.enums;

/**
 * @Author:REX
 * @Date: Create in 16:36 2018/3/18
 */
public enum ShopCategoryStateEnum {
	SUCCESS(0,"创建成功"),
	INNER_ERROR(-1001,"内部错误_操作失败"),
	EMPTY(-1002,"区域信息为空")
	;

	private int state;
	private String stateInfo;

	ShopCategoryStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public static ShopCategoryStateEnum stateOf(int index) {
		for (ShopCategoryStateEnum state : values()) {
			if (state.getState() == index) {
				return state;
			}
		}
		return null;
	}
}
