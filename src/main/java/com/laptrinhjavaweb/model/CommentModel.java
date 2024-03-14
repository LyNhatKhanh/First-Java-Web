package com.laptrinhjavaweb.model;

public class CommentModel extends AbstractModel {
	/*
	id bigint NOT NULL PRIMARY KEY auto_increment, -- primary key
    content TEXT NOT NULL,
    user_id bigint NOT NULL,
	news_id bigint NOT NULL,
	
    createdate TIMESTAMP NULL,
    modifieddate TIMESTAMP NULL,
    createby VARCHAR(255) NULL,
	modifiedby VARCHAR(255) NULL
	 */

	private String content;
	private Long userId;
	private Long newId;
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getNewId() {
		return newId;
	}
	public void setNewId(Long newId) {
		this.newId = newId;
	}
	
}
