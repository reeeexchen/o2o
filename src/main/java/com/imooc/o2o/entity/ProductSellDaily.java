package com.imooc.o2o.entity;

import java.util.Date;

/**
 * @Author:REX
 * @Date: Create in 11:11 2018/5/27
 */
public class ProductSellDaily {
	private Long productSellDailyId;
	// 某日销量
	private Date createTime;
	// 销量
	private Integer total;
	// 商品
	private Product product;
	// 店铺
	private Shop shop;

	public Long getProductSellDailyId() {
		return productSellDailyId;
	}

	public void setProductSellDailyId(Long productSellDailyId) {
		this.productSellDailyId = productSellDailyId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}
}
