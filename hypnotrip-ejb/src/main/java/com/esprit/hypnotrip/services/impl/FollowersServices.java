package com.esprit.hypnotrip.services.impl;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.esprit.hypnotrip.persistence.Follows;
import com.esprit.hypnotrip.persistence.FollowsId;
import com.esprit.hypnotrip.persistence.User;
import com.esprit.hypnotrip.services.interfaces.FollowersServicesLocal;
import com.esprit.hypnotrip.services.interfaces.FollowersServicesRemote;
import com.esprit.hypnotrip.services.interfaces.UserServicesLocal;

/**
 * Session Bean implementation class FollowersServices
 */
@Stateless
public class FollowersServices implements FollowersServicesRemote, FollowersServicesLocal {

	@PersistenceContext
	private EntityManager entityManager;

	@EJB
	UserServicesLocal userServicesLocal;

	User user = new User();
	Follows follows = new Follows();
	FollowsId followsId = new FollowsId();

	/**
	 * Default constructor.
	 */
	public FollowersServices() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void SaveOrUpdate(String idUser, Integer idPage, boolean followStat) {

		followsId.setDateFollow(new Date());
		followsId.setPageId(idPage);
		followsId.setUserId(idUser);
		follows.setId(followsId);
		follows.setFollowStat(followStat);
		entityManager.merge(follows);

	}

	@Override
	public List<Follows> findAllFollowByUserId(String idUser) {

		String jpql = "SELECT f FROM Follows f WHERE f.id.userId=:param AND f.followStat=true";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("param", idUser);
		return query.getResultList();
	}

}
