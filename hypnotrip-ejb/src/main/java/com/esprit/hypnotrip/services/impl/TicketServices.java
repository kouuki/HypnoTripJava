package com.esprit.hypnotrip.services.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.esprit.hypnotrip.persistence.Pages;
import com.esprit.hypnotrip.persistence.Tickets;
import com.esprit.hypnotrip.services.exceptions.EventOverException;
import com.esprit.hypnotrip.services.exceptions.TicketAlreadyBookedException;
import com.esprit.hypnotrip.services.interfaces.TicketServicesLocal;
import com.esprit.hypnotrip.services.interfaces.TicketServicesRemote;

/**
 * Session Bean implementation class TicketServices
 */
@Stateless
public class TicketServices implements TicketServicesRemote, TicketServicesLocal {

	@Override
	public void createOrUpdateTicket(Tickets ticket) throws EventOverException, TicketAlreadyBookedException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteTicket(Tickets ticket) throws EventOverException, TicketAlreadyBookedException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Tickets> selectAllTicketsByIdEvent(Integer idEvent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tickets findTicketById(Integer idTicket) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long numberOfTicketsBookedByIdTicket(Integer idTicket) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tickets mostBookedTicket() {
		// TODO Auto-generated method stub
		return null;
	}



}
