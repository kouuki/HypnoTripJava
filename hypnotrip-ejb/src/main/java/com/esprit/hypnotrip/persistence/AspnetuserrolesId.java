package com.esprit.hypnotrip.persistence;
// Generated 19 nov. 2016 19:30:02 by Hibernate Tools 4.3.1.Final

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * AspnetuserrolesId generated by hbm2java
 */
@Embeddable
public class AspnetuserrolesId implements java.io.Serializable {

	private String roleId;
	private String userId;

	public AspnetuserrolesId() {
	}

	public AspnetuserrolesId(String roleId, String userId) {
		this.roleId = roleId;
		this.userId = userId;
	}

	@Column(name = "RoleId", nullable = false, length = 128)
	public String getRoleId() {
		return this.roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	@Column(name = "UserId", nullable = false, length = 128)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof AspnetuserrolesId))
			return false;
		AspnetuserrolesId castOther = (AspnetuserrolesId) other;

		return ((this.getRoleId() == castOther.getRoleId()) || (this.getRoleId() != null
				&& castOther.getRoleId() != null && this.getRoleId().equals(castOther.getRoleId())))
				&& ((this.getUserId() == castOther.getUserId()) || (this.getUserId() != null
						&& castOther.getUserId() != null && this.getUserId().equals(castOther.getUserId())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + (getRoleId() == null ? 0 : this.getRoleId().hashCode());
		result = 37 * result + (getUserId() == null ? 0 : this.getUserId().hashCode());
		return result;
	}

}
