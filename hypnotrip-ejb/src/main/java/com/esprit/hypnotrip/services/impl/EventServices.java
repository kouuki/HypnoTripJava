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

	
	public EventServices() {
		// TODO Auto-generated constructor stub
	}

	// get events in this week
	@Override
	public List<Event> getAllThisWeekEvents() {

		String query = "SELECT * FROM Pages WHERE dateofevent BETWEEN curdate() AND DATE_ADD(curdate(),INTERVAL 7 DAY);";
		Query q = entityManager.createNativeQuery(query, Event.class);

		try {
			events = q.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
		}

		return events;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Event> getAllNextWeekEevents() {

//		List<Event> events = new ArrayList<Event>();

		String query = "SELECT * FROM Pages WHERE dateofevent BETWEEN DATE_ADD(curdate(),INTERVAL 7 DAY) AND DATE_ADD(curdate(),INTERVAL 14 DAY)";
		Query q = entityManager.createNativeQuery(query, Event.class);

		try {
			events = q.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
		}

		return events;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getAllEventMonths() {

//		List<String> months = new ArrayList<String>();

		String queryMonths = "SELECT DATE_FORMAT(dateofevent, '%M') FROM Pages;";
		Query queryM = entityManager.createNativeQuery(queryMonths);
		try {
			months = queryM.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return months;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Event> getAllThisMonthEevents() {

//		List<Event> events = new ArrayList<Event>();

		String sql = "SELECT * FROM Pages WHERE dateofevent BETWEEN curdate() AND DATE_ADD(curdate(),INTERVAL 30 DAY)";
		Query query = entityManager.createNativeQuery(sql, Event.class);
		try {
			events = query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return events;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Event> getAllThisMonthEevents2() {
//		List<Event> events = new ArrayList<Event>();

		String sql = "SELECT * FROM Pages WHERE dateofevent BETWEEN curdate() AND DATE_ADD(curdate(),INTERVAL 31 DAY)";
		Query query = entityManager.createNativeQuery(sql, Event.class);
		try {
			events = query.getResultList();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return events;
	}

	@Override
	public List<Event> getMonthlyEeventsByMonth() {

//		List<Event> events = new ArrayList<Event>();

//			try {
//			    months = getAllEventMonths();
//			} catch (Exception e) {
//				System.out.println("no months");
//			}
//			for (String month : months) {
//				switch (month) {
//				case "January":
//				case "March":
//				case "May":
//				case "July":
//				case "August":
//				case "October":
//				case "December": {
//
//					events = getAllThisMonthEevents();
//
//					break;
//				}
//
//				case "April":
//				case "June":
//				case "September":
//				case "November": {
//
//					events = getAllThisMonthEevents2();
//
//					break;
//				}
//
//				default:
//					System.out.println("error");
//					break;
//				}
//			}
//			
		
		return null;

	}	

}
