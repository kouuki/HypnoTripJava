package com.esprit.hypnotrip.services.interfaces;

import javax.ejb.Remote;

import com.esprit.hypnotrip.persistence.Rates;

@Remote
public interface RateServiceRemote {
	void addRate(Rates rate);

	void deleteRate(Rates rate);

	void deleteRateById(int idRate);

	void updateRate(Rates rate);;

	Rates findRateById(Integer idRate);


}
