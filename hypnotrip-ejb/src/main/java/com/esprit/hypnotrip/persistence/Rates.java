package com.esprit.hypnotrip.persistence;
// Generated 19 nov. 2016 19:30:02 by Hibernate Tools 4.3.1.Final

import java.util.Date;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Rates generated by hbm2java
 */
@Entity
@Table(name = "rates", catalog = "hypnodb")
public class Rates implements java.io.Serializable {

	private RatesId id;
	private Date dateRating;
	private int niveau;

	public Rates() {
	}

	public Rates(RatesId id, Date dateRating, int niveau) {
		this.id = id;
		this.dateRating = dateRating;
		this.niveau = niveau;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "id", column = @Column(name = "Id", nullable = false, length = 128) ),
			@AttributeOverride(name = "postId", column = @Column(name = "PostId", nullable = false) ) })
	public RatesId getId() {
		return this.id;
	}

	public void setId(RatesId id) {
		this.id = id;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DateRating", nullable = false, length = 0)
	public Date getDateRating() {
		return this.dateRating;
	}

	public void setDateRating(Date dateRating) {
		this.dateRating = dateRating;
	}

	@Column(name = "Niveau", nullable = false)
	public int getNiveau() {
		return this.niveau;
	}

	public void setNiveau(int niveau) {
		this.niveau = niveau;
	}

}