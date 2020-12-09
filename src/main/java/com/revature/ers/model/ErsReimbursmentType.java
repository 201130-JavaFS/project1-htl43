package com.revature.ers.model;

public class ErsReimbursmentType {
	private int typeId;
	private String type;
	public ErsReimbursmentType(int typeId, String type) {
		super();
		this.typeId = typeId;
		this.type = type;
	}
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "ErsReimbursmentType [typeId=" + typeId + ", type=" + type + "]";
	}
	
}
