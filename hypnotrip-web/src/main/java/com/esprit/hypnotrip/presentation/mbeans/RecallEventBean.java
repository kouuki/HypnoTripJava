package com.esprit.hypnotrip.presentation.mbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.esprit.hypnotrip.persistence.Follows;
import com.esprit.hypnotrip.services.interfaces.FollowersServicesLocal;
import com.esprit.hypnotrip.services.interfaces.UserServicesLocal;

@ManagedBean
@ViewScoped
public class RecallEventBean {
	@EJB
	private FollowersServicesLocal followersServicesLocal;

	@EJB
	private UserServicesLocal userServicesLocal;

	@ManagedProperty(value = "#{loginBean}")
	private LoginBean loginBean;

	private List<Follows> listAllMyEventForToDay = new ArrayList<>();
	private Follows followSelected = new Follows();
	private String idUserConnected;

	@PostConstruct
	public void init() {
		idUserConnected = loginBean.getUser().getId();
		listAllMyEventForToDay = followersServicesLocal.ListMyEventForToDay(idUserConnected);
		for (Follows follow : listAllMyEventForToDay) {
			follow.setNbrFollowers(followersServicesLocal.nbrFollowers(follow.getId().getPageId()));
			follow.setNbrPost(followersServicesLocal.nbrPostsByUser(follow.getId().getPageId(), idUserConnected));
		}

	}

	public void unfollow() {

		followersServicesLocal.SaveOrUpdate(followSelected.getId(), false, false);
		FacesContext.getCurrentInstance().getViewRoot().getViewMap().clear();

	}

	public void unWish() {

		followersServicesLocal.SaveOrUpdate(followSelected.getId(), true, false);
		FacesContext.getCurrentInstance().getViewRoot().getViewMap().clear();

	}

	public void Wish() {

		followersServicesLocal.SaveOrUpdate(followSelected.getId(), true, true);
		FacesContext.getCurrentInstance().getViewRoot().getViewMap().clear();

	}

	public FollowersServicesLocal getFollowersServicesLocal() {
		return followersServicesLocal;
	}

	public void setFollowersServicesLocal(FollowersServicesLocal followersServicesLocal) {
		this.followersServicesLocal = followersServicesLocal;
	}

	public UserServicesLocal getUserServicesLocal() {
		return userServicesLocal;
	}

	public void setUserServicesLocal(UserServicesLocal userServicesLocal) {
		this.userServicesLocal = userServicesLocal;
	}

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	public List<Follows> getListAllMyEventForToDay() {
		return listAllMyEventForToDay;
	}

	public void setListAllMyEventForToDay(List<Follows> listAllMyEventForToDay) {
		this.listAllMyEventForToDay = listAllMyEventForToDay;
	}

	public Follows getFollowSelected() {
		return followSelected;
	}

	public void setFollowSelected(Follows followSelected) {
		this.followSelected = followSelected;
	}

	public String getIdUserConnected() {
		return idUserConnected;
	}

	public void setIdUserConnected(String idUserConnected) {
		this.idUserConnected = idUserConnected;
	}

}
