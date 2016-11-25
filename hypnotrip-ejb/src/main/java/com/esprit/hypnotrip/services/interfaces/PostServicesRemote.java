package com.esprit.hypnotrip.services.interfaces;

import java.util.List;

import javax.ejb.Remote;

import com.esprit.hypnotrip.persistence.Posts;

@Remote
public interface PostServicesRemote {

	void SaveOrUpdatePost(Posts post);

	List<Posts> findAllPostByUser(String idUser);

	Integer CountNbrTagByUser(String idUser, Integer idTag);

	List<Posts> listMyPost(String idUser);
}
