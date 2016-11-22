package com.esprit.hypnotrip.services.util;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import com.esprit.hypnotrip.persistence.Buy;
import com.esprit.hypnotrip.persistence.BuyId;
import com.esprit.hypnotrip.persistence.Offer;
import com.esprit.hypnotrip.persistence.Pages;
import com.esprit.hypnotrip.services.interfaces.OfferServiceLocal;
import com.esprit.hypnotrip.services.interfaces.PageServiceLocal;

@Singleton
@LocalBean
@Startup

public class DefaultDB {
	@EJB
	private PageServiceLocal pageServiceLocal;
	@EJB
	private OfferServiceLocal offerServiceLocal;

	public DefaultDB() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void init() {

		Pages pages = new Pages("My first page");

		pageServiceLocal.saveOrUpdatePage(pages, "b38f3299-6949-42c7-9a6c-f998c66f485d");

		Pages offre = new Offer("My first offer", 2.5, 1.1);

		pageServiceLocal.saveOrUpdatePage(offre, "b38f3299-6949-42c7-9a6c-f998c66f485d");
		Pages offre2 = new Offer("My second offer", 2.5, 1.1);

		pageServiceLocal.saveOrUpdatePage(offre2, "b38f3299-6949-42c7-9a6c-f998c66f485d");

		BuyId buyId1 = new BuyId(2, "b38f3299-6949-42c7-9a6c-f998c66f485d");

		BuyId buyId2 = new BuyId(3, "b38f3299-6949-42c7-9a6c-f998c66f485d");

		BuyId buyId3 = new BuyId(3, "b22");
		//
		Buy buy1 = new Buy(buyId1);
		Buy buy2 = new Buy(buyId2);
		Buy buy3 = new Buy(buyId3);
		offerServiceLocal.addBuy(buy1);
		offerServiceLocal.addBuy(buy2);
		offerServiceLocal.addBuy(buy3);

		// Dhafer
	}

}
