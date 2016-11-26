package com.esprit.hypnotrip.services.interfaces;

import java.util.List;
import java.util.Map;

import javax.ejb.Remote;

import com.esprit.hypnotrip.persistence.Offer;

@Remote
public interface OfferServiceRemote {

	Offer mostBoughtOffer();

	Offer bestOffer();

	List<Offer> SortOfferByDatePrice();

	void OfferForMostBuyer();

	Map<Offer, Long> sortBoughtOffer();

	List<Offer> listOffers();

	void buyAnOffer(String idUser, Integer idOffer);

	List<Offer> SearchOffer();

}
