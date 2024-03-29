package com.laptrinhjavaweb.dao.impl;

import java.util.List;

import com.laptrinhjavaweb.dao.IUserDAO;
import com.laptrinhjavaweb.mapper.UserMapper;
import com.laptrinhjavaweb.model.UserModel;

public class UserDAO extends AbstractDAO<UserModel> implements IUserDAO {

	@Override
	public UserModel findByUserNameAndPasswordAndStatus(String username, String password, int status) {
		StringBuilder sql = new StringBuilder("SELECT * FROM newservlet.user AS u");
		sql.append(" INNER JOIN role AS r ON r.id = u.id");
		sql.append(" WHERE username = ? AND password = ? AND status = ?");
		List<UserModel> users = query(sql.toString(),new UserMapper(), username, password, status);
		return users.isEmpty() ? null : users.get(0);
	}
	

}
