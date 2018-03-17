package com.imooc.o2o.entity;

import java.util.Date;

/**
 * @Author:REX
 * @Date: Create in 13:50 2018/3/16
 */
public class Area {
	private Integer areaId;
	private String areaName;
	private Integer priority;
	private Date createTime;
	private Date editTime;

	public Integer getAreaId() {
		return areaId;
	}

	public void setAreaId(Integer areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
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
		return "Area{" + "areaId=" + areaId + ", areaName='" + areaName + '\'' + ", priority=" + priority + ", createTime=" + createTime + ", editTime=" + editTime + '}';
	}
}
