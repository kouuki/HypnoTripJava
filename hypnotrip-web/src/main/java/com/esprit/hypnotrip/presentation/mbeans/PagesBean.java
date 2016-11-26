package com.esprit.hypnotrip.presentation.mbeans;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

import com.esprit.hypnotrip.persistence.Event;
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
	private Pages eventSelected = new Event();
	// private Pages touristicSelected = new Touristicplace();

	// Image
	private Part file;
	private String fileContent;

	// le type séléctionné de la page
	private int selectedType;

	private String idOwner;
	private List<Pages> myPages = new ArrayList<>();

	@PostConstruct
	public void init() {
		idOwner = "b38f3299-6949-42c7-9a6c-f998c66f485d";
		myPages = pageServiceLocal.ListMyPages(idOwner);
	}

	// ********************************************************************************
	// Ajout ou update offer Event Touristic page
	public String doAddPage() {
		pageServiceLocal.saveOrUpdatePage(pageSelected, idOwner);
		return "/pages/simpleUserHome/listofMyPages?faces-redirect=true";
	}

	public String doAddOffer() {
		offerSelected.setImageURL(fileContent);
		pageServiceLocal.saveOrUpdatePage(offerSelected, idOwner);
		return "/pages/simpleUserHome/listofMyPages?faces-redirect=true";
	}

	// TODO doAddEvent()
	public String doAddEvent() {
		pageServiceLocal.saveOrUpdatePage(eventSelected, idOwner);
		return "/pages/simpleUserHome/listofMyPages?faces-redirect=true";
	}

	// TODO doAddEvent()

	public String doAddTouristic() {

		pageServiceLocal.saveOrUpdatePage(offerSelected, idOwner);
		return "/pages/simpleUserHome/listofMyPages?faces-redirect=true";
	}

	// ********************************************************************************
	// Suppression de la page
	public String doDeletePage() {
		pageServiceLocal.deletePage(pageSelected);
		return "/pages/simpleUserHome/listofMyPages?faces-redirect=true";
	}

	// ********************************************************************************
	// Passer a La Page Rate
	public void ratePage(int pageId) {
		RateBean.getSelectedItemFromPage(pageId);
	}

	// ********************************************************************************
	public String selectPage() {
		if (pageSelected instanceof Pages) {
			displayForm1 = false;
			displayForm2 = true;
			displayForm3 = false;
			displayFormOffer = false;
			displayFormEvent = false;
			displayFormTouristicPage = false;
		}
		if (pageSelected instanceof Offer) {
			displayForm1 = false;
			displayForm2 = false;
			displayForm3 = false;
			displayFormOffer = true;
			displayFormEvent = false;
			displayFormTouristicPage = false;
		}
		if (pageSelected instanceof Event) {
			displayForm1 = false;
			displayForm2 = false;
			displayForm3 = false;
			displayFormOffer = false;
			displayFormEvent = true;
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

	public int ratingLevels(int idPost) {
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

	// **********************************************************************************
	public void onDateSelect(SelectEvent event) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		facesContext.addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Date Selected", format.format(event.getObject())));
	}

	public void click() {
		RequestContext requestContext = RequestContext.getCurrentInstance();

		requestContext.update("form:display");
		requestContext.execute("PF('dlg').show()");
	}

	// **********************************************************************************
	@SuppressWarnings("resource")
	public void upload() {
		try {
			fileContent = new Scanner(file.getInputStream()).useDelimiter("\\A").next();
			
			offerSelected.setImageURL(fileContent);
			
		} catch (IOException e) {
			// Error handling
		}
	}

	// **********************************************************************************
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

	public void setOfferSelected(Pages offerSelected) {
		this.offerSelected = offerSelected;
	}

	public Event getEventSelected() {
		return (Event) eventSelected;
	}

	public void setEventSelected(Event eventSelected) {
		this.eventSelected = eventSelected;
	}

	public Part getFile() {
		return file;
	}

	public void setFile(Part file) {
		this.file = file;
	}

	@SuppressWarnings("resource")
	public String getFileContent() {
		try {
			fileContent = new Scanner(file.getInputStream()).useDelimiter("\\A").next();
			System.out.println("houniiiiiiiiii   " + fileContent);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return fileContent;
	}

	public void setFileContent(String fileContent) {
		this.fileContent = fileContent;
	}

	public void setEventSelected(Pages eventSelected) {
		this.eventSelected = eventSelected;
	}

}
