package com.esprit.hypnotrip.services.interfaces;

import java.util.List;

import javax.ejb.Local;

import com.esprit.hypnotrip.persistence.Follows;

@Local
public interface FollowersServicesLocal {

	void SaveOrUpdate(String idUser, Integer idPage, boolean FollowStat);

	List<Follows> findAllFollowByUserId(String idUser);

}
