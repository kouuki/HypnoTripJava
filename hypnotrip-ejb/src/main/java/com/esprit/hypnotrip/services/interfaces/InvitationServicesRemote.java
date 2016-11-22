package com.esprit.hypnotrip.services.interfaces;

import javax.ejb.Remote;

import com.esprit.hypnotrip.persistence.Invitations;

@Remote
public interface InvitationServicesRemote {

	void saveOrUpdateInvitation(Invitations invitation);

	void deleteInvitation(Invitations invitation);

}
