package com.esprit.hypnotrip.presentation.mbeans;

import java.io.Serializable;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import org.primefaces.model.chart.PieChartModel;

import com.esprit.hypnotrip.persistence.Offer;
import com.esprit.hypnotrip.services.interfaces.OfferServiceLocal;

@ManagedBean
public class StatisticsForMostOfferBought implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	OfferServiceLocal offerServiceLocal;

	private PieChartModel pieModel2;

	@PostConstruct
	public void init() {
		createPieModels();
	}

	public PieChartModel getPieModel2() {
		return pieModel2;
	}

	private void createPieModels() {

		createPieModel2();
	}

	private void createPieModel2() {
		pieModel2 = new PieChartModel();

		Map<Offer, Long> ltp = offerServiceLocal.sortBoughtOffer();
		pieModel2.setTitle("Most offer bought");
		pieModel2.setLegendPosition("w");

		pieModel2.setDiameter(300);
		for (Entry<Offer, Long> entry : ltp.entrySet()) {
			Offer cle = entry.getKey();
			Long valeur = entry.getValue();

			pieModel2.set(cle.getTitle(), valeur);

		}

	}

}
