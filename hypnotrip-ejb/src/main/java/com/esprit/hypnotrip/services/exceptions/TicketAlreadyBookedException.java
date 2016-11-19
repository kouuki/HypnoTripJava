package com.esprit.hypnotrip.services.exceptions;

public class TicketAlreadyBookedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TicketAlreadyBookedException(String message) {
		super(message) ; 
	}

}
