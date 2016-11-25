package tn.esprit.erpbi.banque.util;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import tn.esprit.erpbi.banque.persistence.Client;
import tn.esprit.erpbi.banque.persistence.Compte;
import tn.esprit.erpbi.banque.services.ServiceClientLocal;
import tn.esprit.erpbi.banque.services.ServiceCompteLocal;

@Singleton
@LocalBean
@Startup

public class DefaultDB {
	@EJB
	private ServiceClientLocal clientLocal;
	@EJB
	private ServiceCompteLocal compteLocal;

	public DefaultDB() {
		// TODO Auto-generated constructor stub
	}

	@PostConstruct
	public void init() {
		Compte compte1 = new Compte("A123456", "abcd", 2000.0, true);
		Compte compte2 = new Compte("AB12345", "abcd", 100.0, true);
		Compte compte3 = new Compte("ABC1234", "abcd", 20.0, false);
		Compte compte4 = new Compte("ABCD123", "abcd", 500.0, true);

		List<Compte> compteJihene = new ArrayList<Compte>();
		compteJihene.add(compte1);
		compteJihene.add(compte3);
		Client client1 = new Client("Ben gharbia Jihene", "Bardo", compteJihene);
		compte1.setClient(client1);
		compte3.setClient(client1);

		List<Compte> compteClient1 = new ArrayList<Compte>();
		compteClient1.add(compte2);
		Client client2 = new Client("Client 1", "Manar", compteClient1);
		compte2.setClient(client2);

		List<Compte> compteClient2 = new ArrayList<Compte>();
		compteClient2.add(compte4);
		Client client3 = new Client("Client 2", "Ariana", compteClient2);
		compte4.setClient(client3);
		clientLocal.createClient(client1);
		clientLocal.createClient(client2);
		clientLocal.createClient(client3);
	}

}
