package com.esprit.hypnotrip.presentation.mbeans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.HorizontalBarChartModel;

import com.esprit.hypnotrip.persistence.Touristicplace;
import com.esprit.hypnotrip.services.interfaces.RateServiceLocal;
import com.esprit.hypnotrip.services.interfaces.ToursiticPlaceServiceLocal;

@ManagedBean
public class StatBeanTouristicPlaceRate {
	@EJB
	RateServiceLocal rateServiceLocal;
	@EJB
	ToursiticPlaceServiceLocal toursiticPlaceServiceLocal;
	
	private BarChartModel barModel;
	private HorizontalBarChartModel horizontalBarModel;

	@PostConstruct
	public void init() {
		createBarModels();
	}

	public BarChartModel getBarModel() {
		return barModel;
	}

	public HorizontalBarChartModel getHorizontalBarModel() {
		return horizontalBarModel;
	}

	private BarChartModel initBarModel() {
		BarChartModel model = new BarChartModel();

		ChartSeries stats = new ChartSeries();
		stats.setLabel("Number of Rates");
		List<Touristicplace> ltp= toursiticPlaceServiceLocal.getAllTouristicPlaces();
		for (Touristicplace touristicplace : ltp) {
			int numberRates = rateServiceLocal.getNumberOfRating(touristicplace.getPageId());
			stats.set(touristicplace.getName(), numberRates);
		}
		
		

		model.addSeries(stats);

		return model;
	}

	private void createBarModels() {
		createBarModel();

	}

	private void createBarModel() {
		barModel = initBarModel();

		barModel.setTitle("Bar Chart");
		barModel.setLegendPosition("ne");

		Axis xAxis = barModel.getAxis(AxisType.X);
		xAxis.setLabel("Touristic Places");

		Axis yAxis = barModel.getAxis(AxisType.Y);
		yAxis.setLabel("Rate Level");
		yAxis.setMin(0);
		yAxis.setMax(10);
	}

}