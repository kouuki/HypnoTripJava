package com.esprit.hypnotrip.presentation.mbeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.esprit.hypnotrip.persistence.Pages;
import com.esprit.hypnotrip.persistence.Tickets;
import com.esprit.hypnotrip.services.exceptions.EventOverException;
import com.esprit.hypnotrip.services.exceptions.TicketAlreadyBookedException;
import com.esprit.hypnotrip.services.interfaces.TicketServicesLocal;

@ViewScoped
@ManagedBean
public class ProUserTicketManagementBean {

	private List<Tickets> tickets;
	private Tickets selectedTicket = new Tickets();
	private Tickets bestBookedTicket = new Tickets();

	private boolean showCreateUpdateForm = false;
	private boolean listAllTickets = true;

	@EJB
	private TicketServicesLocal ticketServicesLocal;

	@PostConstruct
	public void init() {
		Pages event = new Pages();
		event.setPageId(1);
		tickets = ticketServicesLocal.selectAllTicketsByIdEvent(1);
		bestBookedTicket = ticketServicesLocal.mostBookedTicketByEvent(event);
	}

	public String doAddOrUpdateTicket() {

		try {
			ticketServicesLocal.createOrUpdateTicket(selectedTicket, 1);
		} catch (EventOverException | TicketAlreadyBookedException e) {
			e.printStackTrace();
		}
		return "manageTickets?faces-redirect=true";
	}

	public String doDeleteTicket() {

		try {
			ticketServicesLocal.deleteTicket(selectedTicket);
		} catch (EventOverException | TicketAlreadyBookedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "manageTickets?faces-redirect=true";

	}

	public Long numberOfTicketsBooked(Tickets ticket) {
		return 0L; //ticketServicesLocal.numberOfTicketsBookedByIdTicket(ticket.getTicketId());
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

}
