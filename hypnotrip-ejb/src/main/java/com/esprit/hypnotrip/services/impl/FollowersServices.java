package com.esprit.hypnotrip.services.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.esprit.hypnotrip.persistence.Follows;
import com.esprit.hypnotrip.persistence.FollowsId;
import com.esprit.hypnotrip.persistence.Pages;
import com.esprit.hypnotrip.persistence.Posts;
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
	public void SaveOrUpdate(FollowsId followsId, boolean followStat, boolean wishStat) {

		followsId.setDateFollow(followsId.getDateFollow());
		followsId.setPageId(followsId.getPageId());
		followsId.setUserId((followsId.getUserId()));
		follows.setId(followsId);
		follows.setFollowStat(followStat);
		follows.setWishStat(wishStat);

		entityManager.merge(follows);

	}

	@Override
	public List<Follows> findAllFollowByUserId(String idUser) {

		String jpql = "SELECT f FROM Follows f WHERE f.id.userId=:param AND f.followStat=true";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("param", idUser);
		return query.getResultList();
	}

	@Override
	public Integer nbrFollowers(Integer idPages) {

		String jpql = "SELECT f FROM Follows f WHERE f.id.pageId=:param AND f.followStat=true";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("param", idPages);
		@SuppressWarnings("unchecked")
		List<Follows> follows = query.getResultList();
		Integer nbrfollowrs = 0;
		for (@SuppressWarnings("unused")
		Follows follow : follows) {
			nbrfollowrs++;
		}
		return nbrfollowrs;
	}

	@Override
	public Integer nbrPostsByUser(Integer idPages, String idUser) {
		String jpql = "SELECT P FROM Posts p WHERE p.idOwner=:param AND p.pageId=:param1 ";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("param", idUser);
		query.setParameter("param1", idPages);
		@SuppressWarnings("unchecked")
		List<Posts> PostByPageAndUser = query.getResultList();
		Integer nbrPosts = 0;
		for (@SuppressWarnings("unused")
		Posts post : PostByPageAndUser) {
			nbrPosts++;
		}
		return nbrPosts;
	}
	// test commit

	@Override
	public List<Pages> ListAllPages() {
		String jpql = "SELECT p FROM Pages p";
		Query query = entityManager.createQuery(jpql);
		return query.getResultList();

	}

}
