package GUI;

import java.math.BigInteger;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.esprit.hypnotrip.persistence.Event;
import com.esprit.hypnotrip.services.interfaces.EventServicesRemote;

public class EventTest {

	public static void main(String[] args) {

		try {
			Context context = new InitialContext();
			String jndiName = "hypnotrip-ear/hypnotrip-ejb/EventServices!com.esprit.hypnotrip.services.interfaces.EventServicesRemote";
			EventServicesRemote eventServicesRemote = (EventServicesRemote) context.lookup(jndiName);

			List<Event> events = eventServicesRemote.getMonthlyEeventsByMonth();
			
			for (Event event : events) {
				System.out.println(event.toString());
			}
			
			
//			List<String> months = eventServicesRemote.getAllEvenetMonths();
//			
//			for (String integer : months) {
//				System.out.println(integer);
//			}


		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
