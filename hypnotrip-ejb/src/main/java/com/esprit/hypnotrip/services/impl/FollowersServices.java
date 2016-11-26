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
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import com.esprit.hypnotrip.persistence.Event;
import com.esprit.hypnotrip.persistence.Follows;
import com.esprit.hypnotrip.persistence.FollowsId;
import com.esprit.hypnotrip.persistence.Offer;
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
			Pages pages = follows.getPages();
			if (pages instanceof Event) {
				Date actuelle = new Date();
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String dateNow = dateFormat.format(actuelle);
				if (dateNow.equals(((Event) pages).getDateOfEvent().toString())) {
					return true;
				}
			} else if (pages instanceof Offer) {
				Date actuelle = new Date();
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String dateNow = dateFormat.format(actuelle);
				String dateEndOffer = dateFormat.format(((Offer) pages).getFinishDate());
				if (dateNow.equals(dateEndOffer)) {
					return true;
				}
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
			if (follows.getPages() instanceof Offer) {
				String dateEndOffer = dateFormat.format(((Offer) follows.getPages()).getFinishDate());
				if (dateNow.equals(dateEndOffer)) {
					listMyEventForToDay.add(follows);
					System.out.println("jit hné");
				}
			} else if (follows.getPages() instanceof Event) {
				if (dateNow.equals(((Event) follows.getPages()).getDateOfEvent().toString())) {
					listMyEventForToDay.add(follows);
				}
			}
		}
		System.out.println("5rajt men hné ");
		return listMyEventForToDay;
	}

	@Override
	public List<String> findNbrFollowByWS() {

		List<String> nbrFollowers = new ArrayList<>();
		Client client = ClientBuilder.newClient();

		WebTarget nbrAllFollowers = client.target("http://localhost:63784/api/follows/followsPage");

		Response response = nbrAllFollowers.request().get();

		nbrFollowers = response.readEntity(nbrFollowers.getClass());

		response.close();
		return nbrFollowers;

	}

	@Override
	public List<String> findNbrWishByWS() {
		List<String> nbrWish = new ArrayList<>();
		Client client = ClientBuilder.newClient();

		WebTarget nbrAllWish = client.target("http://localhost:63784/api/follows/wishPage");

		Response response = nbrAllWish.request().get();

		nbrWish = response.readEntity(nbrWish.getClass());

		response.close();
		return nbrWish;
	}

	@Override
	public List<Integer> listIdPagesInFollow() {
		String jpql = "SELECT distinct(f.id.pageId) FROM Follows f ";
		Query query = entityManager.createQuery(jpql);
		return query.getResultList();
	}

	@Override
	public String TitlePageById(Integer idPage) {
		String jpql = "SELECT p.title FROM Pages p WHERE p.pageId=:param ";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("param", idPage);
		return (String) query.getResultList().get(0);
	}

	@Override
	public Integer nbrWish(Integer idPages) {
		String jpql = "SELECT f FROM Follows f WHERE f.id.pageId=:param AND f.wishStat=true";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("param", idPages);
		@SuppressWarnings("unchecked")
		List<Follows> follows = query.getResultList();
		Integer nbrWish = 0;
		for (@SuppressWarnings("unused")
		Follows follow : follows) {
			nbrWish++;
		}
		return nbrWish;
	}

	public Follows GetLastFollowForPageByIdUser(Integer idPage, String userId) {

		// String jpql = "UPDATE Follows f SET f.followStat=false,
		// f.wishStat=false WHERE f.id.pageId=(SELECT "
		// + "fo.id.pageId FROM Follows fo WHERE fo.id.pageId=:param ORDER BY
		// fo.id.dateFollow DESC LIMIT 1) AND f.id.userId=(SELECT "
		// + "fo.id.userId FROM Follows fo WHERE fo.id.userId=:param1 ORDER BY
		// fo.id.dateFollow DESC LIMIT 1)";
		// Query query = entityManager.createQuery(jpql);
		// query.setParameter("param", idPage);
		// query.setParameter("param1", userId);
		// query.executeUpdate();

		String jpql = "SELECT f FROM Follows f WHERE f.id.pageId=:param AND f.id.userId=:param1";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("param", idPage);
		query.setParameter("param1", userId);
		@SuppressWarnings("unchecked")
		List<Follows> follows = query.getResultList();
		return follows.get(follows.size()-1);

	}
}
