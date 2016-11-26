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
		// Touristicplace
		Touristicplace page1 = new Touristicplace();
		page1.setCategoriePage("sport");
		page1.setName("Paris");
		page1.setDescription("sport");
		page1.setTitle("page pour le sport");
		page1.setUserId("b38f3299-6949-42c7-9a6c-f998c66f485d");
		page1.setImageURL(
				"https://travel.jumia.com/blog/ng/wp-content/uploads/2016/01/bigstock-Four-Sports-a-lot-of-balls-an-50626115.jpg");
		pageServiceLocal.saveOrUpdatePage(page1, "b38f3299-6949-42c7-9a6c-f998c66f485d");
		Touristicplace page2 = new Touristicplace();
		page2.setCategoriePage("nature");
		page2.setName("London");
		page2.setDescription("nature");
		page2.setTitle("page pour le nature");
		page2.setUserId("b38f3299-6949-42c7-9a6c-f998c66f485d");
		page2.setImageURL("http://winsdesktop.com/wp-content/uploads/2016/09/Cool-Nature-Wallpaper-HD-1024x576.jpg");
		pageServiceLocal.saveOrUpdatePage(page2, "b38f3299-6949-42c7-9a6c-f998c66f485d");
		Touristicplace page3 = new Touristicplace();
		page3.setName("hawaria");
		page3.setCategoriePage("plage");
		page3.setDescription("plage");
		page3.setTitle("page pour le plage");
		page3.setUserId("b38f3299-6949-42c7-9a6c-f998c66f485d");
		page3.setImageURL("https://www.voyageavecnous.fr/wp-content/uploads/2015/01/plage-paradisiaque-maldives.jpg");
		pageServiceLocal.saveOrUpdatePage(page3, "b38f3299-6949-42c7-9a6c-f998c66f485d");
		Touristicplace page4 = new Touristicplace();
		page4.setName("Berneville");
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
		post1.setImageURL("http://3.bp.blogspot.com/-OXiG-KhAUXE/VTiudbE6rEI/AAAAAAAA9WI/L7ycYEmoO0U/s1600/geant.jpg");
		postServicesLocal.SaveOrUpdatePost(post1);
		Posts post2 = new Posts();
		post2.setContent("post qui concerne le nature");
		post2.setDescription("nature");
		post2.setTagId(2);
		post2.setIdOwner("b38f3299-6949-42c7-9a6c-f998c66f4852");
		post2.setPageId(8);
		post2.setPublicationPost(new Date());
		post2.setImageURL("http://www.wallpaperup.com/uploads/wallpapers/2013/03/23/58941/big_thumb_e2df06bf05a11d7bb66a348828845ffe.jpg");
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
		post3.setImageURL("http://www.krasnapolsky.sr/files/thumb/p/o/aanbiedingen_auto_auto_w9df_h9df_c431_c200_Poolpass_fb_post_HK_Eng_versie_22aug16_def.png");

		postServicesLocal.SaveOrUpdatePost(post3);
		postServicesLocal.SaveOrUpdatePost(post3);
		Posts post4 = new Posts();
		post4.setContent("post qui concerne le compagne");
		post4.setDescription("compagne");
		post4.setTagId(4);
		post4.setIdOwner("b38f3299-6949-42c7-9a6c-f998c66f4852");
		post4.setPageId(10);
		post4.setPublicationPost(new Date());
		post4.setImageURL("http://www.locatour.com/html_locatour/magazine/photos/destination-campagne-a-9796.jpg");
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

		// follow
		String IdUser = "b38f3299-6949-42c7-9a6c-f998c66f4852";
		FollowsId followsId = new FollowsId();
		followsId.setDateFollow(new Date());
		followsId.setPageId(17);
		followsId.setUserId(IdUser);

		followersServicesLocal.SaveOrUpdate(followsId, true, true);

	}

}
