package com.esprit.hypnotrip.persistence;
// Generated 19 nov. 2016 19:30:02 by Hibernate Tools 4.3.1.Final

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Event generated by hbm2java
 */
@Entity
@Table(name = "event", catalog = "hypnodb")
public class Event extends Pages implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date dateOfEvent;
	private float latitude;
	private float longitude;

	private String place;

	private float price;

	private List<Follows> followers;

	public Event() {
		super();
	}

	public Event(String description, String userId, Date dateOfEvent, float latitude, float longitude, String place) {
		super(description, userId);
		this.dateOfEvent = new Date();
		this.latitude = latitude;
		this.longitude = longitude;
		this.place = place;
	}

	public Event(String description, String title, Date dateOfEvent, String place) {
		super(description, title);
		this.dateOfEvent = new Date();

		this.place = place;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "DateOfEvent", nullable = true, length = 0)
	public Date getDateOfEvent() {
		return this.dateOfEvent;
	}

	public void setDateOfEvent(Date dateOfEvent) {
		this.dateOfEvent = dateOfEvent;
	}

	@Column(name = "Latitude", nullable = true, precision = 12, scale = 0, columnDefinition = "Decimal(10,2) default '000.00'")
	public float getLatitude() {
		return this.latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	@Column(name = "Longitude", nullable = true, precision = 12, scale = 0, columnDefinition = "Decimal(10,2) default '000.00'")
	public float getLongitude() {
		return this.longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	@OneToMany(mappedBy = "pages", fetch = FetchType.EAGER)
	public List<Follows> getFollowers() {
		return followers;
	}

	public void setFollowers(List<Follows> followers) {
		this.followers = followers;
	}

	
	@Override
	public String toString() {
		return super.toString() + "Event [dateOfEvent=" + dateOfEvent + ", latitude=" + latitude + ", longitude=" + longitude + ", place="
				+ place + ", price=" + price + "]";
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

}
