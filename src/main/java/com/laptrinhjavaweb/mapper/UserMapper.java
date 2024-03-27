package com.laptrinhjavaweb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.laptrinhjavaweb.model.RoleModel;
import com.laptrinhjavaweb.model.UserModel;

public class UserMapper implements RowMapper<UserModel> {

	// SELECT * FROM user INNER JOIN role ON user.roleid = role.id
	@Override
	public UserModel mapRow(ResultSet resultSet) {
		try {
			UserModel user = new UserModel();
			user.setId(resultSet.getLong("id"));
			user.setUserName(resultSet.getString("username"));
			user.setPassword(resultSet.getString("password"));
			user.setFullName(resultSet.getString("fullname"));
			user.setStatus(resultSet.getInt("status"));
			    /* if sql simple (only SELECT * FROM user) => try catch NullPointerException */
			try {
                RoleModel role = new RoleModel();
                role.setCode(resultSet.getString("code"));
                role.setName(resultSet.getString("name"));
                user.setRole(role);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            /*user.setCreateDate(resultSet.getTimestamp("createdate"));
			user.setCreateBy(resultSet.getString("createby"));
			if (resultSet.getTimestamp("modifieddate") != null)
				user.setModifiedDate(resultSet.getTimestamp("modifieddate"));
			if (resultSet.getString("modifiedby") != null)
				user.setModifiedBy(resultSet.getString("modifiedby"));*/
			
			return user;
		} catch (SQLException e) {
			return null;
		}
	}
}
