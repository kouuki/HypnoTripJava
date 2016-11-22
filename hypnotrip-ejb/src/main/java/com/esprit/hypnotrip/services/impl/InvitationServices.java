package com.esprit.hypnotrip.services.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.esprit.hypnotrip.persistence.Invitations;
import com.esprit.hypnotrip.services.interfaces.InvitationServicesLocal;
import com.esprit.hypnotrip.services.interfaces.InvitationServicesRemote;

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
	
    public InvitationServices() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public void saveOrUpdateInvitation(Invitations invitation) {
		entityManager.merge(invitation);
		}

	@Override
	public void deleteInvitation(Invitations invitation) {
		entityManager.remove(entityManager.merge(invitation));
		
	}

}
