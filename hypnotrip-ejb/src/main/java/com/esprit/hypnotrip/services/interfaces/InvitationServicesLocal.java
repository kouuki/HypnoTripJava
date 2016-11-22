package com.esprit.hypnotrip.services.interfaces;

import javax.ejb.Local;

import com.esprit.hypnotrip.persistence.Invitations;

@Local
public interface InvitationServicesLocal {

	void saveOrUpdateInvitation(Invitations invitation);

	void deleteInvitation(Invitations invitation);
	
}
