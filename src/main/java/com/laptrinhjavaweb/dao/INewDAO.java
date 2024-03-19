package com.laptrinhjavaweb.dao;

import java.util.List;

import com.laptrinhjavaweb.model.NewModel;

public interface INewDAO extends IGenericDAO<NewModel> {
	public NewModel findOne(Long id);	// tra ve instance vua them
	public List<NewModel> findByCategoryId(Long categoryId);
	public Long save(NewModel newModel); // return newId
	public void update(NewModel updateNew);
	public void delete(long id);
}
