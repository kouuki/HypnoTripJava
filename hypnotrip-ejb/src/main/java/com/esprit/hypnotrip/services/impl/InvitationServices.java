package com.esprit.hypnotrip.services.impl;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.esprit.hypnotrip.persistence.Invitations;
import com.esprit.hypnotrip.persistence.User;
import com.esprit.hypnotrip.services.exceptions.SenderIsRecieverException;
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

	private String jpql;
	private Query query;
	private User sender;
	private User reciever;
	private List<Invitations> invitations = new ArrayList<Invitations>();
	private Invitations invitation;
	private Integer response;
	private boolean isInvited = false;

	// @EJB
	// service de jihen ;

	public InvitationServices() {
		// TODO Auto-generated constructor stub
	}

	// save or upadte an invitation on a page
	@Override
	public void saveOrUpdateInvitation(int idPage, int invitationStatus, String idSender, String idReciever)
			throws SenderIsRecieverException {

		// since it's an associative class, than we need to verify some things
		// sender exists
		sender = userServicesLocal.findUserById(idSender);

		// reciever exists
		reciever = userServicesLocal.findUserById(idReciever);

		// page exists
		// Pages page = service de jihen;

		// the sender musn't be the reciever
		// we need to verify
		if (!sender.equals(reciever)) {

			// if this condition is correct, than the invitation will be created
			Invitations invitation = new Invitations(idPage, invitationStatus, sender, reciever);
			entityManager.merge(invitation);

		} else {

			// if not, than this exception will be thrown
			throw new SenderIsRecieverException(
					"You can't invite yourself to follow any page, please select a valid user");
		}

	}

	@Override
	public Invitations findInvitationById(Integer idInvitation) {
		return entityManager.find(Invitations.class, idInvitation);
	}

	@Override
	public void deleteInvitation(Invitations invitation) {
		entityManager.remove(entityManager.merge(invitation));

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Invitations> getAllInvitationsByRecieverId(String idReciever) {

		jpql = "SELECT i FROM Invitations i WHERE i.id.receiverUserId =:param AND i.invitationStatus=:param1";
		query = entityManager.createQuery(jpql);
		query.setParameter("param", idReciever);
		query.setParameter("param1", 0);

		try {

			invitations = query.getResultList();

		} catch (Exception e) {
			System.out.println("aaaaaaa");
		}

		return invitations;
	}

	@Override
	public Integer acceptInvitationToFollowAPage(String idReciever, String idSender) {

		jpql = "UPDATE Invitations i SET i.invitationStatus=:param WHERE i.id.receiverUserId =:param1 AND i.id.senderUserId =:param2";
		query = entityManager.createQuery(jpql);
		query.setParameter("param", 1);
		query.setParameter("param1", idReciever);
		query.setParameter("param2", idSender);

		try {

			response = query.executeUpdate();

		} catch (Exception e) {
			System.out.println("aaaaaaa");
		}

		return response;
	}

	@Override
	public Integer declineInvitationToFollowAPage(String idReciever, String idSender) {

		jpql = "UPDATE Invitations i SET i.invitationStatus=:param WHERE i.id.receiverUserId =:param1 AND i.id.senderUserId =:param2";
		query = entityManager.createQuery(jpql);
		query.setParameter("param1", -1);
		query.setParameter("param1", idReciever);
		query.setParameter("param2", idSender);

		try {

			response = query.executeUpdate();

		} catch (Exception e) {
			System.out.println("aaaaaaa");
		}

		return response;

	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean isInvitedToLikeApage(String idReciever, int pageId) {
		jpql="SELECT i FROM Invitations i WHERE i.id.receiverUserId =:param AND i.pageId =:param1 AND i.invitationStatus=0";
		query = entityManager.createQuery(jpql);
		query.setParameter("param", idReciever);
		query.setParameter("param1", pageId);
		try {
			
			invitation = (Invitations) query.getSingleResult();
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		if(invitation != null){
			isInvited = true;
		}
		
		return isInvited;
	}

}
