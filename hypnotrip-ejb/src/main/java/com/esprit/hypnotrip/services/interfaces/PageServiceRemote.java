package com.esprit.hypnotrip.services.interfaces;

import java.util.List;

import javax.ejb.Remote;

import com.esprit.hypnotrip.persistence.Event;
import com.esprit.hypnotrip.persistence.Offer;
import com.esprit.hypnotrip.persistence.Pages;
import com.esprit.hypnotrip.persistence.Touristicplace;

@Remote
public interface PageServiceRemote {
	void saveOrUpdatePage(Pages page,String idOwner);

	void deletePage(Pages page);

	List<Pages> ListMyPages(String idOwner);
	
	List<Offer> ListMyOffers(String idOwner);

	List<Event> ListMyEvents(String idOwner);

	List<Touristicplace> ListMyTouristicPages(String idOwner);
	
	Event findPageById(Integer idPage);
}
