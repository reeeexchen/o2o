package com.imooc.o2o.dto;

import com.imooc.o2o.entity.ProductCategory;
import com.imooc.o2o.enums.ProductCategoryStateEnum;

import java.util.List;

/**
 * @Author:REX
 * @Date: Create in 10:51 2018/3/24
 */
public class ProductCategoryExecution {
	//结果状态
	private int state;
	//状态信息
	private String stateInfo;

	private List<ProductCategory> productCategoryList;

	public ProductCategoryExecution() {
	}

	//SUCCESS
	public ProductCategoryExecution(ProductCategoryStateEnum stateEnum, List<ProductCategory> productCategoryList) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.productCategoryList = productCategoryList;
	}

	//FAILURE
	public ProductCategoryExecution(ProductCategoryStateEnum stateEnum) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
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

	public List<ProductCategory> getProductCategoryList() {
		return productCategoryList;
	}

	public void setProductCategoryList(List<ProductCategory> productCategoryList) {
		this.productCategoryList = productCategoryList;
	}
}
