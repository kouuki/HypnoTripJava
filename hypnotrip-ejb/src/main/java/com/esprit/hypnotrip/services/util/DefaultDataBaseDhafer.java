package com.esprit.hypnotrip.services.util;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import com.esprit.hypnotrip.persistence.Event;
import com.esprit.hypnotrip.persistence.FollowsId;
import com.esprit.hypnotrip.persistence.Posts;
import com.esprit.hypnotrip.persistence.Touristicplace;
import com.esprit.hypnotrip.persistence.User;
import com.esprit.hypnotrip.services.exceptions.EventOverException;
import com.esprit.hypnotrip.services.exceptions.TicketAlreadyBookedException;
import com.esprit.hypnotrip.services.interfaces.FollowersServicesLocal;
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
	@EJB
	private FollowersServicesLocal followersServicesLocal;

	@PostConstruct
	public void init() throws EventOverException, TicketAlreadyBookedException {

		// Simple User 1
		User user1 = new User();
		user1.setAddress("tunis");
		user1.setAccessFailedCount(0);
		user1.setDateOfBirth(new Date());
		user1.setEmail("user@simple.com");
		user1.setEmailConfirmed(false);
		user1.setEtat(1);
		user1.setFirstName("dhafer");
		user1.setSecondName("daoues");
		user1.setPassword("123456789Azerty");
		user1.setPasswordHash("123456789Azerty");
		user1.setLogin("daouesd");
		user1.setUserName("user@simple.com");
		user1.setRole("1");
		user1.setId("b38f3299-6949-42c7-9a6c-f998c66f4fde");
		userServicesLocal.saveOrUpdate(user1);

		// Simple User 2
		User user2 = new User();
		user2.setAddress("tunis");
		user2.setAccessFailedCount(0);
		user2.setDateOfBirth(new Date());
		user2.setEmail("user2@simple.com");
		user2.setEmailConfirmed(false);
		user2.setEtat(1);
		user2.setFirstName("dhafer");
		user2.setSecondName("daoues");
		user2.setPassword("123456789Azerty");
		user2.setPasswordHash("123456789Azerty");
		user2.setLogin("daouesd");
		user2.setUserName("user2@simple.com");
		user2.setRole("1");
		user2.setId("b38f3299-6949-42c7-9a6c-f998c66f499f");
		userServicesLocal.saveOrUpdate(user2);

		// Simple User 3
		User user3 = new User();
		user3.setAddress("tunis");
		user3.setAccessFailedCount(0);
		user3.setDateOfBirth(new Date());
		user3.setEmail("user3@simple.com");
		user3.setEmailConfirmed(false);
		user3.setEtat(1);
		user3.setFirstName("dhafer");
		user3.setSecondName("daoues");
		user3.setPassword("123456789Azerty");
		user3.setPasswordHash("123456789Azerty");
		user3.setLogin("daouesd");
		user3.setUserName("user3@simple.com");
		user3.setRole("1");
		user3.setId("b38f3299-6949-42c7-9a6c-f998c66a258f");
		userServicesLocal.saveOrUpdate(user3);

		// Simple User 4
		User user4 = new User();
		user4.setAddress("tunis");
		user4.setAccessFailedCount(0);
		user4.setDateOfBirth(new Date());
		user4.setEmail("user4@simple.com");
		user4.setEmailConfirmed(false);
		user4.setEtat(1);
		user4.setFirstName("dhafer");
		user4.setSecondName("daoues");
		user4.setPassword("123456789Azerty");
		user4.setPasswordHash("123456789Azerty");
		user4.setLogin("daouesd");
		user4.setUserName("user4@simple.com");
		user4.setRole("1");
		user4.setId("b38f3299-6949-42c7-9a6c-f998c666658f");
		userServicesLocal.saveOrUpdate(user4);

		// Admin
		User user = new User();
		user.setAddress("tunis");
		user.setAccessFailedCount(0);
		user.setDateOfBirth(new Date());
		user.setEmail("karim@admin.com");
		user.setEmailConfirmed(false);
		user.setEtat(1);
		user.setFirstName("karim");
		user.setSecondName("ben romdhane");
		user.setPassword("123456789Azerty");
		user.setPasswordHash("123456789Azerty");
		user.setLogin("karim");
		user.setUserName("karim");
		user.setRole("2");
		user.setId("b38f3299-6949-42c7-9a6c-f998c66f485f");
		userServicesLocal.saveOrUpdate(user);
		// Tags
		tagServicesLocal.SaveOrUpdateTage("sport");
		tagServicesLocal.SaveOrUpdateTage("nature");
		tagServicesLocal.SaveOrUpdateTage("plage");
		tagServicesLocal.SaveOrUpdateTage("compagne");

		// Touristicplace
		Touristicplace page1 = new Touristicplace();
		page1.setCategoriePage("sport");
		page1.setName("Paris");
		page1.setDateOfCreation(new Date());
		page1.setDescription("sport");
		page1.setTitle("page pour le sport");
		page1.setUserId("b38f3299-6949-42c7-9a6c-f998c66f485d");
		page1.setImageURL(
				"https://travel.jumia.com/blog/ng/wp-content/uploads/2016/01/bigstock-Four-Sports-a-lot-of-balls-an-50626115.jpg");
		pageServiceLocal.saveOrUpdatePage(page1, "b38f3299-6949-42c7-9a6c-f998c66f485d");

		Touristicplace page2 = new Touristicplace();
		page2.setCategoriePage("nature");
		page2.setDateOfCreation(new Date());
		page2.setName("London");
		page2.setDescription("nature");
		page2.setTitle("page pour le nature");
		page2.setUserId("b38f3299-6949-42c7-9a6c-f998c66f485d");
		page2.setImageURL("http://winsdesktop.com/wp-content/uploads/2016/09/Cool-Nature-Wallpaper-HD-1024x576.jpg");
		pageServiceLocal.saveOrUpdatePage(page2, "b38f3299-6949-42c7-9a6c-f998c66f485d");

		Touristicplace page3 = new Touristicplace();
		page3.setName("Maroc");
		page3.setDateOfCreation(new Date());
		page3.setCategoriePage("plage");
		page3.setDescription("plage");
		page3.setTitle("page pour le plage");
		page3.setUserId("b38f3299-6949-42c7-9a6c-f998c66f485d");
		page3.setImageURL("https://www.voyageavecnous.fr/wp-content/uploads/2015/01/plage-paradisiaque-maldives.jpg");
		pageServiceLocal.saveOrUpdatePage(page3, "b38f3299-6949-42c7-9a6c-f998c66f4855");

		Touristicplace page4 = new Touristicplace();
		page4.setName("Berneville");
		page4.setDateOfCreation(new Date());
		page4.setCategoriePage("compagne");
		page4.setDescription("compagne");
		page4.setTitle("page pour le compagne");
		page4.setUserId("b38f3299-6949-42c7-9a6c-f998c66f485d");
		page4.setImageURL("http://img1.gtsstatic.com/wallpapers/27023ecf75d7373f40d69bf71c270c39_large.jpeg");
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

		// page 6 2Users
		FollowsId followsId1 = new FollowsId();
		followsId1.setDateFollow(new Date());
		followsId1.setPageId(6);
		followsId1.setUserId(user1.getId());
		followersServicesLocal.SaveOrUpdate(followsId1, true, true);
		FollowsId followsId2 = new FollowsId();
		followsId2.setDateFollow(new Date());
		followsId2.setPageId(6);
		followsId2.setUserId(user2.getId());
		followersServicesLocal.SaveOrUpdate(followsId2, true, true);
		// page 8 1user
		FollowsId followsId3 = new FollowsId();
		followsId3.setDateFollow(new Date());
		followsId3.setPageId(8);
		followsId3.setUserId(user1.getId());
		followersServicesLocal.SaveOrUpdate(followsId3, true, true);
		// page 9 4Users
		FollowsId followsId4 = new FollowsId();
		followsId4.setDateFollow(new Date());
		followsId4.setPageId(9);
		followsId4.setUserId(user1.getId());
		followersServicesLocal.SaveOrUpdate(followsId4, true, true);
		FollowsId followsId5 = new FollowsId();
		followsId5.setDateFollow(new Date());
		followsId5.setPageId(9);
		followsId5.setUserId(user2.getId());
		followersServicesLocal.SaveOrUpdate(followsId5, true, true);
		FollowsId followsId6 = new FollowsId();
		followsId6.setDateFollow(new Date());
		followsId6.setPageId(9);
		followsId6.setUserId(user3.getId());
		followersServicesLocal.SaveOrUpdate(followsId6, true, true);
		FollowsId followsId7 = new FollowsId();
		followsId7.setDateFollow(new Date());
		followsId7.setPageId(9);
		followsId7.setUserId(user4.getId());
		followersServicesLocal.SaveOrUpdate(followsId7, true, true);
		// page 10 3Users
		FollowsId followsId8 = new FollowsId();
		followsId8.setDateFollow(new Date());
		followsId8.setPageId(10);
		followsId8.setUserId(user1.getId());
		followersServicesLocal.SaveOrUpdate(followsId8, true, true);
		FollowsId followsId9 = new FollowsId();
		followsId9.setDateFollow(new Date());
		followsId9.setPageId(10);
		followsId9.setUserId(user2.getId());
		followersServicesLocal.SaveOrUpdate(followsId9, true, true);
		FollowsId followsId10 = new FollowsId();
		followsId10.setDateFollow(new Date());
		followsId10.setPageId(10);
		followsId10.setUserId(user3.getId());
		followersServicesLocal.SaveOrUpdate(followsId10, true, true);

		// follow

	}

}
