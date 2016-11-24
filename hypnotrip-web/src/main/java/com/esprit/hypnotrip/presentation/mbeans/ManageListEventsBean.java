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
import com.esprit.hypnotrip.persistence.Pages;
import com.esprit.hypnotrip.services.interfaces.PageServiceLocal;
import com.esprit.hypnotrip.services.interfaces.RateServiceLocal;

@ManagedBean
@ViewScoped
public class ManageListEventsBean {
	@EJB
	private PageServiceLocal pageServiceLocal;
	@EJB
	private RateServiceLocal rateServiceLocal;

	// Controle sur le chargement des form
	private Boolean displayForm2 = false;
	private Boolean displayForm1 = true;
	private Boolean displayForm3 = false;

	private Boolean displayFormEvent = false;

	// la page séléctionnée

	private Pages eventSelected = new Event();

	private String idOwner;
	private List<Event> myPages = new ArrayList<>();

	// Image
	private Part file;
	private String fileContent;

	@PostConstruct
	public void init() {
		idOwner = "b38f3299-6949-42c7-9a6c-f998c66f485d";
		myPages = pageServiceLocal.ListMyEvents(idOwner);
	}

	// ********************************************************************************
	// Suppression de la page
	public String doDeletePage() {
		pageServiceLocal.deletePage(eventSelected);
		return "/pages/simpleUserHome/listMyEvents?faces-redirect=true";
	}

	// ********************************************************************************
	public String doAddEvent() {
		pageServiceLocal.saveOrUpdatePage(eventSelected, idOwner);
		return "/pages/simpleUserHome/listMyEvents?faces-redirect=true";
	}

	// ********************************************************************************
	// ********************************************************************************
	public String doAddEventForPro() {
		pageServiceLocal.saveOrUpdatePage(eventSelected, idOwner);
		return "/pages/proUserHome/listMyEvents?faces-redirect=true";
	}

	// ********************************************************************************
	// ********************************************************************************
		// Suppression de la page
		public String doDeletePageforPro() {
			pageServiceLocal.deletePage(eventSelected);
			return "/pages/proUserHome/listMyEvents?faces-redirect=true";
		}

		// ********************************************************************************
	// Passer a La Page Rate
	public String ratePage() {

		RateBean.getSelectedItemFromPage(eventSelected.getPageId());
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

		displayFormEvent = true;
		return null;
	}

	public String cancel() {
		displayForm1 = true;
		displayForm2 = false;
		displayForm3 = false;

		displayFormEvent = false;

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

			eventSelected.setImageURL(fileContent);

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

	public List<Event> getMyPages() {
		return myPages;
	}

	public void setMyPages(List<Event> myPages) {
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

	public Boolean getDisplayFormEvent() {
		return displayFormEvent;
	}

	public void setDisplayFormEvent(Boolean displayFormEvent) {
		this.displayFormEvent = displayFormEvent;
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

	public String getFileContent() {
		return fileContent;
	}

	public void setFileContent(String fileContent) {
		this.fileContent = fileContent;
	}

}
