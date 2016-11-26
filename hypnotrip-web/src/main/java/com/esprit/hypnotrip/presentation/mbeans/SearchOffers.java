package com.esprit.hypnotrip.presentation.mbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.esprit.hypnotrip.persistence.Offer;
import com.esprit.hypnotrip.services.interfaces.OfferServiceLocal;

@ManagedBean
@ViewScoped
public class SearchOffers {
	@EJB
	OfferServiceLocal offerServiceLocal;
	private List<Offer> offers = new ArrayList<Offer>();
	private List<Offer> fitredOffers = new ArrayList<Offer>();
	private Boolean displayForm1 = true;
	private Boolean displayForm2 = false;

	private Offer selectedOffer;

	@PostConstruct
	public void init() {
		setOffers(offerServiceLocal.SearchOffer());
	}

	public String selectPage() {

		displayForm1 = false;
		displayForm2 = true;

		return null;
	}

	public List<Offer> getOffers() {
		return offers;
	}

	public void setOffers(List<Offer> offers) {
		this.offers = offers;
	}

	public Boolean getDisplayForm1() {
		return displayForm1;
	}

	public void setDisplayForm1(Boolean displayForm1) {
		this.displayForm1 = displayForm1;
	}

	public Boolean getDisplayForm2() {
		return displayForm2;
	}

	public void setDisplayForm2(Boolean displayForm2) {
		this.displayForm2 = displayForm2;
	}

	public List<Offer> getFitredOffers() {
		return fitredOffers;
	}

	public void setFitredOffers(List<Offer> fitredOffers) {
		this.fitredOffers = fitredOffers;
	}

	public Offer getSelectedOffer() {
		return selectedOffer;
	}

	public void setSelectedOffer(Offer selectedOffer) {
		this.selectedOffer = selectedOffer;
	}

}
