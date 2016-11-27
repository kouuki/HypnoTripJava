package GUI;

import java.util.List;
import java.util.Map;

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

			// period test
//			 List<Event> events =
//			 eventServicesRemote.getAllThisWeekEvents();
//			
//			 for (Event event : events) {
//			 System.out.println(event.toString());
//			 }

			// months test
			// List<Integer> months = eventServicesRemote.getAllEventMonths();
			//
			// for (Integer integer : months) {
			// System.out.println(integer);
			// }

			// most followed upcoming event test
			// Event event = eventServicesRemote.mostFollowedEventToCome();
			// System.out.println(event.toString());

			// is avialable test
			// boolean response = eventServicesRemote.eventIsAvailaible(21);
			// System.out.println(response);

			// events i might like test
			// List<Event> events =
			// eventServicesRemote.availableOrUpcomingEventsInMyArea("5",
			// "tunis");
			// for (Event event : events) {
			// System.out.println(event.toString());
			// }

			// stat test
			// eventServicesRemote.statisticsEvent();

			// is followed test


//			List<Event> events = eventServicesRemote.getAllThisWeekEvents();
//			boolean response = false;
//			for (Event event : events) {
//				response = eventServicesRemote.isFollowedByUser("b38f3299-6949-42c7-9a6c-f998c66f4852",
//						event.getPageId());
//				if (response == false) {
//					System.out.println("rahi mech mawjouda");
//				} else {
//					System.out.println("rahi mawjouda");
//
//				}
//			}

//			Map<Event,Long> events = eventServicesRemote.statisticsEvent();
//			System.out.println(events);
//

			
			
			 eventServicesRemote.followPage("b38f3299-6949-42c7-9a6c-f998c66f4853", 11);

			
			

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
