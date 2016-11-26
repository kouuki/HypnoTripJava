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
	private Pages event = new Pages() ; 

	private boolean showCreateUpdateForm = false;
	private boolean listAllTickets = true;
	private boolean displayMostBookedTicket = false ; 

	@EJB
	private TicketServicesLocal ticketServicesLocal;

	@PostConstruct
	public void init() {
	
		event.setPageId(11);
		tickets = ticketServicesLocal.selectAllTicketsByIdEvent(event.getPageId());
		bestBookedTicket = ticketServicesLocal.mostBookedTicketByEvent(event);
		if (bestBookedTicket!=null) {
			displayMostBookedTicket = true; 
		}
	}

	public String doAddOrUpdateTicket() {

		try {
			ticketServicesLocal.createOrUpdateTicket(selectedTicket, event.getPageId());
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
		System.out.println("---------------------------");
		System.out.println("---------------------------");
		System.out.println("---------------------------");
		System.out.println("---------------------------");
		System.out.println("---------------------------");
		System.out.println("---------"+ticket+"-------");
		if (ticket!=null) {
			 return ticketServicesLocal.numberOfTicketsBookedByIdTicket(ticket.getTicketId());
		} else  {
			return 0L ;
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

}
