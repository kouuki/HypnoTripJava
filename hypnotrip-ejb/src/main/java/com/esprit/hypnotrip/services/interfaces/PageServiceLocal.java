package com.esprit.hypnotrip.services.interfaces;

import java.util.List;

import javax.ejb.Local;

import com.esprit.hypnotrip.persistence.Event;
import com.esprit.hypnotrip.persistence.Offer;
import com.esprit.hypnotrip.persistence.Pages;
import com.esprit.hypnotrip.persistence.Touristicplace;

@Local
public interface PageServiceLocal {
	void saveOrUpdatePage(Pages page, String idOwner);

	void deletePage(Pages page);

	List<Pages> ListMyPages(String idOwner);

	List<Offer> ListMyOffers(String idOwner);

	List<Event> ListMyEvents(String idOwner);

	List<Touristicplace> ListMyTouristicPages(String idOwner);

	Event findPageById(Integer idPage);
}
