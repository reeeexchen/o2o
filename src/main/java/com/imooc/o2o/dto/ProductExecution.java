package com.imooc.o2o.dto;

import com.imooc.o2o.entity.Product;
import com.imooc.o2o.enums.ProductStateEnum;

import java.util.List;

/**
 * @Author:REX
 * @Date: Create in 23:36 2018/3/24
 */
public class ProductExecution {
	//结果状态
	private int state;
	//状态标识
	private String stateInfo;
	//商品数目
	private int count;
	//操作的product(增删改用)
	private Product product;
	//获取的product列表(查询商品列表用)
	private List<Product> productList;

	public ProductExecution() {
	}

	//FAILURE
	public ProductExecution(ProductStateEnum stateEnum) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}

	//SUCCESS
	public ProductExecution(ProductStateEnum stateEnum,Product product) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.product = product;
	}
	public ProductExecution(ProductStateEnum stateEnum, List<Product> productList) {
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
		this.productList = productList;
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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public List<Product> getProductList() {
		return productList;
	}

	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
}
