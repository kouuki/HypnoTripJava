package com.esprit.hypnotrip.persistence;
// Generated 19 nov. 2016 19:30:02 by Hibernate Tools 4.3.1.Final

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Offer generated by hbm2java
 */
@Entity
@Table(name = "offer", catalog = "hypnodb")
public class Offer extends Pages implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Date beginDate;
	private Date finishDate;
	private double price;
	private double discount;

	private User user;

	public Offer() {

	}

	public Offer(String description, String userId) {
		super(description, userId);
		this.beginDate = new Date();
		this.finishDate = new Date();
		// TODO Auto-generated constructor stub
	}

	public Offer(String description, String title, double price, double discount) {
		super(description, title);
		this.beginDate = new Date();
		this.finishDate = new Date();
		this.price = price;
		this.discount = discount;
		// TODO Auto-generated constructor stub
	}

	public Offer(String description, String userId, Date beginDate, Date finishDate) {
		super(description, userId);
		this.beginDate = beginDate;
		this.finishDate = finishDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "BeginDate", nullable = true, length = 0)
	public Date getBeginDate() {
		return this.beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FinishDate", nullable = true, length = 0)
	public Date getFinishDate() {
		return this.finishDate;
	}

	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	@Override
	public String toString() {
		return "Offer [beginDate=" + beginDate + ", finishDate=" + finishDate + ", price=" + price + ", discount="
				+ discount + "]";
	}

	@ManyToOne
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
