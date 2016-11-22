package com.esprit.hypnotrip.services.interfaces;

import java.util.List;

import javax.ejb.Local;

import com.esprit.hypnotrip.persistence.BookDescription;
import com.esprit.hypnotrip.persistence.Pages;
import com.esprit.hypnotrip.persistence.Tickets;
import com.esprit.hypnotrip.persistence.User;
import com.esprit.hypnotrip.services.exceptions.EventOverException;
import com.esprit.hypnotrip.services.exceptions.LimitOfBookingRechedException;
import com.esprit.hypnotrip.services.exceptions.NoMoreTicketsException;
import com.esprit.hypnotrip.services.exceptions.WrongNumberOfCancelingException;

@Local
public interface UserServicesLocal {

	User getUserbyId(String idUser);

	void bookATicket(Tickets ticket, User user)
			throws NoMoreTicketsException, LimitOfBookingRechedException, EventOverException;

	void cancelBooking(Tickets ticket, User user, Integer numberOfTicketsToCancel)
			throws EventOverException, WrongNumberOfCancelingException;

	List<Tickets> listTicketsBookedByUserEvent(User user, Pages event);

	Long numberOfTicketsReservedByIdUser(Tickets ticket, String idUser);

	List<BookDescription> listOfBookingByUser(User user, Tickets ticket);

	List<User> getAllFriendsWhoAreGoingToTheSameEvent(Pages enent, String userId);

	void blocUser(String idUser);

	List<User> listBlockedUser();

	List<User> listNotBlockedUser();

	void saveOrUpdate(User user);

	// services for yasmine
	User findUserById(String idUser);

}
