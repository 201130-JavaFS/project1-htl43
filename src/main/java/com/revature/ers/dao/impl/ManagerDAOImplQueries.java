package com.revature.ers.dao.impl;

public class ManagerDAOImplQueries {
	
	public static final String GET_ALL_RIBS = "SELECT * FROM ers.ers_reimbursement ORDER BY reimb_id ASC";
	
	public static final String UPDATE_REIBS_STATUS_BY_ID = "update ers.ers_reimbursement set reimb_status_id=? where reimb_id=?";
}
