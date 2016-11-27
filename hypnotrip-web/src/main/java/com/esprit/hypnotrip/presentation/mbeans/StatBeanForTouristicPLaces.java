package com.esprit.hypnotrip.presentation.mbeans;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import com.esprit.hypnotrip.persistence.Touristicplace;
import com.esprit.hypnotrip.services.interfaces.RateServiceLocal;
import com.esprit.hypnotrip.services.interfaces.ToursiticPlaceServiceLocal;

@ManagedBean
public class StatBeanForTouristicPLaces implements Serializable {

		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		@EJB
		RateServiceLocal rateServiceLocal;
		@EJB
		ToursiticPlaceServiceLocal toursiticPlaceServiceLocal;

		private BarChartModel barModel;
		private BarChartModel barModel2;
		

		@PostConstruct
		public void init() {
			createBarModels();
			
		}

		public BarChartModel getBarModel() {
			return barModel;
		}

		

		private BarChartModel initBarModel() {
			BarChartModel model = new BarChartModel();

			ChartSeries stats = new ChartSeries();
			stats.setLabel("Number of Rates");
			List<Touristicplace> ltp = toursiticPlaceServiceLocal.getAllTouristicPlaces();
			for (Touristicplace touristicplace : ltp) {
				int numberRates = rateServiceLocal.getNumberOfRating(touristicplace.getPageId());
				stats.set(touristicplace.getName(), numberRates);
			}

			model.addSeries(stats);

			return model;
		}
		private BarChartModel initBarModel2() {
			BarChartModel model = new BarChartModel();

			ChartSeries stats = new ChartSeries();
			stats.setLabel("Rating Level");
			List<Touristicplace> ltp = toursiticPlaceServiceLocal.getAllTouristicPlaces();
			for (Touristicplace touristicplace : ltp) {
				int numberRates = rateServiceLocal.getRateLevel(touristicplace.getPageId());
				stats.set(touristicplace.getName(), numberRates);
			}

			model.addSeries(stats);

			return model;
		}

		private void createBarModels() {
			createBarModel();
			createBarModel2();

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
		
		private void createBarModel2() {
			barModel2 = initBarModel2();

			barModel2.setTitle("Bar Chart");
			barModel2.setLegendPosition("ne");

			Axis xAxis = barModel2.getAxis(AxisType.X);
			xAxis.setLabel("Touristic Places");

			Axis yAxis = barModel2.getAxis(AxisType.Y);
			yAxis.setLabel("Rate Level");
			yAxis.setMin(0);
			yAxis.setMax(10);
		}

		public BarChartModel getBarModel2() {
			return barModel2;
		}

		public void setBarModel2(BarChartModel barModel2) {
			this.barModel2 = barModel2;
		}

	}