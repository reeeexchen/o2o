package com.imooc.o2o.entity;

import java.util.Date;

/**
 * @Author:REX
 * @Date: Create in 14:00 2018/3/16
 */
public class PersonInfo {
	private Long userId;
	private String name;
	private String profileImg;
	private String email;
	private String gender;
	private Integer enableStatus;
	//1.顾客 2.店家 3.超级管理员
	private Integer userType;
	private Date createTime;
	private Date editTime;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProfileImg() {
		return profileImg;
	}

	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getEnableStatus() {
		return enableStatus;
	}

	public void setEnableStatus(Integer enableStatus) {
		this.enableStatus = enableStatus;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getEditTime() {
		return editTime;
	}

	public void setEditTime(Date editTime) {
		this.editTime = editTime;
	}

	@Override
	public String toString() {
		return "PersonInfo{" + "userId=" + userId + ", name='" + name + '\'' + ", profileImg='" + profileImg + '\'' + ", email='" + email + '\'' + ", gender='" + gender + '\'' + ", enableStatus=" + enableStatus + ", userType=" + userType + ", createTime=" + createTime + ", editTime=" + editTime + '}';
	}
}
