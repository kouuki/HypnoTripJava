package com.esprit.hypnotrip.services.interfaces;

import java.util.List;

import javax.ejb.Remote;

import com.esprit.hypnotrip.persistence.Event;
import com.esprit.hypnotrip.persistence.Follows;
import com.esprit.hypnotrip.persistence.FollowsId;
import com.esprit.hypnotrip.persistence.Pages;
import com.esprit.hypnotrip.persistence.Posts;

@Remote
public interface FollowersServicesRemote {

	void SaveOrUpdate(FollowsId followsId, boolean followStat, boolean wishStat);

	List<Follows> findAllFollowByUserId(String idUser);

	Integer nbrFollowers(Integer idPages);

	Integer nbrPostsByUser(Integer idPages, String idUser);

	List<Pages> ListAllPages();

	List<Posts> findListOfTagsOrdredByUsing(String idUser);

	List<Pages> ListAllPagesByTags(List<Posts> posts);;

	boolean MyEventForToDay(String idUser);

	List<Follows> ListMyEventForToDay(String idUser);

	List<String> findNbrFollowByWS();

	List<String> findNbrWishByWS();

	List<Integer> listIdPagesInFollow();

	String TitlePageById(Integer idPage);
	
	Integer nbrWish(Integer idPages);
	
	Follows GetLastFollowForPageByIdUser(Integer idPage, String userId);


}
