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

import com.esprit.hypnotrip.persistence.Offer;
import com.esprit.hypnotrip.persistence.Pages;
import com.esprit.hypnotrip.persistence.Touristicplace;

import com.esprit.hypnotrip.services.interfaces.FollowersServicesLocal;
import com.esprit.hypnotrip.services.interfaces.UserServicesLocal;

@ManagedBean
@ViewScoped
public class MyFollowersPagesBean {

	@EJB
	private FollowersServicesLocal followersServicesLocal;

	@EJB
	private UserServicesLocal userServicesLocal;

	@ManagedProperty(value = "#{loginBean}")
	private LoginBean loginBean;

	private List<Follows> follows = new ArrayList<>();
	private Follows followSelected = new Follows();
	private String idUserConnected;

	private boolean haveFollow = false;

	@PostConstruct
	public void init() {

		idUserConnected = loginBean.getUser().getId();

		follows = followersServicesLocal.findAllFollowByUserId(idUserConnected);
		for (Follows follow : follows) {
			haveFollow = true;
			follow.setNbrFollowers(followersServicesLocal.nbrFollowers(follow.getId().getPageId()));
			follow.setNbrPost(followersServicesLocal.nbrPostsByUser(follow.getId().getPageId(), idUserConnected));
			System.out.println(follow.getNbrFollowers());
			System.out.println(follow.getNbrPost() + " test nbrpost");
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

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	public List<Follows> getFollows() {
		return follows;
	}

	public void setFollows(List<Follows> follows) {
		this.follows = follows;
	}

	public String getIdUserConnected() {
		return idUserConnected;
	}

	public void setIdUserConnected(String idUserConnected) {
		this.idUserConnected = idUserConnected;
	}

	public Follows getFollowSelected() {
		return followSelected;
	}

	public void setFollowSelected(Follows followSelected) {
		this.followSelected = followSelected;
	}

	public boolean isHaveFollow() {
		return haveFollow;
	}

	public void setHaveFollow(boolean haveFollow) {
		this.haveFollow = haveFollow;
	}

}
