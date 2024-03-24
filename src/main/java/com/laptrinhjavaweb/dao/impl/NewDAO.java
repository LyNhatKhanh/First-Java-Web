package com.laptrinhjavaweb.dao.impl;

import java.util.List;

import com.laptrinhjavaweb.dao.INewDAO;
import com.laptrinhjavaweb.mapper.NewMapper;
import com.laptrinhjavaweb.model.NewModel;
import com.laptrinhjavaweb.paging.Pageble;

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
		StringBuilder sql = new StringBuilder("INSERT INTO news");
		sql.append(" (title, thumbnail, shortdescription, content, categoryid, createdate, createby)");
		sql.append(" VALUES(?,?,?,?,?,?,?)");
		
		return insert(sql.toString(), 
				newModel.getTitle(), newModel.getThumbnail(), newModel.getShortDescription(),
				newModel.getContent(), newModel.getCategoryId(), newModel.getCreateDate(), 
				newModel.getCreateBy());
	}

	@Override
	public NewModel findOne(Long id) {
		String sql = "SELECT * FROM newservlet.news WHERE id = ?";
		List<NewModel> news = query(sql,new NewMapper(), id);
		return news.isEmpty() ? null : news.get(0);
	}

	@Override
	public void update(NewModel updateNew) {
		/*String sql = "UPDATE newservlet.news SET title = ?, thumbnail = ?, "
				+ "shortdescription = ?, content = ?, categoryid = ?, "
				+ "createdate = ?, createby = ? WHERE id = ?";*/
		StringBuilder sql = new StringBuilder("UPDATE newservlet.news SET");
		sql.append(" title = ?, thumbnail = ?, shortdescription = ?, content = ?, categoryid = ?,");
		sql.append(" createdate = ?, createby = ?, modifieddate = ?, modifiedby = ? WHERE id = ?");
		update(sql.toString(), updateNew.getTitle(), updateNew.getThumbnail(), 
				updateNew.getShortDescription(), updateNew.getContent(), updateNew.getCategoryId(),
				updateNew.getCreateDate(), updateNew.getCreateBy(), updateNew.getModifiedDate(), 
				updateNew.getModifiedBy(), updateNew.getId());
	}

	@Override
	public void delete(long id) {
		String sql = "DELETE FROM newservlet.news WHERE id = ?";
		update(sql,id);
	}

	@Override
	public List<NewModel> findAll(Pageble pageble) {
		StringBuilder sql = new StringBuilder("SELECT * FROM newservlet.news");
		if (pageble.getSorter() != null) 
			sql.append(" ORDER BY " + pageble.getSorter().getSortName() + " " + pageble.getSorter().getSortBy());
		if (pageble.getOffset() != null && pageble.getLimit() != null) 
			sql.append(" LIMIT " + pageble.getOffset() + ", " + pageble.getLimit());
		return query(sql.toString(), new NewMapper());
	}

	@Override
	public int getTotalItem() {
		String sql = "SELECT count(*) FROM newservlet.news";
		return count(sql);
	}
}
