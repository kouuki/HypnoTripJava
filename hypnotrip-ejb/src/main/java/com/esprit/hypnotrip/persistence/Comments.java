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
 * Comments generated by hbm2java
 */
@Entity
@Table(name = "comments", catalog = "hypnodb")
public class Comments implements java.io.Serializable {

	private CommentsId id;
	private String description;
	private Date publicationDate;

	public Comments() {
	}

	public Comments(CommentsId id, Date publicationDate) {
		this.id = id;
		this.publicationDate = publicationDate;
	}

	public Comments(CommentsId id, String description, Date publicationDate) {
		this.id = id;
		this.description = description;
		this.publicationDate = publicationDate;
	}

	@EmbeddedId

	@AttributeOverrides({
			@AttributeOverride(name = "commentDate", column = @Column(name = "CommentDate", nullable = false, length = 0) ),
			@AttributeOverride(name = "postId", column = @Column(name = "PostId", nullable = false) ),
			@AttributeOverride(name = "userId", column = @Column(name = "UserId", nullable = false, length = 128) ) })
	public CommentsId getId() {
		return this.id;
	}

	public void setId(CommentsId id) {
		this.id = id;
	}

	@Column(name = "Description")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "PublicationDate", nullable = false, length = 0)
	public Date getPublicationDate() {
		return this.publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}

}
