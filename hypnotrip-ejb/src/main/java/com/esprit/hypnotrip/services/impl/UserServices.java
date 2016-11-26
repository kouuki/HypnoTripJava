package com.esprit.hypnotrip.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import com.esprit.hypnotrip.persistence.BookDescription;
import com.esprit.hypnotrip.persistence.Pages;
import com.esprit.hypnotrip.persistence.Tickets;
import com.esprit.hypnotrip.persistence.User;
import com.esprit.hypnotrip.services.exceptions.EventOverException;
import com.esprit.hypnotrip.services.exceptions.LimitOfBookingRechedException;
import com.esprit.hypnotrip.services.exceptions.NoMoreTicketsException;
import com.esprit.hypnotrip.services.exceptions.WrongNumberOfCancelingException;
import com.esprit.hypnotrip.services.interfaces.EventServicesLocal;
import com.esprit.hypnotrip.services.interfaces.TicketServicesLocal;
import com.esprit.hypnotrip.services.interfaces.UserServicesLocal;
import com.esprit.hypnotrip.services.interfaces.UserServicesRemote;

/**
 * Session Bean implementation class UserServices
 */
@Stateless
public class UserServices implements UserServicesRemote, UserServicesLocal {

	@PersistenceContext
	EntityManager entityManager;

	@EJB
	TicketServicesLocal ticketServicesLocal;
	@EJB
	EventServicesLocal eventServices;

	public UserServices() {

	}

	@Override
	public void bookATicket(Tickets ticket, User user)
			throws NoMoreTicketsException, LimitOfBookingRechedException, EventOverException {
		if (eventServices.eventIsAvailaible(ticket.getEvent().getPageId())) {
			throw new EventOverException("You can not book a ticket for a finished event");
		} else if (ticket.getNumberOfPlaces() == null) {
			throw new NoMoreTicketsException("No more tickets avalible");
		} else if (numberOfTicketsReservedByIdUser(ticket, user.getId()) == 2) {
			throw new LimitOfBookingRechedException("Limit of booking reached");
		} else {
			BookDescription bookDescription = new BookDescription(true, user, ticket);
			ticket.getBookDescriptions().add(bookDescription);
			ticket.setNumberOfPlaces(ticket.getNumberOfPlaces() - 1);
			if (ticket.getNumberOfPlaces() == 0) {
				ticket.setNumberOfPlaces(null);
			}

			entityManager.merge(ticket);

		}
	}

	@Override
	public void cancelBooking(Tickets ticket, User user, Integer numberOfTicketsToCancel)
			throws EventOverException, WrongNumberOfCancelingException {
		if (eventServices.eventIsAvailaible(ticket.getEvent().getPageId())) {
			throw new EventOverException("You can not book a ticket for a finished event");
		} else if (numberOfTicketsReservedByIdUser(ticket, user.getId()) < numberOfTicketsToCancel) {
			throw new WrongNumberOfCancelingException("Wrong number of cancel");
		} else {
			List<BookDescription> book = listOfBookingByUser(user, ticket);

			for (int i = 0; i < numberOfTicketsToCancel; i++) {

				BookDescription booking = book.get(i);

				for (int j = 0; j < ticket.getBookDescriptions().size(); j++) {
					if (ticket.getBookDescriptions().get(j).equals(booking)) {
						ticket.getBookDescriptions().get(j).setBookingStatus(false);
						break;
					}
				}

			}
			ticket.setNumberOfPlaces(ticket.getNumberOfPlaces() + numberOfTicketsToCancel);

			entityManager.merge(ticket);

		}

	}

	@Override
	public List<Tickets> listTicketsBookedByUserEvent(User user) {

		String jqpl = "SELECT DISTINCT(tickets)  FROM Tickets tickets INNER JOIN tickets.bookDescriptions db "
				+ "WHERE db.user =:param1 AND db.bookingStatus=true";
		Query query = entityManager.createQuery(jqpl);
		query.setParameter("param1", user);
	

		return query.getResultList();
	}

	@Override
	public Long numberOfTicketsReservedByIdUser(Tickets ticket, String idUser) {
		String jqpl = "SELECT COUNT(bd) FROM BookDescription bd " + "WHERE bd.bookingStatus=true "
				+ "AND bd.ticket=:param1 " + "AND bd.user.id=:param2";
		Query query = entityManager.createQuery(jqpl);
		query.setParameter("param1", ticket);
		query.setParameter("param2", idUser);

		return (Long) query.getSingleResult();
	}

