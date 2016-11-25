package tn.esprit.erpbi.banque.services;

import javax.ejb.Remote;

import tn.esprit.erpbi.banque.persistence.Client;

@Remote
public interface ServiceClientRemote {
	void createClient(Client client);

	Client findClientById(Integer idClient);
}
