package com.esprit.hypnotrip.presentation.mbeans;

import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.esprit.hypnotrip.persistence.Pages;
import com.esprit.hypnotrip.persistence.Tickets;
import com.esprit.hypnotrip.persistence.User;
import com.esprit.hypnotrip.services.exceptions.EventOverException;
import com.esprit.hypnotrip.services.exceptions.TicketAlreadyBookedException;
import com.esprit.hypnotrip.services.interfaces.PageServiceLocal;
import com.esprit.hypnotrip.services.interfaces.TicketServicesLocal;

@ViewScoped
@ManagedBean
public class ProUserTicketManagementBean {

	private List<Tickets> tickets;
	private Tickets selectedTicket = new Tickets();
	private Tickets bestBookedTicket = new Tickets();
	private Integer idEvent;
	private Pages event = new Pages();
	@ManagedProperty(value = "#{loginBean}")
	private LoginBean loginBean;
	private User user;

	private boolean showCreateUpdateForm = false;
	private boolean listAllTickets = true;
	private boolean displayMostBookedTicket = false;

	@EJB
	private TicketServicesLocal ticketServicesLocal;
	@EJB
	private PageServiceLocal pageServiceLocal;

	@PostConstruct
	public void init() {

		Map<String, String> params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		try {

			idEvent = Integer.parseInt(params.get("idEvent"));
			this.user = loginBean.getUser();

			event = pageServiceLocal.findPageById(idEvent);
			tickets = ticketServicesLocal.selectAllTicketsByIdEvent(event.getPageId());
			bestBookedTicket = ticketServicesLocal.mostBookedTicketByEvent(event);
			if (bestBookedTicket != null) {
				displayMostBookedTicket = true;
			}

		} catch (Exception e) {

		}

	}

	public String doAddOrUpdateTicket() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			ticketServicesLocal.createOrUpdateTicket(selectedTicket, event.getPageId());
		} catch (EventOverException e) {
			context.addMessage(null, new FacesMessage("Worning", "Event is over"));
			return "";
		} catch (TicketAlreadyBookedException e) {

			context.addMessage(null, new FacesMessage("Worning", "Ticket already booked"));

			return "";
		}

		return "manageTickets?faces-redirect=true&includeViewParams=true";
	}

	public String doDeleteTicket() {

		try {
			ticketServicesLocal.deleteTicket(selectedTicket);
		} catch (EventOverException e) {

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "The event is over"));
			return null;

		} catch (TicketAlreadyBookedException e) {

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Event is already booked"));
			return null;
		}
		return "manageTickets?faces-redirect=true&includeViewParams=true";

	}

	public Long numberOfTicketsBooked(Tickets ticket) {

		if (ticket != null) {
			return ticketServicesLocal.numberOfTicketsBookedByIdTicket(ticket.getTicketId());
		} else {
			return 0L;
		}

	}

	public String selectTicket() {

		showCreateUpdateForm = true;
		listAllTickets = false;

		return null;
	}

	public String backToList() {
		showCreateUpdateForm = false;
		listAllTickets = true;

		return null;
	}

	public TicketServicesLocal getTicketServicesLocal() {
		return ticketServicesLocal;
	}

	public void setTicketServicesLocal(TicketServicesLocal ticketServicesLocal) {
		this.ticketServicesLocal = ticketServicesLocal;
	}

	public List<Tickets> getTickets() {
		return tickets;
	}

	public void setTickets(List<Tickets> tickets) {
		this.tickets = tickets;
	}

	public boolean isListAllTickets() {
		return listAllTickets;
	}

	public void setListAllTickets(boolean listAllTickets) {
		this.listAllTickets = listAllTickets;
	}

	public Tickets getSelectedTicket() {
		return selectedTicket;
	}

	public void setSelectedTicket(Tickets selectedTicket) {
		this.selectedTicket = selectedTicket;
	}

	public boolean isShowCreateUpdateForm() {
		return showCreateUpdateForm;
	}

	public void setShowCreateUpdateForm(boolean showCreateUpdateForm) {
		this.showCreateUpdateForm = showCreateUpdateForm;
	}

	public Tickets getBestBookedTicket() {
		return bestBookedTicket;
	}

	public void setBestBookedTicket(Tickets bestBookedTicket) {
		this.bestBookedTicket = bestBookedTicket;
	}

	public Pages getEvent() {
		return event;
	}

	public void setEvent(Pages event) {
		this.event = event;
	}

	public boolean isDisplayMostBookedTicket() {
		return displayMostBookedTicket;
	}

	public void setDisplayMostBookedTicket(boolean displayMostBookedTicket) {
		this.displayMostBookedTicket = displayMostBookedTicket;
	}

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public PageServiceLocal getPageServiceLocal() {
		return pageServiceLocal;
	}

	public void setPageServiceLocal(PageServiceLocal pageServiceLocal) {
		this.pageServiceLocal = pageServiceLocal;
	}

	public Integer getIdEvent() {
		return idEvent;
	}

	public void setIdEvent(Integer idEvent) {
		this.idEvent = idEvent;
	}

}
