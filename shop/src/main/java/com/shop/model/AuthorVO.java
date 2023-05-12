package com.shop.model;

import java.util.Date;

import lombok.Data;

@Data
public class AuthorVO {
	private String authorName, nationId, authorIntro, nationName;
	private int authorId;
	private Date regDate, updateDate;
	
}
