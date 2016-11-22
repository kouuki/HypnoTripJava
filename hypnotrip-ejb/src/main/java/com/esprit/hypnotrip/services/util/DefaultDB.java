package com.esprit.hypnotrip.services.util;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import com.esprit.hypnotrip.persistence.Buy;
import com.esprit.hypnotrip.persistence.BuyId;
import com.esprit.hypnotrip.persistence.Offer;
import com.esprit.hypnotrip.persistence.Pages;
import com.esprit.hypnotrip.persistence.Touristicplace;
import com.esprit.hypnotrip.persistence.User;
import com.esprit.hypnotrip.services.interfaces.OfferServiceLocal;
import com.esprit.hypnotrip.services.interfaces.PageServiceLocal;
import com.esprit.hypnotrip.services.interfaces.ToursiticPlaceServiceLocal;
import com.esprit.hypnotrip.services.interfaces.UserServicesLocal;

@Singleton
@LocalBean
@Startup

public class DefaultDB {
	@EJB
	private PageServiceLocal pageServiceLocal;
	@EJB
	private UserServicesLocal userServicesLocal;
	@EJB
	private OfferServiceLocal offerServiceLocal;
	@EJB
	ToursiticPlaceServiceLocal toursiticPlaceServiceLocal;

	public DefaultDB() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void init() {

		Pages pages = new Pages("My first page");

		pageServiceLocal.saveOrUpdatePage(pages, "b38f3299-6949-42c7-9a6c-f998c66f485d");

		Pages offre = new Offer("My first offer", 2.5, 1.1);

		pageServiceLocal.saveOrUpdatePage(offre, "b38f3299-6949-42c7-9a6c-f998c66f485d");
		
		Pages ts1 = new Touristicplace(new Date(),"logo1","Paris");
		pageServiceLocal.saveOrUpdatePage(ts1, "b38f3299-6949-42c7-9a6c-f998c66f485d");
		
		Pages ts2 = new Touristicplace(new Date(),"logo2","Tunis");
		pageServiceLocal.saveOrUpdatePage(ts2, "b38f3299-6949-42c7-9a6c-f998c66f485d");
		
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

		
		
		User user = new User();
		user.setAddress("tunis");
		user.setAccessFailedCount(0);
		user.setDateOfBirth(new Date());
		user.setEmail("daouesd@gmail.com");
		user.setEmailConfirmed(false);
		user.setEtat(1);
		user.setFirstName("dhafer");
		user.setSecondName("daoues");
		user.setPassword("123456789Azerty");
		user.setPasswordHash("123456789Azerty");
		user.setLogin("daouesd");
		user.setUserName("daouesd");
		user.setId("b38f3299-6949-42c7-9a6c-f998c66f485d");
		
		User user2 = new User();
		user2.setAddress("tunis");
		user2.setAccessFailedCount(0);
		user2.setDateOfBirth(new Date());
		user2.setEmail("daouesd@gmail.com");
		user2.setEmailConfirmed(false);
		user2.setEtat(1);
		user2.setFirstName("dhafer");
		user2.setSecondName("daoues");
		user2.setPassword("123456789Azerty");
		user2.setPasswordHash("123456789Azerty");
		user2.setLogin("daouesd");
		user2.setUserName("daouesd");
		user2.setId("b38f3299-6949-42c7-9a6c-f998c66f4852");
		
		
		userServicesLocal.saveOrUpdate(user);
		userServicesLocal.saveOrUpdate(user2);

	}

}
