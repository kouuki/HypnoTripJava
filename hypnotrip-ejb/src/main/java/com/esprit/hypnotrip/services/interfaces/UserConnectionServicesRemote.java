package com.esprit.hypnotrip.services.interfaces;

import javax.ejb.Remote;

@Remote
public interface UserConnectionServicesRemote {
	void SaveConnection(String idUesr);
}