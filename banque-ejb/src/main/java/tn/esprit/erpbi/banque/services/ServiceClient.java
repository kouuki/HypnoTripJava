package tn.esprit.erpbi.banque.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.erpbi.banque.persistence.Client;

/**
 * Session Bean implementation class ServiceClient
 */

@Stateless
public class ServiceClient implements ServiceClientRemote, ServiceClientLocal {
	@PersistenceContext
	private EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public ServiceClient() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createClient(Client client) {
		entityManager.merge(client);

	}

	public Client findClientById(Integer idClient) {

		return entityManager.find(Client.class, idClient);
	}

}
