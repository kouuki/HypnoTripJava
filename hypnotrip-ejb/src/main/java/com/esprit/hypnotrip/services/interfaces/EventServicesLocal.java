package com.esprit.hypnotrip.services.interfaces;

import java.util.List;

import javax.ejb.Local;

import com.esprit.hypnotrip.persistence.Event;

@Local
public interface EventServicesLocal {

	List<Event> getAllThisWeekEvents();

	List<Event> getAllNextWeekEevents();

	List<String> getAllEventMonths();

	List<Event> getAllThisMonthEevents();

	List<Event> getAllThisMonthEevents2();
	
	List<Event> getMonthlyEeventsByMonth();
}
