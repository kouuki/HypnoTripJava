package com.esprit.hypnotrip.persistence;
// Generated 19 nov. 2016 19:30:02 by Hibernate Tools 4.3.1.Final

import static javax.persistence.GenerationType.IDENTITY;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Pages generated by hbm2java
 */
@Entity
@Table(name = "pages", catalog = "hypnodb")
@DiscriminatorColumn(name = "Discriminator", discriminatorType = DiscriminatorType.STRING)
public class Pages implements java.io.Serializable {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	private Integer pageId;
	private String description;
	private String title;
	private String userId;
	private String categoriePage;
	// @OneToMany(mappedBy = "page",fetch=FetchType.EAGER)
	// private List<Rates> rates;

	private List<Tickets> tickets;

	private String ImageURL;

	public String getImageURL() {
		return ImageURL;
	}

	public void setImageURL(String imageURL) {
		ImageURL = imageURL;
	}

	public Pages() {
	}

	public Pages(String description, String title) {
		this.description = description;
		this.title = title;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pageId == null) ? 0 : pageId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pages other = (Pages) obj;
		if (pageId == null) {
			if (other.pageId != null)
				return false;
		} else if (!pageId.equals(other.pageId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Pages [pageId=" + pageId + ", description=" + description + ", userId=" + userId + "]";
	}

	@OneToMany(mappedBy = "event", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	public List<Tickets> getTickets() {
		return tickets;
	}

	public void setTickets(List<Tickets> tickets) {
		this.tickets = tickets;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCategoriePage() {
		return categoriePage;
	}

	public void setCategoriePage(String categoriePage) {
		this.categoriePage = categoriePage;
	}

}
