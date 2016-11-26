package com.esprit.hypnotrip.presentation.mbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import com.esprit.hypnotrip.persistence.Invitations;
import com.esprit.hypnotrip.services.interfaces.InvitationServicesLocal;

@ManagedBean
@ViewScoped
public class TreatInvitesBean {

	
	//model
	private List<Invitations> myInvitations = new ArrayList<Invitations>();

	private String idUser;

	
	private boolean displayFormMyInvites = true;
	private boolean displayFormAcceptInvite = false;
	private boolean displayFormDeclineInvite = false;
	
	
	@EJB
	InvitationServicesLocal invitationServicesLocal;
	
	@ManagedProperty(value = "#{loginBean}")
	private LoginBean loginBean;
	
	@PostConstruct
	public void init(){
		
		idUser = loginBean.getUser().getId();
		System.out.println(idUser);
		
		myInvitations = invitationServicesLocal.getAllInvitationsByRecieverId(idUser);

	}
	
	public String selectInvites() {
		displayFormMyInvites = true;
		displayFormAcceptInvite = false;
		displayFormDeclineInvite = false;
		return null;
	}

	public String selectAcceptInvite(){
		displayFormMyInvites = false;
		displayFormAcceptInvite = true;
		displayFormDeclineInvite = true;
		return null;
	}
	
	public String selectDeclineInvite(){
		displayFormMyInvites = false;
		displayFormAcceptInvite = false;
		displayFormDeclineInvite = true;
		return null;
	}
	
	
	public List<Invitations> getMyInvitations() {
		return myInvitations;
	}

	public void setMyInvitations(List<Invitations> myInvitations) {
		this.myInvitations = myInvitations;
	}

	public boolean isDisplayFormMyInvites() {
		return displayFormMyInvites;
	}

	public void setDisplayFormMyInvites(boolean displayFormMyInvites) {
		this.displayFormMyInvites = displayFormMyInvites;
	}

	public boolean isDisplayFormAcceptInvite() {
		return displayFormAcceptInvite;
	}

	public void setDisplayFormAcceptInvite(boolean displayFormAcceptInvite) {
		this.displayFormAcceptInvite = displayFormAcceptInvite;
	}

	public boolean isDisplayFormDeclineInvite() {
		return displayFormDeclineInvite;
	}

	public void setDisplayFormDeclineInvite(boolean displayFormDeclineInvite) {
		this.displayFormDeclineInvite = displayFormDeclineInvite;
	}

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

}
