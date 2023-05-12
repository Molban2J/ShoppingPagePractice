package com.shop.mapper;

import java.util.ArrayList;

import com.shop.model.AuthorVO;
import com.shop.model.Criteria;

public interface AuthorMapper {
	//작가 등록
	public void authorEnroll(AuthorVO author);
	
	//작가 목록
	public ArrayList<AuthorVO> authorList(Criteria cri);
	
	//전체 데이터 개수
	public int authorTotal(Criteria cri);
}
