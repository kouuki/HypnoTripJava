
package tn.esprit.erpbi.banque.services;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour retraitArgent complex type.
 * 
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="retraitArgent">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="idCompte" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pwd" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="somme" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "retraitArgent", propOrder = {
    "idCompte",
    "pwd",
    "somme"
})
public class RetraitArgent {

    protected String idCompte;
    protected String pwd;
    protected Double somme;

    /**
     * Obtient la valeur de la propri�t� idCompte.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdCompte() {
        return idCompte;
    }

    /**
     * D�finit la valeur de la propri�t� idCompte.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdCompte(String value) {
        this.idCompte = value;
    }

    /**
     * Obtient la valeur de la propri�t� pwd.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPwd() {
        return pwd;
    }

    /**
     * D�finit la valeur de la propri�t� pwd.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPwd(String value) {
        this.pwd = value;
    }

    /**
     * Obtient la valeur de la propri�t� somme.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getSomme() {
        return somme;
    }

    /**
     * D�finit la valeur de la propri�t� somme.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setSomme(Double value) {
        this.somme = value;
    }

}
