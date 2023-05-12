package com.shop.model;

import java.util.Date;

import lombok.Data;

@Data
public class AuthorVO {
	private String authorName, nationId, authorIntro, nationName;
	private int authorId;
	private Date regDate, updateDate;

	public void setNationId(String nationId) {
		this.nationId = nationId;
		
		if(nationId.equals("01")) {
			this.nationId = "국내";
		} else {
			this.nationId = "국외";
	}
		
	}
		

}
