package com.laptrinhjavaweb.mapper;

import java.sql.ResultSet;

// mapping table's fields & classModel's attributes
public interface RowMapper<T> {
	public T mapRow(ResultSet rs);
}
