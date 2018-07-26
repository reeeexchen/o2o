package com.imooc.o2o.dto;

import java.util.HashSet;

/**
 * @Author: REX
 * @Date: Create in 17:11 2018/7/26
 */
public class EchartXAxis {
	private String type = "category";
	private HashSet<String> data;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public HashSet<String> getData() {
		return data;
	}

	public void setData(HashSet<String> data) {
		this.data = data;
	}
}
