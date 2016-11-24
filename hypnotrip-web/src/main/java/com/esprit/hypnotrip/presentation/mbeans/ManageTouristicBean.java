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

import com.esprit.hypnotrip.persistence.Pages;
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

	// Controle sur le chargement des form
	private Boolean displayForm2 = false;
	private Boolean displayForm1 = true;
	private Boolean displayForm3 = false;
	private Boolean displayFormTouristicPage = false;

	private String idOwner;
	private List<Touristicplace> myPages = new ArrayList<>();

	// la page séléctionnée

	private Pages touristicSelected = new Touristicplace();

	// Image
	private Part file;
	private String fileContent;

	@PostConstruct
	public void init() {
		idOwner = "b38f3299-6949-42c7-9a6c-f998c66f485d";
		myPages = pageServiceLocal.ListMyTouristicPages(idOwner);
	}

	// ********************************************************************************
	// Ajout ou update offer Event Touristic page

	public String doAddTouristic() {

		pageServiceLocal.saveOrUpdatePage(touristicSelected, idOwner);
		return "/pages/simpleUserHome/listMyTouristicPages?faces-redirect=true";
	}

	// ********************************************************************************
	// Suppression de la page
	public String doDeletePage() {
		pageServiceLocal.deletePage(touristicSelected);
		return "/pages/simpleUserHome/listMyTouristicPages?faces-redirect=true";
	}

	// ********************************************************************************
	// Passer a La Page Rate
	public String ratePage() {

		RateBean.getSelectedItemFromPage(touristicSelected.getPageId());
		return "/pages/simpleUserHome/ratePages?faces-redirect=true";
	}

	// ********************************************************************************
	public String selectPage() {

		displayForm1 = false;
		displayForm2 = false;
		displayForm3 = false;

		displayFormTouristicPage = true;

		return null;
	}

	// ********************************************************************************
	public String cancel() {
		displayForm1 = true;
		displayForm2 = false;
		displayForm3 = false;
		displayFormTouristicPage = false;
		touristicSelected = new Touristicplace();
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

			touristicSelected.setImageURL(fileContent);

		} catch (IOException e) {
			// Error handling
		}
	}

	// **********************************************************************************
	public int ratingLevels(int idPost) {
		return rateServiceLocal.getRateLevel(idPost);
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

	public Boolean getDisplayFormTouristicPage() {
		return displayFormTouristicPage;
	}

	public void setDisplayFormTouristicPage(Boolean displayFormTouristicPage) {
		this.displayFormTouristicPage = displayFormTouristicPage;
	}

	public Touristicplace getTouristicSelected() {
		return (Touristicplace) touristicSelected;
	}

	public void setTouristicSelected(Touristicplace touristicSelected) {
		this.touristicSelected = touristicSelected;
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
