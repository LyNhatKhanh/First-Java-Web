package com.laptrinhjavaweb.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import com.laptrinhjavaweb.dao.INewDAO;
import com.laptrinhjavaweb.mapper.NewMapper;
import com.laptrinhjavaweb.model.NewModel;

public class NewDAO extends AbstractDAO<NewModel> implements INewDAO {

	@Override
	public List<NewModel> findByCategoryId(Long categoryId) {
		String sql = "SELECT * FROM newservlet.news WHERE categoryid = ?";
		return query(sql, new NewMapper(), categoryId);
	}

	// return id
	@Override
	public Long save(NewModel newModel) {
		ResultSet resultSet = null;
		Long id = null;
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			String sql = "INSERT INTO news (title, content, categoryid) VALUES(?,?,?)";
			connection = getConnection();
			/* NOTE:
			 * - MUST setAutoCommit(false) => Manual-commit can rollback if build fail
			 * - NEED parameter 'Statement.RETURN_GENERATED_KEYS' in 'connection.prepareStatement'
			 * [prevent ERROR at 'resultSet = statement.getGeneratedKeys()']
			 * 
			 * */
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);	// push query to execute
			
			// set parameter
			statement.setString(1,newModel.getTitle());
			statement.setString(2, newModel.getContent());
			statement.setLong(3, newModel.getCategoryId());
			
			statement.executeUpdate();
			
			// return id AI (Auto Incremental)
			resultSet = statement.getGeneratedKeys(); // database generate (get value which database generate)
			if (resultSet.next())
				id = resultSet.getLong(1);
			
			/*
			 * if all of command lines above success => go to [connection.commit()]
			 * else => go to [catch]
			 * */
			connection.commit();
			return id;
		} catch (SQLException e) {
			if (connection != null)
				try {
					connection.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			return null;
		} finally {
			try {
				if (connection != null)
					connection.close();
				if (statement != null)
					statement.close();
				if (resultSet != null)
					resultSet.close();
			} catch (SQLException e) {
				return null;
			}
		}
	}
}
