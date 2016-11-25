package tn.esprit.erpbi.banque.persistence;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Entity implementation class for Entity: Client
 *
 */
@Entity

public class Client implements Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer numClient;
	private String Name;
	private String Address;

	@OneToMany(mappedBy = "client", cascade = CascadeType.MERGE)
	private List<Compte> listCompte = new ArrayList<Compte>();

	private static final long serialVersionUID = 1L;

	public Client() {
		super();
	}

	public Client(String name, String address, List<Compte> listCompte) {
		super();
		Name = name;
		Address = address;
		this.listCompte = listCompte;
	}

	public Integer getNumClient() {
		return this.numClient;
	}

	public void setNumClient(Integer numClient) {
		this.numClient = numClient;
	}

	public String getName() {
		return this.Name;
	}

	public void setName(String Name) {
		this.Name = Name;
	}

	public String getAddress() {
		return this.Address;
	}

	public void setAddress(String Address) {
		this.Address = Address;
	}

	public List<Compte> getListCompte() {
		return listCompte;
	}

	public void setListCompte(List<Compte> listCompte) {
		this.listCompte = listCompte;
	}

}
