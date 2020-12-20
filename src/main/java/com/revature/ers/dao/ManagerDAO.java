package com.revature.ers.dao;

import java.util.List;

import com.revature.ers.exception.BusinessException;
import com.revature.ers.model.ErsReimbursment;

public interface ManagerDAO {

	List<ErsReimbursment> getAllRibs() throws BusinessException;

}
