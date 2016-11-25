package tn.esprit.erpbi.banque.services;

import javax.ejb.Local;

import tn.esprit.erpbi.banque.persistence.Client;

@Local
public interface ServiceClientLocal {
	void createClient(Client client);

	Client findClientById(Integer idClient);
}
