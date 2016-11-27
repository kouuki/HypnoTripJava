package com.esprit.hypnotrip.presentation.mbeans;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.PieChartModel;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import com.esprit.hypnotrip.persistence.Event;
import com.esprit.hypnotrip.services.interfaces.EventServicesLocal;
import com.esprit.hypnotrip.services.interfaces.FollowersServicesLocal;

@ManagedBean
@ViewScoped
public class EventsBean {

	// model
	private List<Event> eventsThisWeek = new ArrayList<Event>();
	private List<Event> eventsNextWeek = new ArrayList<Event>();
	private List<Event> eventsThisMonth = new ArrayList<Event>();
	private List<Event> eventsInMyArea = new ArrayList<Event>();

	private Boolean response = false;

	private List<Event> missedEvents = new ArrayList<Event>();
	private Event mostFollowedUpcoming = new Event();

	private Map<Event, Long> map = new HashMap<Event, Long>();

	// Forms to show in this page
	private Boolean displayFormThisWeek = true;
	private Boolean displayFormNextWeek = false;
	private Boolean displayFormThisMonth = false;
	private Boolean displayFormMyArea = false;
	private Boolean displayFormStatistics = false;

	private Boolean displayFormMostFollowedEvent = true;
	private Boolean displayFormMissedEvents = false;

	private MapModel simpleModelThisWeek = new DefaultMapModel();
	private MapModel simpleModelNextWeek = new DefaultMapModel();
	private MapModel simpleModelThisMonth = new DefaultMapModel();
	private MapModel simpleModelMyArea = new DefaultMapModel();

	// user id
	private String idUser;
	private String address;

	private PieChartModel pieChartModel = new PieChartModel();

	private String selectedMenu;
	private String chaine;

	// inject event services bean
	@EJB
	EventServicesLocal eventServicesLocal;

	@EJB
	FollowersServicesLocal followersServicesLocal;

	@ManagedProperty(value = "#{loginBean}")
	private LoginBean loginBean;

	// methode qui charge les events lors de la creation du bean
	@PostConstruct
	public void init() {

		idUser = loginBean.getUser().getId();
		address = loginBean.getUser().getAddress();
		System.out.println(idUser);
		System.out.println(address);

		// get all lists for events
		eventsThisWeek = eventServicesLocal.getAllThisWeekEvents();
		eventsNextWeek = eventServicesLocal.getAllNextWeekEvents();
		eventsThisMonth = eventServicesLocal.getMonthlyEventsByMonth();
		eventsInMyArea = eventServicesLocal.availableOrUpcomingEventsInMyArea(idUser, address);
		mostFollowedUpcoming = eventServicesLocal.mostFollowedEventToCome();
		missedEvents = eventServicesLocal.eventsIHaveMissed(idUser);
		map = eventServicesLocal.statisticsEvent();

		showMapForThisWeekEvents();
		showMapForNextWeekEvents();
		showMapForThisMonthEvents();
		showMapForMyAreaEvents();

		createStatisticsPie();

	}

	public String selectThisWeekEventsToShow() {
		displayFormThisWeek = true;
		displayFormNextWeek = false;
		displayFormThisMonth = false;
		displayFormMyArea = false;
		displayFormStatistics = false;
		return null;
	}

	public String selectNextWeekEventsToShow() {
		displayFormThisWeek = false;
		displayFormNextWeek = true;
		displayFormThisMonth = false;
		displayFormMyArea = false;
		displayFormStatistics = false;
		return null;
	}

	public String selectNextMonthEventsToShow() {
		displayFormThisWeek = false;
		displayFormNextWeek = false;
		displayFormThisMonth = true;
		displayFormMyArea = false;
		displayFormStatistics = false;
		return null;
	}

	public String selectEventsInMyAreaToShow() {
		displayFormThisWeek = false;
		displayFormNextWeek = false;
		displayFormThisMonth = false;
		displayFormMyArea = true;
		displayFormStatistics = false;
		return null;
	}

	public String selectStatistics() {
		displayFormThisWeek = false;
		displayFormNextWeek = false;
		displayFormThisMonth = false;
		displayFormMyArea = false;
		displayFormStatistics = true;
		return null;
	}

	public String selectMostFollowedUpcomingEvent() {
		displayFormMostFollowedEvent = true;
		displayFormMissedEvents = false;
		return null;
	}

	public String selectMissedEvents() {
		displayFormMostFollowedEvent = false;
		displayFormMissedEvents = true;
		return null;
	}

	// map for this week's events
	public void showMapForThisWeekEvents() {
		System.out.println("hani dkhalt lel bean");
		for (Event event : eventsThisWeek) {
			// Shared coordinates
			LatLng coord = new LatLng(event.getLatitude(), event.getLongitude());

			// Basic marker
			simpleModelThisWeek.addOverlay(new Marker(coord, event.getPlace()));

		}
		System.out.println("hani kamalt");
	}

	// show map for next week events
	public void showMapForNextWeekEvents() {

		for (Event event : eventsNextWeek) {
			// Shared coordinates
			LatLng coord = new LatLng(event.getLatitude(), event.getLongitude());

			// Basic marker
			simpleModelNextWeek.addOverlay(new Marker(coord, event.getPlace()));

		}
	}

