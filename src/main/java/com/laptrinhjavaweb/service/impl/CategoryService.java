package com.laptrinhjavaweb.service.impl;

import java.util.List;

import javax.inject.Inject;

import com.laptrinhjavaweb.dao.ICategoryDAO;
import com.laptrinhjavaweb.model.CategoryModel;
import com.laptrinhjavaweb.service.ICategoryService;

public class CategoryService implements ICategoryService {
	/*
	 * // để nhúng CategoryDAO thì phải có constructor 
	// cách truyền thống
	private ICategoryDAO category;
	
	public CategoryService() {
		super();
		this.category = new CategoryDAO();
	}*/

	// cách hiện đại: Weld Servlet
	@Inject
	private ICategoryDAO category;

	@Override
	public List<CategoryModel> findAll() {
		return category.findAll();
	}

}
