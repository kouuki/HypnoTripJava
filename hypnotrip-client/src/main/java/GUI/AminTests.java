package GUI;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.esprit.hypnotrip.persistence.Tickets;
import com.esprit.hypnotrip.services.exceptions.EventOverException;
import com.esprit.hypnotrip.services.exceptions.TicketAlreadyBookedException;
import com.esprit.hypnotrip.services.interfaces.TicketServicesRemote;

public class AminTests {

	public static void main(String[] args) throws NamingException, EventOverException, TicketAlreadyBookedException {

		Context context = new InitialContext();
		String jndiTickets = "hypnotrip-ear/hypnotrip-ejb/TicketServices!com.esprit.hypnotrip.services.interfaces.TicketServicesRemote";
		TicketServicesRemote ticketServicesRemote = (TicketServicesRemote) context.lookup(jndiTickets);

		Tickets ticket = new Tickets();
		ticket.setPrice(15);
		ticket.setType("This is the best ticket ever");
	

	}

}
