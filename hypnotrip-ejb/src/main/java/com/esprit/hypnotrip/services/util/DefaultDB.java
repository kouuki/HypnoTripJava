package com.esprit.hypnotrip.services.util;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import com.esprit.hypnotrip.persistence.Event;
import com.esprit.hypnotrip.persistence.FollowsId;
import com.esprit.hypnotrip.persistence.Offer;
import com.esprit.hypnotrip.persistence.Pages;
import com.esprit.hypnotrip.persistence.Touristicplace;
import com.esprit.hypnotrip.persistence.User;
import com.esprit.hypnotrip.services.exceptions.EventOverException;
import com.esprit.hypnotrip.services.exceptions.TicketAlreadyBookedException;
import com.esprit.hypnotrip.services.interfaces.FollowersServicesLocal;
import com.esprit.hypnotrip.services.interfaces.OfferServiceLocal;
import com.esprit.hypnotrip.services.interfaces.PageServiceLocal;
import com.esprit.hypnotrip.services.interfaces.TicketServicesLocal;
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
	@EJB
	TicketServicesLocal ticketServicesLocal;
	@EJB
	private FollowersServicesLocal followersServicesLocal;

	public DefaultDB() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void init() throws EventOverException, TicketAlreadyBookedException, ParseException {
		// ********************************************************************************************************************
		// ********************************************************************************************************************
		// ********************************************************************************************************************

		Date actuelle = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(actuelle);
		c.add(Calendar.DATE, 1);
		actuelle = c.getTime();

		System.out.println(actuelle);
		Pages offre = new Offer("My first offer", "1er offre", 20, 10, actuelle);
		offre.setCategoriePage("offer");
		offre.setImageURL(
				"http://imalbum.aufeminin.com/album/D20120821/874136_8U6IQUUQOIMFXZLJOO4CCYL22D7B6I_petit-dejeuner_H125650_L.jpg");
		pageServiceLocal.saveOrUpdatePage(offre, "b38f3299-6949-42c7-9a6c-f998c66f485d");
		Pages offre2 = new Offer("My second offer", "2eme offre", 1000, 15, actuelle);
		offre2.setImageURL("http://je-voyage.net/wp-content/uploads/2014/11/thailande.jpg");
		offre2.setCategoriePage("offer");
		pageServiceLocal.saveOrUpdatePage(offre2, "b38f3299-6949-42c7-9a6c-f998c66f485d");

		Pages offre3 = new Offer("My third offer", "3eme offre", 40, 15, actuelle);
		offre3.setImageURL("https://i.ytimg.com/vi/r2g4vQR_1i8/maxresdefault.jpg");
		offre3.setCategoriePage("offer");
		pageServiceLocal.saveOrUpdatePage(offre3, "b38f3299-6949-42c7-9a6c-f998c66f485d");
		// ********************************************************************************************************************
		FollowsId followsId = new FollowsId();
		followsId.setDateFollow(new Date());
		followsId.setPageId(1);
		followsId.setUserId("b38f3299-6949-42c7-9a6c-f998c66f4fde");
		followersServicesLocal.SaveOrUpdate(followsId, true, true);
		FollowsId followsId1 = new FollowsId();
		followsId1.setDateFollow(new Date());
		followsId1.setPageId(2);
		followsId1.setUserId("b38f3299-6949-42c7-9a6c-f998c66f4fde");
		followersServicesLocal.SaveOrUpdate(followsId1, true, true);
		// ********************************************************************************************************************
		// ********************************************************************************************************************
		Pages ts1 = new Touristicplace("First Touristic page", "1ere page", new Date(), "logo1", "Paris");
		ts1.setImageURL(
				"http://www.telegraph.co.uk/content/dam/Travel/Destinations/Europe/France/Paris/paris-attractions-xlarge.jpg");
		ts1.setCategoriePage("sport");
		pageServiceLocal.saveOrUpdatePage(ts1, "b38f3299-6949-42c7-9a6c-f998c66f4853");

		Pages ts2 = new Touristicplace("Second Touristic page", "2eme page", new Date(), "logo2", "America");
		ts2.setImageURL("http://www.horizon-virtuel.com/amerique/new-york/statue-liberty.jpg");
		ts2.setCategoriePage("nature");
		pageServiceLocal.saveOrUpdatePage(ts2, "b38f3299-6949-42c7-9a6c-f998c66f485d");

		Pages ts3 = new Touristicplace("Third Touristic page", "3eme page", new Date(), "logo2", "London");
		ts3.setImageURL("http://cms.inspirato.com/media/5682444/London_Dest_16531610X.jpg");
		ts3.setCategoriePage("nature");
		pageServiceLocal.saveOrUpdatePage(ts3, "b38f3299-6949-42c7-9a6c-f998c66f4853");
		// ********************************************************************************************************************
		// ********************************************************************************************************************

		// ********************************************************************************************************************
		// ********************************************************************************************************************
		// ********************************************************************************************************************
		User user = new User();
		user.setAddress("tunis");
		user.setAccessFailedCount(0);
		user.setDateOfBirth(new Date());
		user.setEmail("dhafer@gmail.com");
		user.setEmailConfirmed(false);
		user.setEtat(1);
		user.setFirstName("dhafer");
		user.setSecondName("daoues");
		user.setPassword("123456789Azerty");
		user.setPasswordHash("123456789Azerty");
		user.setLogin("daouesd");
		user.setUserName("daouesd");
		user.setRole("0");
		user.setId("b38f3299-6949-42c7-9a6c-f998c66f485d");

		User user4 = new User();
		user4.setAddress("tunis");
		user4.setAccessFailedCount(0);
		user4.setDateOfBirth(new Date());
		user4.setEmail("Amine@gmail.com");
		user4.setEmailConfirmed(false);
		user4.setEtat(1);
		user4.setFirstName("Amin");
		user4.setSecondName("Ghorbel");
		user4.setPassword("123456789Azerty");
		user4.setPasswordHash("123456789Azerty");
		user4.setLogin("Amin");
		user4.setUserName("ghorbel");
		user4.setRole("1");
		user4.setId("c25c4e23-1275-45d8-b327-c6fb06d9455");

		User user2 = new User();
		user2.setAddress("tunis");
		user2.setAccessFailedCount(0);
		user2.setDateOfBirth(new Date());
		user2.setEmail("jihene@gmail.com");
		user2.setEmailConfirmed(false);
		user2.setEtat(1);
		user2.setFirstName("Jihene");
		user2.setSecondName("Ben Gharbia");
		user2.setPassword("123456789Azerty");
		user2.setPasswordHash("123456789Azerty");
		user2.setLogin("daouesd");
		user2.setUserName("daouesd");
		user2.setRole("1");
		user2.setId("b38f3299-6949-42c7-9a6c-f998c66f4852");

		User user3 = new User();
		user3.setAddress("tunis");
		user3.setAccessFailedCount(0);
		user3.setDateOfBirth(new Date());
		user3.setEmail("Yasmine@gmail.com");
		user3.setEmailConfirmed(false);
		user3.setEtat(1);
		user3.setFirstName("Yasmine");
		user3.setSecondName("Torkhani");
		user3.setPassword("123456789Azerty");
		user3.setPasswordHash("123456789Azerty");
		user3.setLogin("Yasmine");
		user3.setUserName("Yasmine");
		user3.setRole("1");
		user3.setId("b38f3299-6949-42c7-9a6c-f998c66f4853");

		userServicesLocal.saveOrUpdate(user);
		userServicesLocal.saveOrUpdate(user2);
		userServicesLocal.saveOrUpdate(user3);
		userServicesLocal.saveOrUpdate(user4);

		// ********************************************************************************************************************
		// ********************************************************************************************************************
		// ********************************************************************************************************************
		Pages event = new Event("MyFirst Event", "1er evenement", actuelle, "Tunis");
		event.setCategoriePage("event");
		event.setImageURL(
				"http://www.ville-senlis.fr/var/www/storage/images/mediatheque/site-de-developpement/images/tests/evenements/1509-1-fre-FR/Evenements.jpg");
		pageServiceLocal.saveOrUpdatePage(event, "b38f3299-6949-42c7-9a6c-f998c66f485d");

		Pages event2 = new Event("MySecond Event", "2éme evenement", new Date(), "Bardo");
		event2.setCategoriePage("event");
		event2.setImageURL("http://productionsalterego.com/media/decorsdesalle.jpg");
		pageServiceLocal.saveOrUpdatePage(event2, "b38f3299-6949-42c7-9a6c-f998c66f485d");

		Pages event3 = new Event("My third Event", "3éme evenement", actuelle, "Hammamet");
		event3.setCategoriePage("event");
		event3.setImageURL("http://www.birthday-party-ideas-101.com/images/BeachParty1.jpg");
		pageServiceLocal.saveOrUpdatePage(event3, "b38f3299-6949-42c7-9a6c-f998c66f485d");

		// ********************************************************************************************************************
		// ********************************************************************************************************************
		// ********************************************************************************************************************

	}

}
