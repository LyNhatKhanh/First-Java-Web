package com.laptrinhjavaweb.dao.impl;

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
//		String sql = "INSERT INTO news (title, content, categoryid) VALUES(?,?,?)";
		StringBuilder sql = new StringBuilder();
		return insert(sql.toString(), newModel.getTitle(), newModel.getContent(), newModel.getCategoryId());
	}

	@Override
	public NewModel findOne(Long id) {
		String sql = "SELECT * FROM newservlet.news WHERE id = ?";
		List<NewModel> news = query(sql,new NewMapper(), id);
		return news.isEmpty() ? null : news.get(0);
	}

	@Override
	public void update(NewModel updateNew) {
		String sql = "UPDATE newservlet.news SET title = ?, thumbnail = ?, "
				+ "shortdescription = ?, content = ?, categoryid = ?, "
				+ "createdate = ?, createby = ? WHERE id = ?";
		update(sql, updateNew.getTitle(), updateNew.getThumbnail(), updateNew.getShortDescription(),
				updateNew.getContent(), updateNew.getCategoryId(), updateNew.getCreateDate(),
				updateNew.getCreateBy(), updateNew.getId());
	}

	@Override
	public void delete(long id) {
		String sql = "DELETE FROM newservlet.news WHERE id = ?";
		update(sql,id);
	}
}
