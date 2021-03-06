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
//			   BarChartModel model = new BarChartModel();
//			   
//		        ChartSeries boys = new ChartSeries();
//		        boys.setLabel("Boys");
//		        boys.set("2004", 120);
//		        boys.set("2005", 100);
//		        boys.set("2006", 44);
//		        boys.set("2007", 150);
//		        boys.set("2008", 25);
//		 
//		        ChartSeries girls = new ChartSeries();
//		        girls.setLabel("Girls");
//		        girls.set("2004", 52);
//		        girls.set("2005", 60);
//		        girls.set("2006", 110);
//		        girls.set("2007", 135);
//		        girls.set("2008", 120);
//		 
//		        model.addSeries(boys);
//		        model.addSeries(girls);
//		         
//		        return model;
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