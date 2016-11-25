package com.esprit.hypnotrip.presentation.mbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.esprit.hypnotrip.persistence.Event;
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

	private List<Pages> ListAllEvent = new ArrayList<>();
	private List<Pages> ListAllOffers = new ArrayList<>();
	private List<Pages> ListAllTouristicPlace = new ArrayList<>();
	private List<Pages> ListAllPages = new ArrayList<>();
	private List<Follows> ListAllMyFollowAndWish = new ArrayList<>();

	// ** Display
	private boolean displayAllEvent = false;
	private boolean displayAllOffer = false;
	private boolean displayAllTouristicPlaces = false;
	private boolean displayAllPages = false;
	private boolean displayAll = true;
	// **

	public void DisplayOffer() {
		displayAllOffer = true;
		displayAllEvent = false;
		displayAllTouristicPlaces = false;
		displayAllPages = false;
		displayAll = false;
	}

	public void DisplayPages() {
		displayAllPages = true;
		displayAllOffer = false;
		displayAllEvent = false;
		displayAllTouristicPlaces = false;
		displayAll = false;

	}

	public void DisplayEvent() {
		displayAllEvent = true;
		displayAllPages = false;
		displayAllOffer = false;
		displayAllTouristicPlaces = false;
		displayAll = false;

	}

	public void DisplayTouristicPlaces() {
		displayAllTouristicPlaces = true;
		displayAllPages = false;
		displayAllOffer = false;
		displayAllEvent = false;
		displayAll = false;

	}

	@PostConstruct
	public void init() {

		idUserConnected = loginBean.getUser().getId();

		follows = followersServicesLocal.findAllFollowByUserId(idUserConnected);
		for (Follows follow : follows) {
			follow.setNbrFollowers(followersServicesLocal.nbrFollowers(follow.getId().getPageId()));
			follow.setNbrPost(followersServicesLocal.nbrPostsByUser(follow.getId().getPageId(), idUserConnected));
			System.out.println(follow.getNbrFollowers());
			System.out.println(follow.getNbrPost() + " test nbrpost");
		}
		ListAllMyFollowAndWish = followersServicesLocal.findAllFollowByUserId(idUserConnected);
		for (Follows follow : ListAllMyFollowAndWish) {
			if (follow.getPages() instanceof Event) {
				ListAllEvent.add(follow.getPages());
			} else if (follow.getPages() instanceof Offer) {
				ListAllOffers.add(follow.getPages());
			} else if (follow.getPages() instanceof Touristicplace) {
				ListAllTouristicPlace.add(follow.getPages());
			} else if (follow.getPages() instanceof Pages) {
				ListAllPages.add(follow.getPages());
			}
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

	public List<Pages> getListAllEvent() {
		return ListAllEvent;
	}

	public void setListAllEvent(List<Pages> listAllEvent) {
		ListAllEvent = listAllEvent;
	}

	public List<Pages> getListAllOffers() {
		return ListAllOffers;
	}

	public void setListAllOffers(List<Pages> listAllOffers) {
		ListAllOffers = listAllOffers;
	}

	public List<Pages> getListAllTouristicPlace() {
		return ListAllTouristicPlace;
	}

	public void setListAllTouristicPlace(List<Pages> listAllTouristicPlace) {
		ListAllTouristicPlace = listAllTouristicPlace;
	}

	public List<Pages> getListAllPages() {
		return ListAllPages;
	}

	public void setListAllPages(List<Pages> listAllPages) {
		ListAllPages = listAllPages;
	}

	public boolean isDisplayAllEvent() {
		return displayAllEvent;
	}

	public void setDisplayAllEvent(boolean displayAllEvent) {
		this.displayAllEvent = displayAllEvent;
	}

	public boolean isDisplayAllOffer() {
		return displayAllOffer;
	}

	public void setDisplayAllOffer(boolean displayAllOffer) {
		this.displayAllOffer = displayAllOffer;
	}

	public boolean isDisplayAllTouristicPlaces() {
		return displayAllTouristicPlaces;
	}

	public void setDisplayAllTouristicPlaces(boolean displayAllTouristicPlaces) {
		this.displayAllTouristicPlaces = displayAllTouristicPlaces;
	}

	public boolean isDisplayAllPages() {
		return displayAllPages;
	}

	public void setDisplayAllPages(boolean displayAllPages) {
		this.displayAllPages = displayAllPages;
	}

	public List<Follows> getListAllMyFollowAndWish() {
		return ListAllMyFollowAndWish;
	}

	public void setListAllMyFollowAndWish(List<Follows> listAllMyFollowAndWish) {
		ListAllMyFollowAndWish = listAllMyFollowAndWish;
	}

	public boolean isDisplayAll() {
		return displayAll;
	}

	public void setDisplayAll(boolean displayAll) {
		this.displayAll = displayAll;
	}

}
