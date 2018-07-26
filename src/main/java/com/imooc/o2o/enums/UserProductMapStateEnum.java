package com.imooc.o2o.enums;

/**
 * @Author: REX
 * @Date: Create in 14:57 2018/7/26
 */
public enum UserProductMapStateEnum {
	SUCCESS(1,"操作成功")
	;
	private int state;
	private String stateInfo;

	UserProductMapStateEnum(int state,String stateInfo){
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public static UserProductMapStateEnum stateOf(int index){
		for(UserProductMapStateEnum state:values()){
			if(state.getState() == index){
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
