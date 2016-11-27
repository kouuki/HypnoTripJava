package com.esprit.hypnotrip.services.interfaces;

import javax.ejb.Local;

@Local
public interface UserConnectionServicesLocal {
	void SaveConnection(String idUesr);
}