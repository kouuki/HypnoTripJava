package com.esprit.hypnotrip.services.util;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import com.esprit.hypnotrip.persistence.Offer;
import com.esprit.hypnotrip.persistence.Pages;
import com.esprit.hypnotrip.services.interfaces.PageServiceLocal;

@Singleton
@LocalBean
@Startup

public class DefaultDB {
	@EJB
	private PageServiceLocal pageServiceLocal;

	public DefaultDB() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void init() {

		Pages pages = new Pages("My first page", "b38f3299-6949-42c7-9a6c-f998c66f485d");

		pageServiceLocal.saveOrUpdatePage(pages);

		Pages offre = new Offer("My first offer", "b38f3299-6949-42c7-9a6c-f998c66f485d");
  
		pageServiceLocal.saveOrUpdatePage(offre);
	}

}
