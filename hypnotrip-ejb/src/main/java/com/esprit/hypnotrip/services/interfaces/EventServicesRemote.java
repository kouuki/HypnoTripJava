package com.esprit.hypnotrip.services.interfaces;

import java.util.List;

import javax.ejb.Remote;

import com.esprit.hypnotrip.persistence.Event;

@Remote
public interface EventServicesRemote {

	List<Event> getAllThisWeekEvents();

	List<Event> getAllNextWeekEevents();

	List<String> getAllEventMonths();

	List<Event> getAllThisMonthEevents();

	List<Event> getAllThisMonthEevents2();

	List<Event> getMonthlyEeventsByMonth();

}
