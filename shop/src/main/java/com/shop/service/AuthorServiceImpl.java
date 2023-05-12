package com.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.mapper.AuthorMapper;
import com.shop.model.AuthorVO;

@Service
public class AuthorServiceImpl implements AuthorService{
	
	@Autowired
	private AuthorMapper mapper;

	@Override
	public void authorEnroll(AuthorVO author) throws Exception {
		mapper.authorEnroll(author);
	}

}
