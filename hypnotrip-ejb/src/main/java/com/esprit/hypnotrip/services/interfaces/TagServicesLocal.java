package com.esprit.hypnotrip.services.interfaces;

import javax.ejb.Local;

@Local
public interface TagServicesLocal {
	
	void SaveOrUpdateTage(String nameTag);
	
	

}
