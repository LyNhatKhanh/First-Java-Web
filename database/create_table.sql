use newservlet;

CREATE TABLE role (
	id bigint NOT NULL PRIMARY KEY auto_increment, -- primary key
    name VARCHAR(255) NOT NULL,
    code VARCHAR(255) NOT NULL,
    
    createdate TIMESTAMP NULL,
    modifieddate TIMESTAMP NULL,
    createby VARCHAR(255) NULL,
	modifiedby VARCHAR(255) NULL
);

CREATE TABLE user (
	id bigint NOT NULL PRIMARY KEY auto_increment, -- primary key
    username VARCHAR(150) NOT NULL,
    password VARCHAR(150) NOT NULL,
    fullname VARCHAR(150) NULL,
    status int NOT NULL,
    
    roleid bigint NOT NULL, -- foreign key
    
    createdate TIMESTAMP NULL,
    modifieddate TIMESTAMP NULL,
    createby VARCHAR(255) NULL,
	modifiedby VARCHAR(255) NULL
);

ALTER TABLE user ADD CONSTRAINT fk_user_role FOREIGN KEY (roleid) REFERENCES user(id);

CREATE TABLE news (
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
);

CREATE TABLE category (
	id bigint NOT NULL PRIMARY KEY auto_increment, -- primary key
    name VARCHAR(255) NOT NULL,
    code VARCHAR(255) NOT NULL,
    
    createdate TIMESTAMP NULL,
    modifieddate TIMESTAMP NULL,
    createby VARCHAR(255) NULL,
	modifiedby VARCHAR(255) NULL
);

ALTER TABLE news ADD CONSTRAINT fk_news_category FOREIGN KEY (categoryid) REFERENCES category(id);

CREATE TABLE comment (
	id bigint NOT NULL PRIMARY KEY auto_increment, -- primary key
    content TEXT NOT NULL,
    user_id bigint NOT NULL,
	news_id bigint NOT NULL,
	
    createdate TIMESTAMP NULL,
    modifieddate TIMESTAMP NULL,
    createby VARCHAR(255) NULL,
	modifiedby VARCHAR(255) NULL
);

ALTER TABLE comment ADD CONSTRAINT fk_comment_user FOREIGN KEY (user_id) REFERENCES user(id);
ALTER TABLE comment ADD CONSTRAINT fk_comment_news FOREIGN KEY (news_id) REFERENCES news(id);

