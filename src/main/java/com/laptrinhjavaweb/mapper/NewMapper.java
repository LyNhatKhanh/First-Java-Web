package com.laptrinhjavaweb.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.laptrinhjavaweb.model.NewModel;

public class NewMapper implements RowMapper<NewModel> {

	@Override
	public NewModel mapRow(ResultSet resultSet) {
		try {
			NewModel news = new NewModel();
			news.setId(resultSet.getLong("id"));
			news.setTitle(resultSet.getString("title"));
			news.setThumbnail(resultSet.getString("thumbnail"));
			news.setShortDescription(resultSet.getString("shortdescription"));
			news.setContent(resultSet.getString("content"));
			news.setCategoryId(resultSet.getLong("categoryid"));
			news.setCreateDate(resultSet.getTimestamp("createdate"));
			news.setCreateBy(resultSet.getString("createby"));
			if (resultSet.getTimestamp("modifieddate") != null)
				news.setModifiedDate(resultSet.getTimestamp("modifieddate"));
			if (resultSet.getString("modifiedby") != null)
				news.setModifiedBy(resultSet.getString("modifiedby"));
			
			return news;
		} catch (SQLException e) {
			return null;
		}
	}
}
