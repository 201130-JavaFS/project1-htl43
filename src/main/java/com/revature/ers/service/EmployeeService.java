package com.revature.ers.service;

import org.apache.log4j.Logger;

import com.revature.ers.dao.EmployeeDAO;
import com.revature.ers.dao.impl.EmployeeDaoImpl;
import com.revature.ers.exception.BusinessException;
import com.revature.ers.model.ErsReimbursment;

public class EmployeeService {
	
	private static Logger log=Logger.getLogger(EmployeeService.class);
	private EmployeeDAO employeeDAO = new EmployeeDaoImpl();

	public int submitErsReibursment(ErsReimbursment ersReimbursement) throws BusinessException {
		if(ersReimbursement.getAuthor()<=0 ) {
			throw new BusinessException ("Submition Failed! Invalid Author Id!");
		} else if (ersReimbursement.getAmount()<0){
			throw new BusinessException ("Invalid value for the amount= " + ersReimbursement.getAmount() + 
					". The amount must not be negative number");
		} else {
			ersReimbursement.getStatus().setStatusId(100); // Set default id=100 for pending status
			int c = employeeDAO.createReimbursement(ersReimbursement);
			if(c>0) {
				return c;
			} else {
				throw new BusinessException ("System can't create the reimbursment. Please check again for the field requirements");
			}
		}
	}
}
