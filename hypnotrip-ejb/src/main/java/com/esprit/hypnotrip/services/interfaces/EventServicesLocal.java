package com.esprit.hypnotrip.services.interfaces;

import java.util.List;
import java.util.Map;

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

	List<Event> eventsIHaveMissed(String idUser);

	Event mostFollowedEventToCome();

	boolean eventIsAvailaible(Integer idPage);

	List<Event> availableOrUpcomingEventsInMyArea(String idUser, String place);

	List<Event> getAllEvents();

	Map<Event, Long> statisticsEvent();

	boolean isFollowedByUser(String idUser, Integer idPage);

	List<Event> getAllEventsFollowedByUser(String idUser);

	void followPage(String idUser, int idPage);

	void unfollowPage(String idUser, int idPage);
	
	int numberOfEvents();

}
