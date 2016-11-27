package com.esprit.hypnotrip.presentation.mbeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

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
public class ListOfUserBookingBean {

	private Tickets selectedTicket = new Tickets();
	@EJB
	private TicketServicesLocal ticketServicesLocal;
	@EJB
	private UserServicesLocal userServicesLocal;
	@ManagedProperty(value = "#{loginBean}")
	private LoginBean loginBean;

	private User user;
	
	@PostConstruct
	public void init() {

		this.user = loginBean.getUser(); 
		
	}
	
	
	
	public List<Tickets> doFindAllBookedtickets() {

		return userServicesLocal.listTicketsBookedByUserEvent(user);
	}
	
	public Long numberOfReserverTicket(Tickets ticket) {

		return userServicesLocal.numberOfTicketsReservedByIdUser(ticket, this.user.getId());
	}
	
	public String doBook() {

		user.setId(this.user.getId());
		try {
			userServicesLocal.bookATicket(selectedTicket, user);
		} catch (NoMoreTicketsException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "No more tickets are avalible"));
		
		} catch (LimitOfBookingRechedException e) {

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "You only can book 2 tickets"));
			   return null ; 

		} catch (EventOverException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "The event is over"));
			   return null ; 
		}

		return "?faces-redirect=true";
	}

	public Tickets getSelectedTicket() {
		return selectedTicket;
	}

	public void setSelectedTicket(Tickets selectedTicket) {
		this.selectedTicket = selectedTicket;
	}
	
	public String doCancelBooking() {

		try {
			userServicesLocal.cancelBooking(selectedTicket, user, 1);
		} catch (EventOverException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "The event is over"));
			   return null ; 

		} catch (WrongNumberOfCancelingException e) {

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!", "Wrong number of cancellation"));
		   return null ; 
		}
		return "listOfBookedTickets?faces-redirect=true";
	}

	public TicketServicesLocal getTicketServicesLocal() {
		return ticketServicesLocal;
	}

	public void setTicketServicesLocal(TicketServicesLocal ticketServicesLocal) {
		this.ticketServicesLocal = ticketServicesLocal;
	}

	public UserServicesLocal getUserServicesLocal() {
		return userServicesLocal;
	}

	public void setUserServicesLocal(UserServicesLocal userServicesLocal) {
		this.userServicesLocal = userServicesLocal;
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
