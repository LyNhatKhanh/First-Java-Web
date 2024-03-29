package com.laptrinhjavaweb.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import com.laptrinhjavaweb.dao.IGenericDAO;
import com.laptrinhjavaweb.mapper.RowMapper;

public class AbstractDAO<T> implements IGenericDAO<T> {

	// db.properties in resources folder
	ResourceBundle resourceBundle = ResourceBundle.getBundle("db");

	public Connection getConnection() {
		// ClassNotFoundException: prevent when we forget add dependency in pom.xml
		// SQLException: url | user | password is not correct
		try {
			/*Class.forName("com.mysql.jdbc.Driver");			// load Driver (giống cổng kết nối | Connect vô mysql)
			String url = "jdbc:mysql://localhost:3306/newservlet";
			String user = "root";
			String password = "123456";*/
			Class.forName(resourceBundle.getString("driverName"));			// load Driver (giống cổng kết nối | Connect vô mysql)
			String url = resourceBundle.getString("url");
			String user = resourceBundle.getString("user");
			String password = resourceBundle.getString("password");
			return DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException | SQLException e) {		// multiple catch (2 case) 
			return null;
		} /*catch (SQLException e) {
			return null;
		}*/
	}

	@SuppressWarnings("hiding")
	@Override
	public <T> List<T> query(String sql, RowMapper<T> rowMapper, Object... parameters) {
		List<T> results = new ArrayList<>();
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultSet = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql);
			
			// set parameter
			setParameter(statement, parameters);
			
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				results.add(rowMapper.mapRow(resultSet));
			}
			return results;
		} catch (SQLException e) {
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

	private void setParameter(PreparedStatement statement, Object... parameters) {
		try {
			for(int i=0; i<parameters.length; i++) {
				Object parameter = parameters[i];
				int index = i+1;
				if (parameter instanceof Long) 
					statement.setLong(index, (Long) parameter);
				else if (parameter instanceof String)
					statement.setString(index, (String) parameter);
				else if (parameter instanceof Integer)
					statement.setInt(index, (Integer) parameter);
				else if (parameter instanceof Timestamp)
					statement.setTimestamp(index, (Timestamp) parameter);
				/*else if (parameter == null)
					statement.setNull(index, Types.NULL);*/
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void update(String sql, Object... parameters) {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			
			// push query to execute
			statement = connection.prepareStatement(sql);

			// set parameters
			setParameter(statement, parameters);

			// execute Update
			statement.executeUpdate();
			
			/*
			 * if all of command lines above success => go to [connection.commit()] else =>
			 * go to [catch]
			 */
			connection.commit();
		} catch (SQLException e) {
			if (connection != null)
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
		} finally {
			try {
				if (connection != null)
					connection.close();
				if (statement != null)
					statement.close();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
		}
	}

	@Override
	public Long insert(String sql, Object... parameters) {
		ResultSet resultSet = null;
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			Long id = null;
			connection = getConnection();
			connection.setAutoCommit(false);
			
			// push query to execute
			statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);	

			// set parameters
			setParameter(statement, parameters);

			// execute Update
			statement.executeUpdate();
			
			// return id AI (Auto Incremental)
			resultSet = statement.getGeneratedKeys(); // database generate (get value which database generate)
			if (resultSet.next())
				id = resultSet.getLong(1);	// 1: indexColumn of database
			
			/*
			 * if all of command lines above success => go to [connection.commit()] 
			 * else => go to [catch]
			 */
			connection.commit();
			return id;
		} catch (SQLException e) {
			if (connection != null)
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
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
		
		
		return null;
	}

	@Override
	public int count(String sql, Object... parameters) {
		ResultSet resultSet = null;
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			int count = 0;
			connection = getConnection();
			statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);	
			setParameter(statement, parameters);
			resultSet = statement.executeQuery();
			if (resultSet.next())
				count = resultSet.getInt(1);	// 1: indexColumn of database
			return count;
		} catch (SQLException e) {
			return 0;
		} finally {
			try {
				if (connection != null)
					connection.close();
				if (statement != null)
					statement.close();
				if (resultSet != null)
					resultSet.close();
			} catch (SQLException e) {
				return 0;
			}
		}
	}

}
