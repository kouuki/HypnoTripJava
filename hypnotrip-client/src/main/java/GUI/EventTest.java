package GUI;

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

			
			//period test
			List<Event> events = eventServicesRemote.eventsIHaveMissedInTheLastWeek("3");

			for (Event event : events) {
				System.out.println(event.toString());
			}

			//months test
//			 List<Integer> months = eventServicesRemote.getAllEventMonths();
//			
//			 for (Integer integer : months) {
//			 System.out.println(integer);
//			 }

			
			//most followed upcoming event test
			// Event event = eventServicesRemote.mostFollowedEventToCome();
			// System.out.println(event.toString());
			
			//is avialable test
//			boolean response = eventServicesRemote.eventIsAvailaible(5);
//			System.out.println(response);
			
			//events i might like test
//			List<Event> events = eventServicesRemote.availableOrUpcomingEventsIMightLike("5","tunis");
//
//			for (Event event : events) {
//				System.out.println(event.toString());
//			}

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
