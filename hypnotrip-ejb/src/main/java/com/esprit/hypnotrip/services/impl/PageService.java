package com.esprit.hypnotrip.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.esprit.hypnotrip.persistence.Event;
import com.esprit.hypnotrip.persistence.Offer;
import com.esprit.hypnotrip.persistence.Pages;
import com.esprit.hypnotrip.persistence.Touristicplace;
import com.esprit.hypnotrip.services.exceptions.EventOverException;
import com.esprit.hypnotrip.services.interfaces.PageServiceLocal;
import com.esprit.hypnotrip.services.interfaces.PageServiceRemote;

/**
 * Session Bean implementation class PageService
 */
@Stateless
public class PageService implements PageServiceRemote, PageServiceLocal {
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public PageService() {

	}

	@Override
	public void saveOrUpdatePage(Pages page, String idOwner) {

		page.setUserId(idOwner);
		entityManager.merge(page);

	}

	@Override
	public void deletePage(Pages page) {
		entityManager.remove(entityManager.merge(page));

	}

	@Override
	public List<Pages> ListMyPages(String idOwner) {

		String jpql = "SELECT p FROM Pages p WHERE p.userId=:param1";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("param1", idOwner);
		return query.getResultList();

	}

	@Override
	public List<Offer> ListMyOffers(String idOwner) {
		List<Offer> myOffers = new ArrayList<Offer>();

		String jpql = "SELECT p FROM Pages p WHERE p.userId=:param1";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("param1", idOwner);
		List<Pages> myListOfPages = query.getResultList();
		for (Pages e : myListOfPages) {
			if (e instanceof Offer)
				myOffers.add((Offer) e);
		}
		System.out.println(myOffers);
		return myOffers;
	}

	@Override
	public List<Event> ListMyEvents(String idOwner) {

		List<Event> myEvents = new ArrayList<Event>();

		String jpql = "SELECT p FROM Pages p WHERE p.userId=:param1";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("param1", idOwner);
		List<Pages> myListOfPages = query.getResultList();
		for (Pages e : myListOfPages) {
			if (e instanceof Event)
				myEvents.add((Event) e);
		}
		return myEvents;
	}

	@Override
	public List<Touristicplace> ListMyTouristicPages(String idOwner) {

		List<Touristicplace> myTouristicPlaces = new ArrayList<Touristicplace>();

		String jpql = "SELECT p FROM Pages p WHERE p.userId=:param1 AND p.etat<>0";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("param1", idOwner);
		List<Pages> myListOfPages = query.getResultList();
		for (Pages e : myListOfPages) {
			if (e instanceof Touristicplace)
				myTouristicPlaces.add((Touristicplace) e);
		}
		return myTouristicPlaces;
	}

	@Override
	public Event findPageById(Integer idPage) {
		Event event = null;
		Pages page = entityManager.find(Pages.class, idPage);
		if (page instanceof Event)
			event = (Event) page;
		return event;
	}

}
