package tn.esprit.erpbi.banque.persistence;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Entity implementation class for Entity: Compte
 *
 */
@Entity
@XmlRootElement
public class Compte implements Serializable {

	@Id
	@XmlAttribute(name = "numCompte")
	private String numCompte;
	@XmlAttribute(name = "password")
	private String password;
	@XmlAttribute(name = "solde")
	private Double solde;
	@XmlAttribute(name = "dateOfCreation")
	private Date dateOfCreation;
	@XmlAttribute(name = "etat")
	private boolean etat;
	@ManyToOne
	private Client client;

	private static final long serialVersionUID = 1L;

	public Compte() {
		super();
	}

	public Compte(String numCompte, String password, Double solde, boolean etat) {
		super();
		this.numCompte = numCompte;
		this.password = password;
		this.solde = solde;
		this.dateOfCreation = new Date();
		this.etat = etat;
	}

	public String getNumCompte() {
		return this.numCompte;
	}

	public void setNumCompte(String numCompte) {
		this.numCompte = numCompte;
	}

	public Double getSolde() {
		return this.solde;
	}

	public void setSolde(Double solde) {
		this.solde = solde;
	}

	public Date getDateOfCreation() {
		return this.dateOfCreation;
	}

	public void setDateOfCreation(Date dateOfCreation) {
		this.dateOfCreation = dateOfCreation;
	}

	public boolean getEtat() {
		return this.etat;
	}

	public void setEtat(boolean etat) {
		this.etat = etat;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
