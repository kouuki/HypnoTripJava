package GUI;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.esprit.hypnotrip.services.interfaces.OfferServiceRemote;

public class JiheneTests {

	public static void main(String[] args) throws NamingException {
		Context context = new InitialContext();
		// String jndiTickets =
		// "hypnotrip-ear/hypnotrip-ejb/PageService!com.esprit.hypnotrip.services.interfaces.PageServiceRemote";
		// PageServiceRemote pageServiceRemote = (PageServiceRemote)
		// context.lookup(jndiTickets);

		// System.out.println(pageServiceRemote.ListMyPages("b38f3299-6949-42c7-9a6c-f998c66f485d"));

		String jndi = "hypnotrip-ear/hypnotrip-ejb/OfferService!com.esprit.hypnotrip.services.interfaces.OfferServiceRemote";
		OfferServiceRemote offerServiceRemote = (OfferServiceRemote) context.lookup(jndi);

		System.out.println(offerServiceRemote.SearchOfferByPriceDiscount(14.0));
	}

}
