package com.laptrinhjavaweb.model;

public class NewModel extends AbstractModel {
	/*
	id bigint NOT NULL PRIMARY KEY auto_increment, -- primary key
    tilte VARCHAR(255) NULL,
    thumbnail VARCHAR(255) NULL, -- image
    shortdescription TEXT NULL,
    content TEXT NULL,
    
    categoryid bigint NOT NULL, -- foreign key
    
    createdate TIMESTAMP NULL,
    modifieddate TIMESTAMP NULL,
    createby VARCHAR(255) NULL,
	modifiedby VARCHAR(255) NULL
	 */

	private String tilte;
	private String thumbnail;
	private String shortDescription;
	private String content;
	private String categoryId;
	
	public String getTilte() {
		return tilte;
	}
	public void setTilte(String tilte) {
		this.tilte = tilte;
	}
	public String getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getShortDescription() {
		return shortDescription;
	}
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	


}
