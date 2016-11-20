package com.esprit.hypnotrip.services.interfaces;

import java.util.List;

import javax.ejb.Local;

import com.esprit.hypnotrip.persistence.Event;
import com.esprit.hypnotrip.persistence.Tickets;
import com.esprit.hypnotrip.persistence.User;
import com.esprit.hypnotrip.services.exceptions.EventOverException;
import com.esprit.hypnotrip.services.exceptions.LimitOfBookingRechedException;
import com.esprit.hypnotrip.services.exceptions.NoMoreTicketsException;
import com.esprit.hypnotrip.services.exceptions.WrongNumberOfCancelingException;

@Local
public interface UserServicesLocal {

	void bookATicket(Tickets ticket, String idUser)
			throws NoMoreTicketsException, LimitOfBookingRechedException, EventOverException;

	void cancelBooking(Tickets ticket, String idUser, Integer numberOfTicketsToCancel)
			throws EventOverException, WrongNumberOfCancelingException;

	List<Tickets> listTicketsBookedByUserEvent(User user, Event event);
	
	Integer numberOfTicketsReservedByIdUser(Tickets ticket, String idUser);

}
