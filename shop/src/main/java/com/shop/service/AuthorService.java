package com.shop.service;

import org.springframework.stereotype.Service;

import com.shop.model.AuthorVO;

@Service
public interface AuthorService {
	//작가 등록
	public void authorEnroll(AuthorVO author) throws Exception;
}
