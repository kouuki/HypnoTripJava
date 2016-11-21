package com.esprit.hypnotrip.services.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


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

	public int getRateLevel(int idPost) {

		int totalRate = 0;
		int nbrRate = 0;
		Query query = entityManager.createQuery("SELECT r FROM Rates r WHERE r.id.pageId=:param1");
		query.setParameter("param1", idPost);
		List<Rates> lr = query.getResultList();
		for (Rates rates : lr) {
			totalRate = totalRate + rates.getNiveau();
			nbrRate++;
		}
		if (nbrRate ==0)
			return 0;
		return (int) Math.floor(totalRate / nbrRate);
	}
}
