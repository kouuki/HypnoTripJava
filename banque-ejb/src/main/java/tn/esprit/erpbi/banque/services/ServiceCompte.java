package tn.esprit.erpbi.banque.services;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.erpbi.banque.persistence.Compte;

/**
 * Session Bean implementation class ServiceCompte
 */
@WebService(endpointInterface = "tn.esprit.erpbi.banque.services.ServiceCompteRemote", portName = "ComptePort", name = "CompteService")
@Stateless
public class ServiceCompte implements ServiceCompteRemote, ServiceCompteLocal {
	@PersistenceContext
	private EntityManager entityManager;
	@EJB
	private ServiceClientLocal serviceClient;

	/**
	 * Default constructor.
	 */
	public ServiceCompte() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void createAccount(Integer idClient, Compte compte) {
		compte.setClient(serviceClient.findClientById(idClient));
		entityManager.merge(compte);
	}

	@Override
	public String retraitArgent(String idCompte, String password, Double somme) {
		Compte compte = findCompte(idCompte);
		String result = "La transaction a échoué";
		if (compte.getPassword().equals(password)) {
			if (compte.getEtat() == true) {
				if (compte.getSolde() >= somme) {
					compte.setSolde(compte.getSolde() - somme);
					result = "Transaction effectuée";
				}
			} else {
				result = "Compte désactivé";
			}
		} else {
			result = "Mot de passe incorrecte";
		}
		return result;
	}

	public Compte findCompte(String idCompte) {
		return entityManager.find(Compte.class, idCompte);

	}

}
