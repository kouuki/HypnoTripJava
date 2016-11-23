package com.esprit.hypnotrip.presentation.mbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.esprit.hypnotrip.persistence.Event;
import com.esprit.hypnotrip.services.interfaces.EventServicesLocal;

@ManagedBean
@ViewScoped
public class EventsBean {

	// model
	private List<Event> eventsThisWeek = new ArrayList<Event>();
	private List<Event> eventsNextWeek = new ArrayList<Event>();
	private List<Event> eventsThisMonth = new ArrayList<Event>();

	// Forms to show in this page
	private Boolean displayFormThisWeek = true;
	private Boolean displayFormNextWeek = false;
	private Boolean displayFormThisMonth = false;

	// inject event services bean
	@EJB
	EventServicesLocal eventServicesLocal;

	@PostConstruct
	public void init() {
		eventsThisWeek = eventServicesLocal.getAllThisWeekEvents();
		eventsNextWeek = eventServicesLocal.getAllNextWeekEvents();
		eventsThisMonth = eventServicesLocal.getMonthlyEventsByMonth();
	}

	public String selectThisWeekEventsToShow() {
		displayFormThisWeek = true;
		displayFormNextWeek = false;
		displayFormThisMonth = false;
		return null;
	}

	public String selectNextWeekEventsToShow() {
		displayFormThisWeek = false;
		displayFormNextWeek = true;
		displayFormThisMonth = false;
		return null;
	}
	public String selectNextMonthEventsToShow() {
		displayFormThisWeek = false;
		displayFormNextWeek = false;
		displayFormThisMonth = true;
		return null;
	}
	
	
	// recall of wanted methods/services

	public List<Event> getEventsThisWeek() {
		return eventsThisWeek;
	}

	public void setEventsThisWeek(List<Event> eventsThisWeek) {
		this.eventsThisWeek = eventsThisWeek;
	}

	public List<Event> getEventsNextWeek() {
		return eventsNextWeek;
	}

	public void setEventsNextWeek(List<Event> eventsNextWeek) {
		this.eventsNextWeek = eventsNextWeek;
	}

	public List<Event> getEventsThisMonth() {
		return eventsThisMonth;
	}

	public void setEventsThisMonth(List<Event> eventsThisMonth) {
		this.eventsThisMonth = eventsThisMonth;
	}

	public Boolean getDisplayFormThisWeek() {
		return displayFormThisWeek;
	}

	public void setDisplayFormThisWeek(Boolean displayFormThisWeek) {
		this.displayFormThisWeek = displayFormThisWeek;
	}

	public Boolean getDisplayFormNextWeek() {
		return displayFormNextWeek;
	}

	public void setDisplayFormNextWeek(Boolean displayFormNextWeek) {
		this.displayFormNextWeek = displayFormNextWeek;
	}

	public Boolean getDisplayFormThisMonth() {
		return displayFormThisMonth;
	}

	public void setDisplayFormThisMonth(Boolean displayFormThisMonth) {
		this.displayFormThisMonth = displayFormThisMonth;
	}

}
