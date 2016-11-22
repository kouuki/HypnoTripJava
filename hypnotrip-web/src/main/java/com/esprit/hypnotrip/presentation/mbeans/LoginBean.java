package com.esprit.hypnotrip.presentation.mbeans;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.esprit.hypnotrip.persistence.User;
import com.esprit.hypnotrip.services.interfaces.UserServicesLocal;

@ManagedBean
@SessionScoped
public class LoginBean {

	private User user = new User();
	private Boolean loggedInAsSimpleUser = false;
	private Boolean loggedInAsProUser = false;
	private Boolean loggedInAsAdmin = false;
	private Boolean identifiedUser = false;

	@EJB
	private UserServicesLocal userServicesLocal;

	public String doLogin() {
		String navaigateTo = "";
		User userLoggedIn = userServicesLocal.findUserByLoginAndPassword(user.getEmail(), user.getPassword());
		if (userLoggedIn != null) {
			System.out.println("userFounded");
			user = userLoggedIn;
			setIdentifiedUser(true);
			if (userLoggedIn.getRole().equals("1")) {
				setLoggedInAsSimpleUser(true);
				navaigateTo = "/pages/customerHome/home?faces-redirect=true";
			} else if (userLoggedIn.getRole().equals("0")) {
				loggedInAsProUser = true;
				navaigateTo = "/pages/companyHome/home?faces-redirect=true";
			} else if (userLoggedIn.getRole().equals("2")) {
				loggedInAsAdmin = true;
				navaigateTo = "/pages/companyHome/home?faces-redirect=true";
			}
		} else {
			System.out.println("userNotFounded");
			navaigateTo = "/horreur?faces-redirect=true";
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

}
