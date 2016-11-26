package com.esprit.hypnotrip.presentation.mbeans;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;

import com.esprit.hypnotrip.persistence.Event;
import com.esprit.hypnotrip.persistence.Pages;
import com.esprit.hypnotrip.persistence.User;
import com.esprit.hypnotrip.services.interfaces.PageServiceLocal;
import com.esprit.hypnotrip.services.interfaces.RateServiceLocal;

@ManagedBean
@ViewScoped
public class ManageListEventsBean {
	@EJB
	private PageServiceLocal pageServiceLocal;
	@EJB
	private RateServiceLocal rateServiceLocal;

	@ManagedProperty(value = "#{loginBean}")
	private LoginBean loginBean;
	
	private User user;

	// Controle sur le chargement des form
	private Boolean displayForm2 = false;
	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	private Boolean displayForm1 = true;
	private Boolean displayForm3 = false;

	private Boolean displayFormEvent = false;
	private Date dateTime = new Date();
	// la page séléctionnée

	private Pages eventSelected = new Event();

	private List<Event> myPages = new ArrayList<>();

	// Primitives
	private static final int BUFFER_SIZE = 6124;
	String res = "";

	@PostConstruct
	public void init() {
		user=loginBean.getUser();

		myPages = pageServiceLocal.ListMyEvents(user.getId());
	}

	// ********************************************************************************
	// Suppression de la page
	public String doDeletePage() {
		pageServiceLocal.deletePage(eventSelected);
		return "/pages/simpleUserHome/listMyEvents?faces-redirect=true";
	}

	// ********************************************************************************
	public String doAddEvent() {

		if (res != "") {
			System.out.println(res);
			eventSelected.setImageURL(res);
		}
		pageServiceLocal.saveOrUpdatePage(eventSelected, user.getId());
		return "/pages/simpleUserHome/listMyEvents?faces-redirect=true";
	}

	// ********************************************************************************
	// ********************************************************************************
	public String doAddEventForPro() {
		if (res != "") {
			System.out.println(res);
			eventSelected.setImageURL(res);
		}
		pageServiceLocal.saveOrUpdatePage(eventSelected, user.getId());
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
	public void handleFileUpload(FileUploadEvent event) {

		ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
		File result = new File(extContext.getRealPath("//uploads//" + event.getFile().getFileName()));
		// res = extContext.getRealPath("//uploads//" +
		// event.getFile().getFileName());
		res = "//uploads//" + event.getFile().getFileName();
		System.out.println(res);

		try {
			FileOutputStream fileOutputStream = new FileOutputStream(result);

			byte[] buffer = new byte[BUFFER_SIZE];

			int bulk;
			InputStream inputStream = event.getFile().getInputstream();
			while (true) {
				bulk = inputStream.read(buffer);
				if (bulk < 0) {
					break;
				}
				fileOutputStream.write(buffer, 0, bulk);
				fileOutputStream.flush();
			}

			fileOutputStream.close();
			inputStream.close();

			FacesMessage msg = new FacesMessage("File Description",
					"file name: " + event.getFile().getFileName() + "<br/>file size: "
							+ event.getFile().getSize() / 1024 + " Kb<br/>content type: "
							+ event.getFile().getContentType() + "<br/><br/>The file was uploaded.");
			FacesContext.getCurrentInstance().addMessage(null, msg);

		} catch (IOException e) {
			e.printStackTrace();

			FacesMessage error = new FacesMessage(FacesMessage.SEVERITY_ERROR, "The files were not uploaded!", "");
			FacesContext.getCurrentInstance().addMessage(null, error);
		}
	}

	// **********************************************************************************

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

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
