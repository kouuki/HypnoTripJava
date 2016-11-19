package com.esprit.hypnotrip.services.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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

	/**
	 * Default constructor.
	 */
	@PersistenceContext
	private EntityManager entityManager;

	public TicketServices() {

	}

	@Override
	public void createOrUpdateTicket(Tickets ticket) throws EventOverException, TicketAlreadyBookedException {

		if (false) {
			throw new EventOverException("You can not create a ticket for a finished event");
		} else {
			Tickets ticketFound = findTicketById(ticket.getTicketId());
			if (ticketFound != null) {
				Integer numberOfTicketBooked = numberOfTicketsBookedByIdTicket(ticketFound.getTicketId());
				if (numberOfTicketBooked != 0) {
					if (ticket.getPrice() != ticketFound.getPrice())
						throw new TicketAlreadyBookedException(
								"You can not update the price of a ticket already booked");
				}
			} else {
				entityManager.merge(ticket);
			}
		}

		// TODO Event if over exception
	}

	@Override
	public void deleteTicket(Tickets ticket) throws EventOverException, TicketAlreadyBookedException {
		Tickets mergedTicket = entityManager.merge(ticket);
		if (false) {
			throw new EventOverException("You can not delete a ticket for a finished event");
			// TODO test if the user want to delete a ticket of a finished event
		} else {
			if (mergedTicket != null) {
				Integer numberOfTicketBooked = numberOfTicketsBookedByIdTicket(mergedTicket.getTicketId());
				if (numberOfTicketBooked != 0)
					throw new TicketAlreadyBookedException("You can not update the price of a ticket already booked");
			} else {
				entityManager.remove(mergedTicket);
			}
		}
    
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tickets> selectAllTicketsByIdEvent(Integer idEvent) {
		
		String jpql = "SELECT ticket FROM Tickets ticket WHERE Tickets.eventPageId=:param1";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("param1", idEvent);
		return query.getResultList();
	}

	@Override
	public Tickets findTicketById(Integer idTicket) {
	
		return 	entityManager.find(Tickets.class, idTicket);
	}

	@Override
	public Integer numberOfTicketsBookedByIdTicket(Integer idTicket) {
	    Tickets ticketFound  = findTicketById(idTicket) ; 
		return ticketFound.getBookDescriptions().size();
	}

}
