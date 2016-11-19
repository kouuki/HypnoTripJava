package com.esprit.hypnotrip.services.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.esprit.hypnotrip.persistence.Rates;
import com.esprit.hypnotrip.services.interfaces.RateServiceLocal;
import com.esprit.hypnotrip.services.interfaces.RateServiceRemote;

/**
 * Session Bean implementation class RateService
 */
@Stateless
public class RateService implements RateServiceRemote, RateServiceLocal {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void addRate(Rates rate) {
		entityManager.merge(rate);

	}

	@Override
	public void deleteRate(Rates rate) {
		entityManager.remove(rate);
	}

	@Override
	public void deleteRateById(int idRate) {
		entityManager.remove(findRateById(idRate));
	}

	@Override
	public void updateRate(Rates rate) {
		entityManager.merge(rate);

	}

	@Override
	public Rates findRateById(Integer idRate) {
		return entityManager.find(Rates.class, idRate);
	}

}
