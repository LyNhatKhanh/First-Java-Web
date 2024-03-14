package com.laptrinhjavaweb.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.laptrinhjavaweb.dao.INewDAO;
import com.laptrinhjavaweb.model.NewModel;

public class NewDAO implements INewDAO {
	
	public Connection getConnection() {
		// ClassNotFoundException: prevent when we forget add dependency in pom.xml
		// SQLException: url | user | password is not correct
		try {
			Class.forName("com.mysql.jdbc.Driver");			// load Driver (giống cổng kết nối | Connect vô mysql)
			String url = "jdbc:mysql://localhost:3306/newservlet";
			String user = "root";
			String password = "123456";
			return DriverManager.getConnection(url, user, password);
		} catch (ClassNotFoundException | SQLException e) {		// multiple catch (2 case) 
			return null;
		} /*catch (SQLException e) {
			return null;
		}*/
	}

	@Override
	public List<NewModel> findByCategoryId(Long categoryId) {
		List<NewModel> results = new ArrayList<>();
		// open connection 
		String sql = "SELECT * FROM newservlet.news WHERE categoryid = ?";
		Connection connection = getConnection();
		PreparedStatement statement = null;		// Truyền câu query vào
		ResultSet resultSet = null;				// Đối tượng được trả về sau khi query data lên
		if (connection != null) {
			try {
				statement = connection.prepareStatement(sql);
				// init parameter when have WHERE ; ONLY can did it at PreparedStatement [not Statement]
				statement.setLong(1, categoryId);
				resultSet = statement.executeQuery();	// execute query => return object resultSet
				while(resultSet.next()) {
					NewModel news = new NewModel();
					news.setId(resultSet.getLong("id"));
					news.setTilte(resultSet.getString("title"));
//					news.setThumbnail(resultSet.getString("name"));
//					news.setContent(resultSet.getString("code"));
//					news.setContent(resultSet.getString("code"));
//					news.setShortDescription(resultSet.getString("code"));
//					news.setCategoryId(resultSet.getLong("code"));
					results.add(news);
				}
				return results;
			} catch (SQLException e) {
				return null;
			} finally {			// Luôn đóng lại tất cả đối tượng (tác vụ) - cả trong trường hợp xấu nhất là bị lỗi ở các command trên
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
		return results;
	}

}
