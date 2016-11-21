package com.esprit.hypnotrip.presentation.mbeans;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.esprit.hypnotrip.persistence.Offer;
import com.esprit.hypnotrip.persistence.Pages;
import com.esprit.hypnotrip.services.interfaces.PageServiceLocal;
import com.esprit.hypnotrip.services.interfaces.RateServiceLocal;

@ManagedBean
@ViewScoped
public class PagesBean {
	@EJB
	private PageServiceLocal pageServiceLocal;
	@EJB
	private RateServiceLocal rateServiceLocal;

	// Controle sur le chargement des form
	private Boolean displayForm2 = false;
	private Boolean displayForm1 = true;
	private Boolean displayForm3 = false;
	private Boolean displayFormOffer = false;
	private Boolean displayFormEvent = false;
	private Boolean displayFormTouristicPage = false;

	// la page séléctionnée
	private String selectedItem;
	private Pages pageSelected = new Pages();
	private Pages offerSelected = new Offer();
	// private Pages eventSelected = new Event();
	// private Pages touristicSelected = new Touristicplace();

	// le type séléctionné de la page
	private int selectedType;
	// le rating de chaque Page
	private int rating=3;
	

	private String idOwner;
	private List<Pages> myPages = new ArrayList<>();


	@PostConstruct
	public void init() {
		idOwner = "b38f3299-6949-42c7-9a6c-f998c66f485d";
		myPages = pageServiceLocal.ListMyPages(idOwner);
	}
//********************************************************************************	
//Ajout ou update offer Event Touristic page
	public String doAddPage() {
		pageServiceLocal.saveOrUpdatePage(pageSelected, idOwner);
		return "/pages/simpleUserHome/listofMyPages?faces-redirect=true";
	}
	public String doAddOffer() {
		pageServiceLocal.saveOrUpdatePage(offerSelected, idOwner);
		return "/pages/simpleUserHome/listofMyPages?faces-redirect=true";
	}
	//TODO  doAddEvent()
	public String doAddEvent() {
		pageServiceLocal.saveOrUpdatePage(offerSelected, idOwner);
		return "/pages/simpleUserHome/listofMyPages?faces-redirect=true";
	}
	//TODO doAddEvent()
	public String doAddTouristic() {
		pageServiceLocal.saveOrUpdatePage(offerSelected, idOwner);
		return "/pages/simpleUserHome/listofMyPages?faces-redirect=true";
	}
//********************************************************************************
//Suppression de la page
	public String doDeletePage() {
		pageServiceLocal.deletePage(pageSelected);
		return "/pages/simpleUserHome/listofMyPages?faces-redirect=true";
	}
//********************************************************************************
	public String selectPage() {
		if(pageSelected instanceof Pages)
		{
			displayForm1 = false;
			displayForm2 = true;
			displayForm3 = false;
			displayFormOffer = false;
			displayFormEvent = false;
			displayFormTouristicPage = false;
		}
		if(pageSelected instanceof Offer)
		{
			displayForm1 = false;
			displayForm2 = false;
			displayForm3 = false;
			displayFormOffer = true;
			displayFormEvent = false;
			displayFormTouristicPage = false;
		}
		return null;
	}

	public String selectTypeOfPage() {
		displayForm1 = false;
		displayForm2 = false;
		displayForm3 = true;
		displayFormOffer = false;
		displayFormEvent = false;
		displayFormTouristicPage = false;
		return null;
	}
public int ratingLevels(int idPost){
		return rateServiceLocal.getRateLevel(idPost);
}

	public String RedirectTypeOfPage() {
		if (selectedType == 1) {
			displayForm1 = false;
			displayForm2 = false;
			displayForm3 = true;
			displayFormOffer = true;
			displayFormEvent = false;
			displayFormTouristicPage = false;
			System.out.println("hello from 1");
		}
		if (selectedType == 2) {
			displayForm1 = false;
			displayForm2 = false;
			displayForm3 = true;
			displayFormOffer = false;
			displayFormEvent = true;
			displayFormTouristicPage = false;
			System.out.println("hello from 2");
		}
		if (selectedType == 3) {
			displayForm1 = false;
			displayForm2 = false;
			displayForm3 = true;
			displayFormOffer = false;
			displayFormEvent = false;
			displayFormTouristicPage = true;
			System.out.println("hello from 3");
		}
		return null;
	}

	public String cancel() {
		displayForm1 = true;
		displayForm2 = false;
		displayForm3 = false;
		displayFormOffer = false;
		displayFormEvent = false;
		pageSelected = new Pages();
		return "";
	}

	public String getIdOwner() {
		return idOwner;
	}

	public void setIdOwner(String idOwner) {
		this.idOwner = idOwner;
	}

	public List<Pages> getMyPages() {
		return myPages;
	}

	public void setMyPages(List<Pages> myPages) {
		this.myPages = myPages;
	}

	public Boolean getDisplayForm2() {
		return displayForm2;
	}

	public void setDisplayForm2(Boolean displayForm2) {
		this.displayForm2 = displayForm2;
	}

	public Boolean getDisplayForm1() {
		return displayForm1;
	}

	public void setDisplayForm1(Boolean displayForm1) {
		this.displayForm1 = displayForm1;
	}

	public String getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(String selectedItem) {
		this.selectedItem = selectedItem;
	}

	public Pages getPageSelected() {
		return pageSelected;
	}

	public void setPageSelected(Pages pageSelected) {
		this.pageSelected = pageSelected;
	}

	public int getSelectedType() {
		return selectedType;
	}

	public void setSelectedType(int selectedType) {
		this.selectedType = selectedType;
	}

	public Boolean getDisplayForm3() {
		return displayForm3;
	}

	public void setDisplayForm3(Boolean displayForm3) {
		this.displayForm3 = displayForm3;
	}

	public Boolean getdisplayFormOffer() {
		return displayFormOffer;
	}

	public void setDisplayFormOffer(Boolean displayFormOffer) {
		this.displayFormOffer = displayFormOffer;
	}

	public Boolean getDisplayFormEvent() {
		return displayFormEvent;
	}

	public void setDisplayFormEvent(Boolean displayFormEvent) {
		this.displayFormEvent = displayFormEvent;
	}

	public Boolean getDisplayFormTouristicPage() {
		return displayFormTouristicPage;
	}

	public void setDisplayFormTouristicPage(Boolean displayFormTouristicPage) {
		this.displayFormTouristicPage = displayFormTouristicPage;
	}

	public Offer getOfferSelected() {
		return (Offer) offerSelected;
	}

	public void setOfferSelected(Offer offerSelected) {
		this.offerSelected = offerSelected;
	}

	public Boolean getDisplayFormOffer() {
		return displayFormOffer;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}

}
