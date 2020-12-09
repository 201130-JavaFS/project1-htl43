package com.revature.ers.model;

public class ErsRedimbursementStatus {
	private int statusId;
	private String status;
	public ErsRedimbursementStatus(int statusId, String status) {
		super();
		this.statusId = statusId;
		this.status = status;
	}
	public int getStatusId() {
		return statusId;
	}
	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "ErsRedimbursementStatus [statusId=" + statusId + ", status=" + status + "]";
	}
	
}
