package com.esprit.hypnotrip.presentation.mbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.esprit.hypnotrip.persistence.Touristicplace;
import com.esprit.hypnotrip.persistence.User;
import com.esprit.hypnotrip.services.interfaces.ToursiticPlaceServiceLocal;
import com.esprit.hypnotrip.services.interfaces.UserServicesLocal;

@ManagedBean
@ViewScoped
public class AdminBean {
	@EJB
	UserServicesLocal userservicelocal;
	@EJB
	ToursiticPlaceServiceLocal toursiticPlaceServiceLocal;

	private List<User> usersNotBlocked = new ArrayList<>();
	private List<User> usersBlocked = new ArrayList<>();
	private List<Touristicplace> touristicPlaceNotAccepted = new ArrayList<>();

	@PostConstruct
	public void init() {
		setUsersNotBlocked(userservicelocal.listNotBlockedUser());
		setUsersBlocked(userservicelocal.listBlockedUser());
		setTouristicPlaceNotAccepted(toursiticPlaceServiceLocal.getAllTouristicPlaceNotAccepted());
	}

	public List<User> getUsersNotBlocked() {
		return usersNotBlocked;
	}

	public void setUsersNotBlocked(List<User> usersNotBlocked) {
		this.usersNotBlocked = usersNotBlocked;
	}

	public List<User> getUsersBlocked() {
		return usersBlocked;
	}

	public void setUsersBlocked(List<User> usersBlocked) {
		this.usersBlocked = usersBlocked;
	}

	// to change the displayed Stat from int to String
	public String getState(int etat) {
		if (etat == 1) {
			return "Activated";
		} else {
			return "Blocked";
		}
	}

	// to change the displayed Stat from int to String
	public String getStateTouristicPlace(int etat) {
		if (etat == 1) {
			return "Accepted";
		} else if (etat == -1) {
			return "Refused";
		} else {
			return "Pending";
		}
	}
// BLOCK DEBLOCK USER ===> WEB API LATER
	public String blockUser(String userId) {
		userservicelocal.blocUser(userId);
		return "/pages/Admin/AdminPage?faces-redirect=true";
	}

	public String DeblockUser(String userId) {
		userservicelocal.deblocUser(userId);
		return "/pages/Admin/AdminPage?faces-redirect=true";
	}
// ACCEPT TOURISTIC PLACE 
	public String AcceptTouristicPlace(int idPage){
		toursiticPlaceServiceLocal.acceptTouristicPlace(idPage);
		return "/pages/Admin/AdminPage?faces-redirect=true";
	}
	public List<Touristicplace> getTouristicPlaceNotAccepted() {
		return touristicPlaceNotAccepted;
	}

	public void setTouristicPlaceNotAccepted(List<Touristicplace> touristicPlaceNotAccepted) {
		this.touristicPlaceNotAccepted = touristicPlaceNotAccepted;
	}

}
