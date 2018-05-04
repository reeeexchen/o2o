package com.imooc.o2o.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 用户实体类 接收昵称 openid等用户信息
 * @Author:REX
 * @Date: Create in 23:13 2018/5/4
 */
public class WechatUser implements Serializable{

	// openId 标识公众号下唯一用户的id

	// 用户昵称
	@JsonProperty("openId")
	private String openId;
	// 性别
	@JsonProperty("sex")
	private int sex;
	// 省份
	@JsonProperty("province")
	private String province;
	// 城市
	@JsonProperty("city")
	private String city;
	// 国家
	@JsonProperty("country")
	private String country;
	// 头像
	@JsonProperty("headimgurl")
	private String headimgurl;
	// 语言
	@JsonProperty("language")
	private String language;
	// 权限
	@JsonProperty("privilege")
	private String[] privilege;

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String[] getPrivilege() {
		return privilege;
	}

	public void setPrivilege(String[] privilege) {
		this.privilege = privilege;
	}
}
