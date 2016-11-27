package com.esprit.hypnotrip.presentation.mbeans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartSeries;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartModel;

import com.esprit.hypnotrip.persistence.Pages;
import com.esprit.hypnotrip.services.interfaces.FollowersServicesLocal;

@ManagedBean
public class StatPagesOrderByFollowersAndOrderByWish {

	@EJB
	private FollowersServicesLocal followersServicesLocal;

	private LineChartModel multiAxisModelFollowers;
	private LineChartModel multiAxisModelWish;

	private List<Pages> listPagesByOrderOfFollowers = new ArrayList<>();

	@PostConstruct
	public void init() {

		setListPagesByOrderOfFollowers(followersServicesLocal.listPagesOrdredByFollowing());
		multiAxisModelFollowers = new LineChartModel();
		BarChartSeries Followers = new BarChartSeries();
		Followers.setLabel("Followers");
		multiAxisModelWish = new LineChartModel();
		BarChartSeries Wish = new BarChartSeries();
		Wish.setLabel("Wish");
		for (Pages pages : listPagesByOrderOfFollowers) {
			Followers.set(followersServicesLocal.TitlePageById(pages.getPageId()),
					followersServicesLocal.nbrFollowers(pages.getPageId()));
			Wish.set(followersServicesLocal.TitlePageById(pages.getPageId()),
					followersServicesLocal.nbrWish(pages.getPageId()));
		}
		multiAxisModelFollowers.addSeries(Followers);
		multiAxisModelWish.addSeries(Wish);

		multiAxisModelFollowers.setTitle("Followers Chart");
		multiAxisModelFollowers.setMouseoverHighlight(false);

		multiAxisModelFollowers.getAxes().put(AxisType.X, new CategoryAxis("Pages"));
		multiAxisModelFollowers.setAnimate(true);
		Axis yAxisFollowers = multiAxisModelFollowers.getAxis(AxisType.Y);
		yAxisFollowers.setLabel("Followers");
		yAxisFollowers.setMin(0);
		yAxisFollowers.setMax(12);
		multiAxisModelWish.setAnimate(true);
		multiAxisModelWish.setTitle("Wish List Chart");
		multiAxisModelWish.setMouseoverHighlight(false);

		multiAxisModelWish.getAxes().put(AxisType.X, new CategoryAxis("Pages"));

		Axis yAxisWish = multiAxisModelWish.getAxis(AxisType.Y);
		yAxisWish.setLabel("Wish List");
		yAxisWish.setMin(0);
		yAxisWish.setMax(12);

	}

	public LineChartModel getMultiAxisModelFollowers() {
		return multiAxisModelFollowers;
	}

	public void setMultiAxisModelFollowers(LineChartModel multiAxisModelFollowers) {
		this.multiAxisModelFollowers = multiAxisModelFollowers;
	}

	public LineChartModel getMultiAxisModelWish() {
		return multiAxisModelWish;
	}

	public void setMultiAxisModelWish(LineChartModel multiAxisModelWish) {
		this.multiAxisModelWish = multiAxisModelWish;
	}

	public List<Pages> getListPagesByOrderOfFollowers() {
		return listPagesByOrderOfFollowers;
	}

	public void setListPagesByOrderOfFollowers(List<Pages> listPagesByOrderOfFollowers) {
		this.listPagesByOrderOfFollowers = listPagesByOrderOfFollowers;
	}

}
