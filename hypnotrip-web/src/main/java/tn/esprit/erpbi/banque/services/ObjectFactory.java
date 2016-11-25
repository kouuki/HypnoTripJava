
package tn.esprit.erpbi.banque.services;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the tn.esprit.erpbi.banque.services package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _RetraitArgent_QNAME = new QName("http://services.banque.erpbi.esprit.tn/", "retraitArgent");
    private final static QName _RetraitArgentResponse_QNAME = new QName("http://services.banque.erpbi.esprit.tn/", "retraitArgentResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: tn.esprit.erpbi.banque.services
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RetraitArgent }
     * 
     */
    public RetraitArgent createRetraitArgent() {
        return new RetraitArgent();
    }

    /**
     * Create an instance of {@link RetraitArgentResponse }
     * 
     */
    public RetraitArgentResponse createRetraitArgentResponse() {
        return new RetraitArgentResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RetraitArgent }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.banque.erpbi.esprit.tn/", name = "retraitArgent")
    public JAXBElement<RetraitArgent> createRetraitArgent(RetraitArgent value) {
        return new JAXBElement<RetraitArgent>(_RetraitArgent_QNAME, RetraitArgent.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RetraitArgentResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://services.banque.erpbi.esprit.tn/", name = "retraitArgentResponse")
    public JAXBElement<RetraitArgentResponse> createRetraitArgentResponse(RetraitArgentResponse value) {
        return new JAXBElement<RetraitArgentResponse>(_RetraitArgentResponse_QNAME, RetraitArgentResponse.class, null, value);
    }

}
