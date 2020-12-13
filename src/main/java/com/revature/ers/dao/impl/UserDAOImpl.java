package com.revature.ers.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;

import com.revature.ers.dao.UserDAO;
import com.revature.ers.exception.BusinessException;
import com.revature.ers.model.ErsUser;
import com.revature.ers.model.ErsUserRole;
import com.revature.ers.utilities.PostresSqlConnection;

public class UserDAOImpl implements UserDAO {
	
	private static Logger log=Logger.getLogger(UserDAOImpl.class);

	@Override
	public ErsUser loginAccount(String username, String password) throws BusinessException {
		try (Connection connection = PostresSqlConnection.getConnection()) {
			String sql = UserDAOImpQueries.GET_ERS_USER;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				ErsUser ersUser = new ErsUser(
						resultSet.getInt("ers_users_id"), 
						resultSet.getString("ers_username"), 
						resultSet.getString("ers_password"), 
						resultSet.getString("user_first_name"), 
						resultSet.getString("user_last_name"), 
						resultSet.getString("user_email"), 
						null);
				ErsUserRole ersUserRole = new ErsUserRole(
						resultSet.getInt("ers_user_role_id"), 
						resultSet.getString("user_role"));
				ersUser.setRole(ersUserRole);
				return ersUser;
			}else {
				throw new BusinessException("Login failed. System can't find any record with your username"
						+ " and password");
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.warn(e.getMessage());
			throw new BusinessException("Internal error occured.. Kindly contact SYSADMIN");
		}
	}

	@Override
	public ErsUserRole getUserRoleById(int userRoleId) throws BusinessException {
		try (Connection connection = PostresSqlConnection.getConnection()) {
			String sql = UserDAOImpQueries.GET_ERS_USER_ROLE_BY_ID;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, userRoleId);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				ErsUserRole ersUserRole;
				ersUserRole = new ErsUserRole(resultSet.getInt("ers_user_role_id"), resultSet.getString("user_role"));
				return ersUserRole;
			}else {
				throw new BusinessException("Sorry. System can't find any record that matchs with user role id= " +userRoleId);
			}
		} catch (ClassNotFoundException | SQLException e) {
			log.warn(e.getMessage());
			throw new BusinessException("Internal error occured.. Kindly contact SYSADMIN");
		}
	}

}
