package com.laptrinhjavaweb.model;

public class CategoryModel extends AbstractModel {
	/*
	id bigint NOT NULL PRIMARY KEY auto_increment, -- primary key
    name VARCHAR(255) NOT NULL,
    code VARCHAR(255) NOT NULL,
    
    createdate TIMESTAMP NULL,
    modifieddate TIMESTAMP NULL,
    createby VARCHAR(255) NULL,
	modifiedby VARCHAR(255) NULL
	 */

	private String name;
	private String code;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

}
