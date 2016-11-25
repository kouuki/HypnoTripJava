package tn.esprit.erpbi.banque.services;

import javax.ejb.Local;

import tn.esprit.erpbi.banque.persistence.Compte;

@Local
public interface ServiceCompteLocal {
	void createAccount(Integer idClient, Compte compte);

	String retraitArgent(String idCompte, String password, Double somme);

}
