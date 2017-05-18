package com.cab.graduation.utils;

import java.util.List;

public class Page<T> {
	
	//从1开始
	private Integer pageStartNum;
	private Integer pageSize;
	private Integer totalSize;
	private Integer totalPage;
	private List<T> items;
	
	private boolean showGoodsManagePage;

	public Page() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Page(Integer pageStartNum, Integer pageSize) {
		super();
		this.pageStartNum = pageStartNum;
		this.pageSize = pageSize;
	}

	public Page(Integer pageStartNum, Integer pageSize, boolean showGoodsManagePage) {
		super();
		this.pageStartNum = pageStartNum;
		this.pageSize = pageSize;
		this.showGoodsManagePage = showGoodsManagePage;
	}

	public Integer getPageStartNum() {
		return pageStartNum;
	}

	public void setPageStartNum(Integer pageStartNum) {
		this.pageStartNum = pageStartNum;
	}


	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(Integer totalSize) {
		this.totalSize = totalSize;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}

	public boolean getShowGoodsManagePage() {
		return showGoodsManagePage;
	}

	public void setShowGoodsManagePage(boolean showGoodsManagePage) {
		this.showGoodsManagePage = showGoodsManagePage;
	}

	@Override
	public String toString() {
		return "Page [pageStartNum=" + pageStartNum + ", pageSize=" + pageSize + ", totalSize=" + totalSize
				+ ", totalPage=" + totalPage + ", items=" + items + ", showGoodsManagePage=" + showGoodsManagePage
				+ "]";
	}

}
