package com.esprit.hypnotrip.services.interfaces;

import java.util.List;

import javax.ejb.Remote;

import com.esprit.hypnotrip.persistence.Follows;

@Remote
public interface FollowersServicesRemote {

	void SaveOrUpdate(String idUser, Integer idPage, boolean followStat);

	List<Follows> findAllFollowByUserId(String idUser);

}
