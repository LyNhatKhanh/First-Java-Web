package com.laptrinhjavaweb.dao;

import java.util.List;

import com.laptrinhjavaweb.mapper.RowMapper;

public interface IGenericDAO<T> {
	/*
	 * 
	 * hàm chung có 3 đối tượng truyền vào hàm: 
	 * - câu query (sql)
	 * - đối tượng cần trả về (T)
	 * - giá trị của tham số (Object... parameters)
	 * 
	 * */
	@SuppressWarnings("hiding")
	// Object... == Object[]
	public <T> List<T> query(String sql, RowMapper<T> rowMapper, Object... parameters);	
	public void update(String sql, Object... parameters);
	public Long insert(String sql, Object... parameters);
	
}
