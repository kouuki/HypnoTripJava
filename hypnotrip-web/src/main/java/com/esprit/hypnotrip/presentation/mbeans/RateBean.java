package com.esprit.hypnotrip.presentation.mbeans;

import java.util.Date;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.RateEvent;

import com.esprit.hypnotrip.persistence.Rates;
import com.esprit.hypnotrip.persistence.RatesId;
import com.esprit.hypnotrip.services.interfaces.RateServiceLocal;

@ManagedBean
@ViewScoped
public class RateBean {
	@EJB
	RateServiceLocal rateServiceLocal;

	private Integer rating1;
	private static Integer selectedItem;
	

	public void onrate(RateEvent rateEvent) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Rate Event",
				"You rated:" + ((Integer) rateEvent.getRating()).intValue());
		FacesContext.getCurrentInstance().addMessage(null, message);
		// here we will add the connected User & the selected Page
		
		String user_id="2e1002bb-57b6-4838-8f8f-e762bae52f7f";
		//adding the id of the page
		RatesId rateId = new RatesId();
		rateId.setId(user_id);
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

	public String ReturnList(){
		return "/pages/simpleUserHome/listofMyPages?faces-redirect=true";
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

	
}
