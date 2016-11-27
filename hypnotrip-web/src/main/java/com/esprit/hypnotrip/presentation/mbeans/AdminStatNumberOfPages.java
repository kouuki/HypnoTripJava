package com.esprit.hypnotrip.presentation.mbeans;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import org.primefaces.model.chart.PieChartModel;

import com.esprit.hypnotrip.services.interfaces.EventServicesLocal;
import com.esprit.hypnotrip.services.interfaces.OfferServiceLocal;
import com.esprit.hypnotrip.services.interfaces.ToursiticPlaceServiceLocal;

@ManagedBean
public class AdminStatNumberOfPages {
	@EJB
	ToursiticPlaceServiceLocal toursiticPlaceServiceLocal;
	@EJB
	EventServicesLocal eventServicesLocal;
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
         
        pieModel2.set("Touristic Places", toursiticPlaceServiceLocal.numberOfTouristicPLaces());
        pieModel2.set("Events", eventServicesLocal.numberOfEvents());
        pieModel2.set("Offers", offerServiceLocal.numberOfOffers());
  
        pieModel2.setTitle("Number Of Pages In each Catogory");
        pieModel2.setLegendPosition("e");
        pieModel2.setFill(false);
        pieModel2.setShowDataLabels(true);
        pieModel2.setDiameter(150);
    }
}
