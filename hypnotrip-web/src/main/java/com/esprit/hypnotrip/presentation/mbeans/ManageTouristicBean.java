package com.esprit.hypnotrip.presentation.mbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.esprit.hypnotrip.persistence.Touristicplace;
import com.esprit.hypnotrip.services.interfaces.PageServiceLocal;
import com.esprit.hypnotrip.services.interfaces.RateServiceLocal;

@ManagedBean
@ViewScoped
public class ManageTouristicBean {
	@EJB
	private PageServiceLocal pageServiceLocal;
	@EJB
	private RateServiceLocal rateServiceLocal;

	private String idOwner;
	private List<Touristicplace> myPages = new ArrayList<>();

	@PostConstruct
	public void init() {
		idOwner = "b38f3299-6949-42c7-9a6c-f998c66f485d";
		myPages = pageServiceLocal.ListMyTouristicPages(idOwner);
	}

	public String getIdOwner() {
		return idOwner;
	}

	public void setIdOwner(String idOwner) {
		this.idOwner = idOwner;
	}

	public List<Touristicplace> getMyPages() {
		return myPages;
	}

	public void setMyPages(List<Touristicplace> myPages) {
		this.myPages = myPages;
	}
	
	
}
