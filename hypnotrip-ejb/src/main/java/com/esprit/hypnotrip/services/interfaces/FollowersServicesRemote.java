package com.esprit.hypnotrip.services.interfaces;

import java.util.List;

import javax.ejb.Remote;

import com.esprit.hypnotrip.persistence.Follows;
import com.esprit.hypnotrip.persistence.FollowsId;
import com.esprit.hypnotrip.persistence.Pages;

@Remote
public interface FollowersServicesRemote {

	void SaveOrUpdate(FollowsId followsId, boolean followStat, boolean wishStat);

	List<Follows> findAllFollowByUserId(String idUser);

	Integer nbrFollowers(Integer idPages);
	
	Integer nbrPostsByUser(Integer idPages,String idUser);
	
	List<Pages> ListAllPages();

}
