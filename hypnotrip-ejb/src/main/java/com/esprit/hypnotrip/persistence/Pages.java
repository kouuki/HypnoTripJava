package com.esprit.hypnotrip.persistence;
// Generated 19 nov. 2016 19:30:02 by Hibernate Tools 4.3.1.Final

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Pages generated by hbm2java
 */
@Entity
@Table(name = "pages", catalog = "hypnodb")
public class Pages implements java.io.Serializable {

	private Integer pageId;
	private String description;
	private String userId;

	public Pages() {
	}

	public Pages(String userId) {
		this.userId = userId;
	}

	public Pages(String description, String userId) {
		this.description = description;
		this.userId = userId;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "PageId", unique = true, nullable = false)
	public Integer getPageId() {
		return this.pageId;
	}

	public void setPageId(Integer pageId) {
		this.pageId = pageId;
	}

	@Column(name = "Description")
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Column(name = "UserId", nullable = false, length = 128)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
