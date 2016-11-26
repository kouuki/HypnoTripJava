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
	private List<Event> events = new ArrayList<>();
	private List<Touristicplace> touristicPlaces = new ArrayList<>();
	private List<Offer> offers = new ArrayList<>();
	private Follows followSelected = new Follows();
	private String idUserConnected;

	private boolean haveFollow;
	private boolean haveEvents;
	private boolean haveOffers;
	private boolean haveTouristicPlaces;

	// display
	private boolean displayAllFollow = false;
	private boolean displayAllEvent = false;
	private boolean displayAllOffer = false;
	private boolean displayAllTouristicPlaces = false;
	private boolean displayAllPages = false;

	@PostConstruct
	public void init() {
		haveFollow = false;
		haveEvents = false;
		haveOffers = false;
		haveTouristicPlaces = false;
		displayAllFollow = false;
		displayAllEvent = false;
		displayAllOffer = false;
		displayAllTouristicPlaces = false;
		displayAllPages = false;
		idUserConnected = loginBean.getUser().getId();

		follows = followersServicesLocal.findAllFollowByUserId(idUserConnected);

		for (Follows follow : follows) {
			displayAllFollow = true;
			haveFollow = false;
			follow.setNbrFollowers(followersServicesLocal.nbrFollowers(follow.getId().getPageId()));
			follow.setNbrPost(followersServicesLocal.nbrPostsByUser(follow.getId().getPageId(), idUserConnected));
			System.out.println(follow.getNbrFollowers());
			System.out.println(follow.getNbrPost() + " test nbrpost");
			if (follow.getPages() instanceof Event) {
				haveFollow = true;
				haveEvents = true;
				events.add((Event) follow.getPages());
			} else if (follow.getPages() instanceof Offer) {
				haveFollow = true;
				haveOffers = true;
				offers.add((Offer) follow.getPages());
			} else if (follow.getPages() instanceof Touristicplace) {
				haveFollow = true;
				haveTouristicPlaces = true;
				touristicPlaces.add((Touristicplace) follow.getPages());
			} else
				haveFollow = false;
		}

	}

	public void DisplayOffer() {
		displayAllFollow = false;
		displayAllOffer = true;
		displayAllEvent = false;
		displayAllTouristicPlaces = false;
		displayAllPages = false;
	}

	public void DisplayPages() {
		this.init();
	}

	public void DisplayEvent() {
		displayAllFollow = false;
		displayAllEvent = true;
		displayAllPages = false;
		displayAllOffer = false;
		displayAllTouristicPlaces = false;
	}

	public void DisplayTouristicPlaces() {
		displayAllFollow = false;
		displayAllTouristicPlaces = true;
		displayAllPages = false;
		displayAllOffer = false;
		displayAllEvent = false;

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

	public boolean isHaveEvents() {
		return haveEvents;
	}

	public void setHaveEvents(boolean haveEvents) {
		this.haveEvents = haveEvents;
	}

	public UserServicesLocal getUserServicesLocal() {
		return userServicesLocal;
	}

	public void setUserServicesLocal(UserServicesLocal userServicesLocal) {
		this.userServicesLocal = userServicesLocal;
	}

	public List<Event> getEvents() {
		return events;
	}

	public void setEvents(List<Event> events) {
		this.events = events;
	}

	public List<Touristicplace> getTouristicPlaces() {
		return touristicPlaces;
	}

	public void setTouristicPlaces(List<Touristicplace> touristicPlaces) {
		this.touristicPlaces = touristicPlaces;
	}

	public List<Offer> getOffers() {
		return offers;
	}

	public void setOffers(List<Offer> offers) {
		this.offers = offers;
	}

	public boolean isHaveOffers() {
		return haveOffers;
	}

	public void setHaveOffers(boolean haveOffers) {
		this.haveOffers = haveOffers;
	}

	public boolean isHaveTouristicPlaces() {
		return haveTouristicPlaces;
	}

	public void setHaveTouristicPlaces(boolean haveTouristicPlaces) {
		this.haveTouristicPlaces = haveTouristicPlaces;
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

	public boolean isDisplayAllFollow() {
		return displayAllFollow;
	}

	public void setDisplayAllFollow(boolean displayAllFollow) {
		this.displayAllFollow = displayAllFollow;
	}

}
