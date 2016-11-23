package com.esprit.hypnotrip.presentation.mbeans;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class TestBean {
	private Integer rating1=500;
	public TestBean() {
		// TODO Auto-generated constructor stub
	}
	public Integer getRating1() {
		return rating1;
	}
	public void setRating1(Integer rating1) {
		this.rating1 = rating1;
	}

}
