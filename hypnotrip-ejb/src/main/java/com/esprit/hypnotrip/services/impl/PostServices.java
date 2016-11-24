package com.esprit.hypnotrip.services.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

}
