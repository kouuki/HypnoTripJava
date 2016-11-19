package com.esprit.hypnotrip.persistence;
// Generated 19 nov. 2016 19:30:02 by Hibernate Tools 4.3.1.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Tags generated by hbm2java
 */
@Entity
@Table(name = "tags", catalog = "hypnodb")
public class Tags implements java.io.Serializable {

	private Integer tagId;
	private String name;
	private int nbrUse;

	public Tags() {
	}

	public Tags(int nbrUse) {
		this.nbrUse = nbrUse;
	}

	public Tags(String name, int nbrUse) {
		this.name = name;
		this.nbrUse = nbrUse;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "TagId", unique = true, nullable = false)
	public Integer getTagId() {
		return this.tagId;
	}

	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}

	@Column(name = "Name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "NbrUse", nullable = false)
	public int getNbrUse() {
		return this.nbrUse;
	}

	public void setNbrUse(int nbrUse) {
		this.nbrUse = nbrUse;
	}

}