	// show map for this month events
	public void showMapForThisMonthEvents() {

		for (Event event : eventsThisMonth) {
			// Shared coordinates
			LatLng coord = new LatLng(event.getLatitude(), event.getLongitude());

			// Basic marker
			simpleModelThisMonth.addOverlay(new Marker(coord, event.getPlace()));

		}
	}

	// show map for events in my area
	public void showMapForMyAreaEvents() {

		for (Event event : eventsInMyArea) {
			// Shared coordinates
			LatLng coord = new LatLng(event.getLatitude(), event.getLongitude());

			// Basic marker
			simpleModelMyArea.addOverlay(new Marker(coord, event.getPlace()));

		}
	}

	public String followORUnfollowEvents(int pageId) {

		response = eventServicesLocal.isFollowedByUser(idUser, pageId);
		if (response == false) {

			System.out.println("rahi mech mawjouda");
			chaine = "Follow";

		} else {
			System.out.println("rahi mawjouda");
			chaine = "Unfollow";
		}

		return chaine;
	}

	public String followedORUnfollowedEvents(int pageId) {

		response = eventServicesLocal.isFollowedByUser(idUser, pageId);
		if (response == false) {

			System.out.println("rahi mech mawjouda");
			chaine = "Not Followed";

		} else {
			System.out.println("rahi mawjouda");
			chaine = "Followed";
		}

		return chaine;
	}

	// create statistics for events
	public void createStatisticsPie() {

		pieChartModel.setTitle("Most followed event");
		pieChartModel.setLegendPosition("w");

		pieChartModel.setDiameter(300);
		for (Entry<Event, Long> entry : map.entrySet()) {
			Event cle = entry.getKey();
			Long valeur = entry.getValue();

			pieChartModel.set(cle.getTitle(), valeur);

		}
	}

	// recall of wanted methods/services

	public void doTreatement(int pageId) {
		String navigate = "";

		if (chaine == "Follow") {
			eventServicesLocal.followPage(idUser, pageId);
		} else {
			eventServicesLocal.unfollowPage(idUser, pageId);
		}
	}

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

	public Boolean getDisplayFormMyArea() {
		return displayFormMyArea;
	}

	public void setDisplayFormMyArea(Boolean displayFormMyArea) {
		this.displayFormMyArea = displayFormMyArea;
	}

	public List<Event> getEventsInMyArea() {
		return eventsInMyArea;
	}

	public void setEventsInMyArea(List<Event> eventsInMyArea) {
		this.eventsInMyArea = eventsInMyArea;
	}

	public Event getMostFollowedUpcoming() {
		return mostFollowedUpcoming;
	}

	public void setMostFollowedUpcoming(Event mostFollowedUpcoming) {
		this.mostFollowedUpcoming = mostFollowedUpcoming;
	}

	public Boolean getDisplayFormMostFollowedEvent() {
		return displayFormMostFollowedEvent;
	}

	public void setDisplayFormMostFollowedEvent(Boolean displayFormMostFollowedEvent) {
		this.displayFormMostFollowedEvent = displayFormMostFollowedEvent;
	}

	public Boolean getDisplayFormMissedEvents() {
		return displayFormMissedEvents;
	}

	public void setDisplayFormMissedEvents(Boolean displayFormMissedEvents) {
		this.displayFormMissedEvents = displayFormMissedEvents;
	}

	public List<Event> getMissedEvents() {
		return missedEvents;
	}

	public void setMissedEvents(List<Event> missedEvents) {
		this.missedEvents = missedEvents;
	}

	public Boolean getDisplayFormStatistics() {
		return displayFormStatistics;
	}

	public void setDisplayFormStatistics(Boolean displayFormStatistics) {
		this.displayFormStatistics = displayFormStatistics;
	}

	public PieChartModel getPieChartModel() {
		return pieChartModel;
	}

	public void setPieChartModel(PieChartModel pieChartModel) {
		this.pieChartModel = pieChartModel;
	}

	public Map<Event, Long> getMap() {
		return map;
	}

	public void setMap(Map<Event, Long> map) {
		this.map = map;
	}

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	public MapModel getSimpleModelThisWeek() {
		return simpleModelThisWeek;
	}

	public void setSimpleModelThisWeek(MapModel simpleModelThisWeek) {
		this.simpleModelThisWeek = simpleModelThisWeek;
	}

	public MapModel getSimpleModelNextWeek() {
		return simpleModelNextWeek;
	}

	public void setSimpleModelNextWeek(MapModel simpleModelNextWeek) {
		this.simpleModelNextWeek = simpleModelNextWeek;
	}

	public MapModel getSimpleModelThisMonth() {
		return simpleModelThisMonth;
	}

	public void setSimpleModelThisMonth(MapModel simpleModelThisMonth) {
		this.simpleModelThisMonth = simpleModelThisMonth;
	}

	public MapModel getSimpleModelMyArea() {
		return simpleModelMyArea;
	}

	public void setSimpleModelMyArea(MapModel simpleModelMyArea) {
		this.simpleModelMyArea = simpleModelMyArea;
	}

	public Boolean getResponse() {
		return response;
	}

	public void setResponse(Boolean response) {
		this.response = response;
	}

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSelectedMenu() {
		return selectedMenu;
	}

	public void setSelectedMenu(String selectedMenu) {
		this.selectedMenu = selectedMenu;
	}

	public String getChaine() {
		return chaine;
	}

	public void setChaine(String chaine) {
		this.chaine = chaine;
	}

}
