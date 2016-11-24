package com.esprit.hypnotrip.services.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.esprit.hypnotrip.persistence.Buy;
import com.esprit.hypnotrip.persistence.Offer;
import com.esprit.hypnotrip.services.interfaces.OfferServiceLocal;
import com.esprit.hypnotrip.services.interfaces.OfferServiceRemote;

/**
 * Session Bean implementation class OfferService
 */
@Stateless
public class OfferService implements OfferServiceRemote, OfferServiceLocal {
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public OfferService() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Offer mostBoughtOffer() {

		String jpql = "select  b from Buy b GROUP BY b.id.offerId ORDER BY Count(b.id.offerId) DESC";
		Query query = entityManager.createQuery(jpql);
		Buy buy = (Buy) query.getResultList().get(0);

		return entityManager.find(Offer.class, buy.getId().getOfferId());
	}

	@Override
	public Offer bestOffer() {

		Query query = entityManager.createNativeQuery("SELECT CURRENT_TIMESTAMP");
		Date dateItem = (Date) query.getSingleResult();

		String jpql = "select  b from Offer b where :dateC BETWEEN b.beginDate AND b.finishDate ORDER BY b.price";
		Query query1 = entityManager.createQuery(jpql);
		query1.setParameter("dateC", dateItem);

		Offer offer = (Offer) query1.getResultList().get(0);
		return offer;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Offer> SortOfferByDatePrice() {
		String jpql = "select  b from Offer b ORDER BY b.price,b.beginDate DESC";
		Query query = entityManager.createQuery(jpql);

		return query.getResultList();
	}

	@Override
	public void addBuy(Buy buy) {
		entityManager.merge(buy);

	}

	@Override
	public void OfferForMostBuyer() {
		// TODO Auto-generated method stub

	}

	@Override
	public Map<Offer, Long> sortBoughtOffer() {
		String jpql = "select  b from Buy b GROUP BY b.id.offerId ORDER BY Count(b.id.offerId) DESC";
		Query query = entityManager.createQuery(jpql);
		List<Buy> buys = (List<Buy>) query.getResultList();
		Map<Offer, Long> map = new HashMap<Offer, Long>();
		String jpql1 = "select Count(b) from Buy b GROUP BY b.id.offerId ORDER BY Count(b.id.offerId) DESC";
		Query query1 = entityManager.createQuery(jpql1);
		List<Long> list =  query1.getResultList();
		int i = 0;
		for (Buy buy : buys) {

			map.put(entityManager.find(Offer.class, buy.getId().getOfferId()), list.get(i));
			i++;
		}
		System.out.println(map);
		return map;

	}

}
