package com.esprit.hypnotrip.presentation.mbeans;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.esprit.hypnotrip.persistence.Tickets;
import com.esprit.hypnotrip.services.exceptions.EventOverException;
import com.esprit.hypnotrip.services.exceptions.TicketAlreadyBookedException;
import com.esprit.hypnotrip.services.interfaces.TicketServicesLocal;

@ManagedBean
@SessionScoped
public class ManageTicketsBean {

	@EJB
	private TicketServicesLocal ticketServicesLocal;

	private Tickets ticket = new Tickets();
	private Integer idEvent = 0;

	public Tickets getTicket() {
		return ticket;
	}

	public void setTicket(Tickets ticket) {
		this.ticket = ticket;
	}

	public TicketServicesLocal getTicketServicesLocal() {
		return ticketServicesLocal;
	}

	public void setTicketServicesLocal(TicketServicesLocal ticketServicesLocal) {
		this.ticketServicesLocal = ticketServicesLocal;
	}

	public Integer getIdEvent() {
		return idEvent;
	}

	public void setIdEvent(Integer idEvent) {
		this.idEvent = idEvent;
	}

	public String doCreateOrUpdateTicket() {
		String redirect = "";

		try {

			ticketServicesLocal.createOrUpdateTicket(ticket, idEvent);
		} catch (EventOverException e) {

			redirect = "";
		} catch (TicketAlreadyBookedException e) {
			redirect = "";
		}

		return redirect;
	}

}
