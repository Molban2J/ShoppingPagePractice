package com.shop.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.mapper.AuthorMapper;
import com.shop.model.AuthorVO;
import com.shop.model.Criteria;

@Service
public class AuthorServiceImpl implements AuthorService{
	
	@Autowired
	private AuthorMapper mapper;

	@Override
	public void authorEnroll(AuthorVO author) throws Exception {
		mapper.authorEnroll(author);
	}

	@Override
	public ArrayList<AuthorVO> authorList(Criteria cri) throws Exception {
		return mapper.authorList(cri);
	}

	@Override
	public int authorTotal(Criteria cri) throws Exception {
		
		return mapper.authorTotal(cri);
	}

}
