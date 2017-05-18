package com.cab.graduation.entities.dto;

public class Classify {
	private Integer id;
	private String category;

	public Classify() {

	}

	public Classify(com.cab.graduation.entities.Classify classify) {
		this.id = classify.getId();
		this.category = classify.getCategory();
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

}
