
package tn.esprit.erpbi.banque.services;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "ComptePortType", targetNamespace = "http://services.banque.erpbi.esprit.tn/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface ComptePortType {


    /**
     * 
     * @param somme
     * @param idCompte
     * @param pwd
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(name = "etatTransaction", targetNamespace = "")
    @RequestWrapper(localName = "retraitArgent", targetNamespace = "http://services.banque.erpbi.esprit.tn/", className = "tn.esprit.erpbi.banque.services.RetraitArgent")
    @ResponseWrapper(localName = "retraitArgentResponse", targetNamespace = "http://services.banque.erpbi.esprit.tn/", className = "tn.esprit.erpbi.banque.services.RetraitArgentResponse")
    public String retraitArgent(
        @WebParam(name = "idCompte", targetNamespace = "")
        String idCompte,
        @WebParam(name = "pwd", targetNamespace = "")
        String pwd,
        @WebParam(name = "somme", targetNamespace = "")
        Double somme);

}