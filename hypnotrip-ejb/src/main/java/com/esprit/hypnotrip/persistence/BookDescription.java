package com.esprit.hypnotrip.persistence;

import com.esprit.hypnotrip.persistence.BookId;
import java.io.Serializable;
import java.lang.Boolean;
import java.util.Date;

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
	private Tickets ticket;
	
	
	

	public BookDescription(Boolean bookingStatus, User user, Tickets ticket) {
		super();
		this.bookingStatus = bookingStatus;
		this.user = user;
		this.ticket = ticket;
		this.bookId = new BookId(ticket.getTicketId(), user.getId(),new Date());
	}
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
	public Tickets getTicket() {
		return ticket;
	}
	public void setTicket(Tickets ticket) {
		this.ticket = ticket;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookId == null) ? 0 : bookId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookDescription other = (BookDescription) obj;
		if (bookId == null) {
			if (other.bookId != null)
				return false;
		} else if (!bookId.equals(other.bookId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "BookDescription [bookId=" + bookId + ", bookingStatus=" + bookingStatus + ", user=" + user + ", ticket="
				+ ticket + "]";
	}
	
	
	
   
}
