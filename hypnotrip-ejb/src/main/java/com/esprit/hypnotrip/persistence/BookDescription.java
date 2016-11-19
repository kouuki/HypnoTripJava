package com.esprit.hypnotrip.persistence;

import com.esprit.hypnotrip.persistence.BookId;
import java.io.Serializable;
import java.lang.Boolean;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: BookDescription
 *
 */
@Entity

public class BookDescription implements Serializable {

   @EmbeddedId
	private BookId bookId;
	private Boolean bookingStatus;
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name="userId", referencedColumnName="id",updatable=false , insertable= false)
	private User user ; 
	
	@ManyToOne
	@JoinColumn(name="ticketId", referencedColumnName="ticketId",updatable=false , insertable= false)
	private Tickets tickets;
	
	

	public BookDescription() {
		super();
	}   
	public BookId getBookId() {
		return this.bookId;
	}

	public void setBookId(BookId bookId) {
		this.bookId = bookId;
	}   
	public Boolean getBookingStatus() {
		return this.bookingStatus;
	}

	public void setBookingStatus(Boolean bookingStatus) {
		this.bookingStatus = bookingStatus;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Tickets getTickets() {
		return tickets;
	}
	public void setTickets(Tickets tickets) {
		this.tickets = tickets;
	}
   
}
