package com.esprit.hypnotrip.services.interfaces;

import java.util.List;

import javax.ejb.Remote;

import com.esprit.hypnotrip.persistence.Tickets;
import com.esprit.hypnotrip.services.exceptions.EventOverException;
import com.esprit.hypnotrip.services.exceptions.TicketAlreadyBookedException;

@Remote
public interface TicketServicesRemote {

	void createOrUpdateTicket(Tickets ticket) throws EventOverException, TicketAlreadyBookedException;

	void deleteTicket(Tickets ticket) throws EventOverException, TicketAlreadyBookedException;

	List<Tickets> selectAllTicketsByIdEvent(Integer idEvent);

	Tickets findTicketById(Integer idTicket);

	Long numberOfTicketsBookedByIdTicket(Integer idTicket);

	Tickets mostBookedTicket();
}
