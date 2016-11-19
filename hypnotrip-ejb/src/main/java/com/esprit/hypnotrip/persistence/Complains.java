package com.esprit.hypnotrip.persistence;
// Generated 19 nov. 2016 19:30:02 by Hibernate Tools 4.3.1.Final

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Complains generated by hbm2java
 */
@Entity
@Table(name = "complains", catalog = "hypnodb")
public class Complains implements java.io.Serializable {

	private ComplainsId id;
	private String desciption;
	private String object;

	public Complains() {
	}

	public Complains(ComplainsId id) {
		this.id = id;
	}

	public Complains(ComplainsId id, String desciption, String object) {
		this.id = id;
		this.desciption = desciption;
		this.object = object;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "commentedDate", column = @Column(name = "CommentedDate", nullable = false, length = 0) ),
			@AttributeOverride(name = "postId", column = @Column(name = "PostId", nullable = false) ),
			@AttributeOverride(name = "urId", column = @Column(name = "UrId", nullable = false, length = 128) ),
			@AttributeOverride(name = "userId", column = @Column(name = "UserId", nullable = false, length = 128) ) })
	public ComplainsId getId() {
		return this.id;
	}

	public void setId(ComplainsId id) {
		this.id = id;
	}

	@Column(name = "Desciption")
	public String getDesciption() {
		return this.desciption;
	}

	public void setDesciption(String desciption) {
		this.desciption = desciption;
	}

	@Column(name = "Object")
	public String getObject() {
		return this.object;
	}

	public void setObject(String object) {
		this.object = object;
	}

}
