package com.laptrinhjavaweb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.laptrinhjavaweb.model.RoleModel;
import com.laptrinhjavaweb.model.UserModel;

public class UserMapper implements RowMapper<UserModel> {

	@Override
	public UserModel mapRow(ResultSet resultSet) {
		try {
			UserModel user = new UserModel();
			user.setId(resultSet.getLong("id"));
			user.setUserName(resultSet.getString("username"));
			user.setPassword(resultSet.getString("password"));
			user.setFullName(resultSet.getString("fullname"));
			user.setStatus(resultSet.getInt("status"));
			RoleModel role = new RoleModel();
			role.setCode(resultSet.getString("code"));
			role.setName(resultSet.getString("name"));
			user.setRole(role);
			user.setCreateDate(resultSet.getTimestamp("createdate"));
			user.setCreateBy(resultSet.getString("createby"));
			if (resultSet.getTimestamp("modifieddate") != null)
				user.setModifiedDate(resultSet.getTimestamp("modifieddate"));
			if (resultSet.getString("modifiedby") != null)
				user.setModifiedBy(resultSet.getString("modifiedby"));
			
			return user;
		} catch (SQLException e) {
			return null;
		}
	}
}
