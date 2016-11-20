package com.esprit.hypnotrip.services.interfaces;

import java.util.List;

import javax.ejb.Remote;

import com.esprit.hypnotrip.persistence.Pages;

@Remote
public interface PageServiceRemote {
	void saveOrUpdatePage(Pages page);

	void deletePage(Pages page);

	List<Pages> ListMyPages(Integer idOwner);
}
