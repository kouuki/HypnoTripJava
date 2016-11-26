package GUI;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.esprit.hypnotrip.services.interfaces.FollowersServicesRemote;
import com.esprit.hypnotrip.services.interfaces.OfferServiceRemote;

public class DhaferTests {

	public static void main(String[] args) throws NamingException {
		Context context = new InitialContext();
		// String jndiTickets =
		// "hypnotrip-ear/hypnotrip-ejb/PageService!com.esprit.hypnotrip.services.interfaces.PageServiceRemote";
		// PageServiceRemote pageServiceRemote = (PageServiceRemote)
		// context.lookup(jndiTickets);

		// System.out.println(pageServiceRemote.ListMyPages("b38f3299-6949-42c7-9a6c-f998c66f485d"));

		String jndi = "hypnotrip-ear/hypnotrip-ejb/FollowersServices!com.esprit.hypnotrip.services.interfaces.FollowersServicesRemote";
		FollowersServicesRemote followersServicesRemote = (FollowersServicesRemote) context.lookup(jndi);

//		followersServicesRemote.unfollowLastFollowForPage(11, "b38f3299-6949-42c7-9a6c-f998c666658f");
	}

}
