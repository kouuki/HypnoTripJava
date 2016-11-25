
package tn.esprit.erpbi.banque.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour retraitArgentResponse complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="retraitArgentResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="etatTransaction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "retraitArgentResponse", propOrder = {
    "etatTransaction"
})
public class RetraitArgentResponse {

    protected String etatTransaction;

    /**
     * Obtient la valeur de la propri�t� etatTransaction.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEtatTransaction() {
        return etatTransaction;
    }

    /**
     * D�finit la valeur de la propri�t� etatTransaction.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEtatTransaction(String value) {
        this.etatTransaction = value;
    }

}
