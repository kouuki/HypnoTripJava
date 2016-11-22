package com.esprit.hypnotrip.services.interfaces;

import javax.ejb.Remote;

@Remote
public interface InvitationServicesRemote {

	void saveOrUpdateInvitation(String idSender, String idReciever, int idPage);

}
