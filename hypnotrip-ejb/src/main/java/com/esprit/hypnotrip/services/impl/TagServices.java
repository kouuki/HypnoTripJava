package com.esprit.hypnotrip.services.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.esprit.hypnotrip.persistence.Tags;
import com.esprit.hypnotrip.services.interfaces.TagServicesLocal;
import com.esprit.hypnotrip.services.interfaces.TagServicesRemote;

/**
 * Session Bean implementation class TagServices
 */
@Stateless
public class TagServices implements TagServicesRemote, TagServicesLocal {

	/**
	 * Default constructor.
	 */
	public TagServices() {
		// TODO Auto-generated constructor stub
	}

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void SaveOrUpdateTage(String nameTag) {

		Tags tags = new Tags();
		tags.setName(nameTag);
		entityManager.merge(tags);
	}

}
