package com.esprit.hypnotrip.services.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.esprit.hypnotrip.persistence.Pages;
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
	public void saveOrUpdatePage(Pages page) {
		entityManager.merge(page);

	}

	@Override
	public void deletePage(Pages page) {
		entityManager.remove(page);

	}

	@Override
	public List<Pages> ListMyPages(Integer idOwner) {

		String jpql = "SELECT p FROM Pages p WHERE p.userId=:param1";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("param1", idOwner);
		return query.getResultList();

	}

}
