package com.revature.ers.model;

public class ErsUserRole {
	
	private int userRoleId;
	private String userRole;
	
	public ErsUserRole(int userRoleId, String userRole) {
		super();
		this.userRoleId = userRoleId;
		this.userRole = userRole;
	}

	public int getUserRoleId() {
		return userRoleId;
	}

	public void setUserRoleId(int userRoleId) {
		this.userRoleId = userRoleId;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	@Override
	public String toString() {
		return "ErsUserRole [userRoleId=" + userRoleId + ", userRole=" + userRole + "]";
	}
	
}
