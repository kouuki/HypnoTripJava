package com.esprit.hypnotrip.services.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.esprit.hypnotrip.persistence.Invitations;
import com.esprit.hypnotrip.persistence.User;
import com.esprit.hypnotrip.services.interfaces.InvitationServicesLocal;
import com.esprit.hypnotrip.services.interfaces.InvitationServicesRemote;
import com.esprit.hypnotrip.services.interfaces.UserServicesLocal;

/**
 * Session Bean implementation class InvitationServices
 */
@Stateless
public class InvitationServices implements InvitationServicesRemote, InvitationServicesLocal {

	/**
	 * Default constructor.
	 */

	@PersistenceContext
	EntityManager entityManager;

	// injecting user services EJB
	@EJB
	UserServicesLocal userServicesLocal;

	// @EJB
	// service de jihen ;

	public InvitationServices() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void saveOrUpdateInvitation(String idSender, String idReciever, int idPage) {

		User sender = userServicesLocal.findUserById(idSender);
		User reciever = userServicesLocal.findUserById(idReciever);
		// Pages page = service de jihen;

		Invitations invitation = new Invitations(sender, reciever);
		entityManager.merge(invitation);
	}

}
