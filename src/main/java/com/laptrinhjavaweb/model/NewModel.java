package com.laptrinhjavaweb.model;

public class NewModel extends AbstractModel<NewModel> {
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

	private String title;
	private String thumbnail;
	private String shortDescription;
	private String content;
	private Long categoryId;
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	
	


}
