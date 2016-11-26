package com.esprit.hypnotrip.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import com.esprit.hypnotrip.persistence.Posts;
import com.esprit.hypnotrip.services.interfaces.PostServicesLocal;
import com.esprit.hypnotrip.services.interfaces.PostServicesRemote;

/**
 * Session Bean implementation class PostServices
 */
@Stateless
public class PostServices implements PostServicesRemote, PostServicesLocal {

	/**
	 * Default constructor.
	 */
	public PostServices() {
		// TODO Auto-generated constructor stub
	}

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void SaveOrUpdatePost(Posts post) {
		entityManager.merge(post);
	}

	@Override
	public List<Posts> findAllPostByUser(String idUser) {
		String jpql = "SELECT p FROM Posts p WHERE p.idOwner=:param1";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("param1", idUser);
		return query.getResultList();
	}

	@Override
	public Integer CountNbrTagByUser(String idUser, Integer idTag) {
		String jpql = "SELECT P FROM Posts p WHERE p.idOwner=:param AND p.tagId=:param1 ";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("param", idUser);
		query.setParameter("param1", idTag);
		@SuppressWarnings("unchecked")
		List<Posts> PostByUser = query.getResultList();
		Integer nbrTags = 0;
		for (@SuppressWarnings("unused")
		Posts post : PostByUser) {
			nbrTags++;
		}
		return nbrTags;
	}

	@Override
	public List<Posts> listMyPost(String idUser) {
		List<Posts> listOfpost = new ArrayList<>();

		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:63784/api/PostWS");
		WebTarget myPosts = target.path("ListPosts").path(idUser);
		Response response = myPosts.request().get();
		listOfpost = response.readEntity(listOfpost.getClass());

		response.close();
		System.out.println(listOfpost);
		return listOfpost;

	}

}
