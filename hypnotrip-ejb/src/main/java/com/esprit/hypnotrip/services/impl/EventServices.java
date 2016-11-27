package com.esprit.hypnotrip.services.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.esprit.hypnotrip.persistence.Event;
import com.esprit.hypnotrip.persistence.Follows;
import com.esprit.hypnotrip.persistence.Pages;
import com.esprit.hypnotrip.persistence.Touristicplace;
import com.esprit.hypnotrip.persistence.User;
import com.esprit.hypnotrip.services.interfaces.EventServicesLocal;
import com.esprit.hypnotrip.services.interfaces.EventServicesRemote;
import com.esprit.hypnotrip.services.interfaces.PageServiceLocal;
import com.esprit.hypnotrip.services.interfaces.UserServicesLocal;

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

	@EJB
	UserServicesLocal userServicesLocal;

	@EJB
	PageServiceLocal pageServiceLocal;

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
	public List<Event> eventsIHaveMissed(String idUser) {

		// JPQL QUERY
		jpql = "SELECT e FROM Event e WHERE e.dateOfEvent<curdate()";
		query = entityManager.createQuery(jpql);

		try {

			events = query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
		}

		return events;
	}

	// test if event is still available or not
	// dateofevent must be equals or larger than the current date
	@Override
	public boolean eventIsAvailaible(Integer idPage) {

		boolean response = false;

		// JPQL QUERY
		jpql = "SELECT e FROM Event e WHERE e.pageId =:param AND e.dateOfEvent>=curdate()";

		// JPQL QUERY that get most followed and upcomong event
		query = entityManager.createQuery(jpql);
		query.setParameter("param", idPage);

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
		jpql = "SELECT e FROM Event e WHERE e.place=:param AND e.dateOfEvent>=curdate()";

		// JPQL QUERY that gets available or upcoming events
		Query query = entityManager.createQuery(jpql);

		query.setParameter("param", place);
		events = query.getResultList();

		return events;

	}

	@SuppressWarnings("unchecked")
	@Override
		public Map<Event, Long> statisticsEvent() {

			jpql = "SELECT  e FROM Event e INNER JOIN e.followers f WHERE e.dateOfEvent>=curdate() GROUP BY f.id.pageId ORDER BY Count(f.id.pageId) DESC";
			query = entityManager.createQuery(jpql);
			events = query.getResultList();
			Map<Event, Long> map = new HashMap<Event, Long>();
			jpql1 = "SELECT  COUNT(e) FROM Event e INNER JOIN e.followers f WHERE e.dateOfEvent>=curdate() GROUP BY f.id.pageId ORDER BY Count(f.id.pageId) DESC";
			query1 = entityManager.createQuery(jpql1);
			List<Long> list = query1.getResultList();
			int i = 0;
			for (Event event : events) {

				map.put(entityManager.find(Event.class, event.getPageId()), list.get(i));
				i++;
			}
			System.out.println(map);
			return map;
		}

	@SuppressWarnings("unchecked")
	@Override
	public boolean isFollowedByUser(String idUser, Integer idPage) {

		boolean response = false;
		Follows follows = new Follows();
		// JPQL QUERY
		jpql = "SELECT f FROM Follows f WHERE f.id.userId =:param AND f.id.pageId=:param1 AND f.followStat=true";

		// JPQL QUERY that get most followed and upcomong event
		query = entityManager.createQuery(jpql);
		query.setParameter("param", idUser);
		query.setParameter("param1", idPage);

		try {
			// get the event
			follows = (Follows) query.getSingleResult();
			if (follows != null) {

				response = true;
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return response;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Event> getAllEventsFollowedByUser(String idUser) {
		// JPQL QUERY
		jpql = "SELECT e FROM Event e INNER JOIN e.followers f WHERE f.id.userId =:param AND f.followStat=true and e.dateOfEvent>=curdate()";
		query = entityManager.createQuery(jpql);
		query.setParameter("param", idUser);

		try {
			events = query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
		}

		return events;
	}

	public List<Event> getAllEvents() {
		String jpql = "SELECT e FROM Event e ";
		Query query = entityManager.createQuery(jpql);
		try {
			events = query.getResultList();
		} catch (Exception e) {

		}

		return events;
	}


	
	
	
	@Override
	public void followPage(String idUser, int idPage) {
		// user exists
		User userFound = userServicesLocal.findUserById(idUser);

		// page exists
		Pages page = pageServiceLocal.findPageById(idPage);
		
		Follows follows = new Follows(true, false, userFound, page);
		entityManager.merge(follows);

	}

	@Override
	public void unfollowPage(String idUser, int idPage) {
		// user exists
		User userFound = userServicesLocal.findUserById(idUser);

		// page exists
		Pages page = pageServiceLocal.findPageById(idPage);
		
		Follows follows = new Follows(false, false, userFound, page);
		entityManager.merge(follows);
	}

	@Override
	public int numberOfEvents() {
		List<Event> ls = getAllEvents();
		return ls.size();
	}

}
