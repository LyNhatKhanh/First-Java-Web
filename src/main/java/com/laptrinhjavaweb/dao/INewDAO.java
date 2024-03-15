package com.laptrinhjavaweb.dao;

import java.util.List;

import com.laptrinhjavaweb.model.NewModel;

public interface INewDAO extends IGenericDAO<NewModel> {
	public List<NewModel> findByCategoryId(Long categoryId);
	public Long save(NewModel newModel);
}