	@Override
	public List<BookDescription> listOfBookingByUser(User user, Tickets ticket) {

		String jqpl = "SELECT bd FROM BookDescription bd " + "WHERE bd.bookingStatus=true " + "AND bd.ticket=:param1 "
				+ "AND bd.user=:param2";
		Query query = entityManager.createQuery(jqpl);
		query.setParameter("param1", ticket);
		query.setParameter("param2", user);
		return query.getResultList();

	}

	@Override
	public List<User> getAllFriendsWhoAreGoingToTheSameEvent(Pages event, String userId) {
		
		try{
		List<String> listOfIds = new ArrayList<>();
       
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:63784/api/BelongWS");
		WebTarget userFriends = target.path("UserFriends").path(userId);
		Response response = userFriends.request().get();
		listOfIds = response.readEntity(listOfIds.getClass());

		String jqpl = "SELECT users FROM User users " + "INNER JOIN users.bookDescriptions book "
				+ "INNER JOIN book.ticket t " + "INNER JOIN t.event e " + "WHERE e=:param1";
		Query query = entityManager.createQuery(jqpl);
		query.setParameter("param1", event);
		List<User> users = query.getResultList();
		response.close();

		List<User> usersGoingToTheEnvent = new ArrayList<>();
		if (users != null) {
			for (User u : users) {
				if (listOfIds.contains(u.getId())) {
					usersGoingToTheEnvent.add(u);
				}
			}
		}

		return usersGoingToTheEnvent; } 
		catch(Exception e ) {
			return null ; 
		}
	}

	@Override
	public void blocUser(String idUser) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:63784/api/UserWS");
		WebTarget userFriends = target.path("blockUser").path(idUser);
		Response response = userFriends.request().get();
		response.close();

	}

	public void deblocUser(String idUser) {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:63784/api/UserWS");
		WebTarget userFriends = target.path("DeblockUser").path(idUser);
		Response response = userFriends.request().get();
		response.close();

	}

	@Override
	public List<User> listBlockedUser() {
		Query query = entityManager.createQuery("SELECT u FROM User u WHERE Etat=0 ");
		List<User> lr = query.getResultList();
		return lr;
	}

	@Override
	public List<User> listNotBlockedUser() {
		Query query = entityManager.createQuery("SELECT u FROM User u WHERE Etat= 1 ");
		List<User> lr = query.getResultList();
		return lr;
	}

	@Override
	public User getUserbyId(String idUser) {
		entityManager.find(User.class, idUser);
		return null;
	}

	@Override
	public void saveOrUpdate(User user) {
		entityManager.merge(user);

	}

	// services for yasmine
	@Override
	public User findUserById(String idUser) {

		return entityManager.find(User.class, idUser);
	}

	@Override
	public User findUserByLoginAndPassword(String email, String password) {
		String jpql = "SELECT u FROM User u WHERE u.email=:param AND u.password=:param1 AND u.etat='1'";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("param", email);
		query.setParameter("param1", password);
		@SuppressWarnings("unchecked")
		List<User> userFounded = query.getResultList();
		if (userFounded.isEmpty()) {
			return null;
		}
		return userFounded.get(0);

	}

	@Override
	public int numberOfConnexion() {
		Query query = entityManager.createQuery("SELECT u FROM Userconnections u ");
		List<User> lr = query.getResultList();
		return lr.size();
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	// service for yasmine
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllFriendsByUserId(String idUser) {

		List<String> friendsId = new ArrayList<String>();
		List<User> friends = new ArrayList<User>();

		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:63784/api/BelongWS");
		WebTarget userFriends = target.path("UserFriends").path(idUser);
		Response response = userFriends.request().get();
		friendsId = response.readEntity(friendsId.getClass());

		String jqpl = "SELECT u FROM User u WHERE u.id=:param";
		Query query = entityManager.createQuery(jqpl);

		for (String id : friendsId) {
			query.setParameter("param", id);

			try {

				friends.add((User) query.getSingleResult());
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		response.close();

		return friends;
	}

}
