package com.esprit.hypnotrip.services.util;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;

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
	}

}
