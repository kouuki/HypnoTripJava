package com.esprit.hypnotrip.presentation.mbeans;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.esprit.hypnotrip.persistence.User;
import com.esprit.hypnotrip.services.interfaces.FollowersServicesLocal;
import com.esprit.hypnotrip.services.interfaces.UserConnectionServicesLocal;
import com.esprit.hypnotrip.services.interfaces.UserServicesLocal;

@ManagedBean
@SessionScoped
public class LoginBean {

	private User user = new User();
	private User userLoggedIn = null;
	private Boolean loggedInAsSimpleUser = false;
	private Boolean loggedInAsProUser = false;
	private Boolean loggedInAsAdmin = false;
	private Boolean notification = false;
	private Boolean identifiedUser;

	@EJB
	private UserServicesLocal userServicesLocal;
	@EJB
	private FollowersServicesLocal followersServicesLocal;
	@EJB
	private UserConnectionServicesLocal connectionServicesLocal;

	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/Home?faces-redirect=true";
	}

	public String notificationSenn() {
		notification = false;
		return "/pages/simpleUserHome/RecallEventForToDay.jsf?faces-redirect=true";
	}

	public String doLogin() {
		String navaigateTo = "";

		userLoggedIn = userServicesLocal.findUserByLoginAndPassword(user.getEmail(), user.getPassword());
		if (userLoggedIn != null) {
			System.out.println("userFounded");
			user = userLoggedIn;
			setIdentifiedUser(true);
			if (userLoggedIn.getRole().equals("1")) {
				connectionServicesLocal.SaveConnection(userLoggedIn.getId());
				setLoggedInAsSimpleUser(true);
				notification = followersServicesLocal.MyEventForToDay(userLoggedIn.getId());
				navaigateTo = "/pages/simpleUserHome/home?faces-redirect=true";
			} else if (userLoggedIn.getRole().equals("0")) {
				loggedInAsProUser = true;
				connectionServicesLocal.SaveConnection(userLoggedIn.getId());
				navaigateTo = "/pages/proUserHome/home?faces-redirect=true";
			} else {
				loggedInAsAdmin = true;
				navaigateTo = "/pages/Admin/AdminHome?faces-redirect=true";
			}
		} else if (userLoggedIn == null) {
			navaigateTo = "/IncorrectPassword?faces-redirect=true";
		}

		return navaigateTo;

	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserServicesLocal getUserServicesLocal() {
		return userServicesLocal;
	}

	public void setUserServicesLocal(UserServicesLocal userServicesLocal) {
		this.userServicesLocal = userServicesLocal;
	}

	public Boolean getIdentifiedUser() {
		return identifiedUser;
	}

	public void setIdentifiedUser(Boolean identifiedUser) {
		this.identifiedUser = identifiedUser;
	}

	public Boolean getLoggedInAsSimpleUser() {
		return loggedInAsSimpleUser;
	}

	public void setLoggedInAsSimpleUser(Boolean loggedInAsSimpleUser) {
		this.loggedInAsSimpleUser = loggedInAsSimpleUser;
	}

	public Boolean getLoggedInAsProUser() {
		return loggedInAsProUser;
	}

	public void setLoggedInAsProUser(Boolean loggedInAsProUser) {
		this.loggedInAsProUser = loggedInAsProUser;
	}

	public Boolean getLoggedInAsAdmin() {
		return loggedInAsAdmin;
	}

	public void setLoggedInAsAdmin(Boolean loggedInAsAdmin) {
		this.loggedInAsAdmin = loggedInAsAdmin;
	}

	public Boolean getNotification() {
		return notification;
	}

	public void setNotification(Boolean notification) {
		this.notification = notification;
	}

	public User getUserLoggedIn() {
		return userLoggedIn;
	}

	public void setUserLoggedIn(User userLoggedIn) {
		this.userLoggedIn = userLoggedIn;
	}

}
