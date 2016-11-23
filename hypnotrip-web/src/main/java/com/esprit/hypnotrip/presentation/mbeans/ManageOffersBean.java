package com.esprit.hypnotrip.presentation.mbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.esprit.hypnotrip.persistence.Offer;
import com.esprit.hypnotrip.services.interfaces.PageServiceLocal;
import com.esprit.hypnotrip.services.interfaces.RateServiceLocal;

@ManagedBean
@ViewScoped
public class ManageOffersBean {
	@EJB
	private PageServiceLocal pageServiceLocal;
	@EJB
	private RateServiceLocal rateServiceLocal;
	private String idOwner;
	private List<Offer> myPages = new ArrayList<>();

	@PostConstruct
	public void init() {
		idOwner = "b38f3299-6949-42c7-9a6c-f998c66f485d";
		myPages = pageServiceLocal.ListMyOffers(idOwner);
	}

	public String getIdOwner() {
		return idOwner;
	}

	public void setIdOwner(String idOwner) {
		this.idOwner = idOwner;
	}

	public List<Offer> getMyPages() {
		return myPages;
	}

	public void setMyPages(List<Offer> myPages) {
		this.myPages = myPages;
	}

}
