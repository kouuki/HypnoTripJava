package com.esprit.hypnotrip.services.interfaces;

import java.util.List;

import javax.ejb.Local;

import com.esprit.hypnotrip.persistence.Event;

@Local
public interface EventServicesLocal {

	List<Event> getAllThisWeekEvents();

	List<Event> getAllNextWeekEvents();

	List<Integer> getAllEventMonths();

	List<Event> getAllThisMonthEvents();

	List<Event> getAllThisMonthEvents2();

	List<Event> getMonthlyEventsByMonth();

	List<Event> eventsIHaveMissedInTheLastWeek(String idUser);

	Event mostFollowedEventToCome();

	boolean eventIsAvailaible(Integer idPage);

	List<Event> availableOrUpcomingEventsInMyArea(String idUser, String place);
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	 List<Event> getAllEvents();
}
