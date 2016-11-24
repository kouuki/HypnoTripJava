package com.esprit.hypnotrip.services.interfaces;

import javax.ejb.Remote;

import com.esprit.hypnotrip.persistence.Posts;

@Remote
public interface PostServicesRemote {

	void SaveOrUpdatePost(Posts post);
}
