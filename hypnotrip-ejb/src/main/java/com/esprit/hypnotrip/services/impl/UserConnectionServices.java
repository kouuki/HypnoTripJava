package com.esprit.hypnotrip.services.impl;

import java.util.Date;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.esprit.hypnotrip.persistence.Userconnections;
import com.esprit.hypnotrip.services.interfaces.UserConnectionServicesLocal;
import com.esprit.hypnotrip.services.interfaces.UserConnectionServicesRemote;

/**
 * Session Bean implementation class UserConnectionServices
 */
@Stateless
public class UserConnectionServices implements UserConnectionServicesRemote, UserConnectionServicesLocal {

	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public UserConnectionServices() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void SaveConnection(String idUesr) {

		Userconnections userConnections = new Userconnections();
		userConnections.setDateConnection(new Date());
		userConnections.setUserId(idUesr);
		entityManager.merge(userConnections);

	}

}
