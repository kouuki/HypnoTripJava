package com.esprit.hypnotrip.presentation.mbeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.PieChartModel;

import com.esprit.hypnotrip.persistence.Event;
import com.esprit.hypnotrip.persistence.Tickets;
import com.esprit.hypnotrip.services.interfaces.EventServicesLocal;
import com.esprit.hypnotrip.services.interfaces.TicketServicesLocal;

@ManagedBean(name = "statistics")
@ViewScoped
public class StatisticsTicketsBoughtByEvent {
	@EJB
	private EventServicesLocal eventServicesLocal;
	@EJB
	private TicketServicesLocal ticketServicesLocal;
	private List<Event> events;

	private Event event = new Event();
	private PieChartModel pieModel;
	
	private boolean displayChart = false; 

	@PostConstruct
	public void init() {

		events = eventServicesLocal.getAllEvents();

		createPie();

	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public EventServicesLocal getEventServicesLocal() {
		return eventServicesLocal;
	}

	public void setEventServicesLocal(EventServicesLocal eventServicesLocal) {
		this.eventServicesLocal = eventServicesLocal;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public String createPie() {
		this.displayChart = true ; 
		List<Tickets> tickets = null ; 
		pieModel = new PieChartModel();
		if (event!=null) {
			tickets = ticketServicesLocal.selectAllTicketsByIdEvent(event.getPageId());
	
		
		for (Tickets t : tickets) {

			pieModel.set(t.getLabel(), ticketServicesLocal.numberOfTicketsBookedByIdTicket(t.getTicketId()));
		}

		pieModel.setTitle("Count number of booked Ticket");
		pieModel.setLegendPosition("e");
		pieModel.setFill(false);
		pieModel.setShowDataLabels(true);
		pieModel.setDiameter(150); 	} 
		
		return "" ; 

	}

	public PieChartModel getPieModel() {
		return pieModel;
	}

	public void setPieModel(PieChartModel pieModel) {
		this.pieModel = pieModel;
	}

	public TicketServicesLocal getTicketServicesLocal() {
		return ticketServicesLocal;
	}

	public void setTicketServicesLocal(TicketServicesLocal ticketServicesLocal) {
		this.ticketServicesLocal = ticketServicesLocal;
	}

	public boolean isDisplayChart() {
		return displayChart;
	}

	public void setDisplayChart(boolean displayChart) {
		this.displayChart = displayChart;
	}

}
