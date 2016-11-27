package com.esprit.hypnotrip.presentation.mbeans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.esprit.hypnotrip.persistence.Offer;
import com.esprit.hypnotrip.persistence.User;
import com.esprit.hypnotrip.services.exceptions.EventOverException;
import com.esprit.hypnotrip.services.interfaces.OfferServiceLocal;
import com.esprit.hypnotrip.services.interfaces.RateServiceLocal;

import tn.esprit.erpbi.banque.services.ComptePortType;
import tn.esprit.erpbi.banque.services.ServiceCompteService;

@ManagedBean
@ViewScoped
public class SortOfferByDateAndPrice {
	ServiceCompteService proxy = new ServiceCompteService();
	ComptePortType service = proxy.getComptePort();
	@EJB
	private OfferServiceLocal offerServiceLocal;
	@EJB
	private RateServiceLocal rateServiceLocal;

	// Controle sur le chargement des form
	private Boolean displayForm2 = false;
	private Boolean displayForm1 = true;
	private Boolean displayForm3 = false;

	private Boolean displayFormOffer = false;

	// la page séléctionnée

	private Offer offerSelected = new Offer();

	// payement
	private String numCompte;
	private String password;

	@ManagedProperty(value = "#{loginBean}")
	private LoginBean loginBean;

	private User user;

	private List<Offer> myPages = new ArrayList<>();

	@PostConstruct
	public void init() {
		user = loginBean.getUser();
		myPages = offerServiceLocal.SortOfferByDatePrice();
	}

	public String doBuyOffer() throws EventOverException {
		Date actuelle = new Date();
		if (offerSelected.getFinishDate().after(actuelle)) {
			offerServiceLocal.buyAnOffer(user.getId(), offerSelected.getPageId());
			Double somme = (offerSelected.getPrice()) * ((100 - offerSelected.getDiscount()) / 100);
			service.retraitArgent(numCompte, password, somme);
			return "/pages/simpleUserHome/sortOfferDateAndPrice?faces-redirect=true";
		} else {
			return "/pages/simpleUserHome/offreInvalide?faces-redirect=true";
		}
	}

	// ********************************************************************************
	// Passer a La Page Rate
	public String ratePage() {

		RateBean.getSelectedItemFromPage(offerSelected.getPageId());
		return "/pages/simpleUserHome/ratePages?faces-redirect=true";
	}

	public int ratingLevels(int idPost) {
		return rateServiceLocal.getRateLevel(idPost);
	}

	// ********************************************************************************
	public String selectPage() {

		displayForm1 = false;
		displayForm2 = false;
		displayForm3 = false;

		displayFormOffer = true;
		return null;
	}

	public String cancel() {
		displayForm1 = true;
		displayForm2 = false;
		displayForm3 = false;

		displayFormOffer = false;

		return "";
	}
	
	public String quitter() {


		return "/pages/simpleUserHome/home?faces-redirect=true";
	}
	// **********************************************************************************

	// **********************************************************************************

	public List<Offer> getMyPages() {
		return myPages;
	}

	public void setMyPages(List<Offer> myPages) {
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

	public Boolean getDisplayForm3() {
		return displayForm3;
	}

	public void setDisplayForm3(Boolean displayForm3) {
		this.displayForm3 = displayForm3;
	}

	public Offer getOfferSelected() {
		return (Offer) offerSelected;
	}

	public void setOfferSelected(Offer offerSelected) {
		this.offerSelected = offerSelected;
	}

	public String getNumCompte() {
		return numCompte;
	}

	public void setNumCompte(String numCompte) {
		this.numCompte = numCompte;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getDisplayFormOffer() {
		return displayFormOffer;
	}

	public void setDisplayFormOffer(Boolean displayFormOffer) {
		this.displayFormOffer = displayFormOffer;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

}
