package com.laptrinhjavaweb.dao;

import java.util.List;

import com.laptrinhjavaweb.model.CategoryModel;

public interface ICategoryDAO {
	public List<CategoryModel> findAll();	// Return all in database
}
