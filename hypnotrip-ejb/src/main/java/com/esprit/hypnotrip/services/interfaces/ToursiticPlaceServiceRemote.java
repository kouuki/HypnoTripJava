package com.esprit.hypnotrip.services.interfaces;

import java.util.List;

import javax.ejb.Remote;

import com.esprit.hypnotrip.persistence.Touristicplace;

@Remote
public interface ToursiticPlaceServiceRemote {
	List<Touristicplace> getAllTouristicPlaces();

	List<Touristicplace> mostRatedTouristicPlaces();
}
