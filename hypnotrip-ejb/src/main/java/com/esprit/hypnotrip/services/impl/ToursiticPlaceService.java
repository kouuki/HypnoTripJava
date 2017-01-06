package com.esprit.hypnotrip.services.impl;

import com.esprit.hypnotrip.persistence.Touristicplace;
import com.esprit.hypnotrip.services.interfaces.ToursiticPlaceServiceLocal;
import com.esprit.hypnotrip.services.interfaces.ToursiticPlaceServiceRemote;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Session Bean implementation class ToursiticPlaceService
 */
@Stateless
@LocalBean
public class ToursiticPlaceService implements ToursiticPlaceServiceRemote, ToursiticPlaceServiceLocal {
	@PersistenceContext
	private EntityManager entityManager;

	public ToursiticPlaceService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Touristicplace> mostRatedTouristicPlaces() {
		String jpql = "select  t from Touristicplace t INNER JOIN t.rates r GROUP BY t.pageId ORDER BY Count(r.id.pageId) DESC";
		Query query = entityManager.createQuery(jpql);
		List<Touristicplace> Tp = query.getResultList();
		return Tp;
	}

	@Override
	public List<Touristicplace> getAllTouristicPlaces() {
		String jpql = "select  t from Touristicplace t";
		Query query = entityManager.createQuery(jpql);
		List<Touristicplace> Tp = query.getResultList();
		return Tp;
	}

	@Override
	public List<Touristicplace> getAllTouristicPlaceNotAccepted() {
		String jpql = "select  t from Touristicplace t WHERE t.etat=0";
		Query query = entityManager.createQuery(jpql);
		List<Touristicplace> Tp = query.getResultList();
		return Tp;
	}

	@Override
	public void acceptTouristicPlace(int idPage) {
		Touristicplace ts = findTouristicPlaceById(idPage);
		ts.setEtat(1);
		entityManager.merge(ts);
		
	}

	@Override
	public Touristicplace findTouristicPlaceById(int idPage) {
		return entityManager.find(Touristicplace.class, idPage);
	}

	@Override
	public int numberOfTouristicPLaces() {
		List<Touristicplace> ls = getAllTouristicPlaces();
		return ls.size();
	}
	public int numberOfTouristicPLacesNotAccepted() {
		List<Touristicplace> ls = getAllTouristicPlaceNotAccepted();
		return ls.size();
	}
	
}
