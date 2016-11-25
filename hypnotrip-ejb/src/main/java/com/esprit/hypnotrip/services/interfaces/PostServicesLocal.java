package com.esprit.hypnotrip.services.interfaces;

import java.util.List;

import javax.ejb.Local;

import com.esprit.hypnotrip.persistence.Posts;

@Local
public interface PostServicesLocal {

	void SaveOrUpdatePost(Posts post);

	List<Posts> findAllPostByUser(String idUser);

	Integer CountNbrTagByUser(String idUser, Integer idTag);

}
