package com.esprit.hypnotrip.presentation.mbeans;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

import com.esprit.hypnotrip.persistence.Event;
import com.esprit.hypnotrip.services.interfaces.EventServicesLocal;

@ManagedBean(name = "eventsConverter")
public class EventsConverter implements Converter {
    
	@EJB
	private EventServicesLocal service ; 
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null && value.trim().length() > 0) {
			try {   
						return service.getAllEvents().get(Integer.parseInt(value) - 1);

			} catch (NumberFormatException e) {
				throw new ConverterException(
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Not a valid theme."));
			}
		} else {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			return String.valueOf(((Event) value).getPageId());
		} else {
			return null;
		}
	}

	public EventServicesLocal getService() {
		return service;
	}

	public void setService(EventServicesLocal service) {
		this.service = service;
	}

}
