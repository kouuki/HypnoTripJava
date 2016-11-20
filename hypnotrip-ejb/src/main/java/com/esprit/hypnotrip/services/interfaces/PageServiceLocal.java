package com.esprit.hypnotrip.services.interfaces;

import java.util.List;

import javax.ejb.Local;

import com.esprit.hypnotrip.persistence.Pages;

@Local
public interface PageServiceLocal {
	void saveOrUpdatePage(Pages page,String idOwner);

	void deletePage(Pages page);

	List<Pages> ListMyPages(String idOwner);
}
