package com.esprit.hypnotrip.services.util;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import com.esprit.hypnotrip.persistence.Event;
import com.esprit.hypnotrip.persistence.Pages;
import com.esprit.hypnotrip.persistence.Posts;
import com.esprit.hypnotrip.persistence.User;
import com.esprit.hypnotrip.services.exceptions.EventOverException;
import com.esprit.hypnotrip.services.exceptions.TicketAlreadyBookedException;
import com.esprit.hypnotrip.services.interfaces.PageServiceLocal;
import com.esprit.hypnotrip.services.interfaces.PostServicesLocal;
import com.esprit.hypnotrip.services.interfaces.TagServicesLocal;
import com.esprit.hypnotrip.services.interfaces.UserServicesLocal;

@Singleton
@LocalBean
@Startup
public class DefaultDataBaseDhafer {

	public DefaultDataBaseDhafer() {
	}

	@EJB
	private PageServiceLocal pageServiceLocal;
	@EJB
	private UserServicesLocal userServicesLocal;
	@EJB
	private TagServicesLocal tagServicesLocal;

	@EJB
	private PostServicesLocal postServicesLocal;

	@PostConstruct
	public void init() throws EventOverException, TicketAlreadyBookedException {
		// Admin
		User user = new User();
		user.setAddress("tunis");
		user.setAccessFailedCount(0);
		user.setDateOfBirth(new Date());
		user.setEmail("daouesd@admin.com");
		user.setEmailConfirmed(false);
		user.setEtat(1);
		user.setFirstName("dhafer");
		user.setSecondName("daoues");
		user.setPassword("123456789Azerty");
		user.setPasswordHash("123456789Azerty");
		user.setLogin("daouesd");
		user.setUserName("daouesd");
		user.setRole("2");
		user.setId("b38f3299-6949-42c7-9a6c-f998c66f485f");
		userServicesLocal.saveOrUpdate(user);
		// Tags
		tagServicesLocal.SaveOrUpdateTage("sport");
		tagServicesLocal.SaveOrUpdateTage("nature");
		tagServicesLocal.SaveOrUpdateTage("plage");
		tagServicesLocal.SaveOrUpdateTage("compagne");
		// Pages
		Pages page1 = new Pages();
		page1.setCategoriePage("sport");
		page1.setDescription("sport");
		page1.setTitle("page pour le sport");
		page1.setUserId("b38f3299-6949-42c7-9a6c-f998c66f485d");
		pageServiceLocal.saveOrUpdatePage(page1, "b38f3299-6949-42c7-9a6c-f998c66f485d");
		Pages page2 = new Pages();
		page2.setCategoriePage("nature");
		page2.setDescription("nature");
		page2.setTitle("page pour le nature");
		page2.setUserId("b38f3299-6949-42c7-9a6c-f998c66f485d");
		pageServiceLocal.saveOrUpdatePage(page2, "b38f3299-6949-42c7-9a6c-f998c66f485d");
		Pages page3 = new Pages();
		page3.setCategoriePage("plage");
		page3.setDescription("plage");
		page3.setTitle("page pour le plage");
		page3.setUserId("b38f3299-6949-42c7-9a6c-f998c66f485d");
		pageServiceLocal.saveOrUpdatePage(page3, "b38f3299-6949-42c7-9a6c-f998c66f485d");
		Pages page4 = new Pages();
		page4.setCategoriePage("compagne");
		page4.setDescription("compagne");
		page4.setTitle("page pour le compagne");
		page4.setUserId("b38f3299-6949-42c7-9a6c-f998c66f485d");
		pageServiceLocal.saveOrUpdatePage(page4, "b38f3299-6949-42c7-9a6c-f998c66f485d");

		// Posts
		Posts post1 = new Posts();
		post1.setContent("post qui concerne le sport");
		post1.setDescription("Sport");
		post1.setTagId(1);
		post1.setIdOwner("b38f3299-6949-42c7-9a6c-f998c66f4852");
		post1.setPageId(7);
		post1.setPublicationPost(new Date());
		postServicesLocal.SaveOrUpdatePost(post1);
		Posts post2 = new Posts();
		post2.setContent("post qui concerne le nature");
		post2.setDescription("nature");
		post2.setTagId(2);
		post2.setIdOwner("b38f3299-6949-42c7-9a6c-f998c66f4852");
		post2.setPageId(8);
		post2.setPublicationPost(new Date());
		postServicesLocal.SaveOrUpdatePost(post2);
		postServicesLocal.SaveOrUpdatePost(post2);
		postServicesLocal.SaveOrUpdatePost(post2);
		Posts post3 = new Posts();
		post3.setContent("post qui concerne le plage");
		post3.setDescription("plage");
		post3.setTagId(3);
		post3.setIdOwner("b38f3299-6949-42c7-9a6c-f998c66f4852");
		post3.setPageId(9);
		post3.setPublicationPost(new Date());
		postServicesLocal.SaveOrUpdatePost(post3);
		postServicesLocal.SaveOrUpdatePost(post3);
		Posts post4 = new Posts();
		post4.setContent("post qui concerne le compagne");
		post4.setDescription("compagne");
		post4.setTagId(4);
		post4.setIdOwner("b38f3299-6949-42c7-9a6c-f998c66f4852");
		post4.setPageId(10);
		post4.setPublicationPost(new Date());
		postServicesLocal.SaveOrUpdatePost(post4);
		postServicesLocal.SaveOrUpdatePost(post4);
		postServicesLocal.SaveOrUpdatePost(post4);
		postServicesLocal.SaveOrUpdatePost(post4);

		// Event

		Event envent = new Event();
		envent.setCategoriePage("sport");
		envent.setDescription("sport");
		envent.setDateOfEvent(new Date());
		envent.setTitle("page pour le sport");
		envent.setUserId("b38f3299-6949-42c7-9a6c-f998c66f485d");
		pageServiceLocal.saveOrUpdatePage(envent, "b38f3299-6949-42c7-9a6c-f998c66f485d");
		Event envent1 = new Event();
		envent1.setCategoriePage("nature");
		envent1.setDescription("nature");
		envent1.setDateOfEvent(new Date());
		envent1.setTitle("page pour le nature");
		envent1.setUserId("b38f3299-6949-42c7-9a6c-f998c66f485d");
		pageServiceLocal.saveOrUpdatePage(envent1, "b38f3299-6949-42c7-9a6c-f998c66f485d");
		Event envent2 = new Event();
		envent2.setCategoriePage("plage");
		envent2.setDateOfEvent(new Date());
		envent2.setDescription("plage");
		envent2.setTitle("page pour le plage");
		envent2.setUserId("b38f3299-6949-42c7-9a6c-f998c66f485d");
		pageServiceLocal.saveOrUpdatePage(envent2, "b38f3299-6949-42c7-9a6c-f998c66f485d");
		Event envent3 = new Event();
		envent3.setDateOfEvent(new Date());
		envent3.setCategoriePage("compagne");
		envent3.setDescription("compagne");
		envent3.setTitle("page pour le compagne");
		envent3.setUserId("b38f3299-6949-42c7-9a6c-f998c66f485d");
		pageServiceLocal.saveOrUpdatePage(envent3, "b38f3299-6949-42c7-9a6c-f998c66f485d");

	}

}
