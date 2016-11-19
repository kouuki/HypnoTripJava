package com.esprit.hypnotrip.persistence;
// Generated 19 nov. 2016 19:30:02 by Hibernate Tools 4.3.1.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Cercles generated by hbm2java
 */
@Entity
@Table(name = "cercles", catalog = "hypnodb")
public class Cercles implements java.io.Serializable {

	private Integer cercleId;
	private String category;
	private String name;
	private String userId;

	public Cercles() {
	}

	public Cercles(String category, String name, String userId) {
		this.category = category;
		this.name = name;
		this.userId = userId;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "CercleId", unique = true, nullable = false)
	public Integer getCercleId() {
		return this.cercleId;
	}

	public void setCercleId(Integer cercleId) {
		this.cercleId = cercleId;
	}

	@Column(name = "Category")
	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Column(name = "Name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "UserId", length = 128)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}