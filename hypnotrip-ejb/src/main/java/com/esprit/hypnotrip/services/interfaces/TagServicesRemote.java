package com.esprit.hypnotrip.services.interfaces;

import javax.ejb.Remote;

@Remote
public interface TagServicesRemote {
	
	void SaveOrUpdateTage(String nameTag);

}
