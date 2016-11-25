package com.esprit.hypnotrip.presentation.mbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

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
    int numberOfconnectedUser;
    int numberOfTouristicPlaces;
    int numberOfTouristicPlacesNotAccepted;
	private List<User> usersNotBlocked = new ArrayList<>();
	private List<User> usersBlocked = new ArrayList<>();
	private List<Touristicplace> touristicPlaceNotAccepted = new ArrayList<>();

	@PostConstruct
	public void init() {
		setUsersNotBlocked(userservicelocal.listNotBlockedUser());
		setUsersBlocked(userservicelocal.listBlockedUser());
		setTouristicPlaceNotAccepted(toursiticPlaceServiceLocal.getAllTouristicPlaceNotAccepted());
		numberOfTouristicPlaces=toursiticPlaceServiceLocal.numberOfTouristicPLaces();
		numberOfTouristicPlacesNotAccepted=toursiticPlaceServiceLocal.numberOfTouristicPLacesNotAccepted();
		numberOfconnectedUser=userservicelocal.numberOfConnexion();
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
public String logout(){
	FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	return "/Home?faces-redirect=true";
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
		return "/pages/Admin/AdminManageUser?faces-redirect=true";
	}

	public String DeblockUser(String userId) {
		userservicelocal.deblocUser(userId);
		return "/pages/Admin/AdminManageUser?faces-redirect=true";
	}
// ACCEPT TOURISTIC PLACE 
	public String AcceptTouristicPlace(int idPage){
		toursiticPlaceServiceLocal.acceptTouristicPlace(idPage);
		return "/pages/Admin/AdminTouristicPlaces?faces-redirect=true";
	}
	public List<Touristicplace> getTouristicPlaceNotAccepted() {
		return touristicPlaceNotAccepted;
	}

	public void setTouristicPlaceNotAccepted(List<Touristicplace> touristicPlaceNotAccepted) {
		this.touristicPlaceNotAccepted = touristicPlaceNotAccepted;
	}

	public int getNumberOfconnectedUser() {
		return numberOfconnectedUser;
	}

	public void setNumberOfconnectedUser(int numberOfconnectedUser) {
		this.numberOfconnectedUser = numberOfconnectedUser;
	}

	public int getNumberOfTouristicPlaces() {
		return numberOfTouristicPlaces;
	}

	public void setNumberOfTouristicPlaces(int numberOfTouristicPlaces) {
		this.numberOfTouristicPlaces = numberOfTouristicPlaces;
	}

	public int getNumberOfTouristicPlacesNotAccepted() {
		return numberOfTouristicPlacesNotAccepted;
	}

	public void setNumberOfTouristicPlacesNotAccepted(int numberOfTouristicPlacesNotAccepted) {
		this.numberOfTouristicPlacesNotAccepted = numberOfTouristicPlacesNotAccepted;
	}

}