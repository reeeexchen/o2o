package com.imooc.o2o.enums;

/**
 * @Author:REX
 * @Date: Create in 21:04 2018/5/13
 */
public enum WechatAuthStateEnum {
	LOGINFAIL(-1, "OPEN-ID输入错误"), SUCCESS(0, "操作成功"), NULL_AUTH_INFO(-1006, "注册信息为空");

	private int state;
	private String stateInfo;

	WechatAuthStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	/*
	 * 依据传入的state返回相应的enum值
	 * */
	public static WechatAuthStateEnum stateOf(int state) {
		for (WechatAuthStateEnum stateEnum : values()) {
			if (stateEnum.getState() == state) {
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
