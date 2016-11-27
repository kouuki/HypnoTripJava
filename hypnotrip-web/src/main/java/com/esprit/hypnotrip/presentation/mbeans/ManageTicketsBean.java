package com.esprit.hypnotrip.presentation.mbeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.esprit.hypnotrip.persistence.Pages;
import com.esprit.hypnotrip.persistence.Tickets;
import com.esprit.hypnotrip.persistence.User;
import com.esprit.hypnotrip.services.exceptions.EventOverException;
import com.esprit.hypnotrip.services.exceptions.LimitOfBookingRechedException;
import com.esprit.hypnotrip.services.exceptions.NoMoreTicketsException;
import com.esprit.hypnotrip.services.exceptions.WrongNumberOfCancelingException;
import com.esprit.hypnotrip.services.interfaces.TicketServicesLocal;
import com.esprit.hypnotrip.services.interfaces.UserServicesLocal;

@ManagedBean
@ViewScoped
public class ManageTicketsBean {

	@EJB
	private TicketServicesLocal ticketServicesLocal;
	@EJB
	private UserServicesLocal userServicesLocal;
	@ManagedProperty(value = "#{loginBean}")
	private LoginBean loginBean;

	private User user;

	private boolean showListForm = true;
	private boolean showListOfFriends = false;

	private Pages event;
	private List<Tickets> tickets;
	private Tickets selectedTicket = new Tickets();
	private Tickets mostBookedTicket = new Tickets();
	private List<Tickets> tickesBooked;

	private List<User> friendWhoAreGoingToThisEvent;

	public String doCancelBooking() {

		try {
			userServicesLocal.cancelBooking(selectedTicket, user, 1);
		} catch (EventOverException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "The event is over"));
			return null;

		} catch (WrongNumberOfCancelingException e) {

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Wrong number of cancellation"));
			return null;
		}
		return "simpleUserTicketManagement?faces-redirect=true";
	}

	public Long numberOfReserverTicket(Tickets ticket) {

		return userServicesLocal.numberOfTicketsReservedByIdUser(ticket, this.user.getId());
	}

	public TicketServicesLocal getTicketServicesLocal() {
		return ticketServicesLocal;
	}

	public void setTicketServicesLocal(TicketServicesLocal ticketServicesLocal) {
		this.ticketServicesLocal = ticketServicesLocal;
	}

	public String showFriendsForm() {
		this.showListForm = false;
		this.showListOfFriends = true;
		return null;
	}

	public String showTicketsForm() {
		this.showListForm = true;
		this.showListOfFriends = false;
		return "";
	}

	public Pages getEvent() {
		return event;
	}

	public void setEvent(Pages event) {
		this.event = event;
	}



	@PostConstruct
	public void init() {

		this.user = loginBean.getUser();

		this.event = new Pages();
		event.setPageId(11);

		this.tickets = ticketServicesLocal.selectAllTicketsByIdEvent(event.getPageId());
		this.mostBookedTicket = ticketServicesLocal.mostBookedTicketByEvent(event);
		this.friendWhoAreGoingToThisEvent = userServicesLocal.getAllFriendsWhoAreGoingToTheSameEvent(event,
				this.user.getId());

	}

	public List<Tickets> getTickets() {
		return tickets;
	}

	public void setTickets(List<Tickets> tickets) {
		this.tickets = tickets;
	}

	public Long numberOfBooking(Tickets ticket) {

		return ticketServicesLocal.numberOfTicketsBookedByIdTicket(ticket.getTicketId());
	}

	public String doBook() {

		user.setId(this.user.getId());
		try {
			userServicesLocal.bookATicket(selectedTicket, user);
		} catch (NoMoreTicketsException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "No more tickets are avalible"));
			return null;
		} catch (LimitOfBookingRechedException e) {

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "You only can book 2 tickets"));
			return null;

		} catch (EventOverException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "The event is over"));
			return null;
		}

		return "simpleUserTicketManagement?faces-redirect=true";
	}

	public UserServicesLocal getUserServicesLocal() {
		return userServicesLocal;
	}

	public void setUserServicesLocal(UserServicesLocal userServicesLocal) {
		this.userServicesLocal = userServicesLocal;
	}

	public boolean isShowListForm() {
		return showListForm;
	}

	public void setShowListForm(boolean showListForm) {
		this.showListForm = showListForm;
	}

	public Tickets getSelectedTicket() {
		return selectedTicket;
	}

	public void setSelectedTicket(Tickets selectedTicket) {
		this.selectedTicket = selectedTicket;
	}

	public Tickets getMostBookedTicket() {
		return mostBookedTicket;
	}

	public void setMostBookedTicket(Tickets mostBookedTicket) {
		this.mostBookedTicket = mostBookedTicket;
	}

	public List<User> getFriendWhoAreGoingToThisEvent() {
		return friendWhoAreGoingToThisEvent;
	}

	public void setFriendWhoAreGoingToThisEvent(List<User> friendWhoAreGoingToThisEvent) {
		this.friendWhoAreGoingToThisEvent = friendWhoAreGoingToThisEvent;
	}

	public boolean isShowListOfFriends() {
		return showListOfFriends;
	}

	public void setShowListOfFriends(boolean showListOfFriends) {
		this.showListOfFriends = showListOfFriends;
	}

	public List<Tickets> getTickesBooked() {
		return tickesBooked;
	}

	public void setTickesBooked(List<Tickets> tickesBooked) {
		this.tickesBooked = tickesBooked;
	}

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
