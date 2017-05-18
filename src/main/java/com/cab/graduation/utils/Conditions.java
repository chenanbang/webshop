package com.cab.graduation.utils;

public class Conditions {
//	 String condition=" where g.classify.id = ? "
	
	private String sqlFragment;
	private String[] params;
	
	public Conditions() {

	}
	
	public Conditions(String sqlFragment, String[] params) {
		super();
		this.sqlFragment = sqlFragment;
		this.params = params;
	}

	public String getSqlFragment() {
		return sqlFragment;
	}
	public void setSqlFragment(String sqlFragment) {
		this.sqlFragment = sqlFragment;
	}

	public String[] getParams() {
		return params;
	}

	public void setParams(String[] params) {
		this.params = params;
	}

}
