package com.esprit.hypnotrip.persistence;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * User generated by hbm2java
 */
@Entity
@Table(name = "user", catalog = "hypnodb")
public class User implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private int accessFailedCount;
	private String address;
	private Date dateOfBirth;
	private String email;
	private boolean emailConfirmed;
	private int etat;
	private String firstName;
	private String imageUrl;
	private boolean lockoutEnabled;
	private Date lockoutEndDateUtc;
	private String login;
	private String password;
	private String passwordHash;
	private String phoneNumber;
	private boolean phoneNumberConfirmed;
	private String role;
	private String secondName;
	private String securityStamp;
	private boolean twoFactorEnabled;
	private String userName;
	
	@OneToMany(mappedBy = "user",fetch=FetchType.EAGER)
	private List<Rates> rates;
	
	private List<BookDescription> bookDescriptions;

	private List<Follows> pagesFollowed;
	
	
	public User() {
	}

	public User(String id, int accessFailedCount, Date dateOfBirth, boolean emailConfirmed, int etat,
			boolean lockoutEnabled, boolean phoneNumberConfirmed, boolean twoFactorEnabled) {
		this.id = id;
		this.accessFailedCount = accessFailedCount;
		this.dateOfBirth = dateOfBirth;
		this.emailConfirmed = emailConfirmed;
		this.etat = etat;
		this.lockoutEnabled = lockoutEnabled;
		this.phoneNumberConfirmed = phoneNumberConfirmed;
		this.twoFactorEnabled = twoFactorEnabled;
	}

	public User(String id, int accessFailedCount, String address, Date dateOfBirth, String email,
			boolean emailConfirmed, int etat, String firstName, String imageUrl, boolean lockoutEnabled,
			Date lockoutEndDateUtc, String login, String password, String passwordHash, String phoneNumber,
			boolean phoneNumberConfirmed, String role, String secondName, String securityStamp,
			boolean twoFactorEnabled, String userName) {
		this.id = id;
		this.accessFailedCount = accessFailedCount;
		this.address = address;
		this.dateOfBirth = dateOfBirth;
		this.email = email;
		this.emailConfirmed = emailConfirmed;
		this.etat = etat;
		this.firstName = firstName;
		this.imageUrl = imageUrl;
		this.lockoutEnabled = lockoutEnabled;
		this.lockoutEndDateUtc = lockoutEndDateUtc;
		this.login = login;
		this.password = password;
		this.passwordHash = passwordHash;
		this.phoneNumber = phoneNumber;
		this.phoneNumberConfirmed = phoneNumberConfirmed;
		this.role = role;
		this.secondName = secondName;
		this.securityStamp = securityStamp;
		this.twoFactorEnabled = twoFactorEnabled;
		this.userName = userName;
	}

	@Id

	@Column(name = "Id", unique = true, nullable = false, length = 128)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "AccessFailedCount", nullable = false)
	public int getAccessFailedCount() {
		return this.accessFailedCount;
	}

	public void setAccessFailedCount(int accessFailedCount) {
		this.accessFailedCount = accessFailedCount;
	}

	@Column(name = "Address", length = 128)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DateOfBirth", nullable = false, length = 0)
	public Date getDateOfBirth() {
		return this.dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Column(name = "Email", length = 128)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "EmailConfirmed", nullable = false)
	public boolean isEmailConfirmed() {
		return this.emailConfirmed;
	}

	public void setEmailConfirmed(boolean emailConfirmed) {
		this.emailConfirmed = emailConfirmed;
	}

	@Column(name = "Etat", nullable = false)
	public int getEtat() {
		return this.etat;
	}

	public void setEtat(int etat) {
		this.etat = etat;
	}

	@Column(name = "FirstName", length = 128)
	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "ImageUrl")
	public String getImageUrl() {
		return this.imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	@Column(name = "LockoutEnabled", nullable = false)
	public boolean isLockoutEnabled() {
		return this.lockoutEnabled;
	}

	public void setLockoutEnabled(boolean lockoutEnabled) {
		this.lockoutEnabled = lockoutEnabled;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LockoutEndDateUtc", length = 0)
	public Date getLockoutEndDateUtc() {
		return this.lockoutEndDateUtc;
	}

	public void setLockoutEndDateUtc(Date lockoutEndDateUtc) {
		this.lockoutEndDateUtc = lockoutEndDateUtc;
	}

	@Column(name = "Login", length = 128)
	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@Column(name = "Password")
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "PasswordHash", length = 128)
	public String getPasswordHash() {
		return this.passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	@Column(name = "PhoneNumber", length = 128)
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Column(name = "PhoneNumberConfirmed", nullable = false)
	public boolean isPhoneNumberConfirmed() {
		return this.phoneNumberConfirmed;
	}

	public void setPhoneNumberConfirmed(boolean phoneNumberConfirmed) {
		this.phoneNumberConfirmed = phoneNumberConfirmed;
	}

	@Column(name = "Role", length = 128)
	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Column(name = "SecondName", length = 128)
	public String getSecondName() {
		return this.secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	@Column(name = "SecurityStamp", length = 128)
	public String getSecurityStamp() {
		return this.securityStamp;
	}

	public void setSecurityStamp(String securityStamp) {
		this.securityStamp = securityStamp;
	}

	@Column(name = "TwoFactorEnabled", nullable = false)
	public boolean isTwoFactorEnabled() {
		return this.twoFactorEnabled;
	}

	public void setTwoFactorEnabled(boolean twoFactorEnabled) {
		this.twoFactorEnabled = twoFactorEnabled;
	}

	@Column(name = "UserName", length = 128)
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
    
	
	@OneToMany(mappedBy="user", fetch=FetchType.EAGER)
	public List<BookDescription> getBookDescriptions() {
		return bookDescriptions;
	}

	public void setBookDescriptions(List<BookDescription> bookDescriptions) {
		this.bookDescriptions = bookDescriptions;
	}

	@OneToMany(mappedBy="user", fetch=FetchType.EAGER)
	public List<Follows> getPagesFollowed() {
		return pagesFollowed;
	}

	public void setPagesFollowed(List<Follows> pagesFollowed) {
		this.pagesFollowed = pagesFollowed;
	}

}
