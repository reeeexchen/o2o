package com.imooc.o2o.dto;

import com.imooc.o2o.entity.WechatAuth;
import com.imooc.o2o.enums.WechatAuthStateEnum;

import java.util.List;

/**
 * @Author:REX
 * @Date: Create in 16:07 2018/3/17
 */
public class WechatAuthExecution {
	//结果状态
	private int state;

	//状态标识
	private String stateInfo;

	//WechatAuth数量
	private int count;

	//操作的WechatAuth（增删改查）
	private WechatAuth wechatAuth;

	//WechatAuth列表（查询WechatAuth列表时所用）
	private List<WechatAuth> wechatAuthList;

	public WechatAuthExecution(){

	}

	//WechatAuth操作失败
	public WechatAuthExecution(WechatAuthStateEnum stateEnum){
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}

	//WechatAuth操作成功
	public WechatAuthExecution(WechatAuthStateEnum stateEnum, WechatAuth wechatAuth) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.wechatAuth = wechatAuth;
	}

	//店铺操作成功
	public WechatAuthExecution(WechatAuthStateEnum stateEnum, List<WechatAuth> wechatAuthList) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.wechatAuthList = wechatAuthList;
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

	public WechatAuth getWechatAuth() {
		return wechatAuth;
	}

	public void setWechatAuth(WechatAuth wechatAuth) {
		this.wechatAuth = wechatAuth;
	}

	public List<WechatAuth> getWechatAuthList() {
		return wechatAuthList;
	}

	public void setWechatAuthList(List<WechatAuth> wechatAuthList) {
		this.wechatAuthList = wechatAuthList;
	}

	@Override
	public String toString() {
		return "WechatAuthExecution{" + "state=" + state + ", stateInfo='" + stateInfo + '\'' + ", count=" + count + ", wechatAuth=" + wechatAuth + ", wechatAuthList=" + wechatAuthList + '}';
	}
}
