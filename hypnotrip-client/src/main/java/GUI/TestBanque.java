package GUI;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import tn.esprit.erpbi.banque.services.ServiceCompteRemote;

public class TestBanque {

	public static void main(String[] args) throws NamingException {
		Context context = new InitialContext();

		String jndi = "banque-ear/banque-ejb/ServiceCompte!tn.esprit.erpbi.banque.services.ServiceCompteRemote";
		ServiceCompteRemote serviceCompteRemote = (ServiceCompteRemote) context.lookup(jndi);

		System.out.println(serviceCompteRemote.retraitArgent("AB12345", "abcd", 200.0));

	}

}
