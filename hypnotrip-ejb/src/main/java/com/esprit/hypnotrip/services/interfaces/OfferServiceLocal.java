package com.esprit.hypnotrip.services.interfaces;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import com.esprit.hypnotrip.persistence.Buy;
import com.esprit.hypnotrip.persistence.Offer;
import com.esprit.hypnotrip.services.exceptions.EventOverException;

@Local
public interface OfferServiceLocal {
	void addBuy(Buy buy);

	Offer mostBoughtOffer();

	Offer bestOffer();

	List<Offer> SortOfferByDatePrice();

	Map<Offer, Long> sortBoughtOffer();

	List<Offer> listOffers();

	void buyAnOffer(String idUser, Integer idOffer) throws EventOverException;

	List<Offer> SearchOffer();

}
