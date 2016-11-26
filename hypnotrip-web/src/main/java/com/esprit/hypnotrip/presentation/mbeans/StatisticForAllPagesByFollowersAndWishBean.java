package com.esprit.hypnotrip.presentation.mbeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.HorizontalBarChartModel;

import com.esprit.hypnotrip.services.interfaces.FollowersServicesLocal;

@ManagedBean
public class StatisticForAllPagesByFollowersAndWishBean {

	@EJB
	private FollowersServicesLocal followersServicesLocal;

	private HorizontalBarChartModel horizontalBarModel;

	@PostConstruct
	public void init() {

		horizontalBarModel = new HorizontalBarChartModel();
		List<Integer> listIdAllPagesInFollow = followersServicesLocal.listIdPagesInFollow();
		ChartSeries Follow = new ChartSeries();
		Follow.setLabel("Follow");
		for (Integer idPage : listIdAllPagesInFollow) {
			// nbr Follow And Name Pages;

			Follow.set(followersServicesLocal.TitlePageById(idPage), followersServicesLocal.nbrFollowers(idPage));
			// nbr Wish And Name Pages;
		}

		ChartSeries Wish = new ChartSeries();
		Wish.setLabel("Wish");
		for (Integer idPage : listIdAllPagesInFollow) {

			Wish.set(followersServicesLocal.TitlePageById(idPage), followersServicesLocal.nbrWish(idPage));
		}
		horizontalBarModel.addSeries(Follow);
		horizontalBarModel.addSeries(Wish);
		horizontalBarModel.setAnimate(true);
		horizontalBarModel.setTitle("Statistic For All Pages By Followers And Wish");
		horizontalBarModel.setLegendPosition("e");
		horizontalBarModel.setStacked(true);

		Axis xAxis = horizontalBarModel.getAxis(AxisType.X);
		xAxis.setLabel("Follow And Wish");
		xAxis.setMin(0);
		xAxis.setMax(30);

		Axis yAxis = horizontalBarModel.getAxis(AxisType.Y);
		yAxis.setLabel("Pages");
	}

	public HorizontalBarChartModel getHorizontalBarModel() {
		return horizontalBarModel;
	}

	public void setHorizontalBarModel(HorizontalBarChartModel horizontalBarModel) {
		this.horizontalBarModel = horizontalBarModel;
	}

}
