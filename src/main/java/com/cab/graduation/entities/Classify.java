package com.cab.graduation.entities;

import java.util.Set;

public class Classify {
	
	private Integer id;
	private String category;
	private String productArea;
	private Set<Goods> goods;

	public Classify() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Classify(Integer id) {
		super();
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getProductArea() {
		return productArea;
	}

	public void setProductArea(String productArea) {
		this.productArea = productArea;
	}

	public Set<Goods> getGoods() {
		return goods;
	}

	public void setGoods(Set<Goods> goods) {
		this.goods = goods;
	}

	@Override
	public String toString() {
		return "Classify [id=" + id + ", category=" + category + ", productArea=" + productArea + "]";
	}

}
