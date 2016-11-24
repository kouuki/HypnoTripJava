package com.esprit.hypnotrip.services.interfaces;

import javax.ejb.Local;

import com.esprit.hypnotrip.persistence.Posts;

@Local
public interface PostServicesLocal {

	void SaveOrUpdatePost(Posts post);
}
