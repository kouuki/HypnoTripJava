package com.esprit.hypnotrip.services.interfaces;

import javax.ejb.Local;

@Local
public interface InvitationServicesLocal {

	void saveOrUpdateInvitation(String idSender, String idReciever, int idPage);

}
