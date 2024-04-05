package com.laptrinhjavaweb.dao;

import java.util.List;

import com.laptrinhjavaweb.model.CategoryModel;

public interface ICategoryDAO extends IGenericDAO<CategoryModel> {
	public List<CategoryModel> findAll();	// Return all in database
	public CategoryModel findOne(long id);
	public CategoryModel findOneByCode(String code);
}
