package com.esprit.hypnotrip.persistence;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import java.util.Date;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: BookId
 *
 */

@Embeddable
public class BookId implements Serializable {

	
	private Integer ticketId;
	private String userId;
	private Date dateOfBooking;
	private static final long serialVersionUID = 1L;

	public BookId() {
		super();
	}   
	
	public BookId(Integer ticketId , String userId , Date dateOfBooDate) {
		 
		this.ticketId = ticketId ; 
		this.userId  = userId ; 
		this.dateOfBooking =dateOfBooDate; 
	}
	
	

	public Integer getTicketId() {
		return this.ticketId;
	}

	public void setTicketId(Integer ticketId) {
		this.ticketId = ticketId;
	}   
 
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}   
  
	public Date getDateOfBooking() {
		return this.dateOfBooking;
	}

	public void setDateOfBooking(Date dateOfBooking) {
		this.dateOfBooking = dateOfBooking;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dateOfBooking == null) ? 0 : dateOfBooking.hashCode());
		result = prime * result + ((ticketId == null) ? 0 : ticketId.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		BookId other = (BookId) obj;
		if (dateOfBooking == null) {
			if (other.dateOfBooking != null)
				return false;
		} else if (!dateOfBooking.equals(other.dateOfBooking))
			return false;
		if (ticketId == null) {
			if (other.ticketId != null)
				return false;
		} else if (!ticketId.equals(other.ticketId))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BookId [ticketId=" + ticketId + ", userId=" + userId + ", dateOfBooking=" + dateOfBooking + "]";
	}
	
	
   
}
