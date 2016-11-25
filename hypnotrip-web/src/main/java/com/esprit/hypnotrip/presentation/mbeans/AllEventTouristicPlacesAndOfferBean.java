package com.esprit.hypnotrip.presentation.mbeans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.esprit.hypnotrip.persistence.Event;
import com.esprit.hypnotrip.persistence.Follows;
import com.esprit.hypnotrip.persistence.FollowsId;
import com.esprit.hypnotrip.persistence.Offer;
import com.esprit.hypnotrip.persistence.Pages;
import com.esprit.hypnotrip.persistence.Touristicplace;
import com.esprit.hypnotrip.services.interfaces.FollowersServicesLocal;

@ManagedBean
@ViewScoped
public class AllEventTouristicPlacesAndOfferBean {

	@EJB
	private FollowersServicesLocal followersServicesLocal;
	private List<Pages> ListAllPagesInDataBase = new ArrayList<>();
	private List<Pages> ListAllEvent = new ArrayList<>();
	private List<Pages> ListAllOffers = new ArrayList<>();
	private List<Pages> ListAllTouristicPlace = new ArrayList<>();
	private List<Pages> ListAllPages = new ArrayList<>();
	private List<Pages> ListAllPagesToDisplay = new ArrayList<>();
	private List<Follows> ListAllMyFollowAndWish = new ArrayList<>();

	private boolean displayForm1 = true;
	private boolean displayForm2 = false;

	// ** Display
	private boolean displayAllEvent = false;
	private boolean displayAllOffer = false;
	private boolean displayAllTouristicPlaces = false;
	private boolean displayAllPages = false;
	// **
	private Follows follows = new Follows();
	private FollowsId followsId = new FollowsId();

	private String idUserConnected;

	@ManagedProperty(value = "#{loginBean}")
	private LoginBean loginBean;
	private Pages pagesSelected = new Pages();

	public void DisplayOffer() {
		displayForm1 = false;
		displayAllOffer = true;
		displayAllEvent = false;
		displayAllTouristicPlaces = false;
		displayAllPages = false;
	}

	public void DisplayPages() {
		displayForm1 = false;
		displayAllPages = true;
		displayAllOffer = false;
		displayAllEvent = false;
		displayAllTouristicPlaces = false;
	}

	public void DisplayEvent() {
		displayForm1 = false;
		displayAllEvent = true;
		displayAllPages = false;
		displayAllOffer = false;
		displayAllTouristicPlaces = false;
	}

	public void DisplayTouristicPlaces() {
		displayForm1 = false;
		displayAllTouristicPlaces = true;
		displayAllPages = false;
		displayAllOffer = false;
		displayAllEvent = false;

	}

	@PostConstruct
	public void init() {
		idUserConnected = loginBean.getUser().getId();
		ListAllPagesInDataBase = followersServicesLocal.ListAllPages();
		ListAllMyFollowAndWish = followersServicesLocal.findAllFollowByUserId(idUserConnected);
		ListAllPagesToDisplay = followersServicesLocal.ListAllPages();
		for (Follows follows : ListAllMyFollowAndWish) {
			if (ListAllPagesInDataBase.contains(follows.getPages())) {
				ListAllPagesToDisplay.remove(follows.getPages());
			}
		}
		for (Pages pages : ListAllPagesToDisplay) {
			if (pages instanceof Event) {
				ListAllEvent.add(pages);
			} else if (pages instanceof Offer) {
				ListAllOffers.add(pages);
			} else if (pages instanceof Touristicplace) {
				ListAllTouristicPlace.add(pages);
			} else if (pages instanceof Pages) {
				ListAllPages.add(pages);
			}
		}

	}

	public String follow() {
		followsId.setDateFollow(new Date());
		followsId.setPageId(pagesSelected.getPageId());
		followsId.setUserId(idUserConnected);
		follows.setId(followsId);
		displayAllEvent = false;
		displayAllOffer = false;
		displayAllTouristicPlaces = false;
		displayAllPages = false;
		displayForm1 = false;
		displayForm2 = true;

		return "";
		// FacesContext.getCurrentInstance().getViewRoot().getViewMap().clear();

	}

	public void Wish() {

		followersServicesLocal.SaveOrUpdate(followsId, true, true);

		// followersServicesLocal.SaveOrUpdate(follows.getId(), true, true);

		FacesContext.getCurrentInstance().getViewRoot().getViewMap().clear();

	}

	public void WishDirect() {
		followsId.setDateFollow(new Date());
		followsId.setPageId(pagesSelected.getPageId());
		followsId.setUserId(idUserConnected);
		follows.setId(followsId);
		followersServicesLocal.SaveOrUpdate(followsId, true, true);

		FacesContext.getCurrentInstance().getViewRoot().getViewMap().clear();

	}

	public void cancel() {
		followersServicesLocal.SaveOrUpdate(followsId, true, false);

		FacesContext.getCurrentInstance().getViewRoot().getViewMap().clear();

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

	public void setListAllOffer(List<Pages> listAllOffers) {
		ListAllOffers = listAllOffers;
	}

	public List<Pages> getListAllPages() {
		return ListAllPages;
	}

	public void setListAllPages(List<Pages> listAllPages) {
		ListAllPages = listAllPages;
	}

	public List<Pages> getListAllPagesInDataBase() {
		return ListAllPagesInDataBase;
	}

	public void setListAllPagesInDataBase(List<Pages> listAllPagesInDataBase) {
		ListAllPagesInDataBase = listAllPagesInDataBase;
	}

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	public Pages getPagesSelected() {
		return pagesSelected;
	}

	public void setPagesSelected(Pages pagesSelected) {
		this.pagesSelected = pagesSelected;
	}

	public String getIdUserConnected() {
		return idUserConnected;
	}

	public void setIdUserConnected(String idUserConnected) {
		this.idUserConnected = idUserConnected;
	}

	public List<Follows> getListAllMyFollowAndWish() {
		return ListAllMyFollowAndWish;
	}

	public void setListAllMyFollowAndWish(List<Follows> listAllMyFollowAndWish) {
		ListAllMyFollowAndWish = listAllMyFollowAndWish;
	}

	public FollowersServicesLocal getFollowersServicesLocal() {
		return followersServicesLocal;
	}

	public void setFollowersServicesLocal(FollowersServicesLocal followersServicesLocal) {
		this.followersServicesLocal = followersServicesLocal;
	}

	public List<Pages> getListAllPagesToDisplay() {
		return ListAllPagesToDisplay;
	}

	public void setListAllPagesToDisplay(List<Pages> listAllPagesToDisplay) {
		ListAllPagesToDisplay = listAllPagesToDisplay;
	}

	public boolean isDisplayForm1() {
		return displayForm1;
	}

	public void setDisplayForm1(boolean displayForm1) {
		this.displayForm1 = displayForm1;
	}

	public boolean isDisplayForm2() {
		return displayForm2;
	}

	public void setDisplayForm2(boolean displayForm2) {
		this.displayForm2 = displayForm2;
	}

	public Follows getFollows() {
		return follows;
	}

	public void setFollows(Follows follows) {
		this.follows = follows;
	}

	public FollowsId getFollowsId() {
		return followsId;
	}

	public void setFollowsId(FollowsId followsId) {
		this.followsId = followsId;
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

	public List<Pages> getListAllTouristicPlace() {
		return ListAllTouristicPlace;
	}

	public void setListAllTouristicPlace(List<Pages> listAllTouristicPlace) {
		ListAllTouristicPlace = listAllTouristicPlace;
	}

	public void setListAllOffers(List<Pages> listAllOffers) {
		ListAllOffers = listAllOffers;
	}

}
