package com.esprit.hypnotrip.services.impl;

import com.esprit.hypnotrip.persistence.BookDescription;
import com.esprit.hypnotrip.persistence.Event;
import com.esprit.hypnotrip.persistence.Pages;
import com.esprit.hypnotrip.persistence.Tickets;
import com.esprit.hypnotrip.persistence.User;
import com.esprit.hypnotrip.services.exceptions.EventOverException;
import com.esprit.hypnotrip.services.exceptions.LimitOfBookingRechedException;
import com.esprit.hypnotrip.services.exceptions.NoMoreTicketsException;
import com.esprit.hypnotrip.services.exceptions.WrongNumberOfCancelingException;
import com.esprit.hypnotrip.services.interfaces.UserServicesLocal;
import com.esprit.hypnotrip.services.interfaces.UserServicesRemote;

import java.util.List;

import javax.ejb.Stateless;

/**
 * Session Bean implementation class UserServices
 */
@Stateless
public class UserServices implements UserServicesRemote, UserServicesLocal {

	@Override
	public void bookATicket(Tickets ticket, User user)
			throws NoMoreTicketsException, LimitOfBookingRechedException, EventOverException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cancelBooking(Tickets ticket, User user, Integer numberOfTicketsToCancel)
			throws EventOverException, WrongNumberOfCancelingException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Tickets> listTicketsBookedByUserEvent(User user, Pages event) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long numberOfTicketsReservedByIdUser(Tickets ticket, String idUser) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BookDescription> listOfBookingByUser(User user, Tickets ticket) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getAllFriendsWhoAreGoingToTheSameEvent(Pages enent, String userId) {
		// TODO Auto-generated method stub
		return null;
	}

  


}
