package com.esprit.hypnotrip.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.esprit.hypnotrip.persistence.Event;
import com.esprit.hypnotrip.services.interfaces.EventServicesLocal;
import com.esprit.hypnotrip.services.interfaces.EventServicesRemote;

/**
 * Session Bean implementation class EventServices
 */
@Stateless
public class EventServices implements EventServicesRemote, EventServicesLocal {

	/**
	 * Default constructor.
	 */

	@PersistenceContext
	EntityManager entityManager;

	private List<String> months = new ArrayList<String>();
	private List<Event> events = new ArrayList<Event>();
	private Event event = new Event();

	public EventServices() {
		// TODO Auto-generated constructor stub
	}

	// get events in this week
	@Override
	public List<Event> getAllThisWeekEvents() {

		// SQL QUERY
		String query = "SELECT * FROM Pages WHERE dateofevent BETWEEN curdate() AND DATE_ADD(curdate(),INTERVAL 7 DAY) AND Discriminator = 'Event';";

		// native SQL QUERY with EM
		Query q = entityManager.createNativeQuery(query, Event.class);

		try {

			events = q.getResultList();

		} catch (Exception e) {
			// TODO: handle exception
		}

		return events;
	}

	// get all next week events
	@SuppressWarnings("unchecked")
	@Override
	public List<Event> getAllNextWeekEvents() {

		// SQL QUERY
		String query = "SELECT * FROM Pages WHERE dateofevent BETWEEN DATE_ADD(curdate(),INTERVAL 7 DAY) AND DATE_ADD(curdate(),INTERVAL 14 DAY)AND Discriminator = 'Event'";

		// native SQL QUERY with EM
		Query q = entityManager.createNativeQuery(query, Event.class);

		try {

			events = q.getResultList();

		} catch (Exception e) {
			// TODO: handle exception
		}

		return events;
	}

	// get all months from event
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getAllEventMonths() {

		// SQL QUERY
		String queryMonths = "SELECT DISTINCT DATE_FORMAT(dateofevent, '%M') FROM Pages WHERE discriminator ='Event'";

		// native SQL QUERY with EM
		Query queryM = entityManager.createNativeQuery(queryMonths);

		try {

			months = queryM.getResultList();

		} catch (Exception e) {
			// TODO: handle exception
		}

		return months;
	}

	// get all events available in this current month
	@SuppressWarnings("unchecked")
	@Override
	public List<Event> getAllThisMonthEvents() {

		// SQL QUERY
		String sql = "SELECT * FROM Pages WHERE dateofevent BETWEEN curdate() AND DATE_ADD(curdate(),INTERVAL 30 DAY)AND Discriminator = 'Event'";

		// native SQL QUERY with EM
		Query query = entityManager.createNativeQuery(sql, Event.class);

		try {

			events = query.getResultList();

		} catch (Exception e) {
			// TODO: handle exception
		}

		return events;
	}

	// get all events available this month
	@SuppressWarnings("unchecked")
	@Override
	public List<Event> getAllThisMonthEvents2() {

		// SQL QUERY
		String sql = "SELECT * FROM Pages WHERE dateofevent BETWEEN curdate() AND DATE_ADD(curdate(),INTERVAL 31 DAY)AND Discriminator = 'Event'";

		// native SQL QUERY with EM
		Query query = entityManager.createNativeQuery(sql, Event.class);

		try {

			events = query.getResultList();

		} catch (Exception e) {
			// TODO: handle exception
		}

		return events;
	}

	@Override
	public List<Event> getMonthlyEventsByMonth() {

		// List<Event> events = new ArrayList<Event>();

		// try {
		// months = getAllEventMonths();
		// } catch (Exception e) {
		// System.out.println("no months");
		// }
		// for (String month : months) ;ùmm
		{
			// switch (month) {
			// case "January":
			// case "March":
			// case "May":
			// case "July":
			// case "August":
			// case "October":
			// case "December": {
			//
			// events = getAllThisMonthEevents();
			//
			// break;
			// }
			//
			// case "April":
			// case "June":
			// case "September":
			// case "November": {
			//
			// events = getAllThisMonthEevents2();
			//
			// break;
			// }
			//
			// default:
			// System.out.println("error");
			// break;
			// }
			// }
			//

			return null;

		}
	}

	// get most followed event
	// upcoming event
	// ++ followed by a lot of poeople
	@Override
	public Event mostFollowedEventToCome() {

		// JPQL QUERY
		String sql = "SELECT e FROM Event e INNER JOIN e.followers f WHERE f.followStat = true AND e.dateOfEvent>curdate() ORDER BY COUNT(f.id.pageId) DESC";

		// JPQL QUERY that get most followed and upcomong event
		Query query = entityManager.createQuery(sql);

		try {
			// get the event
			event = (Event) query.getSingleResult();

		} catch (Exception e) {
			// TODO: handle exception
		}

		return event;
	}

	// events i haven't followed
	// that are only in the past week
	@SuppressWarnings("unchecked")
	@Override
	public List<Event> eventsIHaveMissedInTheLastWeek(Integer idUser) {

		// SQL QUERY
		String query = "SELECT * FROM Pages p INNER JOIN Follows f ON f.pageid =p.pageid WHERE p.discriminator='Event' AND f.userid !=:param AND p.dateofevent < DATE_ADD(curdate(),INTERVAL -1 DAY) AND p.dateofevent > DATE_ADD(curdate(),INTERVAL -8 DAY) GROUP BY (p.pageid) ORDER BY (p.dateofevent) DESC;";

		// native SQL QUERY with EM
		Query q = entityManager.createNativeQuery(query, Event.class);
		q.setParameter("param", 5);
		try {

			events = q.getResultList();

		} catch (Exception e) {
			// TODO: handle exception
		}

		return events;
	}

	//test if event is still available or not
	// dateofevent must be equals or larger than the current date
	@Override
	public boolean eventIsAvailaible(Integer idPage) {

		boolean response = false;

		// JPQL QUERY
		String sql = "SELECT e FROM Event e WHERE e.pageId =:param AND e.dateOfEvent>=curdate()";

		// JPQL QUERY that get most followed and upcomong event
		Query query = entityManager.createQuery(sql);
		query.setParameter("param", 5);
		
		try {
			// get the event
			event = (Event) query.getSingleResult();
			if (event != null) {
		
				response = true;
			}
				
		} catch (Exception e) {
			// TODO: handle exception
		}
		

		return response;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Event> availableOrUpcomingEventsIMightLike(String idUser,String place) {
		// JPQL QUERY
		String sql = "SELECT DISTINCT e FROM Event e INNER JOIN e.followers f INNER JOIN f.user u WHERE e.dateOfEvent>=curdate() AND f.id.userId !=:param AND e.place=:param1 ORDER BY (f.id.pageId)";

		// JPQL QUERY that get most followed and upcomong event
		Query query = entityManager.createQuery(sql);
		query.setParameter("param", idUser);
		query.setParameter("param1", place);
		
			try {
					// get the event
					events =  query.getResultList();

			} catch (Exception e) {
					// TODO: handle exception
				}

				return events;
	}

}
