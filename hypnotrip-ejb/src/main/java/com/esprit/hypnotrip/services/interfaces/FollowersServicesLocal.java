package com.esprit.hypnotrip.services.interfaces;

import java.util.List;

import javax.ejb.Local;

import com.esprit.hypnotrip.persistence.Follows;
import com.esprit.hypnotrip.persistence.FollowsId;
import com.esprit.hypnotrip.persistence.Pages;

@Local
public interface FollowersServicesLocal {

	void SaveOrUpdate(FollowsId followsId, boolean followStat, boolean wishStat);

	List<Follows> findAllFollowByUserId(String idUser);

	Integer nbrFollowers(Integer idPages);

	Integer nbrPostsByUser(Integer idPages, String idUser);

	List<Pages> ListAllPages();

	Integer MostUsedTag(String idUser);

	List<Pages> ListAllPagesByTags(Integer IdTag);

}
