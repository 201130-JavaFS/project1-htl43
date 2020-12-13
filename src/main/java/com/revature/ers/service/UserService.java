package com.revature.ers.service;

import org.apache.log4j.Logger;

import com.revature.ers.dao.UserDAO;
import com.revature.ers.dao.impl.UserDAOImpl;
import com.revature.ers.exception.BusinessException;
import com.revature.ers.model.ErsUser;

public class UserService {
	
	private static Logger log=Logger.getLogger(UserService.class);
	private UserDAO userDAO = new UserDAOImpl();
	
	public ErsUser login(String username, String password) throws BusinessException {
		ErsUser ersUser=null;
		if(username.equals("") || password.equals("")) {
			throw new BusinessException("Username and Password must not be blank");
		} else {
			ersUser = userDAO.loginAccount(username, password);
		}	
		return ersUser;
	}
}
