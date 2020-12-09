package com.revature.ers.dao;

import com.revature.ers.exception.BusinessException;
import com.revature.ers.model.ErsUser;

public interface UserDAO {
	
	public ErsUser login(String username, String password) throws BusinessException;
	public ErsUserRole userRole(int userRoleId) throws BusinessException;
}
