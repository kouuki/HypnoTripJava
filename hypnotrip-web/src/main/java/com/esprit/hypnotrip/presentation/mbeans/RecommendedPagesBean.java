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

import com.esprit.hypnotrip.persistence.Follows;
import com.esprit.hypnotrip.persistence.FollowsId;
import com.esprit.hypnotrip.persistence.Pages;
import com.esprit.hypnotrip.persistence.Posts;
import com.esprit.hypnotrip.persistence.Touristicplace;
import com.esprit.hypnotrip.services.interfaces.FollowersServicesLocal;

@ManagedBean
@ViewScoped
public class RecommendedPagesBean {
	@EJB
	private FollowersServicesLocal followersServicesLocal;

	private List<Pages> ListRecommendedPages = new ArrayList<>();

	private boolean displayForm1 = true;
	private boolean displayForm2 = false;
	private boolean displayTouristicPlaces = false;

	private Follows follows = new Follows();
	private FollowsId followsId = new FollowsId();

	private List<Pages> ListAllPagesInDataBase = new ArrayList<>();
	private List<Pages> ListAllPages = new ArrayList<>();
	private List<Pages> ListAllPagesToDisplay = new ArrayList<>();
	private List<Pages> ListAllPagesToDisplayAsAPages = new ArrayList<>();
	private List<Follows> ListAllMyFollowAndWish = new ArrayList<>();
	private String idUserConnected;

	@ManagedProperty(value = "#{loginBean}")
	private LoginBean loginBean;
	private Pages pagesSelected = new Pages();

	@PostConstruct
	public void init() {
		idUserConnected = loginBean.getUser().getId();
		List<Posts> OrderByTagUsed = followersServicesLocal.findListOfTagsOrdredByUsing(idUserConnected);
		ListAllPagesInDataBase = followersServicesLocal.ListAllPagesByTags(OrderByTagUsed);
		ListAllMyFollowAndWish = followersServicesLocal.findAllFollowByUserId(idUserConnected);
		ListAllPagesToDisplay = followersServicesLocal.ListAllPagesByTags(OrderByTagUsed);
		for (Follows follows : ListAllMyFollowAndWish) {
			if (ListAllPagesInDataBase.contains(follows.getPages())) {
				ListAllPagesToDisplay.remove(follows.getPages());
			}
		}
		for (Pages pages : ListAllPagesToDisplay) {
			if (pages instanceof Touristicplace) {
				displayTouristicPlaces = true;
				ListAllPagesToDisplayAsAPages.add(pages);
			}
		}

	}

	public String follow() {
		followsId.setDateFollow(new Date());
		followsId.setPageId(pagesSelected.getPageId());
		followsId.setUserId(idUserConnected);
		follows.setId(followsId);

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

	public FollowersServicesLocal getFollowersServicesLocal() {
		return followersServicesLocal;
	}

	public void setFollowersServicesLocal(FollowersServicesLocal followersServicesLocal) {
		this.followersServicesLocal = followersServicesLocal;
	}

	public List<Pages> getListRecommendedPages() {
		return ListRecommendedPages;
	}

	public void setListRecommendedPages(List<Pages> listRecommendedPages) {
		ListRecommendedPages = listRecommendedPages;
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

	public List<Pages> getListAllPagesInDataBase() {
		return ListAllPagesInDataBase;
	}

	public void setListAllPagesInDataBase(List<Pages> listAllPagesInDataBase) {
		ListAllPagesInDataBase = listAllPagesInDataBase;
	}

	public List<Pages> getListAllPages() {
		return ListAllPages;
	}

	public void setListAllPages(List<Pages> listAllPages) {
		ListAllPages = listAllPages;
	}

	public List<Pages> getListAllPagesToDisplay() {
		return ListAllPagesToDisplay;
	}

	public void setListAllPagesToDisplay(List<Pages> listAllPagesToDisplay) {
		ListAllPagesToDisplay = listAllPagesToDisplay;
	}

	public List<Follows> getListAllMyFollowAndWish() {
		return ListAllMyFollowAndWish;
	}

	public void setListAllMyFollowAndWish(List<Follows> listAllMyFollowAndWish) {
		ListAllMyFollowAndWish = listAllMyFollowAndWish;
	}

	public String getIdUserConnected() {
		return idUserConnected;
	}

	public void setIdUserConnected(String idUserConnected) {
		this.idUserConnected = idUserConnected;
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

	public List<Pages> getListAllPagesToDisplayAsAPages() {
		return ListAllPagesToDisplayAsAPages;
	}

	public void setListAllPagesToDisplayAsAPages(List<Pages> listAllPagesToDisplayAsAPages) {
		ListAllPagesToDisplayAsAPages = listAllPagesToDisplayAsAPages;
	}

	public boolean isDisplayTouristicPlaces() {
		return displayTouristicPlaces;
	}

	public void setDisplayTouristicPlaces(boolean displayTouristicPlaces) {
		this.displayTouristicPlaces = displayTouristicPlaces;
	}

}
