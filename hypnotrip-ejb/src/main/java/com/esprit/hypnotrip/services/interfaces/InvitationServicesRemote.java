package com.esprit.hypnotrip.services.interfaces;

import java.util.List;

import javax.ejb.Remote;

import com.esprit.hypnotrip.persistence.Invitations;
import com.esprit.hypnotrip.services.exceptions.SenderIsRecieverException;

@Remote
public interface InvitationServicesRemote {

	void saveOrUpdateInvitation(int idPage, int invitationStatus, String idSender, String idReciever)
			throws SenderIsRecieverException;

	Invitations findInvitationById(Integer idInvitation);

	void deleteInvitation(Invitations invitation);

	List<Invitations> getAllInvitationsByRecieverId(String idReciever);

	Integer acceptInvitationToFollowAPage(String idReciever,String idSender);

	Integer declineInvitationToFollowAPage(String idReciever,String idSender);
	
	boolean isInvitedToLikeApage(String idReciever, int pageId);
	
}
