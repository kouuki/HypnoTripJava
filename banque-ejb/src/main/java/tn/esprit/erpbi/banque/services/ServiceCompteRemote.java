package tn.esprit.erpbi.banque.services;

import javax.ejb.Remote;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@Remote
@WebService(name = "ComptePortType")
public interface ServiceCompteRemote {

	@WebResult(name = "etatTransaction")
	String retraitArgent(@WebParam(name = "idCompte") String idCompte, @WebParam(name = "pwd") String password,
			@WebParam(name = "somme") Double somme);

}
