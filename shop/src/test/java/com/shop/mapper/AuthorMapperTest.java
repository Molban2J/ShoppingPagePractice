package com.shop.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.shop.model.AuthorVO;

import oracle.security.o3logon.a;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class AuthorMapperTest {
	@Autowired
	private AuthorMapper mapper;
	
	@Test
	public void testAuthorEnroll() throws Exception {
		AuthorVO author = new AuthorVO();
		author.setAuthorName("test");
		author.setAuthorIntro("test");
		author.setNationId("01");
		
		mapper.authorEnroll(author);
	}
}
