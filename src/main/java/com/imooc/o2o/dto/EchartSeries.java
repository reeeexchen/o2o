package com.imooc.o2o.dto;

import java.util.List;

/**
 * @Author: REX
 * @Date: Create in 17:12 2018/7/26
 */
public class EchartSeries {
	private String name;
	private String type = "bar";
	private List<String> data;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<String> getData() {
		return data;
	}

	public void setData(List<String> data) {
		this.data = data;
	}
}
