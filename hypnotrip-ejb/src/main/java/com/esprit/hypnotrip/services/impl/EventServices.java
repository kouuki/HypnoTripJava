package com.esprit.hypnotrip.services.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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

	private String jpql, jpql1;
	private Query query, query1;
	private List<Integer> months = new ArrayList<Integer>();
	private List<Event> events = new ArrayList<Event>();

	List<Event> events1 = new ArrayList<Event>();
	List<Event> events2 = new ArrayList<Event>();

	private Event event = new Event();
	private Calendar calendar = Calendar.getInstance();
	private Calendar calendar2 = Calendar.getInstance();
	private Date searchedDate = calendar.getTime();
	private Date searchedDate2 = calendar.getTime();

	public EventServices() {
		// TODO Auto-generated constructor stub
	}

	// get events in this week
	@SuppressWarnings({ "unchecked", "static-access" })
	@Override
	public List<Event> getAllThisWeekEvents() {

		// JPQL QUERY
		jpql = "SELECT e FROM Event e WHERE e.dateOfEvent BETWEEN CURRENT_DATE AND ?1";
		query = entityManager.createQuery(jpql);

		// get the date i want with calendar
		calendar.setTime(searchedDate);
		calendar.add(Calendar.DATE, 7);

		// send as a parameter, the current date + week
		query.setParameter(1, calendar.getTime());

		try {

			events = query.getResultList();

		} catch (Exception e) {
			// TODO: handle exception
		}

		return events;
	}

	// get all next week events
	@SuppressWarnings("unchecked")
	@Override
	public List<Event> getAllNextWeekEvents() {

		// JPQL QUERY
		jpql = "SELECT e FROM Event e WHERE e.dateOfEvent BETWEEN ?1 AND ?2";
		query = entityManager.createQuery(jpql);

		// get the date i want with calendar
		calendar.setTime(searchedDate);
		calendar.add(Calendar.DATE, 7);

		// get the date i want with calendar
		calendar2.setTime(searchedDate2);
		calendar2.add(Calendar.DATE, 14);

		// send as a parameter, the current date + week
		query.setParameter(1, calendar.getTime());
		query.setParameter(2, calendar2.getTime());

		try {

			events = query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
		}

		return events;
	}

	// get all months from event
	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getAllEventMonths() {

		String queryMonths = "SELECT DISTINCT MONTH(e.dateOfEvent) FROM Event e";
		query = entityManager.createQuery(queryMonths);

		try {

			months = query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
		}

		return months;
	}

	// get all events available in this current month
	@SuppressWarnings("unchecked")
	@Override
	public List<Event> getAllThisMonthEvents() {

		// JPQL QUERY
		jpql = "SELECT e FROM Event e WHERE e.dateOfEvent BETWEEN CURRENT_DATE AND ?1";
		query = entityManager.createQuery(jpql);

		// get the date i want with calendar
		calendar.setTime(searchedDate);
		calendar.add(Calendar.DATE, 30);

		// send as a parameter, the current date + week
		query.setParameter(1, calendar.getTime());

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

		// JPQL QUERY
		jpql = "SELECT e FROM Event e WHERE e.dateOfEvent BETWEEN CURRENT_DATE AND ?1";
		query = entityManager.createQuery(jpql);

		// get the date i want with calendar
		calendar.setTime(searchedDate);
		calendar.add(Calendar.DATE, 31);

		// send as a parameter, the current date + week
		query.setParameter(1, calendar.getTime());

		try {

			events = query.getResultList();

		} catch (Exception e) {
			// TODO: handle exception
		}

		return events;
	}

	@Override
	public List<Event> getMonthlyEventsByMonth() {

		try {

			months = getAllEventMonths();

		} catch (Exception e) {
			System.out.println("no months");
		}

		for (Integer month : months) {

			switch (month) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12: {

				events = getAllThisMonthEvents();

				break;
			}

			case 4:
			case 6:
			case 9:
			case 11: {

				events = getAllThisMonthEvents2();

				break;
			}

			default:
				System.out.println("error");
				break;
			}
		}

		return events;

	}

	// get most followed event
	// upcoming event
	// ++ followed by a lot of poeople
	@Override
	public Event mostFollowedEventToCome() {

		// JPQL QUERY
		jpql = "SELECT e FROM Event e INNER JOIN e.followers f WHERE f.followStat = true AND e.dateOfEvent>curdate() GROUP BY (f.id.pageId) ORDER BY COUNT(f.id.pageId) DESC";

		// JPQL QUERY that get most followed and upcomong event
		query = entityManager.createQuery(jpql);

		try {
			// get the event
			event = (Event) query.getResultList().get(0);

		} catch (Exception e) {
			// TODO: handle exception
		}

		return event;
	}

	// events i haven't followed
	// that are only in the past week
	@SuppressWarnings("unchecked")
	@Override
	public List<Event> eventsIHaveMissedInTheLastWeek(String idUser) {

		// JPQL QUERY
		jpql = "SELECT e FROM Event e WHERE e.dateOfEvent<curdate()";
		query = entityManager.createQuery(jpql);
		events = query.getResultList();

		// JPQL QUERY
		jpql1 = "SELECT DISTINCT e FROM Event e INNER JOIN e.followers f WHERE e.dateOfEvent<curdate() AND f.id.userId !=:param GROUP BY(e.dateOfEvent)";
		query1 = entityManager.createQuery(jpql1);
		query1.setParameter("param", idUser);
		events1 = query1.getResultList();

		for (Event event : events) {
			for (Event event1 : events1) {
				if (event.getPageId() != event1.getPageId()) {
					events2.add(event);
				}
			}
		}

		return events2;
	}

	// test if event is still available or not
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
	public List<Event> availableOrUpcomingEventsInMyArea(String idUser, String place) {

		// JPQL QUERY
		// String sql = "SELECT DISTINCT e FROM Event e INNER JOIN e.followers f
		// INNER JOIN f.user u WHERE e.dateOfEvent>=curdate() AND f.id.userId
		// !=:param AND e.place =:param1 ORDER BY (e.pageId)";
		//
		// // JPQL QUERY that get most followed and upcomong event
		// Query query = entityManager.createQuery(sql);
		// query.setParameter("param", idUser);
		// query.setParameter("param1", place);

		jpql = "SELECT e FROM Event e WHERE e.place=:param AND e.dateOfEvent>=curdate()";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("param", place);
		events = query.getResultList();

		jpql1 = "SELECT DISTINCT e FROM Event e INNER JOIN e.followers f WHERE e.dateOfEvent>=curdate() AND f.id.userId!=:param AND e.place=:param1";
		query1 = entityManager.createQuery(jpql1);
		query1.setParameter("param", idUser);
		query1.setParameter("param1", place);
		events1 = query1.getResultList();

		for (Event event : events) {
			for (Event event1 : events1) {

				if (event != event1) {
					events2.add(event);
				}
			}

		}

		return events2;

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public List<Event> getAllEvents(){
		String jpql="SELECT e FROM Event e ";
		Query query = entityManager.createQuery(jpql);
		try{
			events = query.getResultList();
		}catch(Exception e) {
			
		}
		
		return events;
	}
	
	


}
