package com.esprit.hypnotrip.services.interfaces;

import java.util.List;

import javax.ejb.Local;

import com.esprit.hypnotrip.persistence.Touristicplace;

@Local
public interface ToursiticPlaceServiceLocal {
	List<Touristicplace> getAllTouristicPlaces();

	List<Touristicplace> mostRatedTouristicPlaces();

	List<Touristicplace> getAllTouristicPlaceNotAccepted();
	
	void acceptTouristicPlace(int idPage);
	
	Touristicplace findTouristicPlaceById(int idPage);

}
