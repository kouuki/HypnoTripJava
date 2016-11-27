package com.esprit.hypnotrip.presentation.mbeans;

import java.util.Date;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import org.primefaces.event.RateEvent;

import com.esprit.hypnotrip.persistence.Rates;
import com.esprit.hypnotrip.persistence.RatesId;
import com.esprit.hypnotrip.services.interfaces.RateServiceLocal;

@ManagedBean
@SessionScoped
public class RateBean {
	@EJB
	RateServiceLocal rateServiceLocal;

	private Integer rating1;
	private static Integer selectedItem;
	private String idUserConnected;
	
	@ManagedProperty(value = "#{loginBean}")
	private LoginBean loginBean;

	public void onrate(RateEvent rateEvent) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Rate Event",
				"You rated:" + ((Integer) rateEvent.getRating()).intValue());
		FacesContext.getCurrentInstance().addMessage(null, message);
		// here we will add the connected User & the selected Page
		
	
		//adding the id of the page
		RatesId rateId = new RatesId();
		rateId.setId(loginBean.getUser().getId());
		rateId.setpageId(selectedItem);
		//The rate
		Rates rate = new Rates();
		rate.setNiveau((Integer) rateEvent.getRating());
		rate.setId(rateId);
		rate.setdateRating(new Date());
		rateServiceLocal.addRate(rate);
		
	}
	public static void getSelectedItemFromPage(int idPage){
		selectedItem = idPage;
	}
	public void oncancel() {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cancel Event", "Rate Reset");
		FacesContext.getCurrentInstance().addMessage(null, message);
	}
//// redirection for different pages
	public String ReturnList(){
		rating1=0;
		return "/pages/simpleUserHome/listofMyPages?faces-redirect=true";
	}
	public String ReturnListSortOfferDateAndPrice(){
		rating1=0;
		return "/pages/simpleUserHome/sortOfferDateAndPrice?faces-redirect=true";
	}
	public String ReturnListbestOffer(){
		rating1=0;
		return "/pages/simpleUserHome/bestOffer?faces-redirect=true";
	}
	public String ReturnListBuyAnOffer(){
		rating1=0;
		return "/pages/simpleUserHome/BuyAnOffer?faces-redirect=true";
	}
	public String ReturnListUpcommingEvents(){
		rating1=0;
		return "/pages/simpleUserHome/upcommingEventsPage?faces-redirect=true";
	}
	public String ReturnListRatetouristicPlaces(){
		rating1=0;
		return "/pages/simpleUserHome/TouristicPlacesForRate?faces-redirect=true";
	}
	
	
	
	
	
	
	
	
	
	
	public String RatePage(){
		return "/pages/simpleUserHome/ratePages?faces-redirect=true";
	}

	public Integer getRating1() {
		return rating1;
	}

	public void setRating1(Integer rating1) {
		this.rating1 = rating1;
	}

	public Integer getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(Integer selectedItem) {
		RateBean.selectedItem = selectedItem;
	}
	public String getIdUserConnected() {
		return idUserConnected;
	}
	public void setIdUserConnected(String idUserConnected) {
		this.idUserConnected = idUserConnected;
	}
	public LoginBean getLoginBean() {
		return loginBean;
	}
	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	
}
