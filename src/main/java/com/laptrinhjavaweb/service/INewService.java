package com.laptrinhjavaweb.service;

import java.util.List;

import com.laptrinhjavaweb.model.NewModel;

public interface INewService {
	public List<NewModel> findByCategoryId(Long categoryId);
	public NewModel save(NewModel newModel);
	public NewModel update(NewModel updateNew);
	public void delete(long[] ids);
}
