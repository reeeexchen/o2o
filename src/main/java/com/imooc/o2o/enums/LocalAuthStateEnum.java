package com.imooc.o2o.enums;

/**
 * @Author:REX
 * @Date: Create in 21:45 2018/5/19
 */
public enum LocalAuthStateEnum {
	LOGINFAIL(-1, "密码或帐号输入有误"), SUCCESS(0, "绑定成功"), NULL_AUTH_INFO(-1006,
			"注册信息为空"), ONLY_ONE_ACCOUNT(-1007,"最多只能绑定一个本地帐号");


	private int state;
	private String stateInfo;

	LocalAuthStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	/*
	 * 依据传入的state返回相应的enum值
	 * */
	public static LocalAuthStateEnum stateOf(int state) {
		for (LocalAuthStateEnum stateEnum : values()) {
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
