package com.revature.ers.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import org.apache.log4j.Logger;

import com.revature.ers.dao.EmployeeDAO;
import com.revature.ers.dao.ReimbursmentStatusDAO;
import com.revature.ers.dao.ReimbursmentTypeDAO;
import com.revature.ers.exception.BusinessException;
import com.revature.ers.model.ErsReimbursment;
import com.revature.ers.model.ErsReimbursmentStatus;
import com.revature.ers.model.ErsReimbursmentType;
import com.revature.ers.service.UserService;
import com.revature.ers.utilities.PostresSqlConnection;

public class EmployeeDaoImpl implements EmployeeDAO {
	
	private static Logger log=Logger.getLogger(EmployeeDaoImpl.class);
	private ReimbursmentTypeDAO rbsType = new ReimbursmentTypeDAOImpl();
	private ReimbursmentStatusDAO rbsStatus = new ReimbursmentStatusDAOImpl();
	

	@Override
	public int createReimbursement(ErsReimbursment ersReimbursement) throws BusinessException {
		
		try (Connection connection = PostresSqlConnection.getConnection()) {	
			int c = 0;
			String sql = EmployeeDaoImplQueries.CREATE_ERS_REIMBURSEMENT;
			long timeNow = System.currentTimeMillis();
			Timestamp curreTimestamp = new Timestamp(timeNow);
			
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setDouble(1, ersReimbursement.getAmount());
			preparedStatement.setTimestamp(2, curreTimestamp);
			preparedStatement.setString(3, ersReimbursement.getDescription());
			preparedStatement.setInt(4, ersReimbursement.getAuthor());
			preparedStatement.setInt(5, ersReimbursement.getStatus().getStatusId());
			preparedStatement.setInt(6, ersReimbursement.getType().getTypeId());
				c = preparedStatement.executeUpdate();			
			return c;
		} catch (ClassNotFoundException | SQLException e) {
			log.warn(e.getMessage());
			throw new BusinessException("Internal error occured. Please contact customer "
					+ "service for more imformation");
		}
	}
	
}
