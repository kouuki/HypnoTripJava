package com.esprit.hypnotrip.services.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.esprit.hypnotrip.persistence.BookDescription;
import com.esprit.hypnotrip.persistence.Pages;
import com.esprit.hypnotrip.persistence.Tickets;
import com.esprit.hypnotrip.services.exceptions.EventOverException;
import com.esprit.hypnotrip.services.exceptions.TicketAlreadyBookedException;
import com.esprit.hypnotrip.services.interfaces.EventServicesLocal;
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
	@EJB
	EventServicesLocal eventServices;
	

	public TicketServices() {

	}

	@Override
	public void createOrUpdateTicket(Tickets ticket,Integer idEvent) throws EventOverException, TicketAlreadyBookedException {

		if (eventServices.eventIsAvailaible(idEvent)) {
			throw new EventOverException("You can not create or update a ticket for a finished event");
		} else {
			if (ticket.getTicketId() != null) {
				System.out.println("---------ticket-----------");
				System.out.println("---------ticket-----------");
				
				Tickets ticketFound = findTicketById(ticket.getTicketId());
				System.out.println(ticketFound);
				if (ticketFound != null) {
					Long numberOfTicketBooked = numberOfTicketsBookedByIdTicket(ticketFound.getTicketId());
					if (numberOfTicketBooked != 0) {
						if (ticket.getPrice() != ticketFound.getPrice())
							throw new TicketAlreadyBookedException(
									"You can not update the price of a ticket already booked");
					}
				}
			}
			
			Pages event=	entityManager.find(Pages.class, idEvent);
			ticket.setEvent(event);
			entityManager.merge(ticket);

		}

		// TODO Event if over exception
		// TODO delete static affectation to the event
	}

	@Override
	public void deleteTicket(Tickets ticket) throws EventOverException, TicketAlreadyBookedException {
		Tickets mergedTicket = entityManager.merge(ticket);
		if (eventServices.eventIsAvailaible(ticket.getEvent().getPageId())) {
			throw new EventOverException("You can not delete a ticket for a finished event");
			// TODO test if the user want to delete a ticket of a finished event
		} else {
			if (mergedTicket != null) {

				Long numberOfTicketBooked = numberOfTicketsBookedByIdTicket(ticket.getTicketId());

				if (numberOfTicketBooked != 0) {
					throw new TicketAlreadyBookedException("You can not delete this ticket already booked");
				} else {

					for (BookDescription des : ticket.getBookDescriptions()) {
						entityManager.remove(entityManager.merge(des));
					}
					entityManager.remove(mergedTicket);
				}

			}
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tickets> selectAllTicketsByIdEvent(Integer idEvent) {

		String jpql = "SELECT ticket FROM Tickets ticket WHERE ticket.event.pageId=:param1";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("param1", idEvent);
		return query.getResultList();
	}

	@Override
	public Tickets findTicketById(Integer idTicket) {

		return entityManager.find(Tickets.class, idTicket);
	}

	@Override
	public Long numberOfTicketsBookedByIdTicket(Integer idTicket) {

		String jqpl = "SELECT COUNT(bd) FROM BookDescription bd " + "WHERE bd.bookingStatus=true "
				+ "AND bd.ticket.ticketId =:param1 ";
		Query query = entityManager.createQuery(jqpl);
		query.setParameter("param1", idTicket);

		return (Long) query.getSingleResult();
	}

	@Override
	public Tickets mostBookedTicket() {

		String jpql = "select ticket from Tickets ticket INNER JOIN ticket.bookDescriptions "
				+ "book GROUP BY book.bookId.ticketId ORDER BY Count(book.bookId.ticketId) DESC";

		Query query = entityManager.createQuery(jpql);

		if (query.getResultList() != null) {
			Tickets ticket = (Tickets) query.getResultList().get(0);
			return ticket;
		}

		return null;
	}

	@Override
	public Tickets mostBookedTicketByEvent(Pages event) {

		String jpql = "SELECT ticket FROM Tickets ticket INNER JOIN ticket.bookDescriptions book "
				+ "WHERE ticket.event=:param1 "
				+ "GROUP BY book.bookId.ticketId ORDER BY Count(book.bookId.ticketId) DESC ";
		

		Query query = entityManager.createQuery(jpql);
		query.setParameter("param1", event);

		if (query.getResultList().size() != 0 ) {
			Tickets ticket = (Tickets) query.getResultList().get(0);
			return ticket;
		}

		return null;
	}
	
	
	
	

}
