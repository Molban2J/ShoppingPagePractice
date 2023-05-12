package com.shop.service;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.shop.model.AuthorVO;
import com.shop.model.Criteria;

@Service
public interface AuthorService {
	// 작가 등록
	public void authorEnroll(AuthorVO author) throws Exception;

	// 작가 목록
	public ArrayList<AuthorVO> authorList(Criteria cri) throws Exception;

	// 전체 데이터 개수
	public int authorTotal(Criteria cri) throws Exception;
}
