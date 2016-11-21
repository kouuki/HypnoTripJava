package com.esprit.hypnotrip.services.interfaces;

import javax.ejb.Local;

import com.esprit.hypnotrip.persistence.Rates;

@Local
public interface RateServiceLocal {
	void addRate(Rates rate);

	void deleteRate(Rates rate);

	void deleteRateById(int idRate);

	void updateRate(Rates rate);;

	Rates findRateById(Integer idRate);

	int getRateLevel(int idPost);

}
