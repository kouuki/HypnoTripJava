package com.esprit.hypnotrip.presentation.mbeans;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import org.primefaces.model.chart.PieChartModel;

import com.esprit.hypnotrip.services.interfaces.FollowersServicesLocal;

@ManagedBean
public class FollowAndWishStatisticBean {

	@EJB
	private FollowersServicesLocal followersServicesLocal;
	private PieChartModel pieModel;
	Integer nbrFollowers;
	Integer nbrWishs;

	@PostConstruct
	public void init() {
		pieModel = new PieChartModel();
		
		nbrFollowers = Integer.parseInt(followersServicesLocal.findNbrFollowByWS().get(0));
		nbrWishs = Integer.parseInt(followersServicesLocal.findNbrWishByWS().get(0));

		pieModel.set("FOLLOWERS", nbrFollowers);
		pieModel.set("WISH", nbrWishs);
		pieModel.setTitle(" Pie FOLLOWER VS WISH");
		pieModel.setLegendPosition("e");
		pieModel.setFill(false);
		pieModel.setShowDataLabels(true);
		pieModel.setDiameter(400);
	}

	public FollowersServicesLocal getFollowersServicesLocal() {
		return followersServicesLocal;
	}

	public void setFollowersServicesLocal(FollowersServicesLocal followersServicesLocal) {
		this.followersServicesLocal = followersServicesLocal;
	}

	public PieChartModel getPieModel() {
		return pieModel;
	}

	public void setPieModel(PieChartModel pieModel) {
		this.pieModel = pieModel;
	}

	public Integer getNbrFollowers() {
		return nbrFollowers;
	}

	public void setNbrFollowers(Integer nbrFollowers) {
		this.nbrFollowers = nbrFollowers;
	}

	public Integer getNbrWishs() {
		return nbrWishs;
	}

	public void setNbrWishs(Integer nbrWishs) {
		this.nbrWishs = nbrWishs;
	}

}
