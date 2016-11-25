package com.esprit.hypnotrip.services.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.esprit.hypnotrip.persistence.Event;
import com.esprit.hypnotrip.persistence.Follows;
import com.esprit.hypnotrip.persistence.FollowsId;
import com.esprit.hypnotrip.persistence.Pages;
import com.esprit.hypnotrip.persistence.Posts;
import com.esprit.hypnotrip.persistence.Tags;
import com.esprit.hypnotrip.persistence.User;
import com.esprit.hypnotrip.services.interfaces.FollowersServicesLocal;
import com.esprit.hypnotrip.services.interfaces.FollowersServicesRemote;
import com.esprit.hypnotrip.services.interfaces.PostServicesLocal;
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
	@EJB
	PostServicesLocal postServiceLocal;

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

	@Override
	public List<Posts> findListOfTagsOrdredByUsing(String idUser) {
		String jpql = "Select p from Posts p where p.idOwner=:param GROUP BY p.tagId ORDER BY Count(p.tagId) DESC";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("param", idUser);
		@SuppressWarnings("unchecked")
		List<Posts> OrderByTagUsed = query.getResultList();
		return OrderByTagUsed;
	}

	@Override
	public List<Pages> ListAllPagesByTags(List<Posts> posts) {
		List<Pages> listPageToReturn = new ArrayList<>();
		List<Pages> listPageToRecuperer = new ArrayList<>();
		for (Posts post : posts) {
			String jpql = "SELECT t FROM Tags t WHERE t.tagId=:param";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("param", post.getTagId());
			Tags tags = (Tags) query.getResultList().get(0);
			String CategoriePageToFind = tags.getName();
			String jpql1 = "SELECT p FROM Pages p where p.categoriePage=:param";
			Query query1 = entityManager.createQuery(jpql1);
			query1.setParameter("param", CategoriePageToFind);
			listPageToRecuperer = query1.getResultList();
			for (Pages pages : listPageToRecuperer) {
				listPageToReturn.add(pages);
			}
		}
		return listPageToReturn;

	}

	
	

	@Override
	public boolean MyEventForToDay(String idUser) {
		List<Follows> listAllMyFollow = this.findAllFollowByUserId(idUser);
		for (Follows follows : listAllMyFollow) {
			Event event = (Event) follows.getPages();
			Date actuelle = new Date();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String dateNow = dateFormat.format(actuelle);
			if (dateNow.equals(event.getDateOfEvent().toString())) {
				return true;
			}
		}
		System.out.println("compare not Set");

		return false;
	}

	@Override
	public List<Follows> ListMyEventForToDay(String idUser) {
		List<Follows> listMyEventForToDay = new ArrayList<>();
		List<Follows> listAllMyFollow = this.findAllFollowByUserId(idUser);
		for (Follows follows : listAllMyFollow) {
			Date actuelle = new Date();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String dateNow = dateFormat.format(actuelle);
			if (follows.getPages() instanceof Event) {
				if (dateNow.equals(((Event) follows.getPages()).getDateOfEvent().toString())) {
					listMyEventForToDay.add(follows);
				}
			}
		}

		return listMyEventForToDay;
	}

}
